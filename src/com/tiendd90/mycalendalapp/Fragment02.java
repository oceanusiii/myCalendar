package com.tiendd90.mycalendalapp;

import java.util.ArrayList;

import com.tiendd90.model.Day;
import com.tiendd90.model.Plan;



import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;


public class Fragment02 extends Fragment
{

	private LVAdapterListDay myAdapter;
	private ArrayList<String> data;
	private Day day;
	private TextView tvDateTitle;
	
	
	public Fragment02()
	{
		data = new ArrayList<String>();
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		try
		{
			// get day from last fragment
			day = (Day) getActivity().getIntent().
					getBundleExtra("data").getSerializable("day");
		}
		catch(Exception ex) { day = new Day(); } 
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		return inflater.inflate(
				R.layout.layout_fragment02, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		
		//changeDateTitle();
		// fake data
		// add day into list
		int m = Integer.parseInt(day.getM());
		int y = Integer.parseInt(day.getY());
		int d = 0;
		switch(m)
		{
		case 0: case 2: case 4:
		case 6: case 7: case 9:
		case 11:
			d = 31;
			break;
		case 3: case 5: case 8:
		case 10:
			d = 30;
			break;
		case 1:
			if(y%4==0)
				d = 29;
			else
				d = 28;
			break;
		}
		
		for(int i=0; i<d; ++i)
		{
			data.add(i+1+"");
		}

		// get ListView listDay
		ListView myLv = (ListView) getActivity().findViewById(
										R.id.fragment02_lvDays);

		// create custom listDay adapter
		myAdapter = new LVAdapterListDay(getActivity(), 
							R.layout.custom_daylist, data, day);
		
		// set adapter to listview
		myLv.setAdapter(myAdapter);
		myAdapter.notifyDataSetChanged();
		myLv.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> arg0,
					View arg1, int arg2, long arg3)
			{
				// Open DetailTask activity
				Intent i = new Intent(getActivity(), PlanDetailActivity.class);
				
				Bundle b = new Bundle();
				b.putSerializable("day", day);
				b.putSerializable("plan", new Plan());
				
				i.putExtra("data", b);
				startActivity(i);
			}
		});
		
		// get title TextView
		tvDateTitle = (TextView) getActivity().findViewById(
									R.id.fragment02_tvDateTitle);
		// change date title
		changeDateTitle();
	}

	
	
	private void changeDateTitle()
	{
		tvDateTitle.setText(day.getY() + "/" 
					+ (Integer.parseInt(day.getM()) + 1));
	}
}
