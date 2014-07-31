package com.tiendd90.mycalendalapp;

import java.util.ArrayList;

import com.tiendd90.model.Shift;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;



public class SPAdapterShiftPattern extends BaseAdapter
{
	
	private ArrayList<Shift> data;
	
	
	public SPAdapterShiftPattern()
	{
		data = new ArrayList<Shift>();
	}
	
	

	@Override
	public int getCount()
	{
		// TODO Auto-generated method stub
		return data.size();
	}

	
	
	@Override
	public Object getItem(int position)
	{
		// TODO Auto-generated method stub
		return data.get(position);
	}

	
	
	@Override
	public long getItemId(int position)
	{
		// TODO Auto-generated method stub
		return 0;
	}
	

	@Override
	public View getView(int position, 
			View convertView, ViewGroup parent)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
