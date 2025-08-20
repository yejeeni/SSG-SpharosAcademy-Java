package com.ssg.chatroomapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ChatMessage {
    private String sender; // 보낸 사람
    private String content; // 내용
    private String type; // 메시지 타입
    private long timestamp; // 발송 시간

    public ChatMessage(String sender, String content, String type) {
        this.sender = sender;
        this.content = content;
        this.type = type;
        this.timestamp = System.currentTimeMillis();

    }
}
