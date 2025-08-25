package com.ssg.springwebsocket.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * 채팅방
 */
@Getter @Setter
public class ChatRoom {
    private String roomId;
    private String roomName;
    private Set<String> users = new HashSet<>(); // 참여자 목록
}
