package com.example.calendar_japan;

import java.util.ArrayList;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.adapter.Adapter_Plan_transaction;
import com.example.config.ConfigurationWS;
import com.example.model.Plan_Transaction;
import com.example.model.Shift_TranSaction;
import com.example.model.Work_Shift;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Fragmentone extends Fragment {

	private static final String urlSelectPlanTransaction ="http://192.168.1.199:8061/calendar/select_rows_plan_transaction.php";
	private static final String urlSelectShiftTranSaction ="http://192.168.1.199:8061/calendar/select_rows_shift_transaction.php";
	private static final String urlAllTransaction ="http://192.168.1.199:8061/calendar/get_all_plan_transaction.php";
	private ListView lv;
	private TextView addplan,addShift;
	Adapter_Plan_transaction adapter; 
	private Shift_TranSaction shiftTr;
	ArrayList<Plan_Transaction> arrPlanTransaction = new ArrayList<Plan_Transaction>();
	private String date;
	private Calendar cld;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View v = inflater.inflate(R.layout.layout_fragment1,container, false);
		
		cld= Calendar.getInstance();
		date=String.valueOf(cld.get(Calendar.DAY_OF_MONTH))+String.valueOf(cld.get(Calendar.MONTH)+1)+String.valueOf(cld.get(Calendar.YEAR));
//		date =cld.getTimeInMillis();
		
		CalendarView c = (CalendarView) v.findViewById(R.id.Calendar);
		
		//// get day,month and year
		c.setOnDateChangeListener(new OnDateChangeListener() {
			
			@Override
			public void onSelectedDayChange(CalendarView view, int year, int month,
					int dayOfMonth) {
				// TODO Auto-generated method stub
				
				date =String.valueOf(dayOfMonth)+String.valueOf(month+1)+String.valueOf(year);
				getdata();

				
			}
		});
		
		addShift = (TextView) v.findViewById(R.id.add_shift);
		addShift.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getActivity(),AddShiftTranSaction.class);
				startActivity(i);
			}
		});
			try {
				adapter= new Adapter_Plan_transaction(getActivity(), R.layout.item_plan_transaction,arrPlanTransaction);
				lv = (ListView) v.findViewById(R.id.listview_plan_transaction);
				lv.setAdapter(adapter);
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		addplan = (TextView) v.findViewById(R.id.addplan);
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				Intent i = new Intent(getActivity(),DetailPlanTransaction.class);
				
				Bundle b = new Bundle();
				
				b.putSerializable("planT", arrPlanTransaction.get(position));
				i.putExtra("data",b);
				startActivity(i);
			}
		});
		
		//them plan
		addplan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getActivity(),AddPlan_transaction.class);
				startActivity(i);
			}
		});
		getdata();
		return v;
	}

	
	private void getdata() {
		// TODO Auto-generated method stub
//		new GetShiftTransactionFllowDate(getActivity()).execute(date);
		new GetPlanTransactionFllowDate(getActivity()).execute(date);
	}


	class WSGetAllPlanTransacTion extends AsyncTask<String, String, String> {
		private String TAG = "WSGetAllPhone";
		private ConfigurationWS mWS;
		private Context context;
		private ProgressDialog mProgress;

		public WSGetAllPlanTransacTion(Context mcontext) {
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
	 			JSONArray arrItem = new JSONArray();
				arrItem = mWS.connectWSPut_Get_Data(urlAllTransaction, json, "plan_transaction");
				if (arrItem != null) {
					for (int i = 0; i < arrItem.length(); i++) 
					{
						JSONObject results = arrItem.getJSONObject(i);

						Plan_Transaction p = new Plan_Transaction();
						p.set_id(results.getInt("_id"));
						p.setStarttime(results.getString("starttime"));
						p.setEndtime(results.getString("endtime"));
						p.setNotification_time(results.getString("notification_time"));
						p.setTitle(results.getString("title"));
						p.setIcon(results.getString("icon"));
						p.setContent(results.getString("content"));
						p.setNotification(results.getString("notification"));
						p.setDate(results.getString("date"));
						
//						array.add(p);
					}
				}
			} catch (Exception e) {
				
				Log.i(TAG, "in Get All Products : " + e.getMessage());
			}
			return null;
		}

		protected void onPostExecute(String result) {
			
			super.onPostExecute(result);
			
			adapter.notifyDataSetChanged();
			
			try 
			{	
				mProgress.dismiss();
			} 
			catch (Exception e) { }
		}

