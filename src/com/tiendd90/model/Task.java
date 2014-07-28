package com.tiendd90.model;

public class Task
{
	private String timeStart;
	private String timeEnd;
	private String str1;
	private String str2;
	
	
	public Task()
	{
		
	}
	
	
	public Task(String start, String end, String str1, String str2)
	{
		this.timeStart = start;
		this.timeEnd = end;
		this.str1 = str1;
		this.str2 = str2;
	}


	public String getTimeStart()
	{
		return timeStart;
	}


	public void setTimeStart(String timeStart)
	{
		this.timeStart = timeStart;
	}


	public String getTimeEnd()
	{
		return timeEnd;
	}


	public void setTimeEnd(String timeEnd)
	{
		this.timeEnd = timeEnd;
	}


	public String getStr1()
	{
		return str1;
	}


	public void setStr1(String str1)
	{
		this.str1 = str1;
	}


	public String getStr2()
	{
		return str2;
	}


	public void setStr2(String str2)
	{
		this.str2 = str2;
	}
	
	
	
}
