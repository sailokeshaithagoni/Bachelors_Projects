package com.emildesign.navigationapplication;

import com.example.phonetraker.R;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends Activity{
	EditText name,phone;
	Button submit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		
		name = (EditText) findViewById(R.id.name);
		phone = (EditText) findViewById(R.id.phone);
		submit = (Button) findViewById(R.id.rsubmit);
		submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// TODO Auto-generated method stub
				
				String u_name = name.getText().toString();
			String u_phone = phone.getText().toString();
		if((u_phone == null)|| (u_name == null)){
			Toast.makeText(getApplicationContext(), "Please enter correct details", Toast.LENGTH_LONG).show();
		}else{
			
		
				DBHelper dbh = new DBHelper(getApplicationContext());
				ContentValues cv = new ContentValues();
				SQLiteDatabase db = dbh.getWritableDatabase();
				cv.put("UserName", u_name);
				cv.put("phone", u_phone);
				db.insert("Details", null, cv);
				Toast.makeText(getApplicationContext(), "Registration success", Toast.LENGTH_LONG).show();
				db.close();
	    	 
				Intent in = new Intent(Register.this,MainActivity.class);
				startActivity(in);
		}
				
			}
		});
		
	}
	
	

}
