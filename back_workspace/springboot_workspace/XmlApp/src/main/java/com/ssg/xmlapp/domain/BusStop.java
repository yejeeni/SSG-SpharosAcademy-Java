package com.ssg.xmlapp.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BusStop {
    private int bstopid;
    private String bstopnm;
    private String arsno;
    private double gpsx;
    private double gpsy;
    private String stoptype;
}
