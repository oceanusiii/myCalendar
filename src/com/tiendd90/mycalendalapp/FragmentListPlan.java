package com.tiendd90.mycalendalapp;

import java.util.ArrayList;

import com.tiendd90.dataprovider.TblPlanTHelper;
import com.tiendd90.model.CTask;
import com.tiendd90.model.Day;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class FragmentListPlan extends Fragment
{

	private ArrayList<CTask> lstTask;
	private ListView lvLstPlan;
	private LVAdapterListTask myAdapter;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		
		
		// get View listview
		lvLstPlan = (ListView) getActivity().findViewById(
										R.id.listplan_lvPlan);
		
		lstTask = new ArrayList<CTask>();
		
		// load all data by date
		loadAllTasksByDate(new Day());
		
		// create new adapter
		myAdapter = new LVAdapterListTask(
				getActivity(), 
				R.layout.layout_fragment_listplan, lstTask);
		
		// set adapter for listview
		lvLstPlan.setAdapter(myAdapter);
		
		myAdapter.notifyDataSetChanged();
	}
	
	

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}
	
	

	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState)
	{
		return inflater.inflate(
				R.layout.layout_fragment_listplan, container, false);
	}
	
	
	// load all task by day
	private void loadAllTasksByDate(Day d)
	{
		if(d!=null)
		{
			lstTask.clear();
			// load Plan
			lstTask.addAll(new TblPlanTHelper(
							getActivity()).getByDate(
								d.getY()+d.getM()+d.getD()));
			//lstTask.addAll(tblPlanT.getAll());
			// load Shift
			
			// notification data change
			myAdapter.notifyDataSetChanged();
		}
	}
}
