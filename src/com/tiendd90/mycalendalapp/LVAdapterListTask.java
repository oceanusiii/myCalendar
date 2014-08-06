package com.tiendd90.mycalendalapp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tiendd90.model.CTask;
import com.tiendd90.model.Plan;
import com.tiendd90.model.Shift;


public class LVAdapterListTask extends BaseAdapter
{
	private ArrayList<CTask> data;
	private LayoutInflater inflater;
	private int resource;
	private ViewHolder holder;
	
	
	public LVAdapterListTask(Activity context, 
				int resource, ArrayList<CTask> objects)
	{
		this.resource = resource;
		this.data = objects;
		this.inflater = (LayoutInflater) 
				context.getSystemService(
						Context.LAYOUT_INFLATER_SERVICE);
	}
	

	@Override
	public int getCount()
	{
		return data.size();
	}

	
	@Override
	public Object getItem(int position)
	{
		return data.get(position);
	}
	
	
	@Override
	public long getItemId(int position)
	{
		return 0;
	}

	
	@SuppressLint("SimpleDateFormat")
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View v = convertView;
		
		if(convertView==null)
		{
			
			holder = new ViewHolder();


			v = inflater.inflate(this.resource, null);
			
			// textview time
			holder.tvTime = (TextView) v.findViewById(
									R.id.taskList_tvTime);
			// textview string1
			holder.tvTitle = (TextView) v.findViewById(
									R.id.taskList_tvStr1);
			// textview string2
			holder.tvContent = (TextView) v.findViewById(
									R.id.taskList_tvStr2);
			
			v.setTag(holder);
			
		}
		else
		{
			holder = (ViewHolder) v.getTag();
		}
		
		CTask t = data.get(position);
		
		if(t.getType().equals("Plan"))
		{
			try
			{
				Plan p = (Plan) t;
				Calendar c = Calendar.getInstance();
				java.text.DateFormat format = new SimpleDateFormat("HH:mm");
				c.setTimeInMillis(Long.parseLong(p.getStartTime()));
				String startTime = format.format(c.getTime());
				c.setTimeInMillis(Long.parseLong(p.getEndTime()));
				String endTime = format.format(c.getTime());
				// show data
				holder.tvTime.setText(startTime + "~" + endTime);
				holder.tvTitle.setText(p.getTitle());
				holder.tvContent.setText(p.getContent());
			}
			catch(Exception ex) {}
			
		}
		else if(t.getType().equals("Shift"))
		{
			try
			{
				Shift s = (Shift) t;
				Calendar c = Calendar.getInstance();
				java.text.DateFormat format = new SimpleDateFormat("HH:mm");
				c.setTimeInMillis(Long.parseLong(s.getStartTime()));
				String startTime = format.format(c.getTime());
				c.setTimeInMillis(Long.parseLong(s.getEndTime()));
				String endTime = format.format(c.getTime());
				// show data
				holder.tvTime.setText(startTime + "~" + endTime);
				holder.tvTitle.setText(s.getName());
				holder.tvContent.setText(s.getContent());
			}
			catch(Exception ex) {}
			
		}
		
		
		return v;
	}

	
	public ArrayList<CTask> getData()
	{
		return data;
	}
	
	
	
	/**
	 * 
	 * @author FDM17
	 *
	 */
	class ViewHolder
	{
		TextView tvTime;
		TextView tvTitle;
		TextView tvContent;
	}
}
