package com.tiendd90.mycalendalapp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.tiendd90.dataprovider.TblShiftHelper;
import com.tiendd90.dataprovider.TblShiftTAndMember;
import com.tiendd90.dataprovider.TblShiftTAndMemberHelper;
import com.tiendd90.dataprovider.TblShiftTHelper;
import com.tiendd90.dataprovider.TblShiftT;
import com.tiendd90.dataprovider.TblShift;
import com.tiendd90.model.Day;
import com.tiendd90.model.Shift;
import com.tiendd90.model.ShiftMember;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;



public class ShiftDetailActivity extends Activity
{
	private final String TAG = "SHIFT DETAIL";
	
	private Day day;
	private Shift shift;
	
	private Button btnDel;
	private ImageButton ibtnClose;
	private Switch sbtnNotification;
	private Spinner spnShift;
	private TextView tvStartTime;
	private TextView tvEndTime;
	private TextView tvNDate;
	private TextView tvNTime;
	private EditText edtContent;
	private int notification;
	private int nYear;
	private int nMonth;
	private int nDay;
	private int nHour;
	private int nMinute;
	//
	ArrayList<String> lstShiftName = new ArrayList<String>();
	ArrayList<String> lstShiftID = new ArrayList<String>();
	boolean[] members = new boolean[10];
	ImageView[] lstImageView = new ImageView[10];
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shift_detail);
		
		// get Views
		btnDel = (Button) findViewById(R.id.edit_shift_ibtnDelete);
		spnShift = (Spinner) findViewById(R.id.edit_shift_spnShift);
		ibtnClose = (ImageButton) findViewById(R.id.edit_shift_ibtnClose);
		sbtnNotification = (Switch) findViewById(R.id.edit_shift_sbtnNotification);
		tvStartTime = (TextView) findViewById(R.id.edit_shift_tvStartTime);
		tvEndTime = (TextView) findViewById(R.id.edit_shift_tvEndTime);
		tvNDate = (TextView) findViewById(R.id.edit_shift_tvNDate);
		tvNTime = (TextView) findViewById(R.id.edit_shift_tvNTime);
		edtContent = (EditText) findViewById(R.id.edit_shift_edtContent);
		//
		lstImageView[0] = (ImageView) findViewById(R.id.shift_detail_ivMember1);
		lstImageView[1] = (ImageView) findViewById(R.id.shift_detail_ivMember2);
		lstImageView[2] = (ImageView) findViewById(R.id.shift_detail_ivMember3);
		lstImageView[3] = (ImageView) findViewById(R.id.shift_detail_ivMember4);
		lstImageView[4] = (ImageView) findViewById(R.id.shift_detail_ivMember5);
		lstImageView[5] = (ImageView) findViewById(R.id.shift_detail_ivMember6);
		lstImageView[6] = (ImageView) findViewById(R.id.shift_detail_ivMember7);
		lstImageView[7] = (ImageView) findViewById(R.id.shift_detail_ivMember8);
		lstImageView[8] = (ImageView) findViewById(R.id.shift_detail_ivMember9);
		lstImageView[9] = (ImageView) findViewById(R.id.shift_detail_ivMember10);
		//
		notification = 0;
		nYear = 0;
		nMonth = 0;
		nDay = 0;
		nHour = 0;
		nMinute = 0;
		
		
		// get extra data
		Intent i = getIntent();
		
		day = (Day) i.getBundleExtra("data").
							getSerializable("day");
		shift = (Shift) i.getBundleExtra("data").
							getSerializable("shift");

		
		if(shift!=null)
		{
			// set value of Views
			showViewValue(shift);
		}
		
		//
		resetMembers();
		
		// load shift from database
		ArrayList<Shift> lst = new ArrayList<Shift>();
		if(Setting.SQLITE==1)
		{
			lst.addAll(new TblShiftHelper(
					ShiftDetailActivity.this).getAll());
		}
		else
		{
			try
			{
				lst.addAll(new TblShift().getAll());
			}
			catch(Exception ex)
			{
				Log.e(TAG, "LOAD SHIFT FROM MYSQL: " + ex.getMessage());
			}
		}
		
		lstShiftName.clear();
		lstShiftID.clear();
		for(int j=0; j<lst.size(); ++j)
		{
			lstShiftName.add(lst.get(j).getName());
			lstShiftID.add(lst.get(j).getId());
		}
		
		// create adapter for spiner
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				ShiftDetailActivity.this, 
				android.R.layout.simple_spinner_item, lstShiftName);
		
		adapter.setDropDownViewResource(
				android.R.layout.simple_list_item_single_choice);
		
		spnShift.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		
		// spinner onClick
		spnShift.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id)
			{
				Shift s = new Shift();
				try
				{
					// get from database
					if(Setting.SQLITE==1)	// using SQLite
					{
						s = new TblShiftHelper(
								ShiftDetailActivity.this).getByID(
												lstShiftID.get(position));
					}
					else	// using MySQL
					{
						s = new TblShift().getByID(lstShiftID.get(position));
					}
					// if not first item
					if(!s.getName().equals(""))
					{
						showViewValue(s);
					}
				}
				catch(Exception ex)
				{
					Log.e(TAG, "SELECT SHIFT: " + ex.getMessage());
				}

				spnShift.setTag(s.getName());
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{
				//showViewValue(s);
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
						ShiftDetailActivity.this, callback, 0, 0, true);
				
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
						ShiftDetailActivity.this, callback, 0, 0, true);
				
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
							ShiftDetailActivity.this, callback, 0, 0, true);
					
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
							ShiftDetailActivity.this, callback,
							Integer.parseInt(day.getY()),
							Integer.parseInt(day.getM()),
							Integer.parseInt(day.getD()));
					
					dpd.show();
				}
			}
		});
		
		
		// Close and update shift
		ibtnClose.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if( (shift!=null) 
						&& (day!=null) 
						&& (!spnShift.getTag().equals("")) )
				{
					Calendar c = Calendar.getInstance();
					c.set(nYear, nMonth, nDay, nHour, nMinute);
					
					Shift s = new Shift();
					s.setId(shift.getId());
					s.setStartTime(tvStartTime.getTag()+"");
					s.setEndTime(tvEndTime.getTag()+"");
					s.setNotificationTime(c.getTimeInMillis()+"");
					s.setName(spnShift.getTag().toString());
					s.setColorText(shift.getColorText());
					s.setColorPattern(shift.getColorPattern());
					s.setContent(edtContent.getText().toString());
					s.setIsAllDay(shift.getIsAllDay());
					s.setNotification(notification+"");
					s.setDate(day.getY() + day.getM() + day.getD());
					
					// update table shift
					if(Setting.SQLITE==1)	// using SQLite
					{
						new TblShiftTHelper(
								ShiftDetailActivity.this).update(
												shift.getId(), s);
					}
					else	// using MySQL
					{
						new TblShiftT().update(s);
					}
					
					
					// update table shift_transaction and member
					//
					// if checked and exits then do nothing 
					// if unchecked and exits then delete *
					// if checked and not exits then insert *
					// if unchecked and not exits then do nothing
					for(int x=0; x<members.length; ++x)
					{
						// check exist
						if(Setting.SQLITE==1)	// using SQLite
						{
							ShiftMember sm = new TblShiftTAndMemberHelper(
									ShiftDetailActivity.this).
									getByShiftIDAndMemberID(
											shift.getId(), ((x+1)+"") );
							
							if(sm==null && members[x])
							{
								// insert shiftT and member
								ShiftMember tmpSm = new ShiftMember();
								tmpSm.setMemberId((x+1)+"");
								tmpSm.setShiftId(shift.getId());
								
								new TblShiftTAndMemberHelper(
										ShiftDetailActivity.this).insert(tmpSm);
							}
							if(sm!=null && !members[x])
							{
								// delete shiftT and member
								new TblShiftTAndMemberHelper(
										ShiftDetailActivity.this).delete(sm.getId());
							}
						}
						else	// using MySQL
						{
							try
							{
								ShiftMember sm = new TblShiftTAndMember().getBySIDAndMID(
																shift.getId(), ((x+1)+"") );
								
								if(sm==null && members[x])
								{
									// insert shiftT and member
									ShiftMember tmpSm = new ShiftMember();
									tmpSm.setMemberId((x+1)+"");
									tmpSm.setShiftId(shift.getId());
									
									new TblShiftTAndMember().insert(tmpSm);
								}
								if(sm!=null && !members[x])
								{
									// delete shiftT and member
									new TblShiftTAndMember().delete(sm.getId());
								}
							}
							catch(Exception ex)
							{
								Log.e(TAG, "SAVE SHIFT: " + ex.getMessage());
							}
						}
					}
					
					
					if(notification>0)
					{
						Intent intent = new Intent(
										ShiftDetailActivity.this, NotifyReceiver.class);
						intent.setAction("AlarmShift");
						PendingIntent pIntent = PendingIntent.getBroadcast(
									ShiftDetailActivity.this, 111, intent, 0);
						AlarmManager aManager = (AlarmManager) getSystemService(ALARM_SERVICE);
						
						
						long when = Long.parseLong(s.getNotificationTime());
						aManager.set(AlarmManager.RTC_WAKEUP, when, pIntent);
					}
					
				}
				else
				{
					Toast.makeText(ShiftDetailActivity.this, 
							"Please! Chose Shift Pattern", Toast.LENGTH_SHORT).show();
				}
				
				
				finish();

			}
		});
		
		
		// Button DELETE onClick
		btnDel.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if(shift!=null)
				{
					if(Setting.SQLITE==1)	// using SQLite
					{
						new TblShiftTHelper(
								ShiftDetailActivity.this).delete(
													shift.getId());
					}
					else	// using MySQL
					{
						new TblShiftT().delete(shift.getId());
					}
					finish();
				}
			}
		});
		
	} // END OF onCreate()
	
	
	
	/**
	 * 
	 * @param s
	 */
	@SuppressLint("SimpleDateFormat")
	private void showViewValue(Shift s)
	{
		try
		{
			Calendar c = Calendar.getInstance();
			
			DateFormat format = new SimpleDateFormat("HH:mm");
			long time = 0;
			
			edtContent.setText(s.getContent());
			//
			time = Long.parseLong(s.getStartTime());
			c.setTimeInMillis(time);
			tvStartTime.setText(format.format(c.getTime()));
			tvStartTime.setTag(time);
			//
			time = Long.parseLong(s.getEndTime());
			c.setTimeInMillis(time);
			tvEndTime.setText(format.format(c.getTime()));
			tvEndTime.setTag(time);
			//
			time = Long.parseLong(s.getNotificationTime());
			c.setTimeInMillis(time);
			tvNTime.setText(format.format(c.getTime()));
			tvNTime.setTag(time);
			//
			format = new SimpleDateFormat("yyyy-M-dd");
			time = Long.parseLong(s.getNotificationTime());
			c.setTimeInMillis(time);
			tvNDate.setText(format.format(c.getTime()));
			tvNDate.setTag(time);
			
			
			if(Integer.parseInt(s.getNotification())>0)
			{
				notification = 1;
				sbtnNotification.setChecked(true);
				tvNDate.setVisibility(View.VISIBLE);
				tvNTime.setVisibility(View.VISIBLE);
			}
			else
			{
				notification = 0;
				sbtnNotification.setChecked(false);
				tvNDate.setVisibility(View.INVISIBLE);
				tvNTime.setVisibility(View.INVISIBLE);
			}
			
			ArrayList<ShiftMember> mSelected = new ArrayList<ShiftMember>();
			// show member select
			if(Setting.SQLITE==1)
			{
				mSelected.addAll(new TblShiftTAndMemberHelper(
									ShiftDetailActivity.this).
										getByShiftID(s.getId()));
			}
			else
			{
				mSelected.addAll(new TblShiftTAndMember().getBySID(s.getId()));
			}
			
			if(mSelected.size()>0)
			{
				resetMembers();
				for(ShiftMember sm : mSelected)
				{
					members[Integer.parseInt(
							sm.getMemberId()) - 1] = true;
				}
			}
			ShowMemberSelected();
			
		}
		catch(Exception ex) { Log.e("show detail shift", ex.getMessage()); }
	}
	
	
	
	public void memberSelect(View v)
	{
		if(v==findViewById(R.id.shift_detail_ivMember1))
		{
			members[0] = !members[0];
			changeMember(v, members[0]);
		}
		else if(v==findViewById(R.id.shift_detail_ivMember2))
		{
			members[1] = !members[1];
			changeMember(v, members[1]);
		}
		else if(v==findViewById(R.id.shift_detail_ivMember3))
		{
			members[2] = !members[2];
			changeMember(v, members[2]);
		}
		else if(v==findViewById(R.id.shift_detail_ivMember4))
		{
			members[3] = !members[3];
			changeMember(v, members[3]);
		}
		else if(v==findViewById(R.id.shift_detail_ivMember5))
		{
			members[4] = !members[4];
			changeMember(v, members[4]);
		}
		else if(v==findViewById(R.id.shift_detail_ivMember6))
		{
			members[5] = !members[5];
			changeMember(v, members[5]);
		}
		else if(v==findViewById(R.id.shift_detail_ivMember7))
		{
			members[6] = !members[6];
			changeMember(v, members[6]);
		}
		else if(v==findViewById(R.id.shift_detail_ivMember8))
		{
			members[7] = !members[7];
			changeMember(v, members[7]);
		}
		else if(v==findViewById(R.id.shift_detail_ivMember9))
		{
			members[8] = !members[8];
			changeMember(v, members[8]);
		}
		else if(v==findViewById(R.id.shift_detail_ivMember10))
		{
			members[9] = !members[9];
			changeMember(v, members[9]);
		}
	}
	
	
	private void changeMember(View v, boolean on)
	{
		try
		{
			ImageView iv = (ImageView) v;
			if(on)
				iv.setImageResource(R.drawable.memon);
			else
				iv.setImageResource(R.drawable.memoff);
		}
		catch(Exception ex) { /** View is not ImageView */ }
	}
	
	
	
	private void ShowMemberSelected()
	{
		for(int x=0; x<10; ++x)
		{
			if(members[x])
			{
				lstImageView[x].setImageResource(R.drawable.memon);
			}
			else
			{
				lstImageView[x].setImageResource(R.drawable.memoff);
			}
		}
	}
	
	
	private void resetMembers()
	{
		for(int x=0; x<members.length; ++x)
		{
			members[x] = false;
		}
	}
	

}
