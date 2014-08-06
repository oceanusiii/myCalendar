package com.example.calendar_japan;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.adapter.CustomOnItemSelectedListener;
import com.example.config.ConfigurationWS;
import com.example.model.Shift;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class DetailShiftTranSaction extends Activity {
	
	private static final String urlAllShift ="http://192.168.1.199:8061/calendar/get_all_shift.php";
	private Spinner spn;
	Boolean test1=false,test2=false,test3=false,test4=false,test5=false,test6=false,test7=false,test8=false,test9=false,test10=false;
	private Button member1,member2,member3,member4,member5,member6,member7,member8,member9,member10;
	ArrayList<String> list = new ArrayList<String>();
	
	ArrayList<Shift> arrShift = new ArrayList<Shift>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shift_view);
		initianize();
		list.add("");
		list.add("Shift 1");
		list.add("Shift 2");
		list.add("Shift 3");
		list.add("Shift 4");
		list.add("Shift 5");
		list.add("Shift 6");
		list.add("Shift 7");
		member1.setOnClickListener(new  OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(!test1)
				{
				member1.setBackgroundColor(Color.GREEN);
				test1=!test1;
				}
				else {
					test1=!test1;
					member1.setBackgroundColor(Color.GRAY);
				}
			}
		});
		member2.setOnClickListener(new  OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if(!test1)
						{
						member2.setBackgroundColor(Color.GREEN);
						test1=!test1;
						}
						else {
							test1=!test1;
							member2.setBackgroundColor(Color.GRAY);
						}
					}
				});
		member3.setOnClickListener(new  OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(!test1)
				{
				member3.setBackgroundColor(Color.GREEN);
				test1=!test1;
				}
				else {
					test1=!test1;
					member3.setBackgroundColor(Color.GRAY);
				}
			}
				});
		member4.setOnClickListener(new  OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(!test1)
				{
				member4.setBackgroundColor(Color.GREEN);
				test1=!test1;
				}
				else {
					test1=!test1;
					member4.setBackgroundColor(Color.GRAY);
				}
			}
		});
		member5.setOnClickListener(new  OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(!test1)
				{
				member5.setBackgroundColor(Color.GREEN);
				test1=!test1;
				}
				else {
					test1=!test1;
					member5.setBackgroundColor(Color.GRAY);
				}
			}
		});
		member6.setOnClickListener(new  OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(!test1)
				{
				member6.setBackgroundColor(Color.GREEN);
				test1=!test1;
				}
				else {
					test1=!test1;
					member6.setBackgroundColor(Color.GRAY);
				}
			}
		});
		member7.setOnClickListener(new  OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(!test1)
				{
				member7.setBackgroundColor(Color.GREEN);
				test1=!test1;
				}
				else {
					test1=!test1;
					member7.setBackgroundColor(Color.GRAY);
				}
			}
		});
		member8.setOnClickListener(new  OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(!test1)
				{
				member8.setBackgroundColor(Color.GREEN);
				test1=!test1;
				}
				else {
					test1=!test1;
					member8.setBackgroundColor(Color.GRAY);
				}
			}
		});
		member9.setOnClickListener(new  OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(!test1)
				{
				member9.setBackgroundColor(Color.GREEN);
				test1=!test1;
				}
				else {
					test1=!test1;
					member9.setBackgroundColor(Color.GRAY);
				}
			}
		});
		member10.setOnClickListener(new  OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(!test1)
				{
				member10.setBackgroundColor(Color.GREEN);
				test1=!test1;
				}
				else {
					test1=!test1;
					member10.setBackgroundColor(Color.GRAY);
				}
			}
		});

		ArrayAdapter<String> adapter = 
				new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, list);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spn.setAdapter(adapter);
		addListenerOnSpinnerItemSelection();
	}
	public void addListenerOnSpinnerItemSelection(){
        
        spn.setOnItemSelectedListener(new CustomOnItemSelectedListener());
}
	private void initianize() {
		// TODO Auto-generated method stub
		spn      = (Spinner) findViewById(R.id.spinner);
		member1  = (Button) findViewById(R.id.member1);
		member2  = (Button) findViewById(R.id.member2);
		member3  = (Button) findViewById(R.id.member3);
		member4  = (Button) findViewById(R.id.member4);
		member5  = (Button) findViewById(R.id.member5);
		member6  = (Button) findViewById(R.id.member6);
		member7  = (Button) findViewById(R.id.member7);
		member8  = (Button) findViewById(R.id.member8);
		member9  = (Button) findViewById(R.id.member9);
		member10 = (Button) findViewById(R.id.member10);
		
	}
	class WsGetAllShift extends AsyncTask<String , String, String>{

		private ConfigurationWS mWS;
		private Context ctx;
		
		public WsGetAllShift(Context ctx)
		{
			this.ctx=ctx;
			mWS = new ConfigurationWS(ctx);
		}
		
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			
			JSONObject json = new JSONObject();
			JSONArray jarr = new JSONArray();
			try {
			jarr = mWS.connectWSPut_Get_Data(urlAllShift, json, "shift");
			
			Log.e("Shift",jarr+"");
			for (int i = 0; i < jarr.length(); i++) {
				
					json = jarr.getJSONObject(i);
					
					String _id = json.getString("_id");
					String startTime = json.getString("starttime");
					String endTime = json.getString("endtime");
					String notification_time = json.getString("notification_time");
					String name = json.getString("name");
					String color_text = json.getString("color_text");
					String color_pattern = json.getString("color_pattern");
					String content = json.getString("content");
					String notification = json.getString("notification");
					Shift sh = new Shift(_id, startTime, endTime, notification_time, name, color_text, color_pattern, content, notification);
					
					arrShift.add(sh);
				} 
			}
			catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
		}
		
	}
}
