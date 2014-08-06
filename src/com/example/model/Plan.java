package com.example.model;

public class Plan {
	private int _id;
	private int starttime;
	private int endtime;
	private int notification_time;
	private String title;
	private String icon;
	private String content;
	private int notification;
	private String patternname;
	
	
	
	public Plan() {
	}
	
	public Plan(int _id, int starttime, int endtime, int notification_time,
			String title, String icon, String content, int notification,
			String patternname) {
		super();
		this._id = _id;
		this.starttime = starttime;
		this.endtime = endtime;
		this.notification_time = notification_time;
		this.title = title;
		this.icon = icon;
		this.content = content;
		this.notification = notification;
		this.patternname = patternname;
	}
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public int getStarttime() {
		return starttime;
	}
	public void setStarttime(int starttime) {
		this.starttime = starttime;
	}
	public int getEndtime() {
		return endtime;
	}
	public void setEndtime(int endtime) {
		this.endtime = endtime;
	}
	public int getNotification_time() {
		return notification_time;
	}
	public void setNotification_time(int notification_time) {
		this.notification_time = notification_time;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getNotification() {
		return notification;
	}
	public void setNotification(int notification) {
		this.notification = notification;
	}
	public String getPatternname() {
		return patternname;
	}
	public void setPatternname(String patternname) {
		this.patternname = patternname;
	}
	
	
}
