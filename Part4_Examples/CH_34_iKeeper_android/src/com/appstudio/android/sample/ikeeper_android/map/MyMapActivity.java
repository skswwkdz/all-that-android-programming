package com.appstudio.android.sample.ikeeper_android.map;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.appstudio.android.sample.ikeeper_android.R;
import com.appstudio.android.sample.ikeeper_android.common.Util;
import com.appstudio.android.sample.ikeeper_android.entity.User;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class MyMapActivity extends MapActivity implements LocationListener {
	
  private static final int TWO_MINUTES = 1000 * 60 * 2;
  private static final String TAG = "appstudio";
  private MapView mMapView;
  private LocationManager mLocationManager;
  private MapController mMapController;
  private Location mLocation;
  private MyItemizedOverlay mItemizedoverlay;
  private CustomLocationOverlay mLocationOverlay;
	
  private List<Overlay> mapOverlays;
  
  
  //temp
  private Location currentLocation1;
  
  
  @Override
  protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.map_main);
		init();
  }
  
  private void init(){
	  Button btn_other = (Button)findViewById(R.id.btn_otherLocation);
	  if( Util.getStringAppPreferences(MyMapActivity.this, User.RELATION_MODE).equals("C")){
		  btn_other.setText("부모님 위치 확인");
	  }else{
		  btn_other.setText("아이들 위치 확인");
	  }
	  
	  mMapView = (MapView) findViewById(R.id.mapview_gpsmap);
	  mLocationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		
	  mMapController = mMapView.getController();
	  mMapController.setZoom(18);
	  
	  mMapView.setBuiltInZoomControls(true);
	  mMapView.setSatellite(false);
	 
	  
	  
  }
  public void btnOnclick(View v){
	if(v.getId() == R.id.btn_myLocation){
		
	}else if(v.getId() == R.id.btn_SOS){
		
	}else if(v.getId() == R.id.btn_otherLocation){
		new AddTask().execute(null,null,null);
	}
	  
  }
  
  @Override
  protected void onPause() {
    super.onPause();
    mLocationManager.removeUpdates(this);		
  }

  @Override
  protected void onResume() {
    super.onResume();
    Criteria criteria = new Criteria();
    String strProvider = mLocationManager.getBestProvider(criteria, true);
    mLocation = mLocationManager.getLastKnownLocation(strProvider);
    mLocationManager.requestLocationUpdates(strProvider, 0, 0, this);
    
    updateOverlay();
  }


  protected void updateOverlay() {
	  mapOverlays = mMapView.getOverlays();
		
	  Drawable drawable = this.getResources().getDrawable(R.drawable.child_marker);
		
		
	  drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
		
		
	  mItemizedoverlay = new MyItemizedOverlay(drawable, this);
		
	  currentLocation1 = new Location(LocationManager.GPS_PROVIDER);
	  currentLocation1.setLatitude(37.5363395);
	  currentLocation1.setLongitude(126.9617961);
	   	
	  OverlayItem item1 = new OverlayItem(getGeoPoint(currentLocation1),
		                         "씨너스 강남", 
		                         "가본적 없는 영화관이다.");
		
	 
   	
	  mItemizedoverlay.addOverlayItem(item1);

	  mItemizedoverlay.pupulate();
		
	  mapOverlays.add(mItemizedoverlay);
		
	  mLocationOverlay = new CustomLocationOverlay();
	  mapOverlays.add(mLocationOverlay);
	  
	  
	  int distanceTo = (int)mLocation.distanceTo(currentLocation1);
	  
	  String text = "진형이와의 거리"+distanceTo+"m";
	  LineOverlay lineOverlay = new LineOverlay(getGeoPoint(mLocation), getGeoPoint(currentLocation1),text);
	  mapOverlays.add(lineOverlay);
  }
  
  @Override
  protected boolean isRouteDisplayed() {
    return false;
  }

  @Override
  public void onLocationChanged(Location location) {
    if (location != null) {
      Log.d(TAG, "location changed");
      if(isBetterLocation(location, mLocation))  {
        mLocation = location;
        GeoPoint geoPoint = getGeoPoint(location);
        mMapController.animateTo(geoPoint);
      }
    }
  }

  @Override
  public void onProviderDisabled(String provider) {}

  @Override
  public void onProviderEnabled(String provider) {}

  @Override
  public void onStatusChanged(String provider, int status, Bundle extras) {}
	
  private boolean isBetterLocation(Location location,Location currentBestLocation) {
    
    if (currentBestLocation == null) {
      return true;
    }

    long timeDelta = location.getTime() - 
                                 currentBestLocation.getTime();
    
    boolean isSignificantlyNewer = timeDelta > TWO_MINUTES;
    boolean isSignificantlyOlder = timeDelta <-TWO_MINUTES;
    boolean isNewer = timeDelta > 0;


    if (isSignificantlyNewer) {
      return true;
    } else if (isSignificantlyOlder) {
      return false;
    }

    int accuracyDelta = (int) (location.getAccuracy() - currentBestLocation.getAccuracy());

    boolean isLessAccurate = accuracyDelta > 0;
    boolean isMoreAccurate = accuracyDelta < 0;
    
    boolean isSignificantlyLessAccurate = accuracyDelta > 200;
  
    boolean isFromSameProvider = isSameProvider(location.getProvider(), currentBestLocation.getProvider());

    if (isMoreAccurate) {
      return true;
    } else if (isNewer && !isLessAccurate) {
      return true;
    } else if (isNewer && !isSignificantlyLessAccurate && isFromSameProvider) {
      return true;
    }
    return false;
  }


  private boolean isSameProvider(String provider1, String provider2) {
    if (provider1 == null) {
      return provider2 == null;
    }
    return provider1.equals(provider2);
  }	

  private GeoPoint getGeoPoint(Location location) {
    if (location == null) {
      return null;
    }
    Double lat = location.getLatitude() * 1E6;
    Double lng = location.getLongitude() * 1E6;
    return new GeoPoint(lat.intValue(), lng.intValue());
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
				String aaa = comUtil.getOthersLocation("821037712173");
				Log.i("TAG", "aaa:"+aaa);
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
  
  
  class MyItemizedOverlay extends ItemizedOverlay<OverlayItem> {
    
    private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
    
    private Context mContext;
    public MyItemizedOverlay(Drawable arg0, Context context) {
      
      super(boundCenterBottom(arg0));
      mContext = context;
    }

    @Override
    protected OverlayItem createItem(int arg0) {
      Log.d(TAG, "create "+arg0);
      return mOverlays.get(arg0);
    }

    @Override
    public int size() {
      return mOverlays.size();
    }
    
    @Override
    public boolean onTap(int i) {
      Toast.makeText(mContext, mOverlays.get(i).getSnippet(),Toast.LENGTH_LONG).show();
      
      return true;
    }

    @Override
    public boolean onTap(GeoPoint arg0, MapView arg1) {
      return super.onTap(arg0, arg1);
    }

    public void addOverlayItem(OverlayItem overlay) {
      mOverlays.add(overlay);
    }	
		
    public void pupulate()  {
      populate();
    }
}
	
  private class CustomLocationOverlay extends Overlay {
    
    @Override
    public boolean draw(Canvas canvas, MapView mapView, boolean shadow, long when) {
      
      super.draw(canvas, mapView, shadow);
      Paint paint = new Paint();
	
      Point myScreenCoords = new Point();

      mapView.getProjection().toPixels(getGeoPoint(mLocation), myScreenCoords);
      
      paint.setStrokeWidth(1);
      paint.setColor(Color.BLUE);
      paint.setStyle(Paint.Style.FILL);
      
      Bitmap bmp = BitmapFactory.decodeResource(getResources(),R.drawable.parent_marker);
      
      canvas.drawBitmap(bmp, myScreenCoords.x, myScreenCoords.y, paint);
      
      canvas.drawText("나의 현재 위치", myScreenCoords.x, myScreenCoords.y, paint);
      
      return true;
    }
  }

}
