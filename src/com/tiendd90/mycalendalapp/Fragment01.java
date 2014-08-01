package com.tiendd90.mycalendalapp;


import java.util.ArrayList;



import com.tiendd90.dataprovider.TblPlanTHelper;
import com.tiendd90.model.CTask;
import com.tiendd90.model.Day;
import com.tiendd90.model.Plan;
import com.tiendd90.model.Shift;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;



public class Fragment01 extends Fragment
{

	private ArrayList<CTask> lstTask = new ArrayList<CTask>();
	private TblPlanTHelper tblPlanT;
	private Day day;
	private LVAdapterListTask adapterTask;
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		tblPlanT = new TblPlanTHelper(getActivity());
		
		try
		{
			Bundle b = getActivity().getIntent().getBundleExtra("data");
			day = (Day) b.getSerializable("day");
		}
		catch(Exception ex) { day = new Day(); }
	}

	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, 
										ViewGroup container,
			Bundle savedInstanceState)
	{
		return inflater.inflate(
				R.layout.layout_fragment01, container, false);
	}




	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		
		// get ListView
		ListView lvPlan = (ListView) getActivity().findViewById(
											R.id.fragment01_lvPlan);
		
		// create adapter
		adapterTask = new LVAdapterListTask(
					getActivity(), R.layout.custom_tasklist, lstTask);
		
		// set adapter for listview
		lvPlan.setAdapter(adapterTask);
		
		// first, get tasks in today
		//loadAllTasksByDate(day);
		
		// chose item
		lvPlan.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				CTask t = lstTask.get(position);
				
				if(t.getType().equals("Plan"))
				{
					// Open planDetail Activity
					startPlanDetailActivity(day, (Plan)t);
				}
			}
		});
		
		
		// get CalendarView
		CalendarView cv = (CalendarView) getActivity().findViewById(
											R.id.calendarView1);
		
		// chose date in calendar
		cv.setOnDateChangeListener(new OnDateChangeListener()
		{
			@Override
			public void onSelectedDayChange(CalendarView view, 
							int year, int month, int dayOfMonth)
			{
				day.setD(dayOfMonth + "");
				day.setM(month + "");
				day.setY(year + "");
				
				// load all tasks in day
				loadAllTasksByDate(day);
			}
			
		});
		
		
		// get TextView crePlan
		TextView tvCrePlan = (TextView) getActivity().findViewById(
											R.id.fragment01_tvCrePlan);
		
		// set event onClick on TextView crePlan
		tvCrePlan.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				startPlanDetailActivity(day, new Plan());
			}
		});
		
		
		// get image button create
		ImageButton ibtnEdit = (ImageButton) getActivity().
						findViewById(R.id.fragment01_btnEdit);
		
		
		// set onClick
		ibtnEdit.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent i = new Intent(
					getActivity(), ShiftDetailActivity.class);
				
				Bundle b = new Bundle();
				b.putSerializable("shift", new Shift());
				b.putSerializable("day", day);
				
				i.putExtra("data", b);
				
				startActivity(i);
			}
		});
		
		
		// close
		
		
		// show list
	}

	

	@Override
	public void onPause()
	{
		super.onPause();
		//
		// send day for other fragments
		Intent i = getActivity().getIntent();
		Bundle b = new Bundle();
		b.putSerializable("day", day);
		i.putExtra("data", b);
	}
	
	

	@Override
	public void onResume()
	{
		loadAllTasksByDate(day);
		super.onResume();
	}



	// open activity DetailPlan
	// with data
	private void startPlanDetailActivity(Day d, Plan p)
	{
		Day dt = d;
		if(dt==null) dt = new Day();
		Plan pt = p;
		if(pt==null) pt = new Plan();
		
		Intent i = new Intent(getActivity(), 
				PlanDetailActivity.class);
		Bundle b = new Bundle();
		
		b.putSerializable("day", dt);
		b.putSerializable("plan", pt);
		
		i.putExtra("data", b);
		
		startActivity(i);
	}
	
	
	
	// load all task by day
	private void loadAllTasksByDate(Day d)
	{
		if(d!=null)
		{
			lstTask.clear();
			// load Plan
			lstTask.addAll(tblPlanT.getByDate(
					d.getY()+d.getM()+d.getD()));
			//lstTask.addAll(tblPlanT.getAll());
			// load Shift
			
			// notification data change
			adapterTask.notifyDataSetChanged();
		}
	}
	
}
