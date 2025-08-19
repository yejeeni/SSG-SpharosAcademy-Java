package com.ssg.broadcastapp.model.chat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssg.broadcastapp.dto.ResponseChat;
import com.ssg.broadcastapp.dto.ResponseConnect;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * 웹소켓 구현 방법
 * 1. 순수 java EE API
 * 2. Spring API
 *
 * 메시지 교환 방법
 * 1. 직접 설계
 * 2. STOMP 프로토콜 이용
 */

@ServerEndpoint("/ws/multi")
@Component
@Slf4j
public class ChatEndpoint {

    private static Set<Session> userList = new HashSet<>();
    private static Set<String> userIdList = new HashSet();

    // java <> json 변환 객체
    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 연결 감지
     * @param session
     */
    @OnOpen
    public void onOpen(Session session) throws JsonProcessingException {
        log.debug("opOpen - session id: {}", session.getId());

        // 서버에서 사용할 userList set
        userList.add(session);

        /**
         * 클라이언트에 전송할 접속자 정보 { "responseType": "connect", "data":["1", "2", "3"] }
         */
        ResponseConnect responseConnect = new ResponseConnect();
        responseConnect.setResponseType("connect");
        userIdList.add(session.getId());
        responseConnect.setData(userIdList);

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("{");

        String json = objectMapper.writeValueAsString(userIdList);
        // 클라이언트에 서버의 접속자 명단 전송
        session.getAsyncRemote().sendText(json);
    }

    /**
     * 메시지 감지
     * @param message
     */
    @OnMessage
    public void onMessage(Session session, String message) throws JsonProcessingException {
        log.debug("onMessage - Client message: " + message);

        JsonNode jsonNode = objectMapper.readTree(message);
        String requestType = jsonNode.get("requestType").asText();

        if (requestType.equals("chat")){ // 대화요청일 경우
            String data = jsonNode.get("data").asText();
            ResponseChat responseChat = new ResponseChat("chat", session.getId(), data);

            String json = objectMapper.writeValueAsString(responseChat);

            // 클라이언트에 메시지 전송
            for (Session userSession : userList){
                userSession.getAsyncRemote().sendText("onMessage - Server Message: " + message);
            }
        }

    }
}
