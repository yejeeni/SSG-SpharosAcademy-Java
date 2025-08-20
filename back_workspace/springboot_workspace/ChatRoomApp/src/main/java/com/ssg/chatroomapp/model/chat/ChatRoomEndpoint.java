package com.ssg.chatroomapp.model.chat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ssg.chatroomapp.domain.Member;
import com.ssg.chatroomapp.dto.ChatMessage;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@ServerEndpoint(value="/chat/joinRoom/{uuid}", configurator = HttpSessionConfigurator.class)
public class ChatRoomEndpoint {
    // 방별로 세션을 관리
    private static Map<String, Set<Session>> roomSessions = new ConcurrentHashMap<>();
    // 방별로 사용자 정보를 관리
    private static Map<String, Set<Member>> roomUsers = new ConcurrentHashMap<>();
    private static ObjectMapper objectMapper;

    // 기본 생성자
    public ChatRoomEndpoint() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
    }

    /**
     * 방에 입장했을 때
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("uuid") String UUID, EndpointConfig config) {
        HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());

        if (httpSession != null) {
            Member member = (Member) httpSession.getAttribute("member");

            if (member != null) {
                // 세션에 정보 저장
                session.getUserProperties().put("member", member);
                session.getUserProperties().put("uuid", UUID);

                // 방 세션을 추가
                roomSessions.computeIfAbsent(UUID, k -> new HashSet<>()).add(session);

                // 방 사용자를 추가
                roomUsers.computeIfAbsent(UUID, k -> new HashSet<>()).add(member);
                log.debug("방 {}에 {}님이 입장했습니다. 현재 인원: {}명", UUID, member.getName(), roomUsers.get(UUID).size());

                broadcastRoomInfo(UUID); // 방 정보 업데이트
                // 같은 방 사용자들에게 입장 알림
                ChatMessage message = new ChatMessage("SYSTEM", member.getName()+"님이 입장했습니다.", "JOINED");
                broadcastToRoom(UUID, message, null); // 본인에게도 알림이 보이도록
            } else {
                log.warn("세션에 member 정보가 없습니다.");
                try {
                    session.close();
                } catch (Exception e) {
                    log.error("세션 종료 실패", e);
                }
            }
        }
    }

    /**
     * 메시지를 보냈을 때
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        String uuid = (String) session.getUserProperties().get("uuid");
        Member member = (Member) session.getUserProperties().get("member");

        if (uuid != null && member != null) {
            log.debug("방 {}에서 {}님이 메시지 전송: {}", uuid, member.getName(), message);

            // 메시지를 같은 방의 다른 사용자에게 전송
            ChatMessage receivedMessage = new ChatMessage(member.getName(), message, "RECEIVED");
            broadcastToRoom(uuid, receivedMessage, session);
        }
    }

    @OnClose
    public void onClose(Session session) {
        String uuid = (String) session.getUserProperties().get("uuid");
        Member member = (Member) session.getUserProperties().get("member");

        if (uuid != null) {
            // 세션 제거
            Set<Session> sessions = roomSessions.get(uuid);
            if (sessions != null) {
                sessions.remove(session);
            }

            // 사용자 제거
            if (member != null) {
                Set<Member> users = roomUsers.get(uuid);
                if (users != null) {
                    users.remove(member);

                    log.debug("방 {}에서 {}님이 퇴장했습니다. 현재 인원: {}명", uuid, member.getName(), users.size());

                    // broadcastToRoom(uuid, member.getName() + "님이 퇴장했습니다.");

                    // 방이 비었으면 정리
                    if (users.isEmpty()) {
                        roomUsers.remove(uuid);
                        roomSessions.remove(uuid);
                        log.debug("방 {}가 비어서 삭제되었습니다.", uuid);
                    }
                }
            }
        }
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        log.error("WebSocket 에러 발생", throwable);
    }

    /**
     * 방에 메시지를 브로드캐스트 (본인 제외)
     *
     * @param uuid          방 uuid
     * @param chatMessage   채팅 메시지 클래스
     * @param senderSession 보낸 사람 세션
     */
    private void broadcastToRoom(String uuid, ChatMessage chatMessage, Session senderSession) {
        Set<Session> sessions = roomSessions.get(uuid);
        if (sessions != null) {
            try {
                String jsonMessage = objectMapper.writeValueAsString(chatMessage);
                sessions.forEach(session -> {
                    if (session.isOpen() && !session.equals(senderSession)) {
                        try {
                            session.getAsyncRemote().sendText(jsonMessage);
                        } catch (Exception e) {
                            log.error("메시지 전송 실패", e);
                        }
                    }
                });
            } catch (JsonProcessingException e) {
                log.error("JSON 변환 실패", e);
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 방 정보 브로드캐스트
     * @param uuid
     */
    public void broadcastRoomInfo(String uuid) {
        Set<Member> users = roomUsers.get(uuid); // 해당 방의 사용자
        if (users != null){
            // 방 정보 메시지 생성
            ChatMessage roomInfoMessage = new ChatMessage("SYSTEM", Integer.toString(users.size()), "ROOM_INFO");

            // 추가 정보를 위한 JSON 객체 생성
            ObjectNode messageNode = objectMapper.valueToTree(roomInfoMessage);

            // 방장 정보 추가
            String masterId = findMasterId(uuid); // 방장 ID 찾기
            messageNode.put("masterId", masterId);

            // 참여자 목록 추가
            ArrayNode userListNode = objectMapper.createArrayNode();
            for (Member user : users) {
                ObjectNode userNode = objectMapper.createObjectNode();
                userNode.put("id", user.getId());
                userNode.put("name", user.getName());
                userNode.put("email", user.getEmail());
                userListNode.add(userNode);
            }
            messageNode.set("userList", userListNode);

            // 방의 모든 사용자들에게 전송
            Set<Session> sessions = roomSessions.get(uuid);
            if (sessions != null) {
                sessions.forEach(session -> {
                    if (session.isOpen()) {
                        try {
                            String jsonMessage = objectMapper.writeValueAsString(messageNode);
                            session.getAsyncRemote().sendText(jsonMessage);
                        } catch (Exception e) {
                            log.error("방 정보 전송 실패", e);
                        }
                    }
                });
            }
        }
    }

    /**
     * 방장 조회
     * @param uuid
     * @return
     */
    private String findMasterId(String uuid) {
        return RoomManager.getRoomMaster(uuid);
    }
}