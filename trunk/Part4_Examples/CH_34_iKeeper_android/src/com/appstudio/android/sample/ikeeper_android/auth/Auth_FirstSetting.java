package com.appstudio.android.sample.ikeeper_android.auth;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.appstudio.android.sample.ikeeper_android.R;
import com.appstudio.android.sample.ikeeper_android.common.Constant;
import com.appstudio.android.sample.ikeeper_android.common.Util;
import com.appstudio.android.sample.ikeeper_android.entity.User;



public class Auth_FirstSetting extends Activity implements OnItemSelectedListener {

	private EditText et;
	private EditText papaTelNo;
	private EditText mamaTelNo;
	private String relation_cd = "M";
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_firstsetting);
        
        init();
    }
    
	private void init(){
		et = (EditText)findViewById(R.id.auth_setting_nick);
        et.setPrivateImeOptions("defaultInputmode=korea;");
        
        papaTelNo = (EditText)findViewById(R.id.auth_setting_papano);
        mamaTelNo = (EditText)findViewById(R.id.auth_setting_mamano);
        
        
        
        Spinner spin = (Spinner)findViewById(R.id.auth_spinner);
        spin.setOnItemSelectedListener(this);
        
        ArrayAdapter<CharSequence> spinAdapter = ArrayAdapter.createFromResource(getApplicationContext(),R.array.relation_collection, android.R.layout.simple_spinner_item);
        
        spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(spinAdapter);
        
        Button btn = (Button)findViewById(R.id.btn_auth_setting_confirm);
        btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(et.getText().toString().length()>0){
					Util.setBoolAppPreferences(Auth_FirstSetting.this, Constant.IS_AUTH_YN,true);
					if(relation_cd.equals("C")){
						Util.setBoolAppPreferences(Auth_FirstSetting.this, Constant.IS_PARENT_MODE,false);
					}else{
						Util.setBoolAppPreferences(Auth_FirstSetting.this, Constant.IS_PARENT_MODE,true);
					}		
					User user = new User();
					user.setC2dmAppid(Util.getStringAppPreferences(Auth_FirstSetting.this, Constant.C2DM_CLIENT_APPID));
					user.setTelNo(((TelephonyManager)getSystemService(getApplicationContext().TELEPHONY_SERVICE)).getLine1Number());
					user.setMamaTelNo(mamaTelNo.getText().toString());
					user.setPapaTelNo(papaTelNo.getText().toString());
					user.setName(et.getText().toString());
					user.setRelationMode(relation_cd);
					
					Util.setStringAppPreferences(Auth_FirstSetting.this, User.RELATION_MODE, relation_cd);
					
					new AddTask().execute(user,null,null);
					finish();
				}else{
					Toast.makeText(getApplicationContext(), "대화명을 입력하세요.", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
	
    

    private class AddTask extends AsyncTask<Object, Object, Object> {
    	@Override
    	protected void onPreExecute() {
    		super.onPreExecute();
    		setProgressBarIndeterminateVisibility(true);
    	}

    	@Override
    	protected Object doInBackground(Object... params) {
    		Util comUtil = new Util();
    		try {
    			
				comUtil.store((User)params[0]);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		return null;
    	}

    	@Override
    	protected void onPostExecute(Object result) {
    		super.onPostExecute(result);
    		setProgressBarIndeterminateVisibility(false);
    	}  
    } 

    @Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
    	LinearLayout papa_ll = (LinearLayout)findViewById(R.id.auth_linearLayout_papa);
		LinearLayout mama_ll = (LinearLayout)findViewById(R.id.auth_linearLayout_mama);
		
		if(arg2==0){
			relation_cd ="M";
			papa_ll.setVisibility(View.GONE);
			mama_ll.setVisibility(View.GONE);
		}else if(arg2 == 1){
			relation_cd ="P";
			papa_ll.setVisibility(View.GONE);
			mama_ll.setVisibility(View.GONE);
		}else{
			relation_cd ="C";
			papa_ll.setVisibility(View.VISIBLE);
			mama_ll.setVisibility(View.VISIBLE);
		}
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		
		
	}

}

