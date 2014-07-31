package com.tiendd90.mycalendalapp;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.tiendd90.dataprovider.TblPlanHelper;
import com.tiendd90.dataprovider.TblPlanTHelper;
import com.tiendd90.model.Day;
import com.tiendd90.model.Plan;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;




public class PlanDetailActivity extends Activity
{

	private Day day = null;
	private Plan plan = null;
	// views
	private ImageButton ibtnClose;
	private ToggleButton tbtnNotification;
	private TextView tvPattern;
	private TextView tvStartTime;
	private TextView tvEndTime;
	private TextView tvNDate;
	private TextView tvNTime;
	private EditText edtTitle;
	private EditText edtContent;
	private String iconName;
	private int notification;
	private int nYear;
	private int nMonth;
	private int nDay;
	private int nHour;
	private int nMinute;
	
	// code for got result
	public static final int RESULT_CODE_CHOSEPATTERN = 111;
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_plan_detail);
		
		// get Views
		ibtnClose = (ImageButton) findViewById(R.id.edit_plan_ibtnClose);
		tbtnNotification = (ToggleButton) findViewById(R.id.edit_plan_tbtnNotification);
		tvPattern = (TextView) findViewById(R.id.edit_plan_tvPattern);
		tvStartTime = (TextView) findViewById(R.id.edit_plan_tvStartTime);
		tvEndTime = (TextView) findViewById(R.id.edit_plan_tvEndTime);
		tvNDate = (TextView) findViewById(R.id.edit_plan_tvNDate);
		tvNTime = (TextView) findViewById(R.id.edit_plan_tvNTime);
		edtTitle = (EditText) findViewById(R.id.edit_plan_edtTitle);
		edtContent = (EditText) findViewById(R.id.edit_plan_edtContent);
		iconName = "icon1";
		notification = 0;
		nYear = 0;
		nMonth = 0;
		nDay = 0;
		nHour = 0;
		nMinute = 0;
		
		
		// get extra data
		Intent i = getIntent();
		try
		{
			day = (Day) i.getBundleExtra("data").
								getSerializable("day");
			plan = (Plan) i.getBundleExtra("data").
								getSerializable("plan");
		}
		catch (Exception ex) {}

		if(plan!=null)
		{
			// set value of Views
			showValue(plan);
		}
		else { Log.e("PlanDetail", "plan null"); }
	
		
		
		// close activity and save plan
		ibtnClose.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				Calendar c = Calendar.getInstance();
				c.set(nYear, nMonth, nDay, nHour, nMinute);
				
				Plan p = new Plan();
				p.setStartTime(tvStartTime.getTag()+"");
				p.setEndTime(tvEndTime.getTag()+"");
				p.setNotificationTime(c.getTimeInMillis()+"");
				p.setTitle(edtTitle.getText().toString());
				p.setIcon(iconName);
				p.setContent(edtContent.getText().toString());
				p.setStartTimeSetted(notification+"");
				p.setNotification(notification+"");
				
				if(tvPattern.getText().toString().equals(""))
				{
					p.setPatternName(edtContent.getText().toString());
					new TblPlanHelper(PlanDetailActivity.this).insert(p);
				}
				else
				{	
					p.setDate(day.getY()+day.getM()+day.getD());
					new TblPlanTHelper(PlanDetailActivity.this).insert(p);
				}
				
				
				finish();
			}
		});
		
		
		
		// toggle button ON/OFF on change
		// set notification in app
		tbtnNotification.setOnCheckedChangeListener(
							new OnCheckedChangeListener()
		{
			
			@Override
			public void onCheckedChanged(
					CompoundButton buttonView, boolean isChecked)
			{
				if(isChecked)
				{
					notification = 1;
					tvNDate.setVisibility(View.VISIBLE);
					tvNTime.setVisibility(View.VISIBLE);
				}
				else
				{
					notification = 0;
					tvNDate.setVisibility(View.INVISIBLE);
					tvNTime.setVisibility(View.INVISIBLE);
				}
			}
		});
		
		
		
		
		
		// TextView Pattern onClick
		tvPattern.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// open new activity and
				// get result
				Intent i = new Intent(PlanDetailActivity.this, 
								ChosePatternActivity.class);

				startActivityForResult(i, 
							RESULT_CODE_CHOSEPATTERN);
			}
		});
	
		
		
	
		// TextView StartTime onClick
		tvStartTime.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// open timePicker dialog
				OnTimeSetListener callback = new OnTimeSetListener()
				{
					
					@Override
					public void onTimeSet(TimePicker view, 
									int hourOfDay, int minute)
					{
						// set text
						tvStartTime.setText(hourOfDay + ":" + minute);
						
						Calendar c = Calendar.getInstance();
						
						int y = Integer.parseInt(day.getY());
						int m = Integer.parseInt(day.getM());
						int d = Integer.parseInt(day.getD());
						c.set(y, m, d, hourOfDay, minute);
						
						long milisec = c.getTimeInMillis();
						tvStartTime.setTag(milisec);
					}
				};
				
				
				TimePickerDialog tpd = new TimePickerDialog(
						PlanDetailActivity.this, callback, 0, 0, true);
				
				tpd.show();
			}
		});
		
		
		
		
		// TextView EndTime onClick
		tvEndTime.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// open timePicker dialog
				OnTimeSetListener callback = new OnTimeSetListener()
				{
					
					@Override
					public void onTimeSet(TimePicker view, 
									int hourOfDay, int minute)
					{
						tvEndTime.setText(hourOfDay + ":" + minute);
						
						Calendar c = Calendar.getInstance();
						
						int y = Integer.parseInt(day.getY());
						int m = Integer.parseInt(day.getM());
						int d = Integer.parseInt(day.getD());
						c.set(y, m, d, hourOfDay, minute);
						
						long milisec = c.getTimeInMillis();
						tvEndTime.setTag(milisec);
					}
				};
				
				
				TimePickerDialog tpd = new TimePickerDialog(
						PlanDetailActivity.this, callback, 0, 0, true);
				
				tpd.show();
			}
		});
		
		
		
		
		// TextView Notification Time onClick
		tvNTime.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// open timePicker dialog
				OnTimeSetListener callback = new OnTimeSetListener()
				{
					
					@Override
					public void onTimeSet(TimePicker view, 
									int hourOfDay, int minute)
					{
						tvNTime.setText(hourOfDay + ":" + minute);
						
						nHour = hourOfDay;
						nMinute = minute;
					}
				};
				
				
				if(notification>0)
				{
					TimePickerDialog tpd = new TimePickerDialog(
							PlanDetailActivity.this, callback, 0, 0, true);
					
					tpd.show();
				}
				
			}
		});
		
		
		
		// TextView notification Date onClick
		tvNDate.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// open timePicker dialog
				OnDateSetListener callback = new OnDateSetListener()
				{

					@Override
					public void onDateSet(DatePicker view, int year,
							int monthOfYear, int dayOfMonth)
					{
						//int m = monthOfYear+1;
						tvNDate.setText(year + "-" 
									+ (monthOfYear+1) + "-"
									+ dayOfMonth);
						
						nYear = year;
						nMonth = monthOfYear;
						nDay = dayOfMonth;
					}
				};
				
				
				
				if(notification>0)
				{
					DatePickerDialog dpd = new DatePickerDialog(
							PlanDetailActivity.this, callback,
							Integer.parseInt(day.getY()),
							Integer.parseInt(day.getM()),
							Integer.parseInt(day.getD()));
					
					dpd.show();
				}
			}
		});
		
		
		
	}
	



	/**
	 * Result from detail activity here
	 */
	@Override
	protected void onActivityResult(int requestCode, 
							int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		
		// check result code
		if(resultCode==RESULT_CODE_CHOSEPATTERN)
		{
			Bundle b = data.getBundleExtra("patternChosed");
			Plan p = (Plan) b.getSerializable("patternChosed");
			
			showValue(p);
			
		}
	}
	

	
	
	@SuppressLint("SimpleDateFormat")
	private void showValue(Plan p)
	{
		try
		{
			Calendar c = Calendar.getInstance();
			
			java.text.DateFormat format;
			long time = 0;

			format = new SimpleDateFormat("HH:mm");
			
			// Start Time
			time = Long.parseLong((p.getStartTime()));
			c.setTimeInMillis(time);
			tvStartTime.setText(format.format(c.getTime()));
			tvStartTime.setTag(time);
			
			// End Time
			time = Long.parseLong(p.getEndTime());
			c.setTimeInMillis(time);
			tvEndTime.setText(format.format(c.getTime()));
			tvEndTime.setTag(time);
			
			// Notification Time
			time = Long.parseLong(p.getNotificationTime());
			c.setTimeInMillis(time);
			tvNTime.setText(format.format(c.getTime()));
			tvNTime.setTag(time);

			// Notification Date
			format = new SimpleDateFormat("yyyy-M-dd");
			time = Long.parseLong(p.getNotificationTime());
			c.setTimeInMillis(time);
			tvNDate.setText(format.format(c.getTime()));
			tvNDate.setTag(time);
	
			// duplicate code here :<
			// set invisible for textview
			// notification date and time
			if(Integer.parseInt(p.getNotification())>0)
			{
				tbtnNotification.setChecked(true);
				tvNDate.setVisibility(View.VISIBLE);
				tvNTime.setVisibility(View.VISIBLE);
			}
			else
			{
				tvNDate.setVisibility(View.INVISIBLE);
				tvNTime.setVisibility(View.INVISIBLE);
			}
			
			tvPattern.setText(p.getPatternName());
			edtTitle.setText(p.getTitle());
			edtContent.setText(p.getContent());
		}
		catch (Exception ex) {}
	}
	
}
