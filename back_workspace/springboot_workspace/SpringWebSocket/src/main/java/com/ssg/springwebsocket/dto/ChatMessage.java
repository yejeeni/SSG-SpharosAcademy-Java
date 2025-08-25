package com.ssg.springwebsocket.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 서버와 클라이언트 간 메시지 객체
 */
@Getter @Setter
public class ChatMessage {
    /**
     * CONNECT - 접속,
     * DISCONNECT - 접속 해제,
     * MESSAGE - 채팅 메시지,
     * CREATE_ROOM - 채팅방 생성,
     * ROOM_LIST - 채팅방 몽록,
     * ENTER_ROOM - 채팅방 입장,
     * LEAVE_ROOM - 채팅방 나가기
     */
    private MessageType type; // 형식
    private String sender; // 전송자
    private String content; // 내용
    private String roomId; // 방 번호
}
