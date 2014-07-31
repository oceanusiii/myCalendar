package com.tiendd90.model;

public class Shift extends CTask
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String colorText;
	private String colorPattern;
	private String isAllDay;
	private String viewOrder;
	private String date;
	
	
	
	public Shift()
	{
	}
	
	

	@Override
	public String getType()
	{
		return "Shift";
	}



	public String getName()
	{
		return name;
	}



	public void setName(String name)
	{
		this.name = name;
	}



	public String getColorText()
	{
		return colorText;
	}



	public void setColorText(String colorText)
	{
		this.colorText = colorText;
	}



	public String getColorPattern()
	{
		return colorPattern;
	}



	public void setColorPattern(String colorPattern)
	{
		this.colorPattern = colorPattern;
	}



	public String getIsAllDay()
	{
		return isAllDay;
	}



	public void setIsAllDay(String isAllDay)
	{
		this.isAllDay = isAllDay;
	}



	public String getViewOrder()
	{
		return viewOrder;
	}



	public void setViewOrder(String viewOrder)
	{
		this.viewOrder = viewOrder;
	}



	public String getDate()
	{
		return date;
	}



	public void setDate(String date)
	{
		this.date = date;
	}

}
