package com.ssg.stompchat;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ChatMessage {
    private String type;
    private String sender;
    private String content;
    private String roomId;
}
