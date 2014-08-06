package com.example.model;

import java.io.Serializable;

public class Plan_Transaction implements Serializable {

	private int _id;
	private String starttime;
	private String endtime;
	private String notification_time;
	private String title;
	private String icon;
	private String content;
	private String notification;
	private String date;
	
	
	
	public Plan_Transaction(){
		
	}



	public Plan_Transaction(int _id, String starttime, String endtime,
			String notification_time, String title, String icon,
			String content, String notification, String date) {
		super();
		this._id = _id;
		this.starttime = starttime;
		this.endtime = endtime;
		this.notification_time = notification_time;
		this.title = title;
		this.icon = icon;
		this.content = content;
		this.notification = notification;
		this.date = date;
	}



	public Plan_Transaction(String starttime, String endtime,
			String notification_time, String title, String icon,
			String content, String notification, String date) {
		super();
		this.starttime = starttime;
		this.endtime = endtime;
		this.notification_time = notification_time;
		this.title = title;
		this.icon = icon;
		this.content = content;
		this.notification = notification;
		this.date = date;
	}



	public int get_id() {
		return _id;
	}



	public void set_id(int _id) {
		this._id = _id;
	}



	public String getStarttime() {
		return starttime;
	}



	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}



	public String getEndtime() {
		return endtime;
	}



	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}



	public String getNotification_time() {
		return notification_time;
	}



	public void setNotification_time(String notification_time) {
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



	public String getNotification() {
		return notification;
	}



	public void setNotification(String notification) {
		this.notification = notification;
	}



	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
	}
	
	
}
