package com.example.bankapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bankapplication.R;
import com.example.bankapplication.database.DBHelper;

public class Settings extends Activity{
	EditText uname,uPassword,uCnfPassword;
	Button update;
	String name ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		Button back = (Button) findViewById(R.id.sback);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Settings.this,Home.class);
				startActivity(i);
				
			}
		});
		init();
		DBHelper DBCon = new DBHelper(getApplicationContext());
		SQLiteDatabase db = DBCon.getWritableDatabase();
		String sql = "select * from Details";
		Cursor c = db.rawQuery(sql, null);
		   if(c != null)
	        {
	            while(c.moveToNext()){
	            	 name = c.getString(c.getColumnIndex("UserName"));
	                 String password = c.getString(c.getColumnIndex("Password"));
	                 String cnfPassword = c.getString(c.getColumnIndex("ConformPassword"));
                      uname.setText(name);
                      uPassword.setText(password);
                      uCnfPassword.setText(cnfPassword);
	               

	               	            }
	        }
		   
		   update.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String u_name,u_pass,u_cnf;
				u_name =uname.getText().toString();
				u_pass = uPassword.getText().toString();
				u_cnf = uCnfPassword.getText().toString();
				DBHelper DBCon = new DBHelper(getApplicationContext());
				SQLiteDatabase db = DBCon.getWritableDatabase();
				String sql = "update Details set UserName ='"+u_name+"' ,Password = '"+u_pass+"' , ConformPassword = '"+u_cnf+"' where UserName = '"+name+"'" ;
				Cursor c = db.rawQuery(sql, null);
				   if(c != null)
			        {
					   Intent i = new Intent(Settings.this,Home.class);
						startActivity(i);
						
			        Toast.makeText(getApplicationContext(), "Update Successfully", Toast.LENGTH_LONG).show();
			        }
				   else{
					   Toast.makeText(getApplicationContext(), "Update Not Completd", Toast.LENGTH_LONG).show();
				   }
			}
		});
		}
		
		
		
	
	private void init() {
		// TODO Auto-generated method stub
		uname = (EditText) findViewById(R.id.uName);
		uPassword = (EditText) findViewById(R.id.uPass);
		uCnfPassword = (EditText) findViewById(R.id.uCnfPass);
		update = (Button) findViewById(R.id.update);
	}

}

