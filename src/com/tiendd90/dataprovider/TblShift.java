package com.tiendd90.dataprovider;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

import com.tiendd90.model.Shift;



public class TblShift
{
	public static final String TAG = "TBL_SHIFT_WEBSERVICE";
	private static final String URL_GETALL = 
			"http://192.168.1.117:80/webservice_android/calendar/SHIFT_getAll.php";
	private static final String URL_GETBYID = 
			"http://192.168.1.117:80/webservice_android/calendar/SHIFT_getByID.php";
	
	
	/**
	 * Get all from table SHIFT
	 * @return
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public ArrayList<Shift> getAll() 
			throws InterruptedException, ExecutionException
	{
		return new TaskGetAll().execute().get();
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public Shift getByID(String id) 
			throws InterruptedException, ExecutionException
	{
		return new TaskGetByID().execute(id).get();
	}
	
	
	/**_________________________ASYNCTASK____________________________*/
	/**
	 * 
	 * @author FDM17
	 *
	 * AsyncTask get all from table SHIFT
	 * and add them to `shifts`
	 */
	private class TaskGetAll extends AsyncTask<String, String, ArrayList<Shift>>
	{
		@Override
		protected ArrayList<Shift> doInBackground(String... params)
		{
			ArrayList<Shift> result = new ArrayList<Shift>();
			
			try
			{
				// get data from server
				JSONArray jarr = new Webservice().connect(
						URL_GETALL, new JSONObject(), "SHIFT");
				
				if(jarr != null)
				{
					for(int i=0; i<jarr.length(); ++i)
					{
						JSONObject jobj = jarr.getJSONObject(i);
						
						Shift s = new Shift();
						s.setId(jobj.getString("_ID"));
						s.setStartTime(jobj.getString("START_TIME"));
						s.setEndTime(jobj.getString("END_TIME"));
						s.setNotificationTime(jobj.getString("NOTIFICATION_TIME"));
						s.setName(jobj.getString("NAME"));
						s.setColorText(jobj.getString("COLOR_TEXT"));
						s.setColorPattern(jobj.getString("COLOR_PATTERN"));
						s.setContent(jobj.getString("CONTENT"));
						s.setIsAllDay(jobj.getString("IS_ALLDAY"));
						s.setNotification(jobj.getString("NOTIFICATION"));
						s.setViewOrder(jobj.getString("VIEW_ORDER"));
						
						result.add(s);
					}
				}
			}
			catch(Exception ex)
			{
				Log.e(TAG, "TASK GETALL: " + ex.getMessage());
			}
			
			return result;
		}
	}
	
	/**
	 * 
	 * @author FDM17
	 *
	 */
	private class TaskGetByID extends AsyncTask<String, String, Shift>
	{
		@Override
		protected Shift doInBackground(String... params)
		{
			Shift result = null;
			
			try
			{
				JSONObject json = new JSONObject();
				json.put("_ID", params[0]);
				// get data from server
				JSONArray jarr = new Webservice().connect(
						URL_GETBYID, json, "SHIFT");
				
				if(jarr != null)
				{
					for(int i=0; i<jarr.length(); ++i)
					{
						JSONObject jobj = jarr.getJSONObject(i);
						
						result = new Shift();
						result.setId(jobj.getString("_ID"));
						result.setStartTime(jobj.getString("START_TIME"));
						result.setEndTime(jobj.getString("END_TIME"));
						result.setNotificationTime(jobj.getString("NOTIFICATION_TIME"));
						result.setName(jobj.getString("NAME"));
						result.setColorText(jobj.getString("COLOR_TEXT"));
						result.setColorPattern(jobj.getString("COLOR_PATTERN"));
						result.setContent(jobj.getString("CONTENT"));
						result.setIsAllDay(jobj.getString("IS_ALLDAY"));
						result.setNotification(jobj.getString("NOTIFICATION"));
						result.setViewOrder(jobj.getString("VIEW_ORDER"));
					}
				}
			}
			catch(Exception ex)
			{
				Log.e(TAG, "TASK GETBYID: " + ex.getMessage());
			}
			
			return result;
		}
	}
}
