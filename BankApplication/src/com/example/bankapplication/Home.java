package com.example.bankapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Home extends Activity implements OnClickListener {

	Button bbanking, imps, settings, about,logout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);

		bbanking = (Button) findViewById(R.id.banking);
		imps = (Button) findViewById(R.id.imps);
		settings = (Button) findViewById(R.id.settings);
		about = (Button) findViewById(R.id.aboutUs);
		logout = (Button) findViewById(R.id.logOut);

		bbanking.setOnClickListener(this);
		imps.setOnClickListener(this);
		settings.setOnClickListener(this);
		about.setOnClickListener(this);
		logout.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {

		case R.id.banking:

			//Toast.makeText(getApplicationContext(), "banking", Toast.LENGTH_LONG).show();
			Intent ii = new Intent(this, Bank.class);
			this.startActivity(ii);
			break;

		case R.id.imps:

			//Toast.makeText(getApplicationContext(), "banking", Toast.LENGTH_LONG).show();
			Intent im = new Intent(this, TransactionDetails.class);
			this.startActivity(im);
			break;
			

		case R.id.settings:

			//Toast.makeText(getApplicationContext(), "banking", Toast.LENGTH_LONG).show();
			Intent se = new Intent(this, Settings.class);
			this.startActivity(se);
			break;

		case R.id.aboutUs:

			//Toast.makeText(getApplicationContext(), "banking", Toast.LENGTH_LONG).show();
			Intent ab = new Intent(this, DayTransaction.class);
			this.startActivity(ab);
			break;
			
		case R.id.logOut:
			
			Intent log = new Intent(Home.this,MainActivity.class);
			startActivity(log);
			break;

		default:
			break;
		}

	}

}
