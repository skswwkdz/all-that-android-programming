/*
 * Copyright (C) 2009 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.appstudio.android.sample.ch_29_2_2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.util.Log;

import com.appstudio.android.sample.R;

public class CubeWallpaperSettingActivity 
                       extends PreferenceActivity implements 
         SharedPreferences.OnSharedPreferenceChangeListener {

  @Override
  protected void onCreate(Bundle icicle) {
    Log.d("aa","aaaa");
    super.onCreate(icicle);
    getPreferenceManager().setSharedPreferencesName(
                            CubeWallpaper.SHARED_PREFS_NAME);
    addPreferencesFromResource(R.xml.cube_settings);
    getPreferenceManager().getSharedPreferences().
              registerOnSharedPreferenceChangeListener(this);
  }

  @Override
  protected void onResume() {
    super.onResume();
  }

  @Override
  protected void onDestroy() {
    getPreferenceManager().getSharedPreferences().
            unregisterOnSharedPreferenceChangeListener(this);
    super.onDestroy();
  }

  @Override
  public void onSharedPreferenceChanged(
           SharedPreferences sharedPreferences, String key) {
  }
}
