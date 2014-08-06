package com.tiendd90.dataprovider;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

import com.tiendd90.model.Plan;



public class TblPlanT
{
	public static final String TAG = "TBL_PLANTRANSACTION_WEBSERVICE";
	private static final String URL_GETALL = 
			"http://192.168.1.117:80/webservice_android/calendar/PLAN_TRANSACTION_getAll.php";
	private static final String URL_GETBYDATE = 
			"http://192.168.1.117:80/webservice_android/calendar/PLAN_TRANSACTION_getByDate.php";
	private static final String URL_INSERT = 
			"http://192.168.1.117:80/webservice_android/calendar/PLAN_TRANSACTION_insert.php";
	private static final String URL_UPDATE = 
			"http://192.168.1.117:80/webservice_android/calendar/PLAN_TRANSACTION_update.php";
	private static final String URL_DELETE = 
			"http://192.168.1.117:80/webservice_android/calendar/PLAN_TRANSACTION_delete.php";
	
	
	
	/**
	 * Get all from table SHIFT
	 * @return
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public ArrayList<Plan> getAll() 
			throws InterruptedException, ExecutionException
	{
		return new TaskGetAll().execute().get();
	}
	
	public ArrayList<Plan> getByDate(String date) 
			throws InterruptedException, ExecutionException
	{
		return new TaskGetByDate().execute(date).get();
	}
	
	/**
	 * 
	 * @param p
	 */
	public void insert(Plan p)
	{
		new TaskInsert().execute(p);
	}
	
	/**
	 * 
	 * @param p
	 */
	public void update(Plan p)
	{
		new TaskUpdate().execute(p);
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
	private class TaskGetAll extends AsyncTask<String, String, ArrayList<Plan>>
	{
		@Override
		protected ArrayList<Plan> doInBackground(String... params)
		{
			ArrayList<Plan> result = new ArrayList<Plan>();
			
			try
			{
				// get data from server
				JSONArray jarr = new Webservice().connect(
						URL_GETALL, new JSONObject(), "PLAN_TRANSACTION");
				
				if(jarr != null)
				{
					for(int i=0; i<jarr.length(); ++i)
					{
						JSONObject jobj = jarr.getJSONObject(i);
						
						Plan p = new Plan();
						p.setId(jobj.getString("_ID"));
						p.setStartTime(jobj.getString("START_TIME"));
						p.setEndTime(jobj.getString("END_TIME"));
						p.setNotificationTime(jobj.getString("NOTIFICATION_TIME"));
						p.setTitle(jobj.getString("TITLE"));
						p.setIcon(jobj.getString("ICON"));
						p.setContent(jobj.getString("CONTENT"));
						p.setStartTimeSetted(jobj.getString("IS_STARTTIME_SETTED"));
						p.setNotification(jobj.getString("NOTIFICATION"));
						p.setDate(jobj.getString("DATE"));
						
						result.add(p);
					}
				}
			}
			catch(Exception ex)
			{
				Log.e(TAG, "Task get all: " + ex.getMessage());
			}
			
			return result;
		}
	}
	
	/**
	 * 
	 * @author FDM17
	 *
	 */
	private class TaskGetByDate extends AsyncTask<String, String, ArrayList<Plan>>
	{
		@Override
		protected ArrayList<Plan> doInBackground(String... params)
		{
			ArrayList<Plan> result = new ArrayList<Plan>();
			String date = params[0];
			
			try
			{
				JSONObject json = new JSONObject();
				json.put("DATE", date);
				
				// get data from server
				JSONArray jarr = new Webservice().connect(
						URL_GETBYDATE, json, "PLAN_TRANSACTION");
				
				if(jarr != null)
				{
					for(int i=0; i<jarr.length(); ++i)
					{
						JSONObject jobj = jarr.getJSONObject(i);
						
						Plan p = new Plan();
						p.setId(jobj.getString("_ID"));
						p.setStartTime(jobj.getString("START_TIME"));
						p.setEndTime(jobj.getString("END_TIME"));
						p.setNotificationTime(jobj.getString("NOTIFICATION_TIME"));
						p.setTitle(jobj.getString("TITLE"));
						p.setIcon(jobj.getString("ICON"));
						p.setContent(jobj.getString("CONTENT"));
						p.setStartTimeSetted(jobj.getString("IS_STARTTIME_SETTED"));
						p.setNotification(jobj.getString("NOTIFICATION"));
						p.setDate(jobj.getString("DATE"));
						
						result.add(p);
					}
				}
			}
			catch(Exception ex)
			{
				Log.e(TAG, "Task get all: " + ex.getMessage());
			}
			
			return result;
		}
	}
	
	
	/**
	 * 
	 * @author FDM17
	 *
	 * AsyncTask get all from table SHIFT
	 * and add them to `shifts`
	 */
	private class TaskInsert extends AsyncTask<Plan, Void, String>
	{

		@Override
		protected String doInBackground(Plan... params)
		{
			Plan p = params[0];
			
			if(p != null)
			{
				JSONObject json = new JSONObject();
				try
				{
					json.put("_ID", p.getId());
					json.put("START_TIME", p.getStartTime());
					json.put("END_TIME", p.getEndTime());
					json.put("NOTIFICATION_TIME", p.getNotificationTime());
					json.put("TITLE", p.getTitle());
					json.put("ICON", p.getIcon());
					json.put("CONTENT", p.getContent());
					json.put("IS_STARTTIME_SETTED", p.getStartTimeSetted());
					json.put("NOTIFICATION", p.getNotification());
					json.put("DATE", p.getDate());
					
					// insert
					new Webservice().connect(URL_INSERT, json, "PLAN_TRANSACTION");
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
	private class TaskUpdate extends AsyncTask<Plan, Void, String>
	{

		@Override
		protected String doInBackground(Plan... params)
		{
			Plan p = params[0];
			
			if(p != null)
			{
				JSONObject json = new JSONObject();
				try
				{
					json.put("_ID", p.getId());
					json.put("START_TIME", p.getStartTime());
					json.put("END_TIME", p.getEndTime());
					json.put("NOTIFICATION_TIME", p.getNotificationTime());
					json.put("TITLE", p.getTitle());
					json.put("ICON", p.getIcon());
					json.put("CONTENT", p.getContent());
					json.put("IS_STARTTIME_SETTED", p.getStartTimeSetted());
					json.put("NOTIFICATION", p.getNotification());
					json.put("DATE", p.getDate());
					
					// insert
					new Webservice().connect(URL_UPDATE, json, "PLAN_TRANSACTION");
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
					new Webservice().connect(URL_DELETE, json, "PLAN_TRANSACTION");
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
