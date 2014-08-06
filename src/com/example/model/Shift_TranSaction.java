package com.example.model;

import android.util.Log;

public class Shift_TranSaction {

	private String _id;
	private String startTime;
	private String endTime;
	private String notification_Time;
	private String name;
	private String color_Text;
	private String color_Pattern;
	private String content;
	private String notification;
	private String Date;
	
	public Shift_TranSaction() {
		super();
	}

	public Shift_TranSaction(String startTime, String endTime,
			String notification_Time, String name, String color_Text,
			String color_Pattern, String content, String notification,
			String date) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.notification_Time = notification_Time;
		this.name = name;
		this.color_Text = color_Text;
		this.color_Pattern = color_Pattern;
		this.content = content;
		this.notification = notification;
		Date = date;
	}

	public Shift_TranSaction(String _id, String startTime, String endTime,
			String notification_Time, String name, String color_Text,
			String color_Pattern, String content, String notification,
			String date) {
		super();
		this._id = _id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.notification_Time = notification_Time;
		this.name = name;
		this.color_Text = color_Text;
		this.color_Pattern = color_Pattern;
		this.content = content;
		this.notification = notification;
		Date = date;
		Log.e("_id",_id+"");
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
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

	public String getNotification_Time() {
		return notification_Time;
	}

	public void setNotification_Time(String notification_Time) {
		this.notification_Time = notification_Time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor_Text() {
		return color_Text;
	}

	public void setColor_Text(String color_Text) {
		this.color_Text = color_Text;
	}

	public String getColor_Pattern() {
		return color_Pattern;
	}

	public void setColor_Pattern(String color_Pattern) {
		this.color_Pattern = color_Pattern;
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
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}
}
