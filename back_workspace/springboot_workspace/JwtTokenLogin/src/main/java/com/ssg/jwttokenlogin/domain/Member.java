package com.ssg.jwttokenlogin.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Member 도메인
 */
@Entity
@Table(name="member")
@Getter @Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memberId; // PK

    private String id; // 로그인 ID

    private String name;

    private String password;
}
