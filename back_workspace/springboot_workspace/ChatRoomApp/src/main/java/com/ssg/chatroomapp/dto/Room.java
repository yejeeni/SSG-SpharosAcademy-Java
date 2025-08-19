package com.ssg.chatroomapp.dto;

import com.ssg.chatroomapp.domain.Member;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter @Setter
public class Room {
    private String UUID;
    private String masterId; // 방장 id
    private String roomName; // 방 이름
    private Set<Member> users;
}
