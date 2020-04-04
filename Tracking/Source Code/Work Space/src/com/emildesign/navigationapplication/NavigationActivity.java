package com.emildesign.navigationapplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class NavigationActivity extends FragmentActivity {

	private static LatLng park_latLong = new LatLng(17.4417, 78.3917);
	private static LatLng find_latLong = new LatLng(17.4500, 78.5000);
	private static final LatLng FRANKFURT = new LatLng(17.4372, 78.3444);
	private GoogleMap map;
	private SupportMapFragment fragment;
	private LatLngBounds latlngBounds;
	private Button back;
	private Polyline newPolyline;
	private int width, height;
	private SharedPreferences pref;
	private String lat_value_park;
	private String long_value_park;
	private String lat_value_find;
	private String long_value_find;
	private Marker location_marker;
	private boolean refresh_var = true;
	private boolean pause_var = false;
	private GPSTracker gpsLocation;
	private String provider;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(com.example.phonetraker.R.layout.activity_navigation);
		
		back = (Button) findViewById(com.example.phonetraker.R.id.back);
		
		pref = getApplicationContext().getSharedPreferences("MyPref", 0);

		lat_value_park = pref.getString("LAT_VALUE_PARK", null);
		long_value_park = pref.getString("LONG_VALUE_PARK", null);
		lat_value_find = pref.getString("LAT_VALUE_FIND", null);
		long_value_find = pref.getString("LONG_VALUE_FIND", null);
		
		provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
		gpsLocation = new GPSTracker(NavigationActivity.this);
		
		park_latLong = new LatLng(Double.parseDouble(lat_value_park), Double.parseDouble(long_value_park));
		find_latLong = new LatLng(Double.parseDouble(lat_value_find), Double.parseDouble(long_value_find));
		
		getSreenDimanstions();
	    fragment = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(com.example.phonetraker.R.id.map));
		map = fragment.getMap(); 	
		
		findDirections( park_latLong.latitude, park_latLong.longitude,find_latLong.latitude, find_latLong.longitude, GMapV2Direction.MODE_DRIVING );
		
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
			}
		});
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		
		finish();
	}
	
	/*@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		refresh_var = false;
		pause_var = false;
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Handler handler1 = new Handler();
		handler1.postDelayed(new Runnable() {
			public void run() {
				
				if (pause_var == true) {
					Toast.makeText(getApplicationContext(), "Resumee",
							Toast.LENGTH_SHORT).show();
					if (gpsLocation.canGetLocation()) {
						lat_value_find =String.valueOf(gpsLocation.getLatitude()) ;
						long_value_find =String.valueOf( gpsLocation.getLongitude());
						 find_latLong = new LatLng(Double.parseDouble(lat_value_find), Double.parseDouble(long_value_find));
						 
						findDirections( park_latLong.latitude, park_latLong.longitude,find_latLong.latitude, find_latLong.longitude, GMapV2Direction.MODE_DRIVING );
						
					}else {
						// showSettingsAlert("GPS");
						Toast.makeText(getApplicationContext(), "No GPS",
								Toast.LENGTH_SHORT).show();
					}
					
				}
			}
		},3000);
		
	}*/

	public void handleGetDirectionsResult(ArrayList<LatLng> directionPoints) {
		PolylineOptions rectLine = new PolylineOptions().width(10).color(Color.RED);

		for(int i = 0 ; i < directionPoints.size() ; i++) 
		{
			rectLine.add(directionPoints.get(i));
		}
		if (newPolyline != null)
		{
			newPolyline.remove();
		}
		newPolyline = map.addPolyline(rectLine);
		
		latlngBounds = createLatLngBoundsObject(park_latLong, find_latLong);
		 location_marker = map.addMarker(new MarkerOptions()
			.position(park_latLong).title("Parking").snippet(""));
		 
		 location_marker = map.addMarker(new MarkerOptions()
			.position(find_latLong).title("You").snippet(""));
		 
//        map.animateCamera(CameraUpdateFactory.newLatLngBounds(latlngBounds, width, height, 15));
		 map.moveCamera(CameraUpdateFactory.newLatLngZoom(park_latLong, 15));
		 pause_var = true;
        
	}
	
	private void getSreenDimanstions()
	{
		Display display = getWindowManager().getDefaultDisplay();
		width = display.getWidth(); 
		height = display.getHeight(); 
	}
	
	private LatLngBounds createLatLngBoundsObject(LatLng firstLocation, LatLng secondLocation)
	{
		if (firstLocation != null && secondLocation != null)
		{
			LatLngBounds.Builder builder = new LatLngBounds.Builder();    
			builder.include(firstLocation).include(secondLocation);
			
			return builder.build();
		}
		return null;
	}
	
	public void findDirections(double fromPositionDoubleLat, double fromPositionDoubleLong, double toPositionDoubleLat, double toPositionDoubleLong, String mode)
	{
		Map<String, String> map = new HashMap<String, String>();
		map.put(GetDirectionsAsyncTask.USER_CURRENT_LAT, String.valueOf(fromPositionDoubleLat));
		map.put(GetDirectionsAsyncTask.USER_CURRENT_LONG, String.valueOf(fromPositionDoubleLong));
		map.put(GetDirectionsAsyncTask.DESTINATION_LAT, String.valueOf(toPositionDoubleLat));
		map.put(GetDirectionsAsyncTask.DESTINATION_LONG, String.valueOf(toPositionDoubleLong));
		map.put(GetDirectionsAsyncTask.DIRECTIONS_MODE, mode);
		
		GetDirectionsAsyncTask asyncTask = new GetDirectionsAsyncTask(this);
		asyncTask.execute(map);	
	}
}
