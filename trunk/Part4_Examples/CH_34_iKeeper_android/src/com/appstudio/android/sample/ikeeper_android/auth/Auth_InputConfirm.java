package com.appstudio.android.sample.ikeeper_android.auth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.appstudio.android.sample.ikeeper_android.R;
import com.appstudio.android.sample.ikeeper_android.common.Constant;
import com.appstudio.android.sample.ikeeper_android.common.Util;
 
public class Auth_InputConfirm extends Activity {
    /** Called when the activity is first created. */
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_confirm);
        
       
        Button btn = (Button)findViewById(R.id.btn_auth_confirm);
        btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				String authRandom = Util.getStringAppPreferences(Auth_InputConfirm.this, Constant.AUTH_SMS_MSG);
				String inputParam = ((EditText)findViewById(R.id.auth_editText)).getText().toString();
				
				if(inputParam.equals(authRandom)){
					Intent it = new Intent(getApplicationContext(),Auth_AgreeTerms.class);
				    startActivityForResult(it,0);
				}else{
					Toast.makeText(Auth_InputConfirm.this, "fail", Toast.LENGTH_SHORT).show();
				}
			}
		});
        
        Button reSend = (Button)findViewById(R.id.btn_auth_resend);
        reSend.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Util comUtil = new Util();
				String authRandom = comUtil.getRandomNum(4);
				
				Util.setStringAppPreferences(Auth_InputConfirm.this, Constant.AUTH_SMS_MSG , authRandom);
				
				Log.i("TAG", "authRandom:::"+authRandom);
				
				Intent it = getIntent();
				comUtil.sendSmsMessage(it.getStringExtra("telNo"), authRandom);
			     
				
			}
		});
       
    }
    @Override
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	  super.onActivityResult(requestCode, resultCode, data);
	  Log.i("TAG", "AuthConfirm");
	  finish();
	}
    
   
}