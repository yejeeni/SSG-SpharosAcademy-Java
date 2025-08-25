package com.ssg.stompchat.model.chat.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter @Setter
public class ChatRoom {
    private String roomId;
    private String roomName;
    private Set<String> users = new HashSet<String>();
}