//		@Override
//		protected void onPreExecute() {
//			// TODO Auto-generated method stub
//			super.onPreExecute();
//			mProgress.setMessage("Loading data...");
//			mProgress.setCancelable(false);
//			mProgress.show();
//		}
	}
	
//	class GetShiftTransactionFllowDate extends AsyncTask<String,String,String>{
//		
//		private Context ctx;
//		private ConfigurationWS WS;
//
//		public GetShiftTransactionFllowDate(Context ctx){
//			this.ctx = ctx;
//			WS = new ConfigurationWS(ctx);
//		}
//
//		@Override
//		protected String doInBackground(String... params) {
//			// TODO Auto-generated method stub
//			JSONObject json = new JSONObject();
//			JSONArray jarr = new JSONArray();
//			arrPlanTransaction.clear();
//			try {
//				String n = params[0];
//				json.put("date",n);
//				jarr = WS.connectWSPut_Get_Data(urlSelectShiftTranSaction, json,"shift_transaction_date");
//				Log.e("jarr_shift",jarr+"");
//				if(jarr!=null){
//					System.out.println("vao day chua  ");
//					json = jarr.getJSONObject(0);
//					Log.e("json",json+"");
//					Log.e("json",json.getString("_id")+"");
//					Log.e("json",json.getString("starttime")+"");
//					Log.e("json",json.getString("endtime")+"");
//					Log.e("json",json.getString("notification_time")+"");
//					Log.e("json",json.getString("name")+"");
//					Log.e("json",json.getString("color_pattern")+"");
//					Log.e("json",json.getString("colot_text")+"");
//					Log.e("json",json.getString("content")+"");
//					Log.e("json",json.getString("notification")+"");
//					shiftTr = new Shift_TranSaction();
//					
//					shiftTr.set_id(json.getString("_id"));
//					shiftTr.setStartTime(json.getString("starttime"));
//					shiftTr.setEndTime("endtime");
//					shiftTr.setNotification_Time("notification_time");
//					shiftTr.setName("name");
//					shiftTr.setColor_Text("color_text");
//					shiftTr.setColor_Pattern("color_pattern");
//					shiftTr.setContent("content");
//					shiftTr.setNotification("notification");
//					shiftTr.setDate(n);
//					
//					
//					Plan_Transaction plt = new Plan_Transaction();
//					
//					plt.setStarttime(shiftTr.getStartTime());
//					plt.setEndtime(shiftTr.getEndTime());
//					plt.setNotification_time(shiftTr.getNotification_Time());
//					plt.setTitle(shiftTr.getName());
//					plt.setIcon("");
//					plt.setContent(shiftTr.getContent());
//					plt.setNotification(shiftTr.getNotification());
//					plt.setDate(n);
//					
//					arrPlanTransaction.add(plt);
//					
//				}
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			return null;
//		}
//
//		@Override
//		protected void onPostExecute(String result) {
//			// TODO Auto-generated method stub
//			super.onPostExecute(result);
//			adapter.notifyDataSetChanged();
//		}
//	}
	
	
	class GetPlanTransactionFllowDate extends AsyncTask<String,String,String>{
		
		private Context ctx;
		private ConfigurationWS WS;

		public GetPlanTransactionFllowDate(Context ctx){
			this.ctx = ctx;
			WS = new ConfigurationWS(ctx);
		}
		
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			String n = params[0];
			Log.e("try","cho nai van song");
			JSONObject json = new JSONObject();
			JSONArray jarr = new JSONArray();
			
			JSONArray jarr2 = new JSONArray();
			try {
				
				
				json.put("date",n);
				jarr = WS.connectWSPut_Get_Data(urlSelectShiftTranSaction, json,"shift_transaction_date");
				Log.e("jarr_shift",jarr+"");
				if(jarr!=null){
					System.out.println("vao day chua  ");
					json = jarr.getJSONObject(0);
//					Log.e("json",json+"");
//					Log.e("json",json.getString("_id")+"");
//					Log.e("json",json.getString("starttime")+"");
//					Log.e("json",json.getString("endtime")+"");
//					Log.e("json",json.getString("notification_time")+"");
//					Log.e("json",json.getString("name")+"");
//					Log.e("json",json.getString("color_pattern")+"");
//					Log.e("json",json.getString("colot_text")+"");
//					Log.e("json",json.getString("content")+"");
//					Log.e("json",json.getString("notification")+"");
					shiftTr = new Shift_TranSaction();
					
					shiftTr.set_id(json.getString("_id"));
					shiftTr.setStartTime(json.getString("starttime"));
					shiftTr.setEndTime("endtime");
					shiftTr.setNotification_Time("notification_time");
					shiftTr.setName("name");
					shiftTr.setColor_Text("color_text");
					shiftTr.setColor_Pattern("color_pattern");
					shiftTr.setContent("content");
					shiftTr.setNotification("notification");
					shiftTr.setDate(n);
					
					
					Plan_Transaction plt = new Plan_Transaction();
					
					plt.set_id(1);
					plt.setStarttime(shiftTr.getStartTime());
					plt.setEndtime(shiftTr.getEndTime());
					plt.setNotification_time(shiftTr.getNotification_Time());
					plt.setTitle(shiftTr.getName());
					plt.setIcon("");
					plt.setContent(shiftTr.getContent());
					plt.setNotification(shiftTr.getNotification());
					plt.setDate(n);
					
					arrPlanTransaction.add(plt);
					Log.e("aaaaaaaaaaaaaaaaaaaaaaaa",arrPlanTransaction.size()+"");
				}
				
				jarr2 = WS.connectWSPut_Get_Data(urlSelectPlanTransaction, json,"plan_transaction_date");
				Log.e("jarr",jarr+"");
				if(jarr!=null)
				{
					for (int i = 0; i < jarr.length(); i++) {
						json = jarr2.getJSONObject(i);
						
						Plan_Transaction planTr = new Plan_Transaction(2,json.getString("starttime"),json.getString("endtime"),json.getString("notification_time"),
								json.getString("title"),json.getString("icon"),json.getString("content"),json.getString("notification"),json.getString("date"));
						arrPlanTransaction.add(planTr);
						Log.e("1",arrPlanTransaction.get(i).get_id()+"");
						Log.e("2",arrPlanTransaction.get(i).getStarttime());
						Log.e("3",arrPlanTransaction.get(i).getEndtime());
						Log.e("4",arrPlanTransaction.get(i).getNotification_time());
						Log.e("5",arrPlanTransaction.get(i).getTitle());
						Log.e("6",arrPlanTransaction.get(i).getIcon());
						Log.e("7",arrPlanTransaction.get(i).getContent());
						Log.e("8",arrPlanTransaction.get(i).getNotification());
						Log.e("9",arrPlanTransaction.get(i).getDate());
						
						System.out.println(arrPlanTransaction.size()+" SIZE()");
					}
				}
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			adapter.notifyDataSetChanged();
		}
		
	}
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.e("onStart","onStart()");
	}
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.e("onStop","onStop()");
	}
	@Override
	public void onResume() {
		
		// TODO Auto-generated method stub
		super.onResume();
		Log.e("onResume","onResume()");
		
		getdata();
	}
}
