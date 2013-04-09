package com.appstudio.android.sample.ikeeper_android;

import android.app.PendingIntent;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;

import com.appstudio.android.sample.ikeeper_android.auth.Auth_InputTelno;
import com.appstudio.android.sample.ikeeper_android.common.Constant;
import com.appstudio.android.sample.ikeeper_android.common.Util;
import com.appstudio.android.sample.ikeeper_android.map.MyMapActivity;


public class Ikeeper_AndroidActivity extends TabActivity  {
    private String mapMain = "MAP";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        checkPushNotification();
        
        Intent introit = new Intent(this,Intro.class);
        startActivityForResult(introit, Constant.CODE_INTRO);
        overridePendingTransition(R.anim.fade, R.anim.hold);
        
        TabHost tabHost = getTabHost();
        tabHost.addTab(tabHost.newTabSpec("대화목록")		
        					  .setIndicator("대화목록",getResources().getDrawable(R.drawable.ic_menu_allfriends))
        					  .setContent(getIntent(mapMain)));  
        tabHost.addTab(tabHost.newTabSpec("채팅")	
        					  .setIndicator("채팅",getResources().getDrawable(R.drawable.img_tab_car))		
        					  .setContent(getIntent("Cars")));  
        tabHost.addTab(tabHost.newTabSpec("지도")	
        					  .setIndicator("지도",getResources().getDrawable(R.drawable.img_tab_instrument))	    
        					  .setContent(getIntent("Instruments")));
        tabHost.addTab(tabHost.newTabSpec("설정")	
        					  .setIndicator("설정",getResources().getDrawable(R.drawable.img_tab_insect))		
        					  .setContent(getIntent("Insects")));
        
        tabHost.getTabWidget().getChildAt(0).setBackgroundResource(R.drawable.tab_background);
        tabHost.getTabWidget().getChildAt(1).setBackgroundResource(R.drawable.tab_background);
        tabHost.getTabWidget().getChildAt(2).setBackgroundResource(R.drawable.tab_background);
        tabHost.getTabWidget().getChildAt(3).setBackgroundResource(R.drawable.tab_background);
    }
    
    @Override
 	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
     //	super.onActivityResult(requestCode, resultCode, data);
    	
    	
     	Log.i("TAG", requestCode+"");
     	Log.i("TAG", resultCode+"");
     	Log.i("TAG", data+"");
     	if (requestCode == Constant.CODE_MAIN_TO_AUTH && !Util.getBoolAppPreferences(this, Constant.IS_AUTH_YN)) {
			finish();
		}else{
			if(!Util.getBoolAppPreferences(this, "isAgreeTerms")){
	     		 Intent introit = new Intent(this,Auth_InputTelno.class);
	             startActivityForResult(introit, Constant.CODE_MAIN_TO_AUTH);
	             overridePendingTransition(R.anim.fade, R.anim.hold);
	     	}
		}
     	
     	
     	
     	
     	
 	}
    
    public Intent getIntent(String className){
    	Intent intent = null;

    	if(className.equals(mapMain)){
    		intent = new Intent(this, MyMapActivity.class);
    	}else if(className.equals("Cars")){
    		//intent = new Intent(this, Env_Config.class);
    	}else if(className.equals("Instruments")){
    		//intent = new Intent(this, Lock_ChildList.class);
    	}else if(className.equals("Insects")){
    		//intent = new Intent(this, Insects.class);
    	}
    	
    	return intent;
    } 
    
    private void checkPushNotification(){
    	Log.i("TAG", "checkPushNotification");
    	if(Util.getStringAppPreferences(this, Constant.C2DM_CLIENT_APPID).equals("")){
    		Log.i("TAG", "저장된 AppId가 없습니다.");
			//Appid 요청
    		Intent registrationIntent = new Intent("com.google.android.c2dm.intent.REGISTER");
            registrationIntent.putExtra("app",PendingIntent.getBroadcast(this, 0, new Intent(), 0));
            registrationIntent.putExtra("sender", "papa.interactive@gmail.com");
            startService(registrationIntent);
    	}
      
    }
     
   
    
    
    
    
    
}