package com.tiendd90.dataprovider;

import java.util.ArrayList;

import com.tiendd90.model.Shift;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TblShiftTHelper extends DBConfig
{

	private SQLiteDatabase db;
	
	
	
	public TblShiftTHelper(Context context)
	{
		super(context);
		db = getWritableDatabase();
	}
	
	
	
	public Cursor select()
	{
		return db.query(DBConfig.TB_SHIFT_T, 
				new String[] { DBConfig.CL_ID_ST,
				DBConfig.CL_STARTTIME_ST,
				DBConfig.CL_ENDTIME_ST,
				DBConfig.CL_NOTIFICATION_TIME_ST,
				DBConfig.CL_NAME_ST,
				DBConfig.CL_COLOR_TEXT_ST,
				DBConfig.CL_COLOR_PATTERN_ST,
				DBConfig.CL_CONTENT_ST,
				DBConfig.CL_IS_ALLDAY_ST,
				DBConfig.CL_NOTIFICATION_ST,
				DBConfig.CL_DATE_ST,
			}, 
			null, null, null, null, null);
	}
	
	
	/**
	 * Insert into SHIFT
	 * @param p
	 * @return
	 */
	public long insert(Shift s)
	{
		ContentValues cv = new ContentValues();
		
		cv.put(DBConfig.CL_STARTTIME_ST, s.getStartTime());
		cv.put(DBConfig.CL_ENDTIME_ST, s.getEndTime());
		cv.put(DBConfig.CL_NOTIFICATION_TIME_ST, s.getNotificationTime());
		cv.put(DBConfig.CL_NAME_ST, s.getName());
		cv.put(DBConfig.CL_COLOR_TEXT_ST, s.getColorPattern());
		cv.put(DBConfig.CL_COLOR_PATTERN_ST, s.getColorPattern());
		cv.put(DBConfig.CL_CONTENT_ST, s.getContent());
		cv.put(DBConfig.CL_IS_ALLDAY_ST, s.getIsAllDay());
		cv.put(DBConfig.CL_NOTIFICATION_ST, s.getNotification());
		cv.put(DBConfig.CL_DATE_ST, s.getDate());
		
		return db.insert(DBConfig.TB_SHIFT_T, null, cv);
	}
	
	
	
	/**
	 * update
	 */
	public long update(String _id, Shift s)
	{
		ContentValues cv = new ContentValues();
		
		cv.put(DBConfig.CL_STARTTIME_ST, s.getStartTime());
		cv.put(DBConfig.CL_ENDTIME_ST, s.getEndTime());
		cv.put(DBConfig.CL_NOTIFICATION_TIME_ST, s.getNotificationTime());
		cv.put(DBConfig.CL_NAME_ST, s.getName());
		cv.put(DBConfig.CL_COLOR_TEXT_ST, s.getColorPattern());
		cv.put(DBConfig.CL_COLOR_PATTERN_ST, s.getColorPattern());
		cv.put(DBConfig.CL_CONTENT_ST, s.getContent());
		cv.put(DBConfig.CL_IS_ALLDAY_ST, s.getIsAllDay());
		cv.put(DBConfig.CL_NOTIFICATION_ST, s.getNotification());
		cv.put(DBConfig.CL_DATE_ST, s.getDate());
		
		return db.update(DBConfig.TB_SHIFT_T,
				cv, DBConfig.CL_ID_ST + " =? ",
				new String[] {_id} );
	}
	
	
	
	/**
	 * delete
	 */
	public long delete(String _id)
	{
		return db.delete(DBConfig.TB_SHIFT_T,
				DBConfig.CL_ID_ST + " =? ",
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
	 * Get all shift
	 * @return
	 */
	public ArrayList<Shift> getAll()
	{
		ArrayList<Shift> result = new ArrayList<Shift>();
		
		Cursor c = select();
		
		if(c.moveToFirst())
		{
			do
			{
				Shift s = new Shift();
				// set
				s.setId(c.getString(0));
				s.setStartTime(c.getString(1));
				s.setEndTime(c.getString(2));
				s.setNotificationTime(c.getString(3));
				s.setName(c.getString(4));
				s.setColorText(c.getString(5));
				s.setColorPattern(c.getString(6));
				s.setContent(c.getString(7));
				s.setIsAllDay(c.getString(8));
				s.setNotification(c.getString(9));
				s.setDate(c.getString(9));
				
				// add to list
				result.add(s);
			}
			while(c.moveToNext());
		}
		
		c.close();
		return result;
	}
	
	
	
	/**
	 * Get all shift by DATE
	 */
	public ArrayList<Shift> getByDate(String date)
	{
		ArrayList<Shift> result = new ArrayList<Shift>();
		
		String sql = "select * from " + DBConfig.TB_SHIFT_T 
						+ " where " + DBConfig.CL_DATE_ST + " =? ";

		Cursor c = db.rawQuery(sql, new String[] { date });
		
		
		if(c.moveToFirst())
		{
			do
			{
				Shift s = new Shift();
				// set
				s.setId(c.getString(0));
				s.setStartTime(c.getString(1));
				s.setEndTime(c.getString(2));
				s.setNotificationTime(c.getString(3));
				s.setName(c.getString(4));
				s.setColorText(c.getString(5));
				s.setColorPattern(c.getString(6));
				s.setContent(c.getString(7));
				s.setIsAllDay(c.getString(8));
				s.setNotification(c.getString(9));
				s.setDate(c.getString(9));
				
				result.add(s);
			}
			while(c.moveToNext());
			
			c.close();
		}
		
		c.close();
		return result;
	}
	
	
	
	/**
	 * Get all shift by _id
	 */
	public Shift getByID(String _id)
	{
		String sql = "select * from " + DBConfig.TB_SHIFT_T 
						+ " where " + DBConfig.CL_ID_ST + " =? ";

		Cursor c = db.rawQuery(sql, new String[] { _id });
		
		
		if(c.moveToFirst())
		{
			Shift s = new Shift();
			// set
			s.setId(c.getString(0));
			s.setStartTime(c.getString(1));
			s.setEndTime(c.getString(2));
			s.setNotificationTime(c.getString(3));
			s.setName(c.getString(4));
			s.setColorText(c.getString(5));
			s.setColorPattern(c.getString(6));
			s.setContent(c.getString(7));
			s.setIsAllDay(c.getString(8));
			s.setNotification(c.getString(9));
			s.setDate(c.getString(9));
			
			c.close();
			return s;
		}
		
		c.close();
		return null;
	}
	
}
