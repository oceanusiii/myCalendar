package com.tiendd90.dataprovider;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

import com.tiendd90.model.ShiftMember;

public class TblShiftAndMember
{
	private static final String TAG = "TBL_SHIFT_AND_MEMBER_WEBSERVICE";
	private static final String URL_GETALL = 
			"http://192.168.1.117:80/webservice_android/calendar/SHIFT_AND_MEMBER_getAll.php";
	private static final String URL_GETBYSIDMID = 
			"http://192.168.1.117:80/webservice_android/calendar/SHIFT_AND_MEMBER_getByShiftIDAndMemberID.php";
	
	
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
	public ShiftMember getByID(String id) 
			throws InterruptedException, ExecutionException
	{
		return new TaskGetBySidAndMid().execute(id).get();
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
							URL_GETALL, new JSONObject(), "SHIFT_AND_MEMBER");
				
				if(jarr != null)
				{
					for(int i=0; i<jarr.length(); ++i)
					{
						JSONObject jobj = jarr.getJSONObject(i);
						
						ShiftMember sm = new ShiftMember();
						sm.setId(jobj.getString("_ID"));
						sm.setShiftId(jobj.getString("SHIFT_ID"));
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
			ShiftMember result = new ShiftMember();
			
			try
			{
				JSONObject json = new JSONObject();
				json.put("SHIFT_ID", params[0]);
				json.put("MEMBER_ID", params[1]);
				
				JSONArray jarr = new Webservice().connect(
							URL_GETBYSIDMID, json, "SHIFT_AND_MEMBER");
				
				if(jarr != null)
				{
					JSONObject jobj = jarr.getJSONObject(0);
					
					ShiftMember sm = new ShiftMember();
					sm.setId(jobj.getString("_ID"));
					sm.setShiftId(jobj.getString("SHIFT_ID"));
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
}
