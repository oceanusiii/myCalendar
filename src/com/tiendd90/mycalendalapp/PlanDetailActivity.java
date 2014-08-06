package com.tiendd90.mycalendalapp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Calendar;

import com.tiendd90.dataprovider.TblPlanHelper;
import com.tiendd90.dataprovider.TblPlanTHelper;
import com.tiendd90.dataprovider.TblPlanT;
import com.tiendd90.dataprovider.TblPlan;
import com.tiendd90.model.Day;
import com.tiendd90.model.Plan;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;


public class PlanDetailActivity extends Activity
{
	private final String TAG = "PLAN DETAIL";
	private Day day = null;
	private Plan plan = null;
	// views
	private Button btnExe;
	private ImageButton ibtnClose;
	private Switch sbtnNotification;
	private ImageView ivIcon;
	private TextView tvPattern;
	private TextView tvStartTime;
	private TextView tvEndTime;
	private TextView tvNDate;
	private TextView tvNTime;
	private EditText edtTitle;
	private EditText edtContent;
	private String iconName;
	private int notification;
	private int nYear;
	private int nMonth;
	private int nDay;
	private int nHour;
	private int nMinute;
	private boolean isUpdate = false;
	private int icon = 0;
	
	// code for got result
	public static final int RESULT_CODE_CHOSEPATTERN = 111;
	public static final int RESULT_CODE_CHOSEICON = 112;
	

	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_plan_detail);
		
		// get Views
		btnExe = (Button) findViewById(R.id.edit_plan_btnAction);
		ivIcon = (ImageView) findViewById(R.id.edit_plan_ivIcon);
		ibtnClose = (ImageButton) findViewById(R.id.edit_plan_ibtnClose);
		sbtnNotification = (Switch) findViewById(R.id.edit_plan_sbtnNotification);
		tvPattern = (TextView) findViewById(R.id.edit_plan_tvPattern);
		tvStartTime = (TextView) findViewById(R.id.edit_plan_tvStartTime);
		tvEndTime = (TextView) findViewById(R.id.edit_plan_tvEndTime);
		tvNDate = (TextView) findViewById(R.id.edit_plan_tvNDate);
		tvNTime = (TextView) findViewById(R.id.edit_plan_tvNTime);
		edtTitle = (EditText) findViewById(R.id.edit_plan_edtTitle);
		edtContent = (EditText) findViewById(R.id.edit_plan_edtContent);
		iconName = "icon1";
		notification = 0;
		nYear = 0;
		nMonth = 0;
		nDay = 0;
		nHour = 0;
		nMinute = 0;
		icon = 0;
		
		
		// get extra data
		Intent i = getIntent();
		
		day = (Day) i.getBundleExtra("data").
							getSerializable("day");
		plan = (Plan) i.getBundleExtra("data").
							getSerializable("plan");

		if(plan!=null)
		{
			// set value of Views
			showValue(plan);
			isUpdate = checkUpdate(plan);
		}
		
		// delete button onClick
		btnExe.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				try
				{
					// using SQLite
					if(Setting.SQLITE==1)
					{
						TblPlanTHelper helper = new TblPlanTHelper(PlanDetailActivity.this);
						helper.delete(plan.getId());
					}
					else	// using MySQL
					{
						new TblPlanT().delete(plan.getId());
					}
					finish();
				}
				catch(Exception ex) 
				{
					Log.e(TAG, "DELETE: " + ex.getMessage());
				}
			}
		});
		
		
		// close activity and save plan
		ibtnClose.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				try
				{
					Calendar c = Calendar.getInstance();
					c.set(nYear, nMonth, nDay, nHour, nMinute);
					
					Plan p = new Plan();
					p.setStartTime(tvStartTime.getTag()+"");
					p.setEndTime(tvEndTime.getTag()+"");
					p.setNotificationTime(c.getTimeInMillis()+"");
					p.setTitle(edtTitle.getText().toString());
					p.setIcon(iconName);
					p.setContent(edtContent.getText().toString());
					p.setStartTimeSetted(notification+"");
					p.setNotification(notification+"");
					
					if(!isUpdate)
					{
						// add into PLAN
						if(tvPattern.getText().toString().equals(""))
						{
							p.setPatternName(edtTitle.getText().toString());
							if(Setting.SQLITE==1)	// using SQLite
							{
								new TblPlanHelper(PlanDetailActivity.this).insert(p);
							}
							else	// using MySQL
							{
								new TblPlan().insert(p);
							}
						}
						// add into PLAN_TRANSACTION
						else
						{
							p.setDate(day.getY()+day.getM()+day.getD());
							if(Setting.SQLITE==1)	// using SQLite
							{
								new TblPlanTHelper(PlanDetailActivity.this).insert(p);
							}
							else	// using MySQL
							{
								new TblPlanT().insert(p);
							}
						}
					}
					else
					{
						p.setDate(day.getY()+day.getM()+day.getD());
						if(Setting.SQLITE==1)
						{
							new TblPlanTHelper(PlanDetailActivity.this).update(plan.getId(), p);
						}
						else
						{
							new TblPlanT().update(p);
						}
					}
					
					
					// create notification
					if(notification>0)
					{
						Intent intent = new Intent(
										PlanDetailActivity.this, NotifyReceiver.class);
						intent.setAction("AlarmPlan");
						PendingIntent pIntent = PendingIntent.getBroadcast(
										PlanDetailActivity.this, 111, intent, 0);
						AlarmManager aManager = (AlarmManager) getSystemService(ALARM_SERVICE);
						
						
						long when = Long.parseLong(p.getNotificationTime());
						aManager.set(AlarmManager.RTC_WAKEUP, when, pIntent);
					}
				}
				catch(Exception ex)
				{
					Log.e(TAG, "SAVE: " + ex.getMessage());
				}
				
				finish();
			}
		});
		
		
		// imageview Icon onClick
		// chose icon
		ivIcon.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent i = new Intent(PlanDetailActivity.this,
								ChoseIconActivity.class);
				
				startActivityForResult(i, RESULT_CODE_CHOSEICON);
			}
		});
		
		
		// TextView Pattern onClick
		// chose pattern
		tvPattern.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// open new activity and
				// get result
				Intent i = new Intent(PlanDetailActivity.this, 
								ChosePatternActivity.class);

				startActivityForResult(i, 
							RESULT_CODE_CHOSEPATTERN);
			}
		});
		
		
		// switch button ON/OFF on change
		// set notification in app
		sbtnNotification.setOnCheckedChangeListener(
							new OnCheckedChangeListener()
		{
			@Override
			public void onCheckedChanged(
					CompoundButton buttonView, boolean isChecked)
			{
				if(isChecked)
				{
					notification = 1;
					tvNDate.setVisibility(View.VISIBLE);
					tvNTime.setVisibility(View.VISIBLE);
				}
				else
				{
					notification = 0;
					tvNDate.setVisibility(View.INVISIBLE);
					tvNTime.setVisibility(View.INVISIBLE);
				}
				
			}
		});
		
	
		// TextView StartTime onClick
		tvStartTime.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// open timePicker dialog
				OnTimeSetListener callback = new OnTimeSetListener()
				{
					@Override
					public void onTimeSet(TimePicker view, 
									int hourOfDay, int minute)
					{
						// set text
						tvStartTime.setText(hourOfDay + ":" + minute);
						
						Calendar c = Calendar.getInstance();
						
						int y = Integer.parseInt(day.getY());
						int m = Integer.parseInt(day.getM());
						int d = Integer.parseInt(day.getD());
						c.set(y, m, d, hourOfDay, minute);
						
						long milisec = c.getTimeInMillis();
						tvStartTime.setTag(milisec);
					}
				};
				
				TimePickerDialog tpd = new TimePickerDialog(
						PlanDetailActivity.this, callback, 0, 0, true);
				
				tpd.show();
			}
		});
		
		
		// TextView EndTime onClick
		tvEndTime.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// open timePicker dialog
				OnTimeSetListener callback = new OnTimeSetListener()
				{
					
					@Override
					public void onTimeSet(TimePicker view, 
									int hourOfDay, int minute)
					{
						tvEndTime.setText(hourOfDay + ":" + minute);
						
						Calendar c = Calendar.getInstance();
						
						int y = Integer.parseInt(day.getY());
						int m = Integer.parseInt(day.getM());
						int d = Integer.parseInt(day.getD());
						c.set(y, m, d, hourOfDay, minute);
						
						long milisec = c.getTimeInMillis();
						tvEndTime.setTag(milisec);
						
					}
				};
				
				
				TimePickerDialog tpd = new TimePickerDialog(
						PlanDetailActivity.this, callback, 0, 0, true);
				
				tpd.show();
			}
		});
		
		
		// TextView Notification Time onClick
		tvNTime.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// open timePicker dialog
				OnTimeSetListener callback = new OnTimeSetListener()
				{
					@Override
					public void onTimeSet(TimePicker view, 
									int hourOfDay, int minute)
					{
						tvNTime.setText(hourOfDay + ":" + minute);
						
						nHour = hourOfDay;
						nMinute = minute;
					}
				};
				
				
				if(notification>0)
				{
					TimePickerDialog tpd = new TimePickerDialog(
							PlanDetailActivity.this, callback, 0, 0, true);
					
					tpd.show();
				}
				
			}
		});
		
		
		// TextView notification Date onClick
		tvNDate.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// open timePicker dialog
				OnDateSetListener callback = new OnDateSetListener()
				{
					@Override
					public void onDateSet(DatePicker view, int year,
							int monthOfYear, int dayOfMonth)
					{
						//int m = monthOfYear+1;
						tvNDate.setText(year + "-" 
									+ (monthOfYear+1) + "-"
									+ dayOfMonth);
						
						nYear = year;
						nMonth = monthOfYear;
						nDay = dayOfMonth;
					}
				};
				
				
				if(notification>0)
				{
					DatePickerDialog dpd = new DatePickerDialog(
							PlanDetailActivity.this, callback,
							Integer.parseInt(day.getY()),
							Integer.parseInt(day.getM()),
							Integer.parseInt(day.getD()));
					
					dpd.show();
				}
			}
		});
		
	} // end of onCreate()



	/**
	 * Result from chose pattern activity here
	 */
	@Override
	protected void onActivityResult(int requestCode, 
							int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		
		// check result code
		// if chose pattern
		if(resultCode == RESULT_CODE_CHOSEPATTERN)
		{
			Plan p = (Plan) data.getBundleExtra("data").
							getSerializable("newPattern");
			if(p!=null)
				showValue(p);
		}
		// if chose icon
		else if(resultCode == RESULT_CODE_CHOSEICON)
		{
			iconName = data.getBundleExtra(
					"dataIcon").getString("icon");
			
			setIconImage();
		}
		
	}
	
	
	/**
	 * Show content of Plan
	 * @param p
	 */
	@SuppressLint("SimpleDateFormat")
	private void showValue(Plan p)
	{
		try
		{
			Calendar c = Calendar.getInstance();
			
			DateFormat format = new SimpleDateFormat("HH:mm");
			long time = 0;
			
			// Start Time
			time = Long.parseLong(p.getStartTime());
			c.setTimeInMillis(time);
			tvStartTime.setText(format.format(c.getTime()));
			tvStartTime.setTag(time);
			
			// End Time
			time = Long.parseLong(p.getEndTime());
			c.setTimeInMillis(time);
			tvEndTime.setText(format.format(c.getTime()));
			tvEndTime.setTag(time);
			
			// Notification Time
			time = Long.parseLong(p.getNotificationTime());
			c.setTimeInMillis(time);
			tvNTime.setText(format.format(c.getTime()));
			tvNTime.setTag(time);

			// Notification Date
			format = new SimpleDateFormat("yyyy-M-dd");
			time = Long.parseLong(p.getNotificationTime());
			c.setTimeInMillis(time);
			tvNDate.setText(format.format(c.getTime()));
			tvNDate.setTag(time);
	
			// duplicate code here :<
			// set invisible for textview
			// notification date and time
			if(Integer.parseInt(p.getNotification())>0)
			{
				sbtnNotification.setChecked(true);
				notification = 1;
				tvNDate.setVisibility(View.VISIBLE);
				tvNTime.setVisibility(View.VISIBLE);
			}
			else
			{
				tvNDate.setVisibility(View.INVISIBLE);
				tvNTime.setVisibility(View.INVISIBLE);
			}
			
			
			// show or hide delete button
			if(p.getId().equals(""))
			{
				btnExe.setVisibility(View.INVISIBLE);
				//isUpdate = false;
			}
			else
			{
				btnExe.setVisibility(View.VISIBLE);
				//isUpdate = true;
			}
			
			tvPattern.setText(p.getPatternName());
			edtTitle.setText(p.getTitle());
			edtContent.setText(p.getContent());
			
			iconName = p.getIcon();
			setIconImage();
			
		}
		catch (Exception ex) {}
	}
	
	
	private boolean checkUpdate(Plan p)
	{
		try
		{
			if(p.getId().equals(""))
				return false;
			else
				return true;
		}
		catch(Exception ex) {return false;}
	}
	
	
	
	private void setIconImage()
	{
		if(iconName.equals("pic1"))
		{
			ivIcon.setImageResource(R.drawable.pic1);
			icon = R.drawable.pic1;
		}
		else if(iconName.equals("pic2"))
		{
			ivIcon.setImageResource(R.drawable.pic2);
			icon = R.drawable.pic2;
		}
		else if(iconName.equals("pic3"))
		{
			ivIcon.setImageResource(R.drawable.pic3);
			icon = R.drawable.pic3;
		}
		else if(iconName.equals("pic4"))
		{
			ivIcon.setImageResource(R.drawable.pic4);
			icon = R.drawable.pic4;
		}
		else if(iconName.equals("pic5"))
		{
			ivIcon.setImageResource(R.drawable.pic5);
			icon = R.drawable.pic5;
		}
		else if(iconName.equals("pic7"))
		{
			ivIcon.setImageResource(R.drawable.pic7);
			icon = R.drawable.pic7;
		}
		else if(iconName.equals("pic8"))
		{
			ivIcon.setImageResource(R.drawable.pic8);
			icon = R.drawable.pic8;
		}
		else if(iconName.equals("pic9"))
		{
			ivIcon.setImageResource(R.drawable.pic9);
			icon = R.drawable.pic9;
		}
		else if(iconName.equals("pic10"))
		{
			ivIcon.setImageResource(R.drawable.pic10);
			icon = R.drawable.pic10;
		}
		else if(iconName.equals("pic11"))
		{
			ivIcon.setImageResource(R.drawable.pic11);
			icon = R.drawable.pic11;
		}
	}
}
