package com.ssg.notice.model;

public class Notice {
	private int notice_id;
	private String title;
	private String wtirer;
	private String content;
	private int hit;
	public int getNotice_id() {
		return notice_id;
	}
	public void setNotice_id(int notice_id) {
		this.notice_id = notice_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWtirer() {
		return wtirer;
	}
	public void setWtirer(String wtirer) {
		this.wtirer = wtirer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	
	
}
