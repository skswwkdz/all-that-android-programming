package com.appstudio.android.sample.ikeeper_android;
 
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

 
public class Intro extends Activity {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);
       
        initialize();
       
    }

  
    private void initialize()
    {
        Handler handler =    new Handler()
         {
             @Override
             public void handleMessage(Message msg)
             {
                 finish();    // 액티비티 종료
             }
         };

        handler.sendEmptyMessageDelayed(0, 1500);    // ms, 3초후 종료시킴
    }
   
}