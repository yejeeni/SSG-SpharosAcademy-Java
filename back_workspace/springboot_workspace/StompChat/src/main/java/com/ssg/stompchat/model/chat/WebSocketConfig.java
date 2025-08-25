package com.ssg.stompchat.model.chat;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.net.http.WebSocket;

/**
 *  STOMP WebSocket을 설정하는 클래스
 *
 * STOMP의 장점
 * 간단한 구현 - 복잡한 메시지 라우팅을 어노테이션으로 처리
 * 표준화된 프로토콜 - 다양한 클라이언트에서 지원
 * 구독/발행 패턴 - 채팅방, 실시간 알림 등에 최적화
 * Spring 통합 - 기존 Spring MVC와 유사한 방식
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 브로커 경로 설정
        // 서버가 클라이언트에게 메시지를 브로그캐스팅할 때 사용하는 접두어 (채널 구분)
        registry.enableSimpleBroker("/topic");

        // 앱 경로 설정
        // 클라이언트에서 서버로 요청을 보낼 때에는 접두어에 /app가 붙음
        registry.setApplicationDestinationPrefixes("/app");

        // 개인 메시지 경로 설정
        // 브로드캐스팅이 아닌 1:1 메시징 처리에서 사용할 사용자의 prefix
        registry.setUserDestinationPrefix("/user");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 클라이언트가 WebSocket에 연결할 수 있는 진입점 설정
        registry.addEndpoint("/ws") // 클라이언트가 접속할 url
                .setAllowedOriginPatterns("*").withSockJS(); // 모든 도메인에서 접속 허용, Websocket 지원 안 된느 브라우저에서 폴백 지원
    }
}
