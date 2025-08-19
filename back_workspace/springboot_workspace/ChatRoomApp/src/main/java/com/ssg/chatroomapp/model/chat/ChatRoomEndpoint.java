package com.ssg.chatroomapp.model.chat;

import com.ssg.chatroomapp.domain.Member;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@NoArgsConstructor
@ServerEndpoint(value="/chat/room/{roomId}", configurator = HttpSessionConfigurator.class)
public class ChatRoomEndpoint {
    // 방별로 세션을 관리
    private static Map<String, Set<Session>> roomSessions = new ConcurrentHashMap<>();
    // 방별로 사용자 정보를 관리
    private static Map<String, Set<Member>> roomUsers = new ConcurrentHashMap<>();

    /**
     * 방에 입장했을 때
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("roomId") String roomId, EndpointConfig config) {
        HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());

        if (httpSession != null) {
            Member member = (Member) httpSession.getAttribute("member");

            if (member != null) {
                // 세션에 정보 저장
                session.getUserProperties().put("member", member);
                session.getUserProperties().put("roomId", roomId);

                // 방에 세션을 추가
                roomSessions.computeIfAbsent(roomId, k -> new HashSet<>()).add(session);

                // 방에 사용자를 추가
                roomUsers.computeIfAbsent(roomId, k -> new HashSet<>()).add(member);

                log.debug("방 {}에 {}님이 입장했습니다. 현재 인원: {}명",
                        roomId, member.getName(), roomUsers.get(roomId).size());

                // 같은 방 사용자들에게 입장 알림
                broadcastToRoom(roomId, member.getName() + "님이 입장했습니다.");
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

    @OnMessage
    public void onMessage(String message, Session session) {
        String roomId = (String) session.getUserProperties().get("roomId");
        Member member = (Member) session.getUserProperties().get("member");

        if (roomId != null && member != null) {
            log.debug("방 {}에서 {}님이 메시지 전송: {}", roomId, member.getName(), message);

            // 포맷된 메시지를 같은 방의 모든 사용자에게 전송
            String formattedMessage = member.getName() + ": " + message;
            broadcastToRoom(roomId, formattedMessage);
        }
    }

    @OnClose
    public void onClose(Session session) {
        String roomId = (String) session.getUserProperties().get("roomId");  // 변수명 통일
        Member member = (Member) session.getUserProperties().get("member");

        if (roomId != null) {
            // 세션 제거
            Set<Session> sessions = roomSessions.get(roomId);
            if (sessions != null) {
                sessions.remove(session);
            }

            // 사용자 제거
            if (member != null) {
                Set<Member> users = roomUsers.get(roomId);
                if (users != null) {
                    users.remove(member);

                    log.debug("방 {}에서 {}님이 퇴장했습니다. 현재 인원: {}명",
                            roomId, member.getName(), users.size());

                    broadcastToRoom(roomId, member.getName() + "님이 퇴장했습니다.");

                    // 방이 비었으면 정리
                    if (users.isEmpty()) {
                        roomUsers.remove(roomId);
                        roomSessions.remove(roomId);
                        log.debug("방 {}가 비어서 삭제되었습니다.", roomId);
                    }
                }
            }
        }
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        log.error("WebSocket 에러 발생", throwable);
    }

    private void broadcastToRoom(String roomId, String message) {
        Set<Session> sessions = roomSessions.get(roomId);
        if (sessions != null) {
            sessions.forEach(session -> {
                if (session.isOpen()) {
                    try {
                        session.getAsyncRemote().sendText(message);
                    } catch (Exception e) {
                        log.error("메시지 전송 실패", e);
                    }
                }
            });
        }
    }
}