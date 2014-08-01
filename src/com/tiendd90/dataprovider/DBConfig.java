package com.tiendd90.dataprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBConfig extends SQLiteOpenHelper
{

	public static final String 	DB_NAME = "myCalendar.db";
	public static final int 		DB_VERSION = 1;
	
	// table tblPlan
	// table_name
	public static final String TB_PLAN = "PLAN";
	// column_name
	public static final String CL_ID 					= "_id";
	public static final String CL_STARTTIME 			= "STARTTIME";
	public static final String CL_ENDTIME 				= "ENDTIME";
	public static final String CL_NOTIFICATION_TIME 	= "NOTIFICATION_TIME";
	public static final String CL_TITLE 				= "TITLE";
	public static final String CL_ICON 				= "ICON";
	public static final String CL_CONTENT 				= "CONTENT";
	public static final String CL_STARTTIME_SETTED 	= "STARTTIME_SETTED";
	public static final String CL_NOTIFICATION 		= "NOTIFICATION";
	public static final String CL_PATTERN_NAME 		= "PATTERN_NAME";
	
	// table tblPlanTransaction
	// table_name
	public static final String TB_PLAN_T		 		= "PLAN_TRANSACTION";
	// column_name
	public static final String CL_ID_T					= "_id";
	public static final String CL_STARTTIME_T 			= "STARTTIME";
	public static final String CL_ENDTIME_T			= "ENDTIME";
	public static final String CL_NOTIFICATION_TIME_T 	= "NOTIFICATION_TIME";
	public static final String CL_TITLE_T 				= "TITLE";
	public static final String CL_ICON_T 				= "ICON";
	public static final String CL_CONTENT_T 			= "CONTENT";
	public static final String CL_STARTTIME_SETTED_T 	= "STARTTIME_SETTED";
	public static final String CL_NOTIFICATION_T 		= "NOTIFICATION";
	public static final String CL_DATE_T 				= "DATE";
	
	// table tblShift
	// table_name
	public static final String TB_SHIFT		 		= "SHIFT";
	// column_name
	public static final String CL_ID_S					= "_id";
	public static final String CL_STARTTIME_S 			= "STARTTIME";
	public static final String CL_ENDTIME_S			= "ENDTIME";
	public static final String CL_NOTIFICATION_TIME_S 	= "NOTIFICATION_TIME";
	public static final String CL_NAME_S 				= "NAME";
	public static final String CL_COLOR_TEXT_S			= "COLOR_TEXT";
	public static final String CL_COLOR_PATTERN_S 		= "COLOR_PATTERN";
	public static final String CL_CONTENT_S		 	= "CONTENT";
	public static final String CL_IS_ALLDAY_S	 		= "IS_ALLDAY";
	public static final String CL_NOTIFICATION_S		= "NOTIFICATION";
	public static final String CL_VIEW_ORDER_S			= "VIEW_ORDER";
	
	
	// table tblShift_stransaction
	// table_name
	public static final String TB_SHIFT_T		 		= "SHIFT_TRANSACTION";
	// column_name
	public static final String CL_ID_ST				= "_id";
	public static final String CL_STARTTIME_ST 		= "STARTTIME";
	public static final String CL_ENDTIME_ST			= "ENDTIME";
	public static final String CL_NOTIFICATION_TIME_ST	= "NOTIFICATION_TIME";
	public static final String CL_NAME_ST 				= "NAME";
	public static final String CL_COLOR_TEXT_ST		= "COLOR_TEXT";
	public static final String CL_COLOR_PATTERN_ST 	= "COLOR_PATTERN";
	public static final String CL_CONTENT_ST		 	= "CONTENT";
	public static final String CL_IS_ALLDAY_ST	 		= "IS_ALLDAY";
	public static final String CL_NOTIFICATION_ST		= "NOTIFICATION";
	public static final String CL_DATE_ST		  		= "DATE";
	
	
	// table tblMember
	// table_name
	public static final String TB_MEMBER		= "MEMBER";
	// column_name
	public static final String CL_ID_M			= "_id";
	public static final String CL_NAME_M 		= "NAME";
	public static final String CL_ON_OFF_M		= "ON_OFF";
	
	
	// table tblShift_and_member
	// table_name
	public static final String TB_SHIFT_AND_MEMBER		= "SHIFT_AND_MEMBER";
	// column_name
	public static final String CL_ID_SM				= "_id";
	public static final String CL_SHIFT_ID_SM 			= "SHIFT_ID";
	public static final String CL_MEMBER_ID_SM			= "MEMBER_ID";
	
	
	// table tblShiftTransaction_and_menber
	// table_name
	public static final String TB_SHIFTTRANSACTION_AND_MEMBER	= "SHIFT_TRANSACTION_AND_MEMBER";
	// column_name
	public static final String CL_ID_STM						= "_id";
	public static final String CL_SHIFTTRANSACTION_ID_STM 		= "SHIFTTRANSACTION_ID";
	public static final String CL_MEMBER_ID_STM				= "MEMBER_ID";
	
	
	public DBConfig(Context context)
	{
		super(context, DB_NAME, null, DB_VERSION);
	}
	
	
	
	@Override
	public void onCreate(SQLiteDatabase db)
	{
		// create table PLAN
		String plan_create = "create table " 
				+ TB_PLAN + "( " 
				+ CL_ID + 					" integer primary key autoincrement, "
				+ CL_STARTTIME + 			" integer not null, "
				+ CL_ENDTIME + 				" integer not null, "
				+ CL_NOTIFICATION_TIME + 	" integer not null, "
				+ CL_TITLE + 				" text not null, "
				+ CL_ICON + 				" text not null, "
				+ CL_CONTENT + 				" text not null, " 
				+ CL_STARTTIME_SETTED + 	" integer not null, "
				+ CL_NOTIFICATION + 		" integer not null, "
				+ CL_PATTERN_NAME + 		" text not null" 
				+ ");" ;
		db.execSQL(plan_create);
		
		
		// create table PLAN_TRANSACTION
		String planT_create = 	"create table " + 
				TB_PLAN_T + "( " 
				+ CL_ID_T + 				" integer primary key autoincrement, "
				+ CL_STARTTIME_T + 			" integer not null, "
				+ CL_ENDTIME_T + 			" integer not null, "
				+ CL_NOTIFICATION_TIME_T + 	" integer not null, "
				+ CL_TITLE_T + 				" text not null, "
				+ CL_ICON_T + 				" text not null, "
				+ CL_CONTENT_T + 			" text not null, " 
				+ CL_STARTTIME_SETTED_T + 	" integer not null, "
				+ CL_NOTIFICATION_T + 		" integer not null, "
				+ CL_DATE_T + 				" integer not null" 
				+ ");" ;
		db.execSQL(planT_create);
		
		
		// create table SHIFT
		String shift_create = 	"create table " + 
				TB_SHIFT + "( " 
				+ CL_ID_S + 				" integer primary key autoincrement, "
				+ CL_STARTTIME_S + 			" integer not null, "
				+ CL_ENDTIME_S + 			" integer not null, "
				+ CL_NOTIFICATION_TIME_S + 	" integer not null, "
				+ CL_NAME_S + 				" text not null, "
				+ CL_COLOR_TEXT_S + 		" text not null, "
				+ CL_COLOR_PATTERN_S + 		" text not null, " 
				+ CL_CONTENT_S + 			" text not null, "
				+ CL_IS_ALLDAY_S + 			" integer not null, "
				+ CL_NOTIFICATION_S + 		" integer not null, "
				+ CL_VIEW_ORDER_S + 		" integer not null"
				+ ");" ;
		db.execSQL(shift_create);
		
		
		
		// create table SHIFT_TRANSACTION
		String shiftT_create = 	"create table " + 
				TB_SHIFT_T + "( " 
				+ CL_ID_ST + 				" integer primary key autoincrement, "
				+ CL_STARTTIME_ST + 		" integer not null, "
				+ CL_ENDTIME_ST + 			" integer not null, "
				+ CL_NOTIFICATION_TIME_ST + " integer not null, "
				+ CL_NAME_ST + 				" text not null, "
				+ CL_COLOR_TEXT_ST + 		" text not null, "
				+ CL_COLOR_PATTERN_ST + 	" text not null, " 
				+ CL_CONTENT_ST + 			" text not null, "
				+ CL_IS_ALLDAY_ST + 		" integer not null, "
				+ CL_NOTIFICATION_ST + 		" integer not null, "
				+ CL_DATE_ST + 				" integer not null"
				+ ");" ;
		db.execSQL(shiftT_create);
		
		
		// create table MEMBER
		String member_create = 	"create table " + 
				TB_MEMBER + "( " 
				+ CL_ID_M + 	" integer primary key autoincrement, "
				+ CL_NAME_M + 	" text not null, "
				+ CL_ON_OFF_M + " integer not null"
				+ ");" ;
		db.execSQL(member_create);
		
		
		// create table MEMBER
		String shift_member_create = 	"create table " + 
				TB_SHIFT_AND_MEMBER + "( " 
				+ CL_ID_SM + 			" integer primary key autoincrement, "
				+ CL_SHIFT_ID_SM + 		" integer not null, "
				+ CL_MEMBER_ID_SM + 	" integer not null"
				+ ");" ;
		db.execSQL(shift_member_create);
		
		
		
		// create table SHIFTTRANSACTION_MEMBER
		String shiftT_member_create = 	"create table " + 
				TB_SHIFTTRANSACTION_AND_MEMBER + "( " 
				+ CL_ID_STM + 					" integer primary key autoincrement, "
				+ CL_SHIFTTRANSACTION_ID_STM + 	" integer not null, "
				+ CL_MEMBER_ID_STM + 			" integer not null"
				+ ");" ;
		db.execSQL(shiftT_member_create);
		
	}
	
	
	
	@Override
	public void onUpgrade(SQLiteDatabase db, 
					int oldVersion, int newVersion)
	{
		db.execSQL("drop if table exists " + TB_PLAN);
		db.execSQL("drop if table exists " + TB_PLAN_T);
		db.execSQL("drop if table exists " + TB_SHIFT);
		db.execSQL("drop if table exists " + TB_SHIFT_T);
		db.execSQL("drop if table exists " + TB_MEMBER);
		db.execSQL("drop if table exists " + TB_SHIFT_AND_MEMBER);
		db.execSQL("drop if table exists " + TB_SHIFTTRANSACTION_AND_MEMBER);
		
		onCreate(db);
	}
	
	
	
}
