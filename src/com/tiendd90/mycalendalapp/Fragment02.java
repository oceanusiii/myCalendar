package com.tiendd90.mycalendalapp;

import java.util.ArrayList;

import com.tiendd90.model.DayTask;
import com.tiendd90.model.Task;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;




public class Fragment02 extends Fragment
{

	private LVAdapterListDay myAdapter;
	//private ArrayAdapter<String> myAdapter;
	private ArrayList<DayTask> data;
	
	
	public Fragment02()
	{
		data = new ArrayList<DayTask>();
	}
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}

	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.layout_fragment02, container, false);
	}



	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		
		// fake data
		for(int i=0; i<31; ++i)
		{
			DayTask d = new DayTask();
			d.setDay(""+(i+1));
			ArrayList<Task> tmpTask = new ArrayList<Task>();
			for(int j=0; j<5; ++j)
			{
				tmpTask.add(new Task("0" + j + ":00", "10:00", "string "+j, "String 2"));
			}
			d.setTasks(tmpTask);
			data.add(d);
		}


		// get ListView listDay
		ListView myLv = (ListView) getActivity().findViewById(R.id.fragment02_lvDays);

		
		// create custom listDay adapter
		myAdapter = new LVAdapterListDay(getActivity(), R.layout.custom_daylist, data);
		
		
		
		// set adapter to listview
		myLv.setAdapter(myAdapter);
		
		
		myAdapter.notifyDataSetChanged();
		
	}

	

}
