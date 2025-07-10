package mvcproject.notice.model;

import lombok.Data;

@Data 
public class Notice {
	private int notice_id;
	private String title;
	private String writer;
	private String content;
	private int hit;
	private String regDate;
}
