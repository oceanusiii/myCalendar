package com.tiendd90.dataprovider;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

import com.tiendd90.model.Member;

public class TblMember
{
	
	private static final String TAG = "TBL_MEMBER_WEBSERVICE";
	private static final String URL_GETALL = 
			"http://192.168.1.117:80/webservice_android/calendar/MEMBER_getAll.php";
	private static final String URL_GETBYID = 
			"http://192.168.1.117:80/webservice_android/calendar/MEMBER_getByID.php";
	
	
	/**
	 * 
	 * @return
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public ArrayList<Member> getAll() 
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
	public Member getByID(String id) 
			throws InterruptedException, ExecutionException
	{
		return new TaskGetByID().execute(id).get();
	}
	
	
	
	
	
	/**___________________ASYNCTASK___________________*/
	/**
	 * 
	 * @author FDM17
	 *
	 * 
	 */
	private class TaskGetAll extends AsyncTask<String, String, ArrayList<Member>>
	{
		@Override
		protected ArrayList<Member> doInBackground(String... params)
		{
			ArrayList<Member> result = new ArrayList<Member>();
			
			try
			{
				JSONArray jarr = new Webservice().connect(
							URL_GETALL, new JSONObject(), "MEMBER");
				
				if(jarr != null)
				{
					for(int i=0; i<jarr.length(); ++i)
					{
						JSONObject jobj = jarr.getJSONObject(i);
						
						Member m = new Member();
						m.setId(jobj.getString("_ID"));
						m.setName(jobj.getString("NAME"));
						m.setOnoff(jobj.getString("ON_OFF"));
						
						result.add(m);
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
	private class TaskGetByID extends AsyncTask<String, String, Member>
	{

		@Override
		protected Member doInBackground(String... params)
		{
			Member result = new Member();
			
			try
			{
				JSONObject json = new JSONObject();
				json.put("_ID", params[0]);
				
				JSONArray jarr = new Webservice().connect(
							URL_GETBYID, json, "MEMBER");
				
				if(jarr != null)
				{
					JSONObject jobj = jarr.getJSONObject(0);
					
					Member m = new Member();
					m.setId(jobj.getString("_ID"));
					m.setName(jobj.getString("NAME"));
					m.setOnoff(jobj.getString("ON_OFF"));
					
					result = m;
				}
			}
			catch (Exception ex)
			{
				Log.e(TAG, "TASK GETBYID: " + ex.getMessage());
			}
			
			return result;
		}
	}
}
