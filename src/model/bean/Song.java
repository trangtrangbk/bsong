package model.bean;

import java.sql.Timestamp;

public class Song {
	private int id;
	private String name;
	private String pre;
	private String detail;
	private Timestamp date;
	private String picture;
	private int counter;
	private int cat_id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPre() {
		return pre;
	}
	public void setPre(String pre) {
		this.pre = pre;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	public int getCat_id() {
		return cat_id;
	}
	public void setCat_id(int cat_id) {
		this.cat_id = cat_id;
	}
	public Song() {
		super();
	}
	public Song(int id, String name, String pre, String detail, Timestamp date, String picture, int counter,
			int cat_id) {
		super();
		this.id = id;
		this.name = name;
		this.pre = pre;
		this.detail = detail;
		this.date = date;
		this.picture = picture;
		this.counter = counter;
		this.cat_id = cat_id;
	}
	
	
}
