package com.myweb.board.model;

import java.sql.Timestamp;

public class boardVO {
	private int bno;
	private String writer;
	private String content;
	private int hit;
	private Timestamp regdate;
	private String title;
	public boardVO() {
		super();
	}
	
	public boardVO(int bno, String writer, String content, int hit, Timestamp regdate, String title) {
		super();
		this.bno = bno;
		this.writer = writer;
		this.content = content;
		this.hit = hit;
		this.regdate = regdate;
		this.title = title;
	}

	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
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
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
	public String toString() {
		return "boardVO [bno=" + bno + ", writer=" + writer + ", content=" + content + ", hit=" + hit + ", regdate="
				+ regdate + ", title=" + title + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
