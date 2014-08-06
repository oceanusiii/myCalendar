package com.example.model;

public class Work {

	private String _id,startTime,endTime,notificationTime,content,notification,date;

	public Work() {
		super();
	}

	public Work(String startTime, String endTime, String notificationTime,
			String content, String notification, String date) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.notificationTime = notificationTime;
		this.content = content;
		this.notification = notification;
		this.date = date;
	}

	public Work(String _id, String startTime, String endTime,
			String notificationTime, String content, String notification,
			String date) {
		super();
		this._id = _id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.notificationTime = notificationTime;
		this.content = content;
		this.notification = notification;
		this.date = date;
	}

	public String getId() {
		return _id;
	}

	public void setId(String _id) {
		this._id = _id;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getNotificationTime() {
		return notificationTime;
	}

	public void setNotificationTime(String notificationTime) {
		this.notificationTime = notificationTime;
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
