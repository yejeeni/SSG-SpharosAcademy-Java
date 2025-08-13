package com.ssg.xmlapp.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Board {
    private int boardId;
    private String title;
    private String writer;
    private String content;
    private Date regdate;
    private int hit;
}
