package myframework.admin.model;

import lombok.Data;

//Annotation : 프로그램에 읽혀지는 주석
@Data
public class Notice {
	private int notice_id;
	private String title;
	private String writer;
	private String content;
	private String regdate;
	private int hit;
}
