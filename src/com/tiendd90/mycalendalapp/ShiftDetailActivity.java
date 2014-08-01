package com.tiendd90.mycalendalapp;

import java.util.ArrayList;
import java.util.Calendar;

import com.tiendd90.dataprovider.TblShiftHelper;
import com.tiendd90.model.Day;
import com.tiendd90.model.Shift;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;



public class ShiftDetailActivity extends Activity
{
	private Day day;
	private Shift shift;
	
	private Button btnExe;
	private ImageButton ibtnClose;
	private Switch sbtnNotification;
	private Spinner spnShift;
	private TextView tvStartTime;
	private TextView tvEndTime;
	private TextView tvNDate;
	private TextView tvNTime;
	private EditText edtContent;
	private int notification;
	private int nYear;
	private int nMonth;
	private int nDay;
	private int nHour;
	private int nMinute;
	//
	ArrayList<String> lstShiftName = new ArrayList<String>();
	ArrayList<String> lstShiftID = new ArrayList<String>();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shift_detail);
		
		// get Views
		//btnExe = (Button) findViewById(R.id.edit_shift_btnAction);
		spnShift = (Spinner) findViewById(R.id.edit_shift_spnShift);
		ibtnClose = (ImageButton) findViewById(R.id.edit_shift_ibtnClose);
		sbtnNotification = (Switch) findViewById(R.id.edit_shift_sbtnNotification);
		tvStartTime = (TextView) findViewById(R.id.edit_shift_tvStartTime);
		tvEndTime = (TextView) findViewById(R.id.edit_shift_tvEndTime);
		tvNDate = (TextView) findViewById(R.id.edit_shift_tvNDate);
		tvNTime = (TextView) findViewById(R.id.edit_shift_tvNTime);
		edtContent = (EditText) findViewById(R.id.edit_shift_edtContent);
		notification = 0;
		nYear = 0;
		nMonth = 0;
		nDay = 0;
		nHour = 0;
		nMinute = 0;
		
		
		// get extra data
		Intent i = getIntent();
		
		day = (Day) i.getBundleExtra("data").
							getSerializable("day");
		shift = (Shift) i.getBundleExtra("data").
							getSerializable("shift");

		if(shift!=null)
		{
			// set value of Views
			showViewValue(shift);
			//isUpdate = checkUpdate(plan);
		}
		
		
		// add data
//		for(int i=0; i<10; ++i)
//		{
//			Shift s = new Shift();
//			
//			s.setStartTime("0");
//			s.setEndTime("0");
//			s.setNotificationTime("0");
//			s.setName("Shift " + (i+1));
//			s.setColorText("#" + i + "96969");
//			s.setColorPattern("#" + i + "69696");
//			s.setContent("");
//			s.setIsAllDay("0");
//			s.setNotification("0");
//			s.setViewOrder((i+1) + "");
//			
//			new TblShiftHelper(ShiftDetailActivity.this).insert(s);
//			
//		}
		
		ArrayList<Shift> lst = new ArrayList<Shift>();
		lst.addAll(new TblShiftHelper(ShiftDetailActivity.this).getAll());
		
		
		for(int j=0; j<lst.size(); ++j)
		{
			lstShiftName.add(lst.get(j).getName());
			lstShiftID.add(lst.get(j).getId());
		}
		
		
		// create adapter for spiner
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				ShiftDetailActivity.this, 
				android.R.layout.simple_spinner_item, lstShiftName);
		
		adapter.setDropDownViewResource(
				android.R.layout.simple_list_item_single_choice);
		
		spnShift.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		
		
		// spinner onClick
		spnShift.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id)
			{
				Shift s = new TblShiftHelper(
						ShiftDetailActivity.this).getByID(
										lstShiftID.get(position));
				
				showViewValue(s);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{
				
			}
		});
		
		
		// switch button ON/OFF on change
		// set notification in app
		sbtnNotification.setOnCheckedChangeListener(
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
						ShiftDetailActivity.this, callback, 0, 0, true);
				
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
						ShiftDetailActivity.this, callback, 0, 0, true);
				
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
							ShiftDetailActivity.this, callback, 0, 0, true);
					
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
							ShiftDetailActivity.this, callback,
							Integer.parseInt(day.getY()),
							Integer.parseInt(day.getM()),
							Integer.parseInt(day.getD()));
					
					dpd.show();
				}
			}
		});
		
		
	}
	
	
	
	/**
	 * 
	 * @param s
	 */
	private void showViewValue(Shift s)
	{
		try
		{
			edtContent.setText(s.getContent());
			tvStartTime.setText(s.getStartTime());
			tvEndTime.setText(s.getEndTime());
			tvNDate.setText(s.getNotificationTime());
			tvNTime.setText(s.getNotificationTime());
			
			if(Integer.parseInt(s.getNotification())>0)
			{
				notification = 1;
				sbtnNotification.setChecked(true);
				tvNDate.setVisibility(View.VISIBLE);
				tvNTime.setVisibility(View.VISIBLE);
			}
			else
			{
				notification = 0;
				sbtnNotification.setChecked(false);
				tvNDate.setVisibility(View.INVISIBLE);
				tvNTime.setVisibility(View.INVISIBLE);
			}
		}
		catch(Exception ex) {  }
	}
	
	
	
	
	
	
}
