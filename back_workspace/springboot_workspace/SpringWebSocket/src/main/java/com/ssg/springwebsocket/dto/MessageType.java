package com.ssg.springwebsocket.dto;

public enum MessageType {
    CONNECT("접속"),
    DISCONNECT("접속 해제"),
    MESSAGE("채팅 메시지"),
    CREATE_ROOM("채팅방 생성"),
    ROOM_LIST("채팅방 목록"),
    ENTER_ROOM("채팅방 입장"),
    LEAVE_ROOM("채팅방 나가기");

    private final String description;

    MessageType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
