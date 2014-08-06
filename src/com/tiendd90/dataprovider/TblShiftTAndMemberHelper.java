package com.tiendd90.dataprovider;

import java.util.ArrayList;

import com.tiendd90.model.ShiftMember;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TblShiftTAndMemberHelper extends DBConfig
{
	
	private SQLiteDatabase db;

	
	
	public TblShiftTAndMemberHelper(Context context)
	{
		super(context);
		this.db = getWritableDatabase();
	}
	
	
	
	public Cursor select()
	{
		return db.query(DBConfig.TB_SHIFTTRANSACTION_AND_MEMBER, 
				new String[] { 
				DBConfig.CL_ID_SM,
				DBConfig.CL_SHIFT_ID_SM,
				DBConfig.CL_MEMBER_ID_SM,
			}, 
			null, null, null, null, null);
	}
	
	
	
	/**
	 * Insert into PLAN_TRANSACTION
	 * @param p
	 * @return
	 */
	public long insert(ShiftMember sm)
	{
		ContentValues cv = new ContentValues();
		
		cv.put(DBConfig.CL_SHIFTTRANSACTION_ID_STM, sm.getShiftId());
		cv.put(DBConfig.CL_MEMBER_ID_STM, sm.getMemberId());
		
		return db.insert(
				DBConfig.TB_SHIFTTRANSACTION_AND_MEMBER, null, cv);
	}
	
	
	/**
	 * update
	 */
	public long update(String _id, ShiftMember sm)
	{
		ContentValues cv = new ContentValues();
		
		cv.put(DBConfig.CL_SHIFTTRANSACTION_ID_STM, sm.getShiftId());
		cv.put(DBConfig.CL_MEMBER_ID_STM, sm.getMemberId());
		
		return db.update(
				DBConfig.TB_SHIFTTRANSACTION_AND_MEMBER,
				cv, DBConfig.CL_ID_SM + " =? ",
				new String[] {_id} );
	}
	
	
	/**
	 * delete
	 */
	public long delete(String _id)
	{
		return db.delete(
				DBConfig.TB_SHIFTTRANSACTION_AND_MEMBER,
				DBConfig.CL_ID_STM + " =? ",
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
	 * Get All plan in PLAN_TRANSACTION
	 * @return
	 */
	public ArrayList<ShiftMember> getAll()
	{
		ArrayList<ShiftMember> result = new ArrayList<ShiftMember>();
		
		Cursor c = select();
		
		if(c.moveToFirst())
		{
			do
			{
				ShiftMember sm = new ShiftMember();
				// set
				sm.setId(c.getString(0));
				sm.setShiftId(c.getString(1));
				sm.setMemberId(c.getString(2));
				
				// add to list
				result.add(sm);
			}
			while(c.moveToNext());
		}
		
		c.close();
		return result;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<ShiftMember> getByShiftID(String sID)
	{
		ArrayList<ShiftMember> result = new ArrayList<ShiftMember>();
		
		String sql = "select * from " + DBConfig.TB_SHIFTTRANSACTION_AND_MEMBER 
				+ " where " + DBConfig.CL_SHIFTTRANSACTION_ID_STM + " =? ";
		Cursor c = db.rawQuery(sql, new String[] { sID });
		
		if(c.moveToFirst())
		{
			do
			{
				ShiftMember sm = new ShiftMember();
				// set
				sm.setId(c.getString(0));
				sm.setShiftId(c.getString(1));
				sm.setMemberId(c.getString(2));
				
				// add to list
				result.add(sm);
			}
			while(c.moveToNext());
		}
		
		c.close();
		return result;
	}
	

	
	/**
	 * Get ShiftMember by _id
	 */
	public ShiftMember getByID(String _id)
	{
		String sql = "select * from " + DBConfig.TB_SHIFTTRANSACTION_AND_MEMBER 
						+ " where " + DBConfig.CL_ID_STM + " =? ";
		Cursor c = db.rawQuery(sql, new String[] { _id });
		
		if(c.moveToFirst())
		{
			ShiftMember sm = new ShiftMember();
			// set
			sm.setId(c.getString(0));
			sm.setShiftId(c.getString(1));
			sm.setMemberId(c.getString(2));
			
			c.close();
			return sm;
		}
		
		c.close();
		return null;
	}
	
	
	
	
	/**
	 * Get ShiftMember by _id
	 */
	public ShiftMember getByShiftIDAndMemberID(String sId, String mID)
	{
		String sql = "select * from " + DBConfig.TB_SHIFTTRANSACTION_AND_MEMBER 
						+ " where " + DBConfig.CL_SHIFTTRANSACTION_ID_STM + " =? and " 
						+ DBConfig.CL_MEMBER_ID_STM + " =? ";
		Cursor c = db.rawQuery(sql, new String[] { sId, mID });
		
		if(c.moveToFirst())
		{
			ShiftMember sm = new ShiftMember();
			// set
			sm.setId(c.getString(0));
			sm.setShiftId(c.getString(1));
			sm.setMemberId(c.getString(2));
			
			c.close();
			return sm;
		}
		
		c.close();
		return null;
	}
	
	
}
