package com.tiendd90.mycalendalapp;

import java.util.ArrayList;

import com.tiendd90.model.DayTask;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;



public class LVAdapterListDay extends ArrayAdapter<String>
{

	private ArrayList<String> data;
	private LayoutInflater inflater;
	private ViewHolder holder;
	
	
	
	public LVAdapterListDay(Activity context, int resource, ArrayList<String> objects)
	{
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
		
		data = objects;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	
	
	@SuppressLint("InflateParams") @Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		// TODO Auto-generated method stub
		
		View v = convertView;
		
		if(convertView==null)
		{
			holder = new ViewHolder();
			
			//v = inflater.inflate(R.layout.custom_daylist, null);
			v = inflater.inflate(android.R.layout.simple_list_item_1, null);
			
			// textview
			holder.tvDay = (TextView) v.findViewById(R.id.daylist_tvDay);
			// listview
			//
			
			v.setTag(holder);
			
		}
		else
		{
			holder = (ViewHolder) v.getTag();
		}
		
		
		//DayTask dt = data.get(position);
		
		Log.e("myAdapter", data.get(position));
		
		holder.tvDay.setText(data.get(position).toString());
		//holder.lvTasks
		
		
		return v;
	}
	
	
	
	
	
	class ViewHolder
	{
		TextView tvDay;
		ListView lvTasks;
	}

}
