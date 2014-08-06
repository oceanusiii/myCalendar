package com.tiendd90.mycalendalapp;


import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


import com.tiendd90.dataprovider.TblPlanT;
import com.tiendd90.dataprovider.TblPlanTHelper;
import com.tiendd90.dataprovider.TblShiftHelper;
import com.tiendd90.dataprovider.TblShiftT;
import com.tiendd90.dataprovider.TblShiftTHelper;
import com.tiendd90.model.CTask;
import com.tiendd90.model.Day;
import com.tiendd90.model.Plan;
import com.tiendd90.model.Shift;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

	private final String TAG = "FRAGMENT 01";
	private ArrayList<CTask> lstTask = new ArrayList<CTask>();
	private TblPlanTHelper tblPlanT;
	private TblShiftTHelper tblShiftT;
	private Day day;
	private LVAdapterListTask adapterTask;
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		tblPlanT = new TblPlanTHelper(getActivity());
		tblShiftT = new TblShiftTHelper(getActivity());
		
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
					openPlanDetailActivity(day, (Plan)t);
				}
				if(t.getType().equals("Shift"))
				{
					// Open shiftDetail Activity
					openShiftDetailActivity(day, (Shift)t);
				}
			}
		});
		
		
		// get CalendarView
		CalendarView cv = (CalendarView) getActivity().
							findViewById(R.id.calendarView1);
		
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
				try
				{
					loadAllTasksByDate(day);
				}
				catch(Exception ex) 
				{ 
					Log.e(TAG, "On calendar date change: " + ex.getMessage()); 
				}
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
				openPlanDetailActivity(day, new Plan());
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
		
		
		// create new shift
		TextView tvOpenShiftList = (TextView) getActivity().
						findViewById(R.id.fragment01_tvLstShift);
		
		tvOpenShiftList.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				try
				{
					if(Setting.SQLITE==1)	// using SQLite
					{
						// test: simulator `add new shift transaction by pattern`
						// 
						// get first shift in table SHIFT
						// and insert it to table SHIFT_TRANSACTION
						Shift s = new TblShiftHelper(getActivity()).getByID("1");
						if(s!=null)
						{
							// add field DATE to it
							s.setDate(day.getY() + day.getM() + day.getD());
							// insert to table shift_transaction
							tblShiftT.insert(s);
						}
					}
					else	// using MySQL
					{
						// test: simulator `add new shift transaction by pattern`
						// 
						// get first shift in table SHIFT
						// and insert it to table SHIFT_TRANSACTION
						Shift s = new TblShiftT().getByID("1");
						if(s!=null)
						{
							// add field DATE to it
							s.setDate(day.getY() + day.getM() + day.getD());
							new TblShiftT().insert(s);
						}
					}
					// load
					loadAllTasksByDate(day);
				}
				catch(Exception ex) 
				{ 
					Log.e(TAG, "On TextView `OpenTaskList`: " + ex.getMessage()); 
				}
			}
		});
	
	} // END OF onActivityCreated()


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
		try
		{
			loadAllTasksByDate(day);
		}
		catch(Exception ex) 
		{ 
			Log.e(TAG, "OnResume: " + ex.getMessage()); 
		}
		super.onResume();
	}


	// open activity DetailPlan
	// with data
	private void openPlanDetailActivity(Day d, Plan p)
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
	
	
	// open activity DetailShift
	// with data
	private void openShiftDetailActivity(Day d, Shift s)
	{
		Day dt = d;
		if(dt==null) dt = new Day();
		Shift st = s;
		if(st==null) st = new Shift();
		
		Intent i = new Intent(getActivity(), 
				ShiftDetailActivity.class);
		Bundle b = new Bundle();
		
		b.putSerializable("day", dt);
		b.putSerializable("shift", st);
		
		i.putExtra("data", b);
		
		startActivity(i);
	}
	
	
	// load all task by day
	private void loadAllTasksByDate(Day d) 
			throws InterruptedException, ExecutionException
	{
		if(d!=null)
		{
			String curDate = d.getY() + d.getM() + d.getD();
			// load data from SQLite
			if(Setting.SQLITE == 1)
			{
				lstTask.clear();
				// load Plan 
				lstTask.addAll(tblPlanT.getByDate(curDate));
				// get latest shift
				ArrayList<Shift> tmpShift = tblShiftT.getByDate(curDate);
				if(tmpShift.size()>0)
				{
					lstTask.add(tmpShift.get(tmpShift.size()-1));
				}
			}
			// Load data from MySQL
			else
			{
				lstTask.clear();
				// load Plan
				lstTask.addAll(new TblPlanT().getByDate(curDate));
				// get latest shift
				ArrayList<Shift> tmpShift = new TblShiftT().getByDate(curDate);
				if(tmpShift.size()>0)
				{
					lstTask.add(tmpShift.get(tmpShift.size()-1));
				}
			}
			
			// notification data change
			adapterTask.notifyDataSetChanged();
		}
	}
	
}
