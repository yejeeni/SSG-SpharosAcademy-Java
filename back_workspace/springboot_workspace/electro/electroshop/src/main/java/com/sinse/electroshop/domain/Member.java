package com.sinse.electroshop.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="member")
@Data
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memberId;

    private String id;
    private String password;
    private String name;
}
