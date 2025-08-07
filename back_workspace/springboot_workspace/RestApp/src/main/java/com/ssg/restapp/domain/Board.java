package com.ssg.restapp.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
@Entity
public class Board {
    @Id
    @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int boardId;
    private String title;
    private String writer;
    private String content;
    @Column(name = "regdate", insertable = false, updatable = false)
    private Date regdate;
    private int hit;
}
