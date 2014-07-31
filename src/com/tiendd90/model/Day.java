package com.tiendd90.model;

import java.io.Serializable;


public class Day implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String d;
	private String m;
	private String y;
	
	
	public Day()
	{
	}


	public String getD()
	{
		return d;
	}


	public void setD(String d)
	{
		this.d = d;
	}


	public String getM()
	{
		return m;
	}


	public void setM(String m)
	{
		this.m = m;
	}


	public String getY()
	{
		return y;
	}


	public void setY(String y)
	{
		this.y = y;
	}
	
	
	
}
