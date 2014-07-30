package com.example.calendar_japan;

import java.util.Calendar;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class AddPlan extends Activity {
	private int mHour,mMinute;
	private TextView startTime,endTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_plan);
		
		
		initianize();
		startTime.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final Calendar c = Calendar.getInstance();
	            mHour = c.get(Calendar.HOUR_OF_DAY);
	            mMinute = c.get(Calendar.MINUTE);
	 
	            // Launch Time Picker Dialog
	            TimePickerDialog tpd = new TimePickerDialog(AddPlan.this,new OnTimeSetListener() {
					
					@Override
					public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
						// TODO Auto-generated method stub
						startTime.setText(mHour +":"+ mMinute);
					}
				}, mHour, mMinute, false);
	            tpd.show();
			}
		});
		endTime.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final Calendar c = Calendar.getInstance();
				 mHour = c.get(Calendar.HOUR_OF_DAY);
		            mMinute = c.get(Calendar.MINUTE);
		 
		            // Launch Time Picker Dialog
		            TimePickerDialog tpd = new TimePickerDialog(AddPlan.this,new OnTimeSetListener() {
						
						@Override
						public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
							// TODO Auto-generated method stub
							endTime.setText(hourOfDay +":"+ minute);
							
						}
					}, mHour, mMinute, false);
		            tpd.show();
			}
		});
	}
	public void initianize(){
		startTime = (TextView) findViewById(R.id.starttime);
		endTime = (TextView) findViewById(R.id.endtime);
	}
	

}
