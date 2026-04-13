package com.example.demo.entities;

import jakarta.validation.constraints.NotBlank;

public class Note{
	
	private long id;
	@NotBlank
	private String title;
	@NotBlank
	String content;
//	STATUS status;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

}
enum STATUS {
	CLOSED,CREATED;
}