package com.tiendd90.dataprovider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Webservice
{
	private static final String TAG = "WEBSERVICE";
	private static final int TIMEOUT = 30000;
	
	/**
	 * 
	 * @param url
	 * @param json
	 * @param jsonName
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws JSONException 
	 */
	public JSONArray connect(
			String url, JSONObject json, String jsonName) 
					throws ClientProtocolException, IOException, JSONException
	{
		JSONArray result = null;
		
		HttpParams params = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(params, TIMEOUT);
		HttpConnectionParams.setSoTimeout(params, TIMEOUT);
		
		// HTTP Client
		HttpClient client = new DefaultHttpClient();
		
		// POST
		HttpPost postRequest = new HttpPost(url);
		// set Entity
		postRequest.setEntity(new ByteArrayEntity(
					json.toString().getBytes("UTF-8")));
		
		// Response
		HttpResponse response = client.execute(postRequest);
		
		// get string Result 
		String r = inputStreamToString(response.getEntity().getContent());
		Log.d(TAG, "Result: " + r);
		
		JSONObject jobj = new JSONObject(r);
		result = jobj.getJSONArray(jsonName);
		
		return result;
	}
	
	
	/**
	 * 
	 * @param is
	 * @return
	 * @throws IOException 
	 */
	private String inputStreamToString(InputStream is) 
			throws IOException
	{
		StringBuilder sbuilder = new StringBuilder();
		BufferedReader bReader = new BufferedReader(
							new InputStreamReader(is));
		
		String row = null;
		while( (row = bReader.readLine()) != null)
		{
			sbuilder.append(row + "\n");
		}
			
		return sbuilder.toString();
	}
}
