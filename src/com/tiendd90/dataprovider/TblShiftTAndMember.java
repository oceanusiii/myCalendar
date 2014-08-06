package com.tiendd90.dataprovider;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

import com.tiendd90.model.ShiftMember;

public class TblShiftTAndMember
{
	private static final String TAG = "TBL_SHIFT_TRANSACTION_AND_MEMBER_WEBSERVICE";
	private static final String URL_GETALL = 
			"http://192.168.1.117:80/webservice_android/calendar/SHIFT_TRANSACTION_AND_MEMBER_getAll.php";
	private static final String URL_GETBYSIDMID = 
			"http://192.168.1.117:80/webservice_android/calendar/SHIFT_TRANSACTION_AND_MEMBER_getByShiftIDAndMemberID.php";
	private static final String URL_GETBYSID = 
			"http://192.168.1.117:80/webservice_android/calendar/SHIFT_TRANSACTION_AND_MEMBER_getByShiftID.php";
	private static final String URL_INSERT = 
			"http://192.168.1.117:80/webservice_android/calendar/SHIFT_TRANSACTION_AND_MEMBER_insert.php";
	private static final String URL_UPDATE = 
			"http://192.168.1.117:80/webservice_android/calendar/SHIFT_TRANSACTION_AND_MEMBER_update.php";
	private static final String URL_DELETE = 
			"http://192.168.1.117:80/webservice_android/calendar/SHIFT_TRANSACTION_AND_MEMBER_delete.php";
	
	
	/**
	 * 
	 * @return
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public ArrayList<ShiftMember> getAll() 
			throws InterruptedException, ExecutionException
	{
		return new TaskGetAll().execute().get();
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public ShiftMember getBySIDAndMID(String sid, String mid) 
			throws InterruptedException, ExecutionException
	{
		return new TaskGetBySidAndMid().execute(sid, mid).get();
	}
	
	/**
	 * 
	 * @param sid
	 * @param mid
	 * @return
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public ArrayList<ShiftMember> getBySID(String sid) 
			throws InterruptedException, ExecutionException
	{
		return new TaskGetBySID().execute(sid).get();
	}
	
	/**
	 * 
	 * @param p
	 */
	public void insert(ShiftMember sm)
	{
		new TaskInsert().execute(sm);
	}
	
	/**
	 * 
	 * @param p
	 */
	public void update(ShiftMember sm)
	{
		new TaskUpdate().execute(sm);
	}
	
	/**
	 * 
	 * @param id
	 */
	public void delete(String id)
	{
		new TaskDelete().execute(id);
	}
	
	
	
	
	/**___________________ASYNCTASK___________________*/
	/**
	 * 
	 * @author FDM17
	 *
	 * 
	 */
	private class TaskGetAll extends AsyncTask<String, String, ArrayList<ShiftMember>>
	{
		@Override
		protected ArrayList<ShiftMember> doInBackground(String... params)
		{
			ArrayList<ShiftMember> result = new ArrayList<ShiftMember>();
			
			try
			{
				JSONArray jarr = new Webservice().connect(
							URL_GETALL, new JSONObject(),
							"SHIFT_TRANSACTION_AND_MEMBER");
				
				if(jarr != null)
				{
					for(int i=0; i<jarr.length(); ++i)
					{
						JSONObject jobj = jarr.getJSONObject(i);
						
						ShiftMember sm = new ShiftMember();
						sm.setId(jobj.getString("_ID"));
						sm.setShiftId(jobj.getString("SHIFTTRANSACTION_ID"));
						sm.setMemberId(jobj.getString("MEMBER_ID"));
						
						result.add(sm);
					}
				}
			}
			catch (Exception ex)
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
	private class TaskGetBySidAndMid extends AsyncTask<String, String, ShiftMember>
	{
		@Override
		protected ShiftMember doInBackground(String... params)
		{
			ShiftMember result = null;
			
			try
			{
				JSONObject json = new JSONObject();
				json.put("SHIFTTRANSACTION_ID", params[0]);
				json.put("MEMBER_ID", params[1]);
				
				JSONArray jarr = new Webservice().connect(
							URL_GETBYSIDMID, json,
							"SHIFT_TRANSACTION_AND_MEMBER");
				
				if(jarr != null && jarr.length() > 0)
				{
					JSONObject jobj = jarr.getJSONObject(0);
					
					ShiftMember sm = new ShiftMember();
					sm.setId(jobj.getString("_ID"));
					sm.setShiftId(jobj.getString("SHIFTTRANSACTION_ID"));
					sm.setMemberId(jobj.getString("MEMBER_ID"));
					
					result = sm;
				}
			}
			catch (Exception ex)
			{
				Log.e(TAG, "TASK GETBYSIDANDMID: " + ex.getMessage());
			}
			
			return result;
		}
	}
	
	/**
	 * 
	 * @author FDM17
	 *
	 */
	private class TaskGetBySID extends AsyncTask<String, String, ArrayList<ShiftMember>>
	{
		@Override
		protected ArrayList<ShiftMember> doInBackground(String... params)
		{
			ArrayList<ShiftMember> result = new ArrayList<ShiftMember>();
			
			try
			{
				JSONObject json = new JSONObject();
				json.put("SHIFTTRANSACTION_ID", params[0]);
				JSONArray jarr = new Webservice().connect(
							URL_GETBYSID, json,
							"SHIFT_TRANSACTION_AND_MEMBER");
				
				if(jarr != null)
				{
					for(int i=0; i<jarr.length(); ++i)
					{
						JSONObject jobj = jarr.getJSONObject(i);
						
						ShiftMember sm = new ShiftMember();
						sm.setId(jobj.getString("_ID"));
						sm.setShiftId(jobj.getString("SHIFTTRANSACTION_ID"));
						sm.setMemberId(jobj.getString("MEMBER_ID"));
						
						result.add(sm);
					}
				}
			}
			catch (Exception ex)
			{
				Log.e(TAG, "TASK GETBYID: " + ex.getMessage());
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
	private class TaskInsert extends AsyncTask<ShiftMember, Void, String>
	{

		@Override
		protected String doInBackground(ShiftMember... params)
		{
			ShiftMember sm = params[0];
			
			if(sm != null)
			{
				JSONObject json = new JSONObject();
				try
				{
					json.put("_ID", sm.getId());
					json.put("SHIFTTRANSACTION_ID", sm.getShiftId());
					json.put("MEMBER_ID", sm.getMemberId());
					
					// insert
					new Webservice().connect(URL_INSERT, json,
								"SHIFT_TRANSACTION_AND_MEMBER");
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
	private class TaskUpdate extends AsyncTask<ShiftMember, Void, String>
	{

		@Override
		protected String doInBackground(ShiftMember... params)
		{
			ShiftMember sm = params[0];
			
			if(sm != null)
			{
				JSONObject json = new JSONObject();
				try
				{
					json.put("_ID", sm.getId());
					json.put("SHIFTTRANSACTION_ID", sm.getShiftId());
					json.put("MEMBER_ID", sm.getMemberId());
					
					// insert
					new Webservice().connect(URL_UPDATE, json, 
								"SHIFT_TRANSACTION_AND_MEMBER");
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
					new Webservice().connect(URL_DELETE, json,
								"SHIFT_TRANSACTION_AND_MEMBER");
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
