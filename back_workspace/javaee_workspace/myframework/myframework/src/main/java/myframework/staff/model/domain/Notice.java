package myframework.staff.model.domain;

import lombok.Data;

@Data
public class Notice {
	int notice_id;
	String title;
	String writer;
	String content;
	String regdate;
	int hit;
}
