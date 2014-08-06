package com.example.model;

public class Work_Shift extends Work {
	
	private String name,colorText,colorPattern;
	
	

	public Work_Shift(String startTime, String endTime, String notificationTime,String content, String notification, String date,String name, String colorText, String colorPattern) {
		super(startTime,endTime,notificationTime,content,notification,date);
		this.name = name;
		this.colorText = colorText;
		this.colorPattern = colorPattern;
	}


	public Work_Shift(String _id,String startTime, String endTime, String notificationTime,String content, String notification, String date,String name, String colorText, String colorPattern) {
		super(_id,startTime,endTime,notificationTime,content,notification,date);
		this.name = name;
		this.colorText = colorText;
		this.colorPattern = colorPattern;
	}

	
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return super.getId();
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		super.setId(id);
	}

	@Override
	public String getStartTime() {
		// TODO Auto-generated method stub
		return super.getStartTime();
	}

	@Override
	public void setStartTime(String startTime) {
		// TODO Auto-generated method stub
		super.setStartTime(startTime);
	}

	@Override
	public String getEndTime() {
		// TODO Auto-generated method stub
		return super.getEndTime();
	}

	@Override
	public void setEndTime(String endTime) {
		// TODO Auto-generated method stub
		super.setEndTime(endTime);
	}

	@Override
	public String getNotificationTime() {
		// TODO Auto-generated method stub
		return super.getNotificationTime();
	}

	@Override
	public void setNotificationTime(String notificationTime) {
		// TODO Auto-generated method stub
		super.setNotificationTime(notificationTime);
	}

	@Override
	public String getContent() {
		// TODO Auto-generated method stub
		return super.getContent();
	}

	@Override
	public void setContent(String content) {
		// TODO Auto-generated method stub
		super.setContent(content);
	}

	@Override
	public String getNotification() {
		// TODO Auto-generated method stub
		return super.getNotification();
	}

	@Override
	public void setNotification(String notification) {
		// TODO Auto-generated method stub
		super.setNotification(notification);
	}

	@Override
	public String getDate() {
		// TODO Auto-generated method stub
		return super.getDate();
	}

	@Override
	public void setDate(String date) {
		// TODO Auto-generated method stub
		super.setDate(date);
	}
	
}
