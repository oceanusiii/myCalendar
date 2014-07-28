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
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;



public class LVAdapterListDay extends BaseAdapter
{

	private ArrayList<DayTask> data;
	private LayoutInflater inflater;
	private Activity fatherActivity;
	private int resource;
	private ViewHolder holder;

	
	
	public LVAdapterListDay(Activity context, int resource, ArrayList<DayTask> objects)
	{
		this.fatherActivity = context;
		this.data = objects;
		this.resource = resource;
		this.inflater = (LayoutInflater) context.getSystemService(
										Context.LAYOUT_INFLATER_SERVICE);
	}
	
	
	
	@SuppressLint("InflateParams") @Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		// TODO Auto-generated method stub
		
		View v = convertView;
		
		if(convertView==null)
		{
			
			holder = new ViewHolder();


			v = inflater.inflate(this.resource, null);
			
			// textview
			holder.tvDay = (TextView) v.findViewById(R.id.daylist_tvDay);
			// listview
			holder.lvTasks = (ListView) v.findViewById(R.id.daylist_lvTask);
			
			v.setTag(holder);
			
		}
		else
		{
			holder = (ViewHolder) v.getTag();
		}
		
		
		DayTask dt = data.get(position);
		
		for(int i=0; i<dt.getTasks().size(); ++i)
			Log.e("test", dt.getTasks().get(i).getTimeStart());

		holder.tvDay.setText(dt.getDay());
		LVAdapterListTask dayAdapter = new LVAdapterListTask(
											fatherActivity, 
											R.layout.custom_tasklist, 
											dt.getTasks());
		holder.lvTasks.setAdapter(dayAdapter);
		dayAdapter.notifyDataSetChanged();
		
		
		return v;
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

	
	
	
	class ViewHolder
	{
		TextView tvDay;
		ListView lvTasks;
	}
}
