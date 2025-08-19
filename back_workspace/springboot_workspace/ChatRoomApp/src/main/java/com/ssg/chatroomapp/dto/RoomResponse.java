package com.ssg.chatroomapp.dto;

import com.ssg.chatroomapp.domain.Member;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter @Setter
public class RoomResponse {
    private String responseType;
    private Set<Member> memberList;
    private Set<Room> roomList;
}
