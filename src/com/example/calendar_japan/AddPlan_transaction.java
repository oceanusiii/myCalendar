package com.example.calendar_japan;

import java.util.ArrayList;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.config.ConfigurationWS;
import com.example.model.Plan_Transaction;
import com.example.model.Shift;

import android.R.integer;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class AddPlan_transaction extends Activity {
	private int mHour,mMinute,mDay,mMonth,mYear;
	String result;
	String hh=null;
	String mm=null;
	String ntt =null;
	private TextView startTime,endTime,startDate,notification_time;
	private ImageView add_plan,icon,notification,date;
	private EditText title,content;
	ArrayList<Plan_Transaction> array;
	ProgressDialog progress;
	
	private static final String add_plan_tran ="http://192.168.1.199:8060/calendar/create_plan_transaction.php";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_plan);
		
		
		initianize();
		add_plan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				if(title.getText().toString().equals("")||content.getText().toString().equals(""))
				{
					Toast.makeText(getApplicationContext(),"Please enter content", Toast.LENGTH_SHORT).show();
				}
				else 
				{

					new WSAddPlanTransaction(AddPlan_transaction.this).execute();
//					Plan_Transaction plan_tr = new Plan_Transaction(12, 13, 13, "a", "a", "a", 12, 11);
//					String txt = dat.responseString("insert", dat.Plan_Transaction2Json(plan_tr));
//					Toast.makeText(getApplicationContext(), txt,Toast.LENGTH_SHORT).show();
//					Log.e("test insert",txt);
					finish();
				}
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
	            TimePickerDialog tpd = new TimePickerDialog(AddPlan_transaction.this,new OnTimeSetListener() {
					
					@Override
					public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
						// TODO Auto-generated method stub
						startTime.setText(hourOfDay +":"+ minute);
						hh = hourOfDay+":"+minute;
						Log.e("hhhhhhhhhhhhh",String.valueOf(hh));
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
		            TimePickerDialog tpd = new TimePickerDialog(AddPlan_transaction.this,new OnTimeSetListener() {
						
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
				DatePickerDialog  dpd = new DatePickerDialog(AddPlan_transaction.this,
						new DatePickerDialog.OnDateSetListener() {
					
					@Override
					public void onDateSet(DatePicker view, int year, int monthOfYear,
							int dayOfMonth) {
						startDate.setText(dayOfMonth+" / "+(monthOfYear+1)+" / "+year);
						result =String.valueOf(dayOfMonth)+String.valueOf(monthOfYear+1)+String.valueOf(year);
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
		            TimePickerDialog tpd = new TimePickerDialog(AddPlan_transaction.this,new OnTimeSetListener() {
						
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
		add_plan = (ImageView) findViewById(R.id.add_plan);
		title = (EditText) findViewById(R.id.title_);
		content = (EditText) findViewById(R.id.content_);
		icon = (ImageView) findViewById(R.id.icont_select);
	}

class WSAddPlanTransaction extends AsyncTask<String, String, String> {
		
		private String TAG = "WSGetAllPhone";
		private ConfigurationWS mWS;
		private Context context;
		private ProgressDialog mProgress;

		public WSAddPlanTransaction(Context mcontext) {
			super();
			this.context = mcontext;
			mWS = new ConfigurationWS(mcontext);
//			mProgress = new ProgressDialog(mcontext);
		}

		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			
			try {
				
				JSONObject json = new JSONObject();
				
				
				Plan_Transaction plan_tr = new Plan_Transaction(hh,mm,ntt, title.getText().toString(),"",
						content.getText().toString(),"",result);
				
				json.put("starttime",plan_tr.getStarttime());
				json.put("endtime",plan_tr.getEndtime());
				json.put("notification_time", plan_tr.getNotification_time());
				json.put("icon", plan_tr.getIcon());
				json.put("content", plan_tr.getContent());
				json.put("notification", plan_tr.getNotification());
				json.put("date",result);
				json.put("title", plan_tr.getTitle());
				Log.e("dateeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee",result+"");
				// send request to server
				mWS.connectWSPut_Get_Data(add_plan_tran, json, "plan_transaction");

				
			} catch (Exception e) { }
			
			return null;
		}

		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
//			progress.dismiss();
//			Intent intent = new Intent(AddVoucher.this, ListVoucher.class);
//			startActivity(intent);
		}

//		@Override
//		protected void onPreExecute() {
//			// TODO Auto-generated method stub
//			super.onPreExecute();
//			progress = new ProgressDialog(AddPlan_transaction.this);
//			progress.setMessage("Loading products. Please wait...");
//			progress.setIndeterminate(false);
//			progress.setCancelable(false);
//			progress.show();
//		}
	}

}
