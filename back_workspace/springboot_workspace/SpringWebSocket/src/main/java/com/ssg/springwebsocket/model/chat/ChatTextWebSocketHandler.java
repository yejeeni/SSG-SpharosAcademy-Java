package com.ssg.springwebsocket.model.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssg.springwebsocket.dto.ChatMessage;
import com.ssg.springwebsocket.dto.ChatRoom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TextWebSocketHandler
 * 다루는 데이터가 Text에서 효율적
 */
@Slf4j
@Component
public class ChatTextWebSocketHandler extends TextWebSocketHandler {

    // java, json 문자열 간 변환 처리 객체
    private ObjectMapper objectMapper = new ObjectMapper();
    // 서버에 연결되어 있는 모든 클라이언트 세션의 집합 (서버 저장 용도)
    private Set<WebSocketSession> sessions = new ConcurrentHashMap<>().newKeySet();
    // 서버에 연결되어 있는 모든 클라이언트 아이디 집합 (클라이언트 전송 용도)
    private Set<String> connectedUsers = new ConcurrentHashMap<>().newKeySet();
    // Room 목록 집합
    private Map<String, ChatRoom> roomStorage = new ConcurrentHashMap<>();
    // 세션과 사용자 ID 매핑
    private Map<WebSocketSession, String> sessionUserMap = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        log.info("새로운 WebSocket 연결: {}", session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        ChatMessage chatMessage = objectMapper.readValue(message.getPayload(), ChatMessage.class);

        switch (chatMessage.getType()) {
            case CONNECT -> {
                log.info("사용자 접속: {}", chatMessage.getSender());

                // 접속자 추가
                connectedUsers.add(chatMessage.getSender());
                sessionUserMap.put(session, chatMessage.getSender());

                // 접속자 목록 브로드캐스팅
                broadCast("/users", connectedUsers);
            }
            case DISCONNECT -> {
                log.info("사용자 접속 해제: {}", chatMessage.getSender());

                // 접속자 제거
                connectedUsers.remove(chatMessage.getSender());
                sessionUserMap.remove(session);

                // 업데이트된 접속자 목록 브로드캐스팅
                broadCast("/users", connectedUsers);
            }
            case MESSAGE -> {
                log.info("메시지 전송: {} -> {}", chatMessage.getSender(), chatMessage.getContent());
                broadCast("/messages", chatMessage);
            }
            case CREATE_ROOM -> {
                log.info("채팅방 생성: {}", chatMessage.getContent());

                ChatRoom room = new ChatRoom();
                String uuid = UUID.randomUUID().toString();
                room.setRoomId(uuid);
                room.setRoomName(chatMessage.getContent());

                roomStorage.put(uuid, room);

                // 생성된 방 정보를 요청한 클라이언트에게 전송
                sendToSession(session, "/room-created", room);

                // 모든 클라이언트에게 업데이트된 방 목록 전송
                broadCast("/rooms", roomStorage.values());
            }
            case ENTER_ROOM -> {
                log.info("채팅방 입장: {} -> {}", chatMessage.getSender(), chatMessage.getRoomId());

                // 방 입장 처리 로직 추가 가능
                ChatRoom room = roomStorage.get(chatMessage.getRoomId());
                if (room != null) {
                    // 방 입장 성공 메시지
                    sendToSession(session, "/room-entered", room);
                }
            }
            case LEAVE_ROOM -> {
                log.info("채팅방 나가기: {} -> {}", chatMessage.getSender(), chatMessage.getRoomId());

                // 방 나가기 처리 로직 추가 가능
                sendToSession(session, "/room-left", Map.of("roomId", chatMessage.getRoomId()));
            }
            case ROOM_LIST -> {
                log.info("채팅방 목록 요청: {}", chatMessage.getSender());
                sendToSession(session, "/rooms", roomStorage.values());
            }
            default -> {
                log.warn("알 수 없는 메시지 타입: {}", chatMessage.getType());
            }
        }
    }

    /**
     * 모든 클라이언트에게 메시지 브로드캐스트
     */
    private void broadCast(String destination, Object data) throws Exception {
        String payload = objectMapper.writeValueAsString(Map.of("destination", destination, "data", data));

        // 접속한 모든 클라이언트들에 전송
        for (WebSocketSession session : sessions) {
            if (session.isOpen()) {
                try {
                    session.sendMessage(new TextMessage(payload));
                } catch (IOException e) {
                    log.error("메시지 전송 실패: {}", e.getMessage());
                }
            }
        }
    }

    /**
     * 특정 세션에게만 메시지 전송
     */
    private void sendToSession(WebSocketSession session, String destination, Object data) throws Exception {
        if (session.isOpen()) {
            String payload = objectMapper.writeValueAsString(Map.of("destination", destination, "data", data));
            try {
                session.sendMessage(new TextMessage(payload));
            } catch (IOException e) {
                log.error("세션 메시지 전송 실패: {}", e.getMessage());
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // 세션 제거
        sessions.remove(session);

        // 해당 세션의 사용자 정보 제거
        String userId = sessionUserMap.remove(session);
        if (userId != null) {
            connectedUsers.remove(userId);
            // 업데이트된 접속자 목록 브로드캐스팅
            try {
                broadCast("/users", connectedUsers);
            } catch (Exception e) {
                log.error("연결 종료 시 브로드캐스트 실패: {}", e.getMessage());
            }
        }

        log.info("WebSocket 연결 종료: {}, 상태: {}", session.getId(), status);
        super.afterConnectionClosed(session, status);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.error("WebSocket 전송 오류: {}", exception.getMessage());

        // 오류 발생 시 세션 정리
        sessions.remove(session);
        String userId = sessionUserMap.remove(session);
        if (userId != null) {
            connectedUsers.remove(userId);
        }

        super.handleTransportError(session, exception);
    }
}