package com.example.calendar_japan;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.json.JSONObject;

import com.example.config.ConfigurationWS;
import com.example.model.Plan_Transaction;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class DetailPlanTransaction extends Activity {

	ConfigurationWS WSdetail = new ConfigurationWS(DetailPlanTransaction.this);
	private Plan_Transaction planT;
	private int mHour,mMinute,mDay,mMonth,mYear,mMonth_SV,mDay_SV,mYear_SV;
	String result;
	String hh=null;
	String mm=null;
	String ntt =null;
	private TextView startTime,endTime,startDate,notification_time,save,delete;;
	private ImageView update_plan,icon,notification,date;
	private EditText title,content;
	ArrayList<Plan_Transaction> array;
	
	private static final String url_update = "http://192.168.1.199:8061/Calendar/update_plan_transaction.php";
	private static final String url_delete ="http://192.168.1.199:8061/Calendar/delete_plan_transaction.php";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_plan);
		
		initianize();
		Intent i = getIntent();
		
		planT= (Plan_Transaction) i.getBundleExtra("data").getSerializable("planT");
		
		Log.e("planTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT",planT.get_id()+"");
		
		startTime.setText(String.valueOf(planT.getStarttime()));
		endTime.setText(String.valueOf(planT.getEndtime()));
		startDate.setText(mDay_SV+":"+mMonth_SV+":"+mYear_SV);
		notification_time.setText(String.valueOf(planT.getNotification_time()));
		title.setText(planT.getTitle());
		content.setText(planT.getContent());
		
		
		//update plan transaction 
		update_plan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				new update().execute();
				finish();
			}
		});
		
		//delete plan transaction
		delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				new delete().execute();
//				String txt;
//				String data;
//					data = String.valueOf(planT.get_id());
//					Log.e("data",data);
//					txt = dat.responseString("delete",data);
//					Toast.makeText(DetailPlanTransaction.this, txt, Toast.LENGTH_SHORT).show();
					finish();
			}
		});
		//set time start plan
				startTime.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						final Calendar c = Calendar.getInstance();
			            mHour = c.get(Calendar.HOUR_OF_DAY);
			            mMinute = c.get(Calendar.MINUTE);
			 
			            // Launch Time Picker Dialog
			            TimePickerDialog tpd = new TimePickerDialog(DetailPlanTransaction.this,new OnTimeSetListener() {
							
							@Override
							public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
								// TODO Auto-generated method stub
								startTime.setText(hourOfDay +":"+ minute);
								hh = hourOfDay+":"+minute;
							}
						}, mHour, mMinute, false);
			            tpd.show();
					}
				});
				
				//set time end phan
				endTime.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						final Calendar c = Calendar.getInstance();
						 mHour = c.get(Calendar.HOUR_OF_DAY);
				            mMinute = c.get(Calendar.MINUTE);
				 
				            // Launch Time Picker Dialog
				            TimePickerDialog tpd = new TimePickerDialog(DetailPlanTransaction.this,new OnTimeSetListener() {
								
								@Override
								public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
									// TODO Auto-generated method stub
									endTime.setText(hourOfDay +":"+ minute);
									mm = hourOfDay+":"+minute;
								}
							}, mHour, mMinute, false);
				            tpd.show();
					}
				});
				
				//set date Alarms
				startDate.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {

						final Calendar c = Calendar.getInstance();
						mDay = c.get(Calendar.DAY_OF_MONTH);
						mMonth = c.get(Calendar.MONTH);
						mYear = c.get(Calendar.YEAR);
						
						//Launch Date picker dialog
						DatePickerDialog  dpd = new DatePickerDialog(DetailPlanTransaction.this,
								new DatePickerDialog.OnDateSetListener() {
							
							@Override
							public void onDateSet(DatePicker view, int year, int monthOfYear,
									int dayOfMonth) {
								startDate.setText(dayOfMonth+" : "+(monthOfYear+1)+" : "+year);
								c.set(year, monthOfYear, dayOfMonth);
								result = dayOfMonth+":"+(monthOfYear+1)+":"+year;
								Log.e("result",result+"");
							}
						}, mYear,mMonth,mDay);
						dpd.show();
					}
				});
				
		
		//set time alarms
				notification_time.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {

						final Calendar c = Calendar.getInstance();
						 mHour = c.get(Calendar.HOUR_OF_DAY);
				            mMinute = c.get(Calendar.MINUTE);
				 
				            // Launch Time Picker Dialog
				            TimePickerDialog tpd = new TimePickerDialog(DetailPlanTransaction.this,new OnTimeSetListener() {
								
								@Override
								public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
									// TODO Auto-generated method stub
									notification_time.setText(hourOfDay +":"+ minute);
									//chuyen h:m thanh  m
									
									ntt = hourOfDay+":"+minute;
							
								}
							}, mHour, mMinute, false);
				            tpd.show();
					}
				});
	}

	public void initianize(){
		startTime = (TextView) findViewById(R.id.starttime);
		endTime = (TextView) findViewById(R.id.endtime);
		startDate = (TextView) findViewById(R.id.startdate);
		notification_time = (TextView) findViewById(R.id.notification_time);
		notification = (ImageView) findViewById(R.id.notifiaction);
		update_plan = (ImageView) findViewById(R.id.add_plan);
		title = (EditText) findViewById(R.id.title_);
		content = (EditText) findViewById(R.id.content_);
		icon = (ImageView) findViewById(R.id.icont_select);
		save = (TextView) findViewById(R.id.save);
		delete = (TextView) findViewById(R.id.delete);
		save.setVisibility(View.INVISIBLE);
		delete.setVisibility(View.VISIBLE);
	}
	
public class delete extends AsyncTask<String, String, String> {

		
		// 2
		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			try {
				JSONObject json = new JSONObject();
				//json.put("id", voucher.getId());
				json.put("_id",planT.get_id());
				WSdetail.connectWSPut_Get_Data(url_delete, json, "plan_transaction");
				Log.d("delete", "delete");
//				progress.dismiss();
			} 
			catch (Exception e) { }
			
			return null;
		}

	}

class update extends AsyncTask<String, String, String> {

	@Override
	protected String doInBackground(String... arg0) {
		// TODO Auto-generated method stub
		try {
			
			
			// create new json object
			// consist new info of product <voucher>
			JSONObject json = new JSONObject();
			Plan_Transaction plan_tr = new Plan_Transaction(hh,mm,ntt, title.getText().toString(),"",
					content.getText().toString(),"",result);
			
			json.put("_id",planT.get_id());
			json.put("starttime",plan_tr.getStarttime());
			json.put("endtime",plan_tr.getEndtime());
			json.put("notification_time", plan_tr.getNotification_time());
			json.put("icon", plan_tr.getIcon());
			json.put("content", plan_tr.getContent());
			json.put("notification", plan_tr.getNotification());
			json.put("date", plan_tr.getDate());
			json.put("title", plan_tr.getTitle());
			
			Log.e("starttime", plan_tr.get_id()+"");
			
			WSdetail.connectWSPut_Get_Data(url_update, json, "plan_transaction");
		} catch (Exception e) {

		}
		return null;
	}
 }
	
}
