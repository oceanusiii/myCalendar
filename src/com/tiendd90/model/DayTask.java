package com.tiendd90.model;

import java.util.ArrayList;

public class DayTask
{
	private String day;
	private ArrayList<String> tasks;
	
	
	public DayTask()
	{
		
	}


	public void addTask(String task)
	{
		if(task!=null)
		{
			tasks.add(task);
		}
	}
	
	public String getDay()
	{
		return day;
	}


	public void setDay(String day)
	{
		this.day = day;
	}


	public ArrayList<String> getTasks()
	{
		return tasks;
	}


	public void setTasks(ArrayList<String> tasks)
	{
		this.tasks = tasks;
	}
	
	
}
