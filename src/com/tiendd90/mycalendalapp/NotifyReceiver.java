package com.tiendd90.mycalendalapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NotifyReceiver extends BroadcastReceiver
{
	
	private final String PLANACTION = "AlarmPlan";
	private final String SHIFTACTION = "AlarmShift";
	
	@Override
	public void onReceive(Context context, Intent intent)
	{
		//generateNotification(context, "Plan notification");
		String action = intent.getAction();
		
		if (PLANACTION.equals(action)) 
		{
			//do what you want here
			planNotification(context,"Plan notification");
		}
		
		if (SHIFTACTION.equals(action)) 
		{
			//do what you want here
			planNotification(context,"Shift notification");
		}
	}
	
	
	private void planNotification(Context context, String message) 
	{
		int icon = R.drawable.ic_launcher;
		long when = System.currentTimeMillis();
		NotificationManager notificationManager = (NotificationManager) context
					.getSystemService(Context.NOTIFICATION_SERVICE);
		
		Notification notification = new Notification(icon, message, when);
		String title = context.getString(R.string.app_name);
		String subTitle = context.getString(R.string.app_name);
		Intent notificationIntent = new Intent(context, MainActivity.class);
		notificationIntent.putExtra("content", message);
		PendingIntent intent = PendingIntent.getActivity(context, 0,notificationIntent, 0);
		notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
		
		notification.setLatestEventInfo(context, title, subTitle, intent);
		//To play the default sound with your notification:
		notification.defaults |= Notification.DEFAULT_SOUND;
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		notification.defaults |= Notification.DEFAULT_VIBRATE;
		
		notificationManager.notify(0, notification);

    }

	
	
	private void shiftNotification(Context context, Notification notify) 
	{
		int icon = R.drawable.ic_launcher;
		long when = System.currentTimeMillis();
		
		
		NotificationManager notificationManager = (NotificationManager) context
					.getSystemService(Context.NOTIFICATION_SERVICE);
		
		
		// send data to other activity
		Intent notificationIntent = new Intent(context, MainActivity.class);
		notificationIntent.putExtra("content", "planNotification");
		PendingIntent intent = PendingIntent.getActivity(
							context, 0,notificationIntent, 0);
		notificationIntent.setFlags(
				Intent.FLAG_ACTIVITY_CLEAR_TOP 
				| Intent.FLAG_ACTIVITY_SINGLE_TOP);
		
		
		
		// make notification
		notificationManager.notify(0, notify);

    }
	
	
}
