package com.example.bankapplication;

import com.example.bankapplication.database.DBHelper;
import android.support.v7.app.ActionBarActivity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements OnClickListener{
	EditText userName,password;
	Button submit;
  TextView sign_up;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		buildEvents();
		
	}

	private void buildEvents() {
		// TODO Auto-generated method stub
	//	sign_up.setOnClickListener(this);
		submit.setOnClickListener(this);
		
	}

	private void init() {
		// TODO Auto-generated method stub
		userName = (EditText) findViewById(R.id.user_Name);
		password = (EditText) findViewById(R.id.password);
		//sign_up = (TextView) findViewById(R.id.signUp);
		submit = (Button) findViewById(R.id.submit);
		
	}
	@Override
	public void onClick(View v) 
	
	{
			   
		   
		switch (v.getId())
		{
		case R.id.submit:
			//Toast.makeText(getApplicationContext(), "inserted", Toast.LENGTH_LONG).show();
			doLogin();
			break;
			//case R.id.signUp:
			//	Intent intent = new Intent(this,SignUp.class);
				//startActivity(intent);
				//break;
		}
	}

	private void doLogin() {
		// TODO Auto-generated method stub
		String Lname,Lpassword;
		Lname = userName.getText().toString();
		Lpassword = password.getText().toString();
		if((Lname == null) || (Lpassword == null) || (Lname.equals("") || (Lpassword.equals("")))){
			Toast.makeText(getApplicationContext(), "Please fill empty feilds", Toast.LENGTH_LONG).show();
		}else{
	
		DBHelper DBCon = new DBHelper(getApplicationContext());
		SQLiteDatabase db = DBCon.getWritableDatabase();
		String sql = "select * from Details where UserName = '"+ Lname+"' and Password = '"+Lpassword+"'" ;
		Cursor c = db.rawQuery(sql, null);
		if (c.getCount() != 0) {
			Intent i = new Intent(MainActivity.this, Home.class);
			startActivity(i);
			Toast.makeText(getApplicationContext(), " success",
					Toast.LENGTH_LONG).show();

		} else {
			Toast.makeText(getApplicationContext(), "not success",
					Toast.LENGTH_LONG).show();
		}
		}

	}
	}

	

