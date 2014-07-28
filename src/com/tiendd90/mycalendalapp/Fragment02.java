package com.tiendd90.mycalendalapp;

import java.util.ArrayList;

import com.tiendd90.model.DayTask;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;




public class Fragment02 extends Fragment
{

	private LVAdapterListDay myAdapter;
	private ArrayList<String> data;
	
	
	public Fragment02()
	{
		data = new ArrayList<String>();
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
		
		View v = inflater.inflate(R.layout.layout_fragment02, container, false);
		
		
		// fake data
		for(int i=0; i<31; ++i)
		{
			//DayTask d = new DayTask();
			//d.setDay(""+(i+1));
			data.add(""+(i+1));
		}


		// get ListView
		ListView myLv = (ListView) v.findViewById(R.id.fragment02_lvDays);

		// create adapter
		//myAdapter = new LVAdapterListDay(getActivity(), R.layout.custom_daylist, data);
		myAdapter = new LVAdapterListDay(getActivity(), android.R.layout.simple_list_item_1, data);
		
		
		// set adapter to listview
		myLv.setAdapter(myAdapter);
		
		myAdapter.notifyDataSetChanged();
		
		
		
		return v;
	}



	@Override
	public void onResume()
	{
		// clear old list
		//data.clear();
		
		
		// load new list
		// fake data here
//		for(int i=0; i<31; i++)
//		{
//			DayTask d = new DayTask();
//			d.setDay(i+1+"");
//			data.add(d);
//		}
//		myAdapter.notifyDataSetChanged();
		
		super.onResume();
	}
	
	
	
	
}
