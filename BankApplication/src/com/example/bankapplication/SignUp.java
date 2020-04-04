package com.example.bankapplication;

import com.example.bankapplication.database.DBHelper;

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

public class SignUp extends Activity {
	EditText signName,signPassword,signCnfPassword,transactionPassword;
	Button submit;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup);
		init();
		submit = (Button) findViewById(R.id.button1);
		submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String userName,password,cnfPassword,traPassword;
				userName = signName.getText().toString();
				password = signPassword.getText().toString();
				cnfPassword = signCnfPassword.getText().toString();
				traPassword = transactionPassword.getText().toString();
				if((userName == null) || (password == null) || (cnfPassword == null) || (traPassword == null)||
						(userName.equals("")|| (password.equals("")|| (cnfPassword.equals("")|| (traPassword.equals("")))))){
					Toast.makeText(getApplicationContext(), "Please fill empty feilds", Toast.LENGTH_LONG).show();
				}else{
				
				DBHelper dbh = new DBHelper(getApplicationContext());
				ContentValues cv = new ContentValues();
				SQLiteDatabase db = dbh.getWritableDatabase();
				cv.put("UserName", userName);
				cv.put("Password", password);
				cv.put("ConformPassword", cnfPassword);
				cv.put("TransactionPassword", traPassword);
				db.insert("Details", null, cv);
				Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_LONG).show();
				db.close();
				Intent intent = new Intent(SignUp.this,MainActivity.class);
				startActivity(intent);
				
			}
			}
		});
	}


	

	private void init() {
		// TODO Auto-generated method stub
		signName = (EditText) findViewById(R.id.signuserName);
		signPassword = (EditText) findViewById(R.id.signup_Password);
		signCnfPassword = (EditText) findViewById(R.id.signup_CnfPassword);
		transactionPassword = (EditText) findViewById(R.id.transaction_Password);
	//	submit = (Button) findViewById(R.id.submit);
		
	}


	
	
	

}
