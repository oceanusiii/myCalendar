package com.tiendd90.model;


public class Plan extends CTask
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private String icon;
	private String startTimeSetted;
	private String patternName;
	private String date;
	
	
	
	public Plan()
	{
	}



	public void setNotificationTime(String notificationTime)
	{
		this.notificationTime = notificationTime;
	}



	public String getTitle()
	{
		return title;
	}



	public void setTitle(String title)
	{
		this.title = title;
	}



	public String getIcon()
	{
		return icon;
	}



	public void setIcon(String icon)
	{
		this.icon = icon;
	}



	public String getStartTimeSetted()
	{
		return startTimeSetted;
	}



	public void setStartTimeSetted(String startTimeSetted)
	{
		this.startTimeSetted = startTimeSetted;
	}



	public String getPatternName()
	{
		return patternName;
	}



	public void setPatternName(String patternName)
	{
		this.patternName = patternName;
	}



	public String getDate()
	{
		return date;
	}



	public void setDate(String date)
	{
		this.date = date;
	}



	@Override
	public String getType()
	{
		return "Plan";
	}
	
	
	
	
}
