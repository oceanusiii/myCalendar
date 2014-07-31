package com.tiendd90.dataprovider;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tiendd90.model.Plan;


public class TblPlanTHelper extends DBConfig
{
	
	private SQLiteDatabase db;
	
	
	public TblPlanTHelper(Context context)
	{
		super(context);
		db = super.getWritableDatabase();
	}
	
	
	public Cursor select()
	{
		return db.query(DBConfig.TB_PLAN_T, 
				new String[] { DBConfig.CL_ID_T,
				DBConfig.CL_STARTTIME_T,
				DBConfig.CL_ENDTIME_T,
				DBConfig.CL_NOTIFICATION_TIME_T,
				DBConfig.CL_TITLE_T,
				DBConfig.CL_ICON_T,
				DBConfig.CL_CONTENT_T,
				DBConfig.CL_STARTTIME_SETTED_T,
				DBConfig.CL_NOTIFICATION_T,
				DBConfig.CL_DATE_T,
			}, 
			null, null, null, null, null);
	}
	
	
	
	/**
	 * Insert into PLAN_TRANSACTION
	 * @param p
	 * @return
	 */
	public long insert(Plan p)
	{
		ContentValues cv = new ContentValues();
		
		cv.put(DBConfig.CL_STARTTIME_T, p.getStartTime());
		cv.put(DBConfig.CL_ENDTIME_T, p.getEndTime());
		cv.put(DBConfig.CL_NOTIFICATION_TIME_T, p.getNotificationTime());
		cv.put(DBConfig.CL_TITLE_T, p.getTitle());
		cv.put(DBConfig.CL_ICON_T, p.getIcon());
		cv.put(DBConfig.CL_CONTENT_T, p.getContent());
		cv.put(DBConfig.CL_STARTTIME_SETTED_T, p.getStartTimeSetted());
		cv.put(DBConfig.CL_NOTIFICATION_T, p.getNotification());
		cv.put(DBConfig.CL_DATE_T, p.getDate());
		
		return db.insert(DBConfig.TB_PLAN_T, null, cv);
	}
	
	
	/**
	 * update
	 */
	public long update(String _id, Plan p)
	{
		ContentValues cv = new ContentValues();
		
		cv.put(DBConfig.CL_STARTTIME_T, p.getStartTime());
		cv.put(DBConfig.CL_ENDTIME_T, p.getEndTime());
		cv.put(DBConfig.CL_NOTIFICATION_TIME_T, p.getNotificationTime());
		cv.put(DBConfig.CL_TITLE_T, p.getTitle());
		cv.put(DBConfig.CL_ICON_T, p.getIcon());
		cv.put(DBConfig.CL_CONTENT_T, p.getContent());
		cv.put(DBConfig.CL_STARTTIME_SETTED_T, p.getStartTimeSetted());
		cv.put(DBConfig.CL_NOTIFICATION_T, p.getNotification());
		cv.put(DBConfig.CL_DATE_T, p.getDate());
		
		return db.update(DBConfig.TB_PLAN_T,
				cv, DBConfig.CL_ID_T + " =? ",
				new String[] {_id} );
	}
	
	
	/**
	 * delete
	 */
	public long delete(String _id)
	{
		return db.delete(DBConfig.TB_PLAN_T,
				DBConfig.CL_ID_T + " =? ",
				new String[] {_id} );
	}
	
	
	/**
	 * count all record
	 */
	public int countAll()
	{
		Cursor c = select();
		int n = c.getColumnCount();
		c.close();
		return n;
	}
	
	
	/**
	 * count by date
	 */
	public int countByDate(String date)
	{
		return getByDate(date).size();
	}
	
	
	/**
	 * Get All plan in PLAN_TRANSACTION
	 * @return
	 */
	public ArrayList<Plan> getAll()
	{
		
		ArrayList<Plan> result = new ArrayList<Plan>();
		
		Cursor c = select();
		
		if(c.moveToFirst())
		{
			do
			{
				Plan p = new Plan();
				// set
				p.setId(c.getString(0));
				p.setStartTime(c.getString(1));
				p.setEndTime(c.getString(2));
				p.setNotificationTime(c.getString(3));
				p.setTitle(c.getString(4));
				p.setIcon(c.getString(5));
				p.setContent(c.getString(6));
				p.setStartTimeSetted(c.getString(7));
				p.setNotification(c.getString(8));
				p.setDate(c.getString(9));
				
				// add to list
				result.add(p);
			}
			while(c.moveToNext());
		}
		
		c.close();
		
		return result;
	}
	

	/**
	 * Get all plan by date
	 */
	public ArrayList<Plan> getByDate(String date)
	{
		
		ArrayList<Plan> result = new ArrayList<Plan>();
		
		String sql = "select * from " + DBConfig.TB_PLAN_T 
						+ " where " + DBConfig.CL_DATE_T + " =? ";
		//Log.e("test sql", sql+date);
		Cursor c = db.rawQuery(sql, new String[] { date });
		
		if(c.moveToFirst())
		{
			do
			{
				Plan p = new Plan();
				// set
				p.setId(c.getString(0));
				p.setStartTime(c.getString(1));
				p.setEndTime(c.getString(2));
				p.setNotificationTime(c.getString(3));
				p.setTitle(c.getString(4));
				p.setIcon(c.getString(5));
				p.setContent(c.getString(6));
				p.setStartTimeSetted(c.getString(7));
				p.setNotification(c.getString(8));
				p.setDate(c.getString(9));
				
				// add to list
				result.add(p);
			}
			while(c.moveToNext());
		}
		
		c.close();
		return result;
		
	}
	
	
	/**
	 * Get all plan by _id
	 */
	public ArrayList<Plan> getByID(String _id)
	{
		
		ArrayList<Plan> result = new ArrayList<Plan>();
		
		String sql = "select * from " + DBConfig.TB_PLAN_T 
						+ " where " + DBConfig.CL_ID_T + " =? ";
		//Log.e("test sql", sql+date);
		Cursor c = db.rawQuery(sql, new String[] { _id });
		
		if(c.moveToFirst())
		{
			do
			{
				Plan p = new Plan();
				// set
				p.setId(c.getString(0));
				p.setStartTime(c.getString(1));
				p.setEndTime(c.getString(2));
				p.setNotificationTime(c.getString(3));
				p.setTitle(c.getString(4));
				p.setIcon(c.getString(5));
				p.setContent(c.getString(6));
				p.setStartTimeSetted(c.getString(7));
				p.setNotification(c.getString(8));
				p.setDate(c.getString(9));
				
				// add to list
				result.add(p);
			}
			while(c.moveToNext());
		}
		
		c.close();
		return result;
	}
	
}
