package com.tiendd90.dataprovider;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

import com.tiendd90.model.Shift;;

public class TblShiftT
{
	public static final String TAG = "TBL_SHIFTTRANSACTION_WEBSERVICE";
	private static final String URL_GETALL = 
			"http://192.168.1.117:80/webservice_android/calendar/SHIFT_TRANSACTION_getAll.php";
	private static final String URL_GETBYDATE = 
			"http://192.168.1.117:80/webservice_android/calendar/SHIFT_TRANSACTION_getByDate.php";
	private static final String URL_GETBYID = 
			"http://192.168.1.117:80/webservice_android/calendar/SHIFT_TRANSACTION_getByID.php";
	private static final String URL_INSERT = 
			"http://192.168.1.117:80/webservice_android/calendar/SHIFT_TRANSACTION_insert.php";
	private static final String URL_UPDATE = 
			"http://192.168.1.117:80/webservice_android/calendar/SHIFT_TRANSACTION_update.php";
	private static final String URL_DELETE = 
			"http://192.168.1.117:80/webservice_android/calendar/SHIFT_TRANSACTION_delete.php";

	/**
	 * 
	 * @return
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public ArrayList<Shift> getAll() 
			throws InterruptedException, ExecutionException
	{
		return new TaskGetAll().execute().get();
	}
	
	/**
	 * 
	 * @return
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public ArrayList<Shift> getByDate(String date) 
			throws InterruptedException, ExecutionException
	{
		return new TaskGetByDate().execute(date).get();
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
	
	/**
	 * 
	 * @param p
	 */
	public void insert(Shift s)
	{
		new TaskInsert().execute(s);
	}
	
	/**
	 * 
	 * @param p
	 */
	public void update(Shift s)
	{
		new TaskUpdate().execute(s);
	}
	
	/**
	 * 
	 * @param id
	 */
	public void delete(String id)
	{
		new TaskDelete().execute(id);
	}
	
	
	
	/**____________________ASYNCTASK_____________________*/
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
						URL_GETALL, new JSONObject(), "SHIFT_TRANSACTION");
				
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
						s.setDate(jobj.getString("DATE"));
						
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
	private class TaskGetByDate extends AsyncTask<String, String, ArrayList<Shift>>
	{
		@Override
		protected ArrayList<Shift> doInBackground(String... params)
		{
			ArrayList<Shift> result = new ArrayList<Shift>();
			
			try
			{
				JSONObject json = new JSONObject();
				json.put("DATE", params[0]);
				
				// get data from server
				JSONArray jarr = new Webservice().connect(
						URL_GETBYDATE, json, "SHIFT_TRANSACTION");
				
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
						s.setDate(jobj.getString("DATE"));
						
						result.add(s);
					}
				}
			}
			catch(Exception ex)
			{
				Log.e(TAG, "TASK GETBYDATE: " + ex.getMessage());
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
			Shift s = null;
			try
			{
				JSONObject json = new JSONObject();
				json.put("_ID", params[0]);
				
				// get data from server
				JSONArray jarr = new Webservice().connect(
						URL_GETBYID, json, "SHIFT_TRANSACTION");
				
				if(jarr != null)
				{
					JSONObject jobj = jarr.getJSONObject(0);
					
					s = new Shift();
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
					s.setDate(jobj.getString("DATE"));
				}
			}
			catch(Exception ex)
			{
				Log.e(TAG, "TASK GETBYID: " + ex.getMessage());
			}
			
			return s;
		}
	}
	
	/**
	 * 
	 * @author FDM17
	 *
	 * AsyncTask get all from table SHIFT
	 * and add them to `shifts`
	 */
	private class TaskInsert extends AsyncTask<Shift, Void, String>
	{

		@Override
		protected String doInBackground(Shift... params)
		{
			Shift s = params[0];
			
			if(s != null)
			{
				JSONObject json = new JSONObject();
				try
				{
					json.put("_ID", s.getId());
					json.put("START_TIME", s.getStartTime());
					json.put("END_TIME", s.getEndTime());
					json.put("NOTIFICATION_TIME", s.getNotificationTime());
					json.put("NAME", s.getName());
					json.put("COLOR_TEXT", s.getColorText());
					json.put("COLOR_PATTERN", s.getColorPattern());
					json.put("CONTENT", s.getContent());
					json.put("IS_ALLDAY", s.getIsAllDay());
					json.put("NOTIFICATION", s.getNotification());
					json.put("DATE", s.getDate());
					
					// insert
					new Webservice().connect(URL_INSERT, json, "SHIFT_TRANSACTION");
				}
				catch (Exception ex)
				{
					Log.e(TAG, "TASK INSERT: " + ex.getMessage());
				}
			}
			
			return null;
		}
		
	}
	
	/**
	 * 
	 * @author FDM17
	 *
	 * AsyncTask get all from table SHIFT
	 * and add them to `shifts`
	 */
	private class TaskUpdate extends AsyncTask<Shift, Void, String>
	{

		@Override
		protected String doInBackground(Shift... params)
		{
			Shift s = params[0];
			
			if(s != null)
			{
				JSONObject json = new JSONObject();
				try
				{
					json.put("_ID", s.getId());
					json.put("START_TIME", s.getStartTime());
					json.put("END_TIME", s.getEndTime());
					json.put("NOTIFICATION_TIME", s.getNotificationTime());
					json.put("NAME", s.getName());
					json.put("COLOR_TEXT", s.getColorText());
					json.put("COLOR_PATTERN", s.getColorPattern());
					json.put("CONTENT", s.getContent());
					json.put("IS_ALLDAY", s.getIsAllDay());
					json.put("NOTIFICATION", s.getNotification());
					json.put("DATE", s.getDate());
					
					// insert
					new Webservice().connect(URL_UPDATE, json, "SHIFT_TRANSACTION");
				}
				catch (Exception ex)
				{
					Log.e(TAG, "TASK UPDATE: " + ex.getMessage());
				}
			}
			
			return null;
		}
	}
	
	/**
	 * 
	 * @author FDM17
	 *
	 * AsyncTask get all from table SHIFT
	 * and add them to `shifts`
	 */
	private class TaskDelete extends AsyncTask<String, Void, String>
	{

		@Override
		protected String doInBackground(String... params)
		{
			String id = params[0];
			
			if((id != null) && (!id.equals("")))
			{
				JSONObject json = new JSONObject();
				try
				{
					json.put("_ID", id);
					// insert
					new Webservice().connect(URL_DELETE, json, "SHIFT_TRANSACTION");
				}
				catch (Exception ex)
				{
					Log.e(TAG, "TASK DELETE: " + ex.getMessage());
				}
			}
			
			return null;
		}
	}

}
