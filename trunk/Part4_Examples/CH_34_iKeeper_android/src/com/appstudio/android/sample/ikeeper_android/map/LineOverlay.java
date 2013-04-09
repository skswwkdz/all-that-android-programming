package com.appstudio.android.sample.ikeeper_android.map;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.util.Log;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.google.android.maps.Projection;

public class LineOverlay extends Overlay {
	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	private GeoPoint geoPoint;
	private GeoPoint childGeoPoint;
	private String text;
	public LineOverlay(GeoPoint geoPoint,GeoPoint childGeoPoint,String text) {
		this.geoPoint 		= geoPoint;
		this.childGeoPoint 	= childGeoPoint;
		this.text = text;
	}
	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		

		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setStyle(Paint.Style.FILL);
		paint.setColor(Color.BLUE);
		//paint.setAlpha(50);
		paint.setStrokeWidth(3);

		
		Projection projection = mapView.getProjection();
	    Point parentPoint = new Point();
	    Point childPoint = new Point();
	    projection.toPixels(geoPoint, parentPoint);
	    projection.toPixels(childGeoPoint, childPoint);
	    
	    canvas.drawLine(parentPoint.x, parentPoint.y, childPoint.x, childPoint.y, paint);
	    canvas.drawText(text, parentPoint.x+30, parentPoint.y-30, paint);
			
	}
	
}
