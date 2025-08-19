package com.ssg.chatapp.model.chat;

import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@ServerEndpoint("/ws/echo")
@Component
public class ChatEndpoint {
    //접속자 감지... 즉 연결 감지 메서드를 정의한다.
    //웹 소켓에서는 더 이상 java ee 시절의 socket 객체가 통신을 담당하지 않는다.
    //객체가 통신을 담당 또한.. 접속자 마다 1:1 대응하는 Thread를 제어할 필요가 없다.
    @OnOpen
    public void onOpen(Session session ) {
        log.info("ChatEndpoint is opened and session's id " + session.getId());
    }

    @OnMessage
    public void onMessage(Session session, String message) throws IOException {
        log.info("onMessage"+session.getId()+","+message);

        // 클라이언트에 표시할 메시지 전송
        session.getBasicRemote().sendText("server: "+message);
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) throws  IOException{
        log.info("close "+session.getId()+","+closeReason);
    }

    @OnError
    public void onError(Session session, Throwable t) throws  IOException{
        t.printStackTrace();
    }
}
