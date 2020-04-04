package com.example.bankapplication;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.example.bankapplication.database.DBHelper;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;

public class DayTransaction extends Activity{
	
	TextView name,accountNumber,amount,date;
	ArrayList<Object> list;
	List<String> lv;
	ListView listl;
//	TableLayout tv;
	TableRow tr ;
 String getdate;
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
				
				Intent i = new Intent(DayTransaction.this,Home.class);
				startActivity(i);
				
			}
		});
		
		name = (TextView) findViewById(R.id.name);
		accountNumber = (TextView) findViewById(R.id.ac_no);
		amount = (TextView) findViewById(R.id.amount);
		date = (TextView) findViewById(R.id.date);
		getTime();
		DBHelper DBCon = new DBHelper(getApplicationContext());
		SQLiteDatabase db = DBCon.getWritableDatabase();
		String sql = "select * from History where Date = '"+getdate+"'";
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
	private void getTime() {
		// TODO Auto-generated method stub
		  Calendar c = Calendar.getInstance();
	        System.out.println("Current time => "+c.getTime());

	        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	         getdate = df.format(c.getTime());
	        // formattedDate have current date/time
	     //   Toast.makeText(this, formattedDate, Toast.LENGTH_SHORT).show();


	      // Now we display formattedDate value in TextView
	       
	    }
	}
	



