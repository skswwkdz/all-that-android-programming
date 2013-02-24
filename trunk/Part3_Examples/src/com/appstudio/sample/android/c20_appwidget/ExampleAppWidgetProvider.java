package com.appstudio.sample.android.c20_appwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.appstudio.sample.android.ExamplesActivity;
import com.appstudio.sample.android.R;

public class ExampleAppWidgetProvider 
                                    extends AppWidgetProvider {
  public void onUpdate(Context context, 
       AppWidgetManager appWidgetManager, int[] appWidgetIds) {
    final int N = appWidgetIds.length;        
    for (int i=0; i<N; i++) {            
      int appWidgetId = appWidgetIds[i];            
      Intent intent = new Intent(context, 
                                       ExamplesActivity.class);
      PendingIntent pendingIntent = 
              PendingIntent.getActivity(context, 0, intent, 0);
      RemoteViews views = new RemoteViews(
                                      context.getPackageName(),
                          R.layout.example_appwidget_provider);
      views.setOnClickPendingIntent(R.id.button,pendingIntent);
          appWidgetManager.updateAppWidget(appWidgetId, views);
    }    
  }
}