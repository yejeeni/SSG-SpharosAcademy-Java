package com.ssg.chatroomapp.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
public class Member implements Serializable {
    private static final long serialVersionUID = 1L;
    private int memberId;
    private String id;
    private String password;
    private String name;
    private String email;
    private String regdate;
}
