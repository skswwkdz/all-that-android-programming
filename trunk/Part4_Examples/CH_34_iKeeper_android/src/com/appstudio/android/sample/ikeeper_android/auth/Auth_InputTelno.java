package com.appstudio.android.sample.ikeeper_android.auth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.appstudio.android.sample.ikeeper_android.R;
import com.appstudio.android.sample.ikeeper_android.common.Constant;
import com.appstudio.android.sample.ikeeper_android.common.Util;



public class Auth_InputTelno extends Activity {
	private Util comUtil; 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_telno);
        
        TelephonyManager mTelephonyMgr = (TelephonyManager)getSystemService(getApplicationContext().TELEPHONY_SERVICE);
	    String myNumber = mTelephonyMgr.getLine1Number();
		
	    ((EditText)findViewById(R.id.editText1)).setText(myNumber);
	    
        comUtil = new Util();
        
        Button btn = (Button)findViewById(R.id.button1);
        btn.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				String authRandom = comUtil.getRandomNum(4);
				Util.setStringAppPreferences(Auth_InputTelno.this,Constant.AUTH_SMS_MSG , authRandom);
				
				
				
				String telNo = ((EditText)findViewById(R.id.editText1)).getText().toString();
				Log.i("TAG", authRandom);
				comUtil.sendSmsMessage(telNo, authRandom);
			     
			    Intent it = new Intent(Auth_InputTelno.this,Auth_InputConfirm.class);
			    it.putExtra("telNo", telNo);
			    startActivityForResult(it,0);
			}
		});
        
    }
    
    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	Log.i("TAG", "AuthTelno");
    	finish();
	}
    
    




    
}