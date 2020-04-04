package com.example.bankapplication;

import com.example.bankapplication.database.DBHelper;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

public class SplashScreen extends Activity {
	ImageView iv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first_splash);
		iv=(ImageView)findViewById(R.id.imageView1);
		Thread background=new Thread()
		{
			public void run()
			{
			try{
				sleep(5000);
				doStart();
				/*final ConnectivityManager connMng=(ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
				final android.net.NetworkInfo wifi=connMng.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
				final android.net.NetworkInfo mobile=connMng.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
				if(wifi.isAvailable()||mobile.isAvailable())
				{
					Intent intent=new Intent(getApplicationContext(),Second.class);
					startActivity(intent);
					finish();
				}else
				{
					AlertDialog.Builder alrt=new AlertDialog.Builder(getApplicationContext());
					alrt.setTitle("Alert");
					alrt.setMessage("Sorry You Dont have InternetConnection");
					alrt.show();
					finish();
					onDestroy();
					
					
				}*/
				
				finish();	
			}catch(Exception e)
			{
				
			}
		}

			private void doStart() {
				// TODO Auto-generated method stub
				
				
					// TODO Auto-generated method stub DBHelper DBCon = new DBHelper(getApplicationContext());
					 DBHelper DBCon = new DBHelper(getApplicationContext());
				  		SQLiteDatabase db = DBCon.getWritableDatabase();
				  		String sql = "select * from Details " ;
				  		Cursor c = db.rawQuery(sql, null);
				  		if (c.getCount()!= 0) {
				  			Intent re = new Intent(SplashScreen.this,MainActivity.class);
				  			startActivity(re);
				  				
				  		Toast.makeText(getApplicationContext(), "Main", Toast.LENGTH_LONG).show();
				  		

				  		} else {
				  			Intent re = new Intent(SplashScreen.this,SignUp.class);
				  			startActivity(re);
				  			
				  			Toast.makeText(getApplicationContext(), " Signup", Toast.LENGTH_LONG).show();
				  		  	
				  		}
				
			}
			
			
		};
		background.start();
	}
	@Override
	protected void onDestroy() {
		
		super.onDestroy();
	}

}
