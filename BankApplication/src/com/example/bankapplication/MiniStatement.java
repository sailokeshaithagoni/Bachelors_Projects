package com.example.bankapplication;

import java.util.ArrayList;
import java.util.List;

import com.example.bankapplication.database.DBHelper;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MiniStatement extends Activity {
	TextView name,accountNumber,amount,date;
	ArrayList<Object> list;
	List<String> lv;
	ListView listl;
//	TableLayout tv;
	TableRow tr ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.transactiondetails);
		Button back = (Button) findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent i = new Intent(MiniStatement.this,Home.class);
				startActivity(i);
				
			}
		});
		
		
		name = (TextView) findViewById(R.id.name);
		accountNumber = (TextView) findViewById(R.id.ac_no);
		amount = (TextView) findViewById(R.id.amount);
		date = (TextView) findViewById(R.id.date);
		
		DBHelper DBCon = new DBHelper(getApplicationContext());
		SQLiteDatabase db = DBCon.getWritableDatabase();
		String sql = "select * from History";
		// listl = (ListView) findViewById(R.id.listView);
		Cursor c = db.rawQuery(sql, null);
		   if(c != null)
	        {
			  	
	            while(c.moveToNext()){
	            	
	            	name.append(c.getString(c.getColumnIndex("Name")) + "\n" );
	            	accountNumber.append(c.getString(c.getColumnIndex("AccountNumber")) + "\n" );
	            	amount.append(c.getString(c.getColumnIndex("Amount")) + "\n" );
	            	date.append(c.getString(c.getColumnIndex("Date")) + "\n" );
	            	
//	            
	            	
	               	            }
	        }
		   
	
		}
	}
	


