package com.example.calendar_japan;

import java.util.ArrayList;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.calendar_japan.DetailShiftTranSaction.WsGetAllShift;
import com.example.config.ConfigurationWS;
import com.example.model.Plan_Transaction;
import com.example.model.Shift;
import com.example.model.Shift_TranSaction;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

public class AddShiftTranSaction extends Activity implements OnClickListener {

	private TextView back;
	private ImageView backTwo;
	private Button btShift1,btShift2,btShift3,btShift4,btShift5,btShift6,btShift7,btShift8,btShift9,btShift10,btShift11,btShift12;
	private Button id_shift;
	private CalendarView cv;
	private String date;
	
	private static final String urlAllShift ="http://192.168.1.199:8061/calendar/get_all_shift.php";
	private static final String add_shift_tran ="http://192.168.1.199:8061/calendar/create_shift_transaction.php";
	ArrayList<Shift> arrShift = new ArrayList<Shift>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_shift);
		initianize();
		Calendar c = Calendar.getInstance();
		date =String.valueOf(c.get(Calendar.DAY_OF_MONTH))+String.valueOf(c.get(Calendar.MONTH)+1)+String.valueOf(c.get(Calendar.YEAR));
		cv.setOnDateChangeListener(new OnDateChangeListener() {
			
			@Override
			public void onSelectedDayChange(CalendarView view, int year, int month,
					int dayOfMonth) {
				// TODO Auto-generated method stub
				date = String.valueOf(dayOfMonth)+String.valueOf(month)+String.valueOf(year);
			}
		});
		
		back.setOnClickListener(onclick);
		backTwo.setOnClickListener(onclick);
		btShift1.setOnClickListener(this);
		btShift2.setOnClickListener(this);
		btShift3.setOnClickListener(this);
		btShift4.setOnClickListener(this);
		btShift5.setOnClickListener(this);
		btShift6.setOnClickListener(this);
		btShift7.setOnClickListener(this);
		btShift8.setOnClickListener(this);
		btShift9.setOnClickListener(this);
		btShift10.setOnClickListener(this);
		btShift11.setOnClickListener(this);
		btShift12.setOnClickListener(this);
	}

	private void initianize() {
		back      = (TextView) findViewById(R.id.back);
		backTwo   = (ImageView)findViewById(R.id.back_fragment_one);
		btShift1  = (Button) findViewById(R.id.shift_1);
		btShift2  = (Button) findViewById(R.id.shift_2);
		btShift3  = (Button) findViewById(R.id.shift_3);
		btShift4  = (Button) findViewById(R.id.shift_4);
		btShift5  = (Button) findViewById(R.id.shift_5);
		btShift6  = (Button) findViewById(R.id.shift_6);
		btShift7  = (Button) findViewById(R.id.shift_7);
		btShift8  = (Button) findViewById(R.id.shift_8);
		btShift9  = (Button) findViewById(R.id.shift_9);
		btShift10 = (Button) findViewById(R.id.shift_10);
		btShift11 = (Button) findViewById(R.id.shift_11);
		btShift12 = (Button) findViewById(R.id.shift_12);
		cv= (CalendarView) findViewById(R.id.cv);
	}

	OnClickListener onclick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i = new Intent(AddShiftTranSaction.this,Fragmentone.class);
			startActivity(i);
		}
	};
	
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
			
			Log.e("Shifttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt",jarr+"");
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
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		new WsGetAllShift(AddShiftTranSaction.this).execute();
	}
	
	class WSAddShiftTransaction extends AsyncTask<Integer, String, String> {
			
			private String TAG = "WSGetAllPhone";
			private ConfigurationWS mWS;
			private Context context;
			private ProgressDialog mProgress;
	
			public WSAddShiftTransaction(Context mcontext) {
				super();
				this.context = mcontext;
				mWS = new ConfigurationWS(mcontext);
				mProgress = new ProgressDialog(mcontext);
			}
	
			@Override
			protected String doInBackground(Integer... arg0) {
				// TODO Auto-generated method stub
				
				int n = arg0[0];
				try {
					Shift shift = arrShift.get(n);
					
					Log.e("date",date);
					
					Shift_TranSaction shift_tr = new Shift_TranSaction(shift.getStartTime(),shift.getEndTime(),shift.getNotification_Time(),shift.getName(),
							shift.getColor_Text(), shift.getColor_Pattern(),"", shift.getNotification(),date);
					JSONObject json = new JSONObject();
					
					json.put("starttime",shift_tr.getStartTime());
					json.put("endtime",shift_tr.getEndTime());
					json.put("notification_time",shift_tr.getNotification_Time());
					json.put("name", shift_tr.getName());
					json.put("color_text",shift_tr.getColor_Text());
					json.put("color_pattern",shift_tr.getColor_Pattern());
					json.put("notification",shift_tr.getNotification());
					json.put("date",shift_tr.getDate());
					
					Log.e("json",json+"");
					// send request to server
					mWS.connectWSPut_Get_Data(add_shift_tran, json, "shift_transaction");
	
					
				} catch (Exception e) { }
				
				return null;
			}
	
			protected void onPostExecute(String result) {
				// TODO Auto-generated method stub
	//			progress.dismiss();
	//			Intent intent = new Intent(AddVoucher.this, ListVoucher.class);
	//			startActivity(intent);
			}
	
		}
	@Override
	public void onClick(View v) {
		int i=v.getId();
		switch (i) {
		case R.id.shift_1:
			new WSAddShiftTransaction(AddShiftTranSaction.this).execute(0);
			break;
		case R.id.shift_2:
			new WSAddShiftTransaction(AddShiftTranSaction.this).execute(1);		
					break;
		case R.id.shift_3:
			new WSAddShiftTransaction(AddShiftTranSaction.this).execute(2);		
					break;
		case R.id.shift_4:
			new WSAddShiftTransaction(AddShiftTranSaction.this).execute(3);
			break;
		case R.id.shift_5:
			new WSAddShiftTransaction(AddShiftTranSaction.this).execute(4);
			break;
		case R.id.shift_6:
			new WSAddShiftTransaction(AddShiftTranSaction.this).execute(5);
			break;
		case R.id.shift_7:
			new WSAddShiftTransaction(AddShiftTranSaction.this).execute(6);
			break;
		case R.id.shift_8:
			new WSAddShiftTransaction(AddShiftTranSaction.this).execute(7);
			break;
		case R.id.shift_9:
			new WSAddShiftTransaction(AddShiftTranSaction.this).execute(8);
			break;
		case R.id.shift_10:
			new WSAddShiftTransaction(AddShiftTranSaction.this).execute(9);
			break;
		case R.id.shift_11:
			new WSAddShiftTransaction(AddShiftTranSaction.this).execute(10);		
				break;
		case R.id.shift_12:
			new WSAddShiftTransaction(AddShiftTranSaction.this).execute(11);
			break;

		default:
			break;
		}
	}
}
