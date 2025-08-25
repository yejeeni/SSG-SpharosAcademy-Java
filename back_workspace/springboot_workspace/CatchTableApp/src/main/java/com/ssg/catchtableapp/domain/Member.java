package com.ssg.catchtableapp.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "member")
@Getter @Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private int memberId;

    private String id;

    private String password;

    private String name;

    private String email;

    private LocalDateTime regdate;

    // 기본 생성자
    protected Member() {}

    // 매개변수 받는 생성자
    public static Member createMember(String id, String name, String email, String password) {
        Member member = new Member();
        member.setId(id);
        member.setName(name);
        member.setEmail(email);
        member.setPassword(password);
        member.setRegdate(LocalDateTime.now());
        return member;
    }
}
