package com.tiendd90.dataprovider;

import java.util.ArrayList;

import com.tiendd90.model.Member;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TblMemberHelper extends DBConfig
{
	
	private SQLiteDatabase db;

	public TblMemberHelper(Context context)
	{
		super(context);
		this.db = getWritableDatabase();
	}
	
	
	public Cursor select()
	{
		return db.query(DBConfig.TB_MEMBER, 
				new String[] { 
				DBConfig.CL_ID_M,
				DBConfig.CL_NAME_M,
				DBConfig.CL_ON_OFF_M,
			}, 
			null, null, null, null, null);
	}
	
	
	
	/**
	 * Insert into PLAN_TRANSACTION
	 * @param p
	 * @return
	 */
	public long insert(Member m)
	{
		ContentValues cv = new ContentValues();
		
		cv.put(DBConfig.CL_NAME_M, m.getName());
		cv.put(DBConfig.CL_ON_OFF_M, m.getOnoff());
		
		return db.insert(DBConfig.TB_MEMBER, null, cv);
	}
	
	
	/**
	 * update
	 */
	public long update(String _id, Member m)
	{
		ContentValues cv = new ContentValues();
		
		cv.put(DBConfig.CL_NAME_M, m.getName());
		cv.put(DBConfig.CL_ON_OFF_M, m.getOnoff());
		
		return db.update(DBConfig.TB_MEMBER,
				cv, DBConfig.CL_ID_M + " =? ",
				new String[] {_id} );
	}
	
	
	/**
	 * delete
	 */
	public long delete(String _id)
	{
		return db.delete(DBConfig.TB_MEMBER,
				DBConfig.CL_ID_M + " =? ",
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
	public ArrayList<Member> getAll()
	{
		ArrayList<Member> result = new ArrayList<Member>();
		
		Cursor c = select();
		
		if(c.moveToFirst())
		{
			do
			{
				Member m = new Member();
				// set
				m.setId(c.getString(0));
				m.setName(c.getString(1));
				m.setOnoff(c.getString(2));
				
				// add to list
				result.add(m);
			}
			while(c.moveToNext());
		}
		
		c.close();
		return result;
	}
	

	
	/**
	 * Get all plan by _id
	 */
	public ArrayList<Member> getByID(String _id)
	{
		
		ArrayList<Member> result = new ArrayList<Member>();
		
		String sql = "select * from " + DBConfig.TB_MEMBER 
						+ " where " + DBConfig.CL_ID_M + " =? ";
		Cursor c = db.rawQuery(sql, new String[] { _id });
		
		if(c.moveToFirst())
		{
			do
			{
				Member m = new Member();
				// set
				m.setId(c.getString(0));
				m.setName(c.getString(1));
				m.setOnoff(c.getString(2));
				
				// add to list
				result.add(m);
			}
			while(c.moveToNext());
		}
		
		c.close();
		return result;
	}
	
}
