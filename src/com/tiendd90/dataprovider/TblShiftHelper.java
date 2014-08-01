package com.tiendd90.dataprovider;

import java.util.ArrayList;

import com.tiendd90.model.Shift;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TblShiftHelper extends DBConfig
{

	private SQLiteDatabase db;
	
	
	public TblShiftHelper(Context context)
	{
		super(context);
		db = getWritableDatabase();
	}
	
	
	public Cursor select()
	{
		return db.query(DBConfig.TB_SHIFT, 
				new String[] { DBConfig.CL_ID_S,
				DBConfig.CL_STARTTIME_S,
				DBConfig.CL_ENDTIME_S,
				DBConfig.CL_NOTIFICATION_TIME_S,
				DBConfig.CL_NAME_S,
				DBConfig.CL_COLOR_TEXT_S,
				DBConfig.CL_COLOR_PATTERN_S,
				DBConfig.CL_CONTENT_S,
				DBConfig.CL_IS_ALLDAY_S,
				DBConfig.CL_NOTIFICATION_S,
				DBConfig.CL_VIEW_ORDER_S,
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
		
		cv.put(DBConfig.CL_STARTTIME_S, s.getStartTime());
		cv.put(DBConfig.CL_ENDTIME_S, s.getEndTime());
		cv.put(DBConfig.CL_NOTIFICATION_TIME_S, s.getNotificationTime());
		cv.put(DBConfig.CL_NAME_S, s.getName());
		cv.put(DBConfig.CL_COLOR_TEXT_S, s.getColorText());
		cv.put(DBConfig.CL_COLOR_PATTERN_S, s.getColorPattern());
		cv.put(DBConfig.CL_CONTENT_S, s.getContent());
		cv.put(DBConfig.CL_IS_ALLDAY_S, s.getIsAllDay());
		cv.put(DBConfig.CL_NOTIFICATION_S, s.getNotification());
		cv.put(DBConfig.CL_VIEW_ORDER_S, s.getViewOrder());
		
		return db.insert(DBConfig.TB_SHIFT, null, cv);
	}
	
	
	
	/**
	 * update
	 */
	public long update(String _id, Shift s)
	{
		ContentValues cv = new ContentValues();
		
		cv.put(DBConfig.CL_STARTTIME_S, s.getStartTime());
		cv.put(DBConfig.CL_ENDTIME_S, s.getEndTime());
		cv.put(DBConfig.CL_NOTIFICATION_TIME_S, s.getNotificationTime());
		cv.put(DBConfig.CL_NAME_S, s.getName());
		cv.put(DBConfig.CL_COLOR_TEXT_S, s.getColorPattern());
		cv.put(DBConfig.CL_COLOR_PATTERN_S, s.getColorPattern());
		cv.put(DBConfig.CL_CONTENT_S, s.getContent());
		cv.put(DBConfig.CL_IS_ALLDAY_S, s.getIsAllDay());
		cv.put(DBConfig.CL_NOTIFICATION_S, s.getNotification());
		cv.put(DBConfig.CL_VIEW_ORDER_S, s.getViewOrder());
		
		return db.update(DBConfig.TB_SHIFT,
				cv, DBConfig.CL_ID_S + " =? ",
				new String[] {_id} );
	}
	
	
	
	/**
	 * delete
	 */
	public long delete(String _id)
	{
		return db.delete(DBConfig.TB_SHIFT,
				DBConfig.CL_ID_S + " =? ",
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
				s.setViewOrder(c.getString(9));
				
				// add to list
				result.add(s);
			}
			while(c.moveToNext());
		}
		
		c.close();
		
		return result;
	}
	
	
	
	/**
	 * Get all shift by _id
	 */
	public Shift getByID(String _id)
	{
		String sql = "select * from " + DBConfig.TB_SHIFT 
						+ " where " + DBConfig.CL_ID_S + " =? ";

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
			s.setViewOrder(c.getString(9));
			
			c.close();
			return s;
		}
		
		c.close();
		return null;
	}

}
