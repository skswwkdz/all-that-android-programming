package com.appstudio.android.sample.ikeeper_android.auth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.appstudio.android.sample.ikeeper_android.R;
import com.appstudio.android.sample.ikeeper_android.common.Constant;



public class Auth_AgreeTerms extends Activity {
    private CheckBox cb;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_terms);
        TextView tv = (TextView)findViewById(R.id.terms_textview);
        Constant ct = new Constant();
        cb = (CheckBox)findViewById(R.id.terms_checkbox);
        tv.setText(ct.getTerms());
       
    }
    
    @Override
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	  super.onActivityResult(requestCode, resultCode, data);
	  Log.i("TAG", "AuthTerms");
	  finish();
	}
    
    public void terms_onclick(View view) throws Exception{
    	if(view.getId() == R.id.terms_confirm){
    		if(cb.isChecked()){
    			Intent it = new Intent(getApplicationContext(),Auth_FirstSetting.class);
    		    startActivityForResult(it,0);
    		}else{
    			Toast.makeText(getApplicationContext(), "약관에 동의하셔야 합니다.", Toast.LENGTH_SHORT).show();
    		}
    		
    	}else if(view.getId() == R.id.terms_cancel){
    		finish();
    	}
    	
    }
}