package com.ssg.springwebsocket.model.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssg.springwebsocket.dto.ChatMessage;
import com.ssg.springwebsocket.dto.ChatRoom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static com.ssg.springwebsocket.dto.MessageType.CONNECT;

/**
 * java ee에서 ServerEndpoint를 지정하는 클래스와 같은 역할을 스프링에서 구현
 */
@Slf4j
@Component
public class ChatWebSocketHandler implements WebSocketHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    // 다중 쓰레드 환경에서 동시성 문제를 해결해놓은 ConcurrentHashMap 이용
    // 서버에서 사용할 총 접속자 목록
    private final Set<WebSocketSession> sessions = new ConcurrentHashMap<>().newKeySet();
    // 클라이언트에서 사용할 총 접속자 목록
    private final Set<String> connectedUsers = new ConcurrentHashMap<>().newKeySet();
    // 방 목록 저장
    private final Map<String, ChatRoom> roomStorage = new ConcurrentHashMap<>();

    /**
     * = @OnOpen
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.debug("새 클라이언트 연결");
        sessions.add(session); // 접속자의 세션을 저장
    }

    /**
     * 클라이언트를 대상으로 메시지를 전송하는 메서드
     */
    private void broadCast(String destination, Object data) throws IOException {
        String json = objectMapper.writeValueAsString(
                Map.of("destination", destination, "body", data)
        );
        // 서버에 접속해있는 모든 클라이언트의 세션만큼 반복
        for(WebSocketSession session: sessions) {
            if (session.isOpen()) {  // 세션이 열려있는지 확인
                try {
                    session.sendMessage(new TextMessage(json));
                } catch (IOException e) {
                    log.error("메시지 전송 실패: {}", e.getMessage());
                }
            }
        }
    }

    /**
     * = @OnMessage
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        log.debug("클라이언트의 메시지 - {}", message.getPayload().toString());

        // 클라이언트의 메시지는 JSON 형태의 단순 문자열이므로 JSON으로 변환하고 ChatMessage 객체로 변환
        ChatMessage chatMessage = objectMapper.readValue(message.getPayload().toString(), ChatMessage.class);

        switch (chatMessage.getType()) {
            case CONNECT -> {
                log.info("사용자 접속: {}", chatMessage.getSender());

                // 접속자 추가
                connectedUsers.add(chatMessage.getSender());

                // 접속자 목록 브로드캐스팅
                String data = objectMapper.writeValueAsString(connectedUsers);
                broadCast("/users", connectedUsers);
            }
            case DISCONNECT -> {
                log.info("사용자 접속 해제: {}", chatMessage.getSender());

                broadCast("/users", connectedUsers);
            }
            case MESSAGE -> {
                log.info("메시지 전송: {} -> {}", chatMessage.getSender(), chatMessage.getContent());
                broadCast("/messages", chatMessage);
            }
            case CREATE_ROOM -> {
                log.info("채팅방 생성: {}", chatMessage.getRoomId());

                String uuid = UUID.randomUUID().toString();
                ChatRoom room = new ChatRoom();
                room.setRoomId(uuid);
                room.setRoomName(chatMessage.getContent());

                roomStorage.put(uuid, room);
            }
            case ENTER_ROOM -> {
                log.info("채팅방 입장: {} -> {}", chatMessage.getSender(), chatMessage.getRoomId());
            }
            case LEAVE_ROOM -> {
                log.info("채팅방 나가기: {} -> {}", chatMessage.getSender(), chatMessage.getRoomId());
            }
            case ROOM_LIST -> {
                log.info("채팅방 목록 요청: {}", chatMessage.getSender());
            }
            default -> {
                log.warn("알 수 없는 메시지 타입: {}", chatMessage.getType());
            }
        }
    }

    /**
     * = @OnError
     * @param session
     * @param exception
     * @throws Exception
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

    }

    /**
     * = @OnClose
     * @param session
     * @param closeStatus
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {

    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
