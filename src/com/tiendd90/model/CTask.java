package com.tiendd90.model;

import java.io.Serializable;

public abstract class CTask implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 970979933775021215L;
	protected String type;
	protected String id;
	protected String startTime;
	protected String endTime;
	protected String notificationTime;
	protected String content;
	protected String notification;
	
	
	public CTask()
	{
	}
	
	
	public abstract String getType();


	public String getId()
	{
		return id;
	}


	public void setId(String id)
	{
		this.id = id;
	}


	public String getStartTime()
	{
		return startTime;
	}


	public void setStartTime(String startTime)
	{
		this.startTime = startTime;
	}


	public String getEndTime()
	{
		return endTime;
	}


	public void setEndTime(String endTime)
	{
		this.endTime = endTime;
	}


	public String getNotificationTime()
	{
		return notificationTime;
	}


	public void setNotificationTime(String notificationTime)
	{
		this.notificationTime = notificationTime;
	}


	public String getContent()
	{
		return content;
	}


	public void setContent(String content)
	{
		this.content = content;
	}


	public String getNotification()
	{
		return notification;
	}


	public void setNotification(String notification)
	{
		this.notification = notification;
	}
}
