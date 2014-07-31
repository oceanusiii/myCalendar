package com.tiendd90.dataprovider;

import java.util.ArrayList;

import com.tiendd90.model.Plan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;



public class TblPlanHelper extends DBConfig
{

	private SQLiteDatabase db;
	
	
	public TblPlanHelper(Context context)
	{
		super(context);
		db = super.getWritableDatabase();
	}
	
	
	public Cursor select()
	{
		return db.query(DBConfig.TB_PLAN, 
				new String[] { DBConfig.CL_ID,
				DBConfig.CL_STARTTIME,
				DBConfig.CL_ENDTIME,
				DBConfig.CL_NOTIFICATION_TIME,
				DBConfig.CL_TITLE,
				DBConfig.CL_ICON,
				DBConfig.CL_CONTENT,
				DBConfig.CL_STARTTIME_SETTED,
				DBConfig.CL_NOTIFICATION,
				DBConfig.CL_PATTERN_NAME,
			}, 
			null, null, null, null, null);
	}
	
	
	
	/**
	 * Insert into PLAN
	 * @param p
	 * @return
	 */
	public long insert(Plan p)
	{
		ContentValues cv = new ContentValues();
		
		cv.put(DBConfig.CL_STARTTIME, p.getStartTime());
		cv.put(DBConfig.CL_ENDTIME, p.getEndTime());
		cv.put(DBConfig.CL_NOTIFICATION_TIME, p.getNotificationTime());
		cv.put(DBConfig.CL_TITLE, p.getTitle());
		cv.put(DBConfig.CL_ICON, p.getIcon());
		cv.put(DBConfig.CL_CONTENT, p.getContent());
		cv.put(DBConfig.CL_STARTTIME_SETTED, p.getStartTimeSetted());
		cv.put(DBConfig.CL_NOTIFICATION, p.getNotification());
		cv.put(DBConfig.CL_PATTERN_NAME, p.getPatternName());
		
		return db.insert(DBConfig.TB_PLAN, null, cv);
	}
	
	
	
	/**
	 * update
	 */
	public long update(String _id, Plan p)
	{
		ContentValues cv = new ContentValues();
		
		cv.put(DBConfig.CL_STARTTIME, p.getStartTime());
		cv.put(DBConfig.CL_ENDTIME, p.getEndTime());
		cv.put(DBConfig.CL_NOTIFICATION_TIME, p.getNotificationTime());
		cv.put(DBConfig.CL_TITLE, p.getTitle());
		cv.put(DBConfig.CL_ICON, p.getIcon());
		cv.put(DBConfig.CL_CONTENT, p.getContent());
		cv.put(DBConfig.CL_STARTTIME_SETTED, p.getStartTimeSetted());
		cv.put(DBConfig.CL_NOTIFICATION, p.getNotification());
		cv.put(DBConfig.CL_PATTERN_NAME, p.getPatternName());
		
		return db.update(DBConfig.TB_PLAN,
				cv, DBConfig.CL_ID + " =? ",
				new String[] {_id} );
	}
	
	
	
	/**
	 * delete
	 */
	public long delete(String _id)
	{
		return db.delete(DBConfig.TB_PLAN,
				DBConfig.CL_ID + " =? ",
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
				p.setPatternName(c.getString(9));
				
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
	public Plan getByID(String _id)
	{
		String sql = "select * from " + DBConfig.TB_PLAN 
						+ " where " + DBConfig.CL_ID + " =? ";

		Cursor c = db.rawQuery(sql, new String[] { _id });
		
		
		if(c.moveToFirst())
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
			p.setPatternName(c.getString(9));
			
			c.close();
			return p;
		}
		
		
		c.close();
		return null;
	}

}
