package com.emildesign.navigationapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.emildesign.navigationapplication.NavigationActivity;
import com.example.phonetraker.R;

public class MainActivity extends ActionBarActivity implements OnClickListener {

	private Button parking, getTracking,send;
	private GPSTracker gpsLocation;
	private String provider;
	private Intent mintent;
	public static double latitude = 1;
	public static double longitude = 2;
	private SharedPreferences pref;
	private Editor editor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(com.example.phonetraker.R.layout.activity_main);
		doStart();

		initViews();
		setViews();
	}

	private void doStart() {
		// TODO Auto-generated method stub DBHelper DBCon = new DBHelper(getApplicationContext());
		 DBHelper DBCon = new DBHelper(getApplicationContext());
	  		SQLiteDatabase db = DBCon.getWritableDatabase();
	  		String sql = "select * from Details " ;
	  		Cursor c = db.rawQuery(sql, null);
	  		if (c.getCount()!= 0) {
	  			//Intent re = new Intent(MainActivity.this,Register.class);
	  		//	startActivity(re);
	  				
	  		Toast.makeText(getApplicationContext(), "registration", Toast.LENGTH_LONG).show();
	  		

	  		} else {
	  			Intent re = new Intent(MainActivity.this,Register.class);
	  			startActivity(re);
	  			
	  			Toast.makeText(getApplicationContext(), " register", Toast.LENGTH_LONG).show();
	  		  	
	  		}

		
	}

	private void initViews() {
		// TODO Auto-generated method stub
		parking = (Button) findViewById(R.id.parking);
		getTracking = (Button) findViewById(R.id.getTrackikng);
		send = (Button) findViewById(R.id.sms);
		
		provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
		gpsLocation = new GPSTracker(MainActivity.this);
		
		pref = getApplicationContext().getSharedPreferences("MyPref", 0);
		editor = pref.edit();

	}

	private void setViews() {
		// TODO Auto-generated method stub
		parking.setOnClickListener(this);
		getTracking.setOnClickListener(this);
		send.setOnClickListener(this);
		
	}
	
	private void turnGPSOn(){

	    if(!provider.contains("gps")){ //if gps is disabled
	        final Intent poke = new Intent();
	        poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider"); 
	        poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
	        poke.setData(Uri.parse("3")); 
	        sendBroadcast(poke);
	    }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.parking:
			if (gpsLocation.canGetLocation()) {
				latitude = gpsLocation.getLatitude();
				longitude = gpsLocation.getLongitude();
				Toast.makeText(getApplicationContext(), "Latitude:"+latitude+"Longitude:"+longitude, Toast.LENGTH_LONG).show();
				
				editor.putString("LAT_VALUE_PARK", String.valueOf(latitude));
				editor.putString("LONG_VALUE_PARK",
						String.valueOf(longitude));
				editor.commit();
				
			}else {
				// showSettingsAlert("GPS");
				Toast.makeText(getApplicationContext(), "No GPS",
						Toast.LENGTH_SHORT).show();
				turnGPSOn();
			}
			
			break;

		case R.id.getTrackikng:
			if ( pref.getString("LAT_VALUE_PARK", null) != null) {
				
			if (gpsLocation.canGetLocation()) {
				latitude = gpsLocation.getLatitude();
				longitude = gpsLocation.getLongitude();

				editor.putString("LAT_VALUE_FIND", String.valueOf(latitude));
				editor.putString("LONG_VALUE_FIND",
						String.valueOf(longitude));
				editor.commit();
				
				 mintent = new Intent(getApplicationContext(),NavigationActivity.class);
					startActivity(mintent);
				
			}else {
				// showSettingsAlert("GPS");
				Toast.makeText(getApplicationContext(), "No GPS",
						Toast.LENGTH_SHORT).show();
				turnGPSOn();
			}
			}else {
				Toast.makeText(getApplicationContext(), "No Gps Location",
						Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.sms:
			sendingSms();
			break;

		default:
			break;
		}

	}

	private void sendingSms() {
		// TODO Auto-generated method stub
		String num = null;

		DBHelper DBCon = new DBHelper(getApplicationContext());
		SQLiteDatabase db = DBCon.getWritableDatabase();
		String sql = "select * from Details " ;
		Cursor c = db.rawQuery(sql, null);
		if (c!= null) {
			 while(c.moveToNext()){
            	 num = c.getString(c.getColumnIndex("Phone"));
            	 
            	 Toast.makeText(getApplicationContext(), "Sending to:"+num,
     					Toast.LENGTH_LONG).show();
			
		}
		}
		
		
		latitude = gpsLocation.getLatitude();
		longitude = gpsLocation.getLongitude();
		String sms = "Avg speed is:40,https://www.google.co.in/maps/@"+latitude+","+longitude+",18z?hl=en";
		try {
			SmsManager smsManager = SmsManager.getDefault();
			smsManager.sendTextMessage("+91"+num, null, sms, null, null);
			Toast.makeText(getApplicationContext(), "SMS Sent!",
					Toast.LENGTH_LONG).show();
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(),
					"SMS faild, please try again later!",
					Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
		
		
	}
}
