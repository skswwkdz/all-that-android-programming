package com.appstudio.android.sample.ikeeper_android;


import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.appstudio.android.sample.ikeeper_android.common.Constant;
import com.appstudio.android.sample.ikeeper_android.common.Util;




public class C2dm_BroadcastReceiver extends BroadcastReceiver{
	public static String registration_id 	= null;
	private String c2dm_msg2="";
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d("TAG", "onReceiver");
		Log.i("TAG", "intent.getAction()::"+intent.getAction());
		
		if (intent.getAction().equals("com.google.android.c2dm.intent.REGISTRATION")) {
			handleRegistration(context, intent);
		} else if (intent.getAction().equals("com.google.android.c2dm.intent.RECEIVE")) {
			c2dm_msg2 = intent.getExtras().getString("msg");
			setNotification(context, c2dm_msg2);
		} 


	}
	//메시지받을때 Notification

	private void setNotification(Context context, String c2dm_msg2)
	{
		NotificationManager notiMgr = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification noti = new Notification(android.R.drawable.stat_notify_error, "메시지가  도착했어요", System.currentTimeMillis());
		
		Intent noti_intent = new Intent();
		noti_intent.setClassName("com.Ikeeper", "com.Ikeeper.Intro");
		PendingIntent picontent = PendingIntent.getActivity(context, 0, noti_intent, 0);
		
//		noti.icon = R.drawable.icon;
		noti.setLatestEventInfo(context, "Ikeeper", c2dm_msg2, picontent);
		notiMgr.notify(1, noti);
		
	}
	private void handleRegistration(Context context, Intent intent) {

		Log.i("TAG", "handleRegistration");
		registration_id = intent.getStringExtra("registration_id");
		Log.i("TAG", "registration_id:"+registration_id);
		

		if (intent.getStringExtra("error") != null) {
			Log.i("TAG", ">>>>>"+ "Registration failed, should try again later." + "<<<<<");

		} else if (intent.getStringExtra("unregistered") != null) {

			Log.i("TAG",">>>>>"+ "unregistration done, new messages from the authorized sender will be rejected"+ "<<<<<");

		} else if (registration_id != null) {
			Log.i("TAG","registration_id complete!!");
			
//			Log.i("TAG","before appid value::"+Util.getStringAppPreferences((Activity)context, Constant.C2DM_CLIENT_APPID));
//			Util.setStringAppPreferences((Activity)context, Constant.C2DM_CLIENT_APPID, registration_id);
//			Log.i("TAG","after appid value::"+Util.getStringAppPreferences((Activity)context, Constant.C2DM_CLIENT_APPID));
			
			 SharedPreferences pref = null;
		      pref = context.getSharedPreferences("ikeeper", 0);
		      SharedPreferences.Editor prefEditor = pref.edit();
		      prefEditor.putString(Constant.C2DM_CLIENT_APPID, registration_id);
		      
		      prefEditor.commit();
			
		}
	}

}