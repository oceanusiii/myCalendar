package com.tiendd90.mycalendalapp;

import java.util.ArrayList;

import com.tiendd90.dataprovider.TblPlanT;
import com.tiendd90.dataprovider.TblPlanTHelper;
import com.tiendd90.dataprovider.TblShiftT;
import com.tiendd90.model.CTask;
import com.tiendd90.model.Day;
import com.tiendd90.model.Shift;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;



public class LVAdapterListDay extends BaseAdapter
{
	private static final String TAG = "Adapter ListView Date";
	private ArrayList<String> data;
	private LayoutInflater inflater;
	private Activity fatherActivity;
	private int resource;
	private ViewHolder holder;
	private Day day;

	
	public LVAdapterListDay(Activity context, 
				int resource, ArrayList<String> objects, Day d)
	{
		this.fatherActivity = context;
		this.day = d;
		this.data = objects;
		this.resource = resource;
		this.inflater = (LayoutInflater) 
				context.getSystemService(
						Context.LAYOUT_INFLATER_SERVICE);
	}
	
	
	@SuppressLint("InflateParams") @Override
	public View getView(int position, 
			View convertView, ViewGroup parent)
	{
		
		View v = convertView;
		
		if(convertView==null)
		{
			holder = new ViewHolder();

			v = inflater.inflate(this.resource, null);
			// textview
			holder.tvDay = (TextView) v.findViewById(
										R.id.daylist_tvDay);
			// listview
			holder.lvTasks = (ListView) v.findViewById(
										R.id.daylist_lvTask);
			
			v.setTag(holder);
		}
		else
		{
			holder = (ViewHolder) v.getTag();
		}
		

		holder.tvDay.setText(data.get(position));
		
		
		ArrayList<CTask> l = new ArrayList<CTask>();
		// load data from SQLite
		String dayofmonth = day.getY()+day.getM()+data.get(position);
		if(Setting.SQLITE == 1)
		{
			try
			{
				// load data
				l.addAll(new TblPlanTHelper(fatherActivity).getByDate(dayofmonth));
				// get latest shift
				ArrayList<Shift> tmpShift = new TblShiftT().getByDate(dayofmonth);
				if(tmpShift.size()>0)
				{
					l.add(tmpShift.get(tmpShift.size()-1));
				}
			}
			catch(Exception ex)
			{
				Log.e(TAG, "GetView" + ex.getMessage());
			}
		}
		// load data from MySQL
		else
		{
			try
			{
				// load data
				l.addAll(new TblPlanT().getByDate(dayofmonth));
				// get latest shift
				ArrayList<Shift> tmpShift = new TblShiftT().getByDate(dayofmonth);
				if(tmpShift.size()>0)
				{
					l.add(tmpShift.get(tmpShift.size()-1));
				}
			}
			catch(Exception ex)
			{
				Log.e(TAG, "GetView" + ex.getMessage());
			}
		}
		
		final LVAdapterListTask taskAdapter = new LVAdapterListTask(
											fatherActivity, 
											R.layout.custom_tasklist, l);
		holder.lvTasks.setAdapter(taskAdapter);
		taskAdapter.notifyDataSetChanged();
		
		
		// listView item onClick
		holder.lvTasks.setOnItemClickListener(
							new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> arg0,
							View arg1, int arg2, long arg3)
			{
				CTask task = taskAdapter.getData().get(arg2);
				
				
				if(task.getType().equals("Plan"))
				{
					Intent i = new Intent(fatherActivity, 
										PlanDetailActivity.class);
					
					Bundle b = new Bundle();
					b.putSerializable("day", day);
					b.putSerializable("plan", task);
					
					i.putExtra("data", b);
					fatherActivity.startActivity(i);
				}
				else
				{
					Intent i = new Intent(fatherActivity, 
										ShiftDetailActivity.class);
		
					Bundle b = new Bundle();
					b.putSerializable("day", day);
					b.putSerializable("shift", task);
					
					i.putExtra("data", b);
					fatherActivity.startActivity(i);
				}
			}
		});
		
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
	
	/**
	 * 
	 * @author FDM17
	 *
	 */
	class ViewHolder
	{
		TextView tvDay;
		ListView lvTasks;
	}
}
