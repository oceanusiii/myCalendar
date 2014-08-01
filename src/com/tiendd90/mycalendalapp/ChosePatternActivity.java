package com.tiendd90.mycalendalapp;

import java.util.ArrayList;

import com.tiendd90.dataprovider.TblPlanHelper;
import com.tiendd90.model.Plan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;



public class ChosePatternActivity extends Activity
{
	
	private ArrayList<String> listPattern;
	private ArrayList<String> listID;
	private ArrayAdapter<String> myAdapter;
	private ListView lvPattern;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chose_pattern);
		
		listID = new ArrayList<String>();
		listPattern = new ArrayList<String>();
		
		
		// get view
		lvPattern = (ListView) findViewById(
							R.id.chose_pattern_lvPattern);
		
		// create adapter
		myAdapter = new ArrayAdapter<String>(
				ChosePatternActivity.this,
					android.R.layout.simple_list_item_1, listPattern);
		
		// set adapter for listview
		lvPattern.setAdapter(myAdapter);
		
		
		// get all pattern from database
		getAllPattern();
		
		
		
		myAdapter.notifyDataSetChanged();
		
		
		// ListView item onClick
		// chose
		lvPattern.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				Plan p = new Plan();
				// get plan by ID
				p = new TblPlanHelper(ChosePatternActivity.this).
										getByID(listID.get(position));
				
				Bundle b = new Bundle();
				b.putSerializable("newPattern", p);
				
				Intent i = getIntent();
				i.putExtra("data", b);
				
				setResult(PlanDetailActivity.RESULT_CODE_CHOSEPATTERN, i);
				
				// close activity
				finish();
			}
		});
		
		
		
		// long click to delete
		lvPattern.setOnItemLongClickListener(
					new OnItemLongClickListener()
		{

			@Override
			public boolean onItemLongClick(AdapterView<?> parent,
					View view, int position, long id)
			{
				
				// delete Plan by ID
				new TblPlanHelper(
						ChosePatternActivity.this).delete(
											listID.get(position));
				
				getAllPattern();
				
				myAdapter.notifyDataSetChanged();
				
				return true;
			}
		});
		
		
	}
	
	
	
	/**
	 * Get all pattern from database
	 */
	private void getAllPattern()
	{
		// select data from database
		ArrayList<Plan> plans = new TblPlanHelper(
							ChosePatternActivity.this).getAll();
		
		if(plans!=null)
		{
			listID.clear();
			listPattern.clear();
			for(Plan p : plans)
			{
				listID.add(p.getId());
				listPattern.add(p.getPatternName());
			}
		}
	}
}
