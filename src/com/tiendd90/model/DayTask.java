package com.tiendd90.model;

import java.util.ArrayList;


public class DayTask
{
	
	private String day;
	private ArrayList<Task> tasks;
	
	
	
	
	public DayTask()
	{
		
	}

	
	
	
	public String getDay()
	{
		return day;
	}

	
	

	public void setDay(String day)
	{
		this.day = day;
	}




	public ArrayList<Task> getTasks()
	{
		return tasks;
	}




	public void setTasks(ArrayList<Task> tasks)
	{
		this.tasks = tasks;
	}

	
	
}
