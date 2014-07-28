package com.tiendd90.mycalendalapp;

import java.util.ArrayList;

import com.tiendd90.model.Task;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;




public class LVAdapterListTask extends BaseAdapter
{
	
	
	private ArrayList<Task> data;
	private LayoutInflater inflater;
	private int resource;
	private ViewHolder holder;
	
	
	
	public LVAdapterListTask(Activity context, int resource, ArrayList<Task> objects)
	{
		this.resource = resource;
		this.data = objects;
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
		return data.get(position);
	}

	
	
	
	@Override
	public long getItemId(int position)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		// TODO Auto-generated method stub
		View v = convertView;
		
		if(convertView==null)
		{
			
			holder = new ViewHolder();


			v = inflater.inflate(this.resource, null);
			
			// textview time
			holder.tvTime = (TextView) v.findViewById(R.id.taskList_tvTime);
			// textview string1
			holder.tvStr1 = (TextView) v.findViewById(R.id.taskList_tvStr1);
			// textview string2
			holder.tvStr2 = (TextView) v.findViewById(R.id.taskList_tvStr2);
			
			v.setTag(holder);
			
		}
		else
		{
			holder = (ViewHolder) v.getTag();
		}
		
		
		Task t = data.get(position);
		
		// show data
		holder.tvTime.setText(t.getTimeStart() + " ~ " + t.getTimeEnd());
		holder.tvStr1.setText(t.getStr1());
		holder.tvStr2.setText(t.getStr2());
		
		
		return v;
	}

	
	
	class ViewHolder
	{
		TextView tvTime;
		TextView tvStr1;
		TextView tvStr2;
	}
}
