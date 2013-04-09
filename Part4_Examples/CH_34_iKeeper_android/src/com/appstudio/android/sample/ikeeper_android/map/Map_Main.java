package com.appstudio.android.sample.ikeeper_android.map;

import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import com.appstudio.android.sample.ikeeper_android.R;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;



public class Map_Main extends MapActivity {
	private int defaultZoom = 18;
	private MapView 			mapView;
	private MapController  		mapController;
	private LocationManager 	locManager;
	private LocationListener 	locListener;
	private Location 			currentLocation;
	
	private List<Overlay> 		mapOverlays;
	private GpsMapOverlay 		gpsOverlay;
	private static final int TWO_MINUTES = 1000 * 60 * 2;
	
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_main);
        
       // init();
       
    }



	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
    
//    private void init(){
//    	 mapView = (MapView)findViewById(R.id.mapview_gpsmap);
//         mapView.setBuiltInZoomControls(true);
//         mapView.setSatellite(false);
//         
//         mapController = mapView.getController();
//         mapController.setZoom(defaultZoom);
//         
//         processLocationInfo();
////         GeoPoint onCreateGeo = getGeoPoint(currentLocation);
////         if(onCreateGeo!=null){
////        	 mapController.animateTo(onCreateGeo);
////         }else{
////        	 Log.i("TAG", "onCreateGeo!=null else");
////         }
//         
//         updateOverlay(currentLocation);
//         if(!locManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
//        	 checkGPS();
//         }
//         
//         
//         mLocationManager = (LocationManager)
//                 getSystemService(Context.LOCATION_SERVICE);
//	
// mMapController = mMapView.getController();
// mMapView.setBuiltInZoomControls(true);
// List<Overlay> mapOverlays = mMapView.getOverlays();
// Drawable drawable = this.getResources().
//                             getDrawable(R.drawable.office);
// 
// drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
//                             drawable.getIntrinsicHeight());
// 
// mItemizedoverlay = new MyItemizedOverlay(drawable, this);
// OverlayItem item1 = new OverlayItem(
//                          new GeoPoint(37498006, 127026447),
//                          "씨너스 강남", 
//                          "가본적 없는 영화관이다.");
//
// OverlayItem item2 = new OverlayItem(
//                         new GeoPoint(37654827, 127061198 ),
//                         "롯데백화점 노원", 
//                         "자주가는 영화관이다.");
//	
// mItemizedoverlay.addOverlayItem(item1);
// mItemizedoverlay.addOverlayItem(item2);
// mItemizedoverlay.pupulate();
// mLocationOverlay = new CustomLocationOverlay();
// mapOverlays.add(mItemizedoverlay);
// mapOverlays.add(mLocationOverlay);
//         
//         
//    }
//    
//    
//    private void checkGPS(){
//    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setMessage("Your GPS is disabled! Would you like to enable it?")
//                .setCancelable(false)
//                .setPositiveButton("Enable GPS",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                            	 Intent gpsOptionsIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                                 startActivity(gpsOptionsIntent);
//                            }
//                    })
//                .setNegativeButton("Do nothing",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                dialog.cancel();
//                            }
//                    });
//        AlertDialog alert = builder.create();
//        alert.show();
//    }
//  
//    
//    public void processLocationInfo(){
//
//    	//위치 매니저를 시스템으로 부터 받음
//        locManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
//        
//        if(locManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
//        	Log.i("TAG", "GPS 동작함");
//        }else{
//        	Log.i("TAG", "GPS 동작안함");
//        }
//        
//      //위치리스너 초기화
//        locListener = new MyLocationListener();
//        // 위치 매니저에 위치 리스너를 세팅
//        //위치 리스너에서 10000ms(10초) 마다 100미터 이상 이동이 발견 되면 업데이트를 하려한다.
//        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this.locListener);
//        locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this.locListener);
//        
//        //기기에 마지막 위치정보
//       // currentLocation = locM.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//        currentLocation =getBestLocation(); 
//        
//    }
//    
//    private boolean isBetterLocation(Location location, Location currentBestLocation) {
//
//if (currentBestLocation == null) {
//// 최선의 위치 정보가 없으면 새로운 위치 정보는 
//// 무조건 최선의 위치 정보가 된다.
//return true;
//}
//
//// 새로운 위치 정보가 너무 옛날 정보인지, 
//// 아주 최신 정보인지, 비교적 최신 정보인지를 구분
//long timeDelta = location.getTime() - 
//             currentBestLocation.getTime();
//
//boolean isSignificantlyNewer = timeDelta > TWO_MINUTES;
//boolean isSignificantlyOlder = timeDelta <-TWO_MINUTES;
//boolean isNewer = timeDelta > 0;
//
//// 새로운 위치가 유지하고 있던 최선의 위치보다 
//// 2분 이상 최신이면 새로운 위치 정보를 사용
//if (isSignificantlyNewer) {
//return true;
//// 새로운 위치가 유지하던 최선의 위치보다 2분 이상 오래되었으면 
//// 유지하던 최선의 위치 사용
//} else if (isSignificantlyOlder) {
//return false;
//}
//
//// 어느 위치의 정확도가 더 높은지, 
//// 새로운 위치의 정확도가 매우 떨어지는지를 식별
//int accuracyDelta = (int) (location.getAccuracy() - 
//        currentBestLocation.getAccuracy());
//
//boolean isLessAccurate = accuracyDelta > 0;
//boolean isMoreAccurate = accuracyDelta < 0;
//
//boolean isSignificantlyLessAccurate = accuracyDelta > 200;
//
//// 위치 제공자가 같은지를 식별
//boolean isFromSameProvider = 
//isSameProvider(location.getProvider(),
//        currentBestLocation.getProvider());
//
//// 시간과 정확도를 기반으로 새로운 위치를 사용할지를 결정
//if (isMoreAccurate) {
//return true;
//} else if (isNewer && !isLessAccurate) {
//return true;
//} else if (isNewer && !isSignificantlyLessAccurate 
//                   && isFromSameProvider) {
//
//return true;
//}
//return false;
//}
//
//private boolean isSameProvider(String provider1, 
//                        String provider2) {
//
//if (provider1 == null) {
//return provider2 == null;
//}
//return provider1.equals(provider2);
//}	
//    
//    public GeoPoint getGeoPoint(Location location) {
//
//		 if (location == null) {
//
//		 return null;
//
//		 }
//
//		 Double lat = location.getLatitude() * 1E6;
//
//		 Double lng = location.getLongitude() * 1E6;
//
//		 return new GeoPoint(lat.intValue(), lng.intValue());
//
//	}
//    
//    protected GeoPoint updateOverlay(Location location) {
//		//기존에 화면에 찍어둔 오버레이를 지운다.
//    	mapOverlays = mapView.getOverlays();
//    	if(mapOverlays.size()>0){
//    		Log.i("TAG", "mapOverlays clear"+mapOverlays.size());
//  	
//    		mapOverlays.clear();
//    	}else{
//    		Log.i("TAG", "mapOverlays non clear");
//    	}
//    	
//    	//Location 객체를 가지고 GeoPoint 객체를 얻어내는 메소드
//    	GeoPoint geoPoint = getGeoPoint(location);
//    	mapController.animateTo(geoPoint);
//    	
//    	//현재 위치를 표시할 이미지
//    	Drawable marker = getResources().getDrawable(R.drawable.androidmarker);
//    	marker.setBounds(0, 0, marker.getIntrinsicWidth(), marker.getIntrinsicHeight());
//    	
//    	//GpsMapOverlay를 이용하여 현재 위치 마커를 찍을 오버레이를 생성
//    	gpsOverlay = new GpsMapOverlay(marker, this);
//    	// touch event의 null point 버그를 방지 하기 위해서 마커찍고 바로 populate 시킴
//    	gpsOverlay.mPopulate();
//    	
//    	//현재 위치 마커 정의
////    	OverlayItem overlayItem = new OverlayItem(geoPoint, "here",  location.getLatitude()+"::"+location.getLongitude());
////    	gpsOverlay.addOverlay(overlayItem);
//    	
////    	Log.i("TAG", "childrenLocations.size():"+childrenLocations.size());
////		for (int inx = 0; inx < childrenLocations.size(); inx++) {
////			//아이의 위치 Location을 Geo좌표료 변환한다.
////			GeoPoint childGeo = comUtil.getGeoPoint(childrenLocations.get(inx));
////			gpsOverlay.addOverlay(new OverlayItem(childGeo, "aaaaaaaaaa",""  ));
////			
////			int distanceTo = (int)currentLocation.distanceTo(childrenLocations.get(inx));
////			
////			//정해진 미터를 벗어낫을 경우 처리
////			if(distanceTo>300){
////				
////			}
////			String text = "진형이와의 거리"+distanceTo+"m";
////	    	LineOverlay lineOverlay = new LineOverlay(geoPoint, childGeo,text);
////	    	mapView.getOverlays().add(lineOverlay);
////		}
////		
////    	mapView.getOverlays().add(gpsOverlay);
//    	
//    	
//    	
//    	
//    	return geoPoint;
//    	
//	}
//    
//    
//    public class MyLocationListener implements LocationListener {
//
//		@Override
//		public void onProviderDisabled(String provider) {
//			Log.d("TAG", "GPS disabled : " + provider);
//			
//		}
//
//		@Override
//		public void onProviderEnabled(String provider) {
//			Log.d("TAG", "GPS Enabled : " + provider);
//			
//			
//		}
//
//		@Override
//		public void onStatusChanged(String provider, int status, Bundle extras) {
//			Log.d("TAG", "onStatusChanged : " + provider + " & status = "+ status);
//			
//		}
//
//		@Override
//		public void onLocationChanged(android.location.Location arg0) {
//			// TODO Auto-generated method stub
//			
//		}
//    	
//    }
//	@Override
//	protected boolean isRouteDisplayed() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//    
//	@Override
//	protected void onDestroy() {
//		if(locListener!=null){
//			locManager.removeUpdates(locListener);
//		}
//		super.onDestroy();
//	}
//   
}