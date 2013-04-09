package com.appstudio.android.sample.ikeeper_android.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.appstudio.android.sample.ikeeper_android.entity.User;
import com.google.android.maps.GeoPoint;

import android.app.Activity;
import android.content.SharedPreferences;
import android.location.Location;
import android.telephony.SmsManager;
import android.util.Log;


public class Util  {

	 public static void setStringAppPreferences(Activity context, String key, String value)
	 {
	      SharedPreferences pref = null;
	      pref = context.getSharedPreferences("ikeeper", 0);
	      SharedPreferences.Editor prefEditor = pref.edit();
	      prefEditor.putString(key, value);
	      
	      prefEditor.commit();
	 }
	     
	 public static String getStringAppPreferences(Activity context, String key)
	 {
	      String returnValue = null;
	      
	      SharedPreferences pref = null;
	      pref = context.getSharedPreferences("ikeeper", 0);
	      
	      returnValue = pref.getString(key, "");
	      
	      return returnValue;
	 }
	
	 public static void setBoolAppPreferences(Activity context, String key, boolean value)
	 {
	      SharedPreferences pref = null;
	      pref = context.getSharedPreferences("ikeeper", 0);
	      SharedPreferences.Editor prefEditor = pref.edit();
	      prefEditor.putBoolean(key, value);
	      
	      prefEditor.commit();
	 }

	 public static boolean getBoolAppPreferences(Activity context, String key)
	 {
		 boolean returnValue = false;
	      
	      SharedPreferences pref = null;
	      pref = context.getSharedPreferences("ikeeper", 0);
	      
	      returnValue = pref.getBoolean(key, false);
	      
	      return returnValue;
	 }
	
	 public boolean getKeyDown(int keyCode){
		 if(keyCode==82 || keyCode==84 || keyCode ==27|| keyCode ==80|| keyCode ==92){
			 return true;
		 }else{
			 return false;
		 }
	 }
	 
	 public String getRandomNum(int number){
		 StringBuffer sb = new StringBuffer();
		 for (int inx = 0; inx < number; inx++) {
			 sb.append((int)(Math.random() * 10));
		 }
		 return sb.toString();
	 }
	
	 public GeoPoint getGeoPoint(Location location) {

		 if (location == null) {

		 return null;

		 }

		 Double lat = location.getLatitude() * 1E6;

		 Double lng = location.getLongitude() * 1E6;

		 return new GeoPoint(lat.intValue(), lng.intValue());

	 }
	 
	 public Location getLocation(GeoPoint geo) {

		 if (geo == null) {return null;}

		 Double lat = geo.getLatitudeE6() / 1E6;

		 Double lng = geo.getLongitudeE6() / 1E6;
		 
		 Location loc = new Location("gps");
		 loc.setLatitude(lat);
		 loc.setLongitude(lng);
		 return loc;

	 }

	
	 public void sendSmsMessage(String address, String message)  {
   	        SmsManager sms = SmsManager.getDefault();
   	        sms.sendTextMessage(address, "1544-0404", message, null, null);        

   	 }
	 
	 
	 public String store(User user) throws Exception {
		 
		// http://localhost:8888/user?action=upsert&TEL_NO=01037712173&NAME=hw&RELATION_MODE=C&PAPA_TEL_NO=01033831847
			 
		 Log.i("TAG", "useruseruseruser:::"+user.getName());
			StringBuilder postBuilder = new StringBuilder();
			postBuilder.append(User.TEL_NO).append("=").append(user.getTelNo());
			postBuilder.append("&").append(User.NAME).append("=").append(user.getName());
			postBuilder.append("&").append(User.RELATION_MODE).append("=").append(user.getRelationMode());
			postBuilder.append("&").append(User.PAPA_TEL_NO).append("=").append(user.getPapaTelNo());
			postBuilder.append("&").append(User.MAMA_TEL_NO).append("=").append(user.getMamaTelNo());
			postBuilder.append("&").append(User.C2DM_APPID).append("=").append(user.getC2dmAppid());
			
			byte[] post = postBuilder.toString().getBytes("UTF-8");
			URL url = new URL("http://appstudioikeeper.appspot.com/user?action=upsert");
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Content-Length", Integer.toString(post.length));
			
			OutputStream out = conn.getOutputStream();
			out.write(post);
			out.close();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String thisline;
			StringBuilder result = new StringBuilder();
			while((thisline = reader.readLine()) != null){
				result.append(thisline);
			}
			return result.toString();
		}
	
	 	
	 public String getOthersLocation(String userNO) throws Exception {
		 
			// http://localhost:8888/user?action=upsert&TEL_NO=01037712173&NAME=hw&RELATION_MODE=C&PAPA_TEL_NO=01033831847
				 
			 Log.i("TAG", "getOthersLocation:::"+userNO);
				StringBuilder postBuilder = new StringBuilder();
				postBuilder.append(User.TEL_NO).append("=").append(userNO);
				
				byte[] post = postBuilder.toString().getBytes("UTF-8");
				URL url = new URL("http://appstudioikeeper.appspot.com/user?action=findothers");
				
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setDoOutput(true);
				conn.setUseCaches(false);
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				conn.setRequestProperty("Content-Length", Integer.toString(post.length));
				
				OutputStream out = conn.getOutputStream();
				out.write(post);
				out.close();
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String thisline;
				StringBuilder result = new StringBuilder();
				while((thisline = reader.readLine()) != null){
					result.append(thisline);
				}
				return result.toString();
			}
	
 
}