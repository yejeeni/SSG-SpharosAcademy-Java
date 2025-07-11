package myframework.admin.model;

import java.util.List;

import lombok.Data;

@Data
public class News {
	private int news_id;
	private String title;
	private String writer;
	private String content;
	private String regdate;
	private int hit;	
	//하나의 뉴스 기사에 코멘트를 대량으로 보유..
	private List<Comment> commentList;
}





