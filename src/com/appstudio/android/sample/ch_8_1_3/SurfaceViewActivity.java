package com.appstudio.android.sample.ch_8_1_3;

import android.app.Activity;
import android.os.Bundle;

public class SurfaceViewActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new SmileSurfaceView( this ));
    }
}