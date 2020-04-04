package com.example.bankapplication;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.example.bankapplication.R;
import com.example.bankapplication.database.DBHelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TransforFunds extends Activity{
	
TextView amount;
EditText name,accNumber,trAmount;
Context context = this;
String formattedDate,hname,accnumber,tamount;
String realAmount ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.transforefunds);
		
		amount = (TextView) findViewById(R.id.totalAmount);
		amount.setText("10000");
		name = (EditText) findViewById(R.id.holderName);
		accNumber = (EditText) findViewById(R.id.accNumber);
		trAmount = (EditText) findViewById(R.id.transfoerAmount);
	   Button	tsub = (Button) findViewById(R.id.tsubmit);
		tsub.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 hname = name.getText().toString();
				 accnumber= accNumber.getText().toString();
				 tamount = trAmount.getText().toString();
				// TODO Auto-generated method stub
				if((hname == null) || (accnumber == null) || (tamount == null) ||(hname.equals("") || (accnumber.equals("") || (tamount.equals(""))))){
					Toast.makeText(getApplicationContext(), "Please fill Empty Feild", Toast.LENGTH_LONG).show();
				}
				
				final AlertDialog.Builder alert = new AlertDialog.Builder(context);
	        	alert.setMessage("Enter TransactionPassword"); //Message here
	 
	            final EditText input = new EditText(context);
	            alert.setView(input);
	 
	        	alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
	        	public void onClick(DialogInterface dialog, int whichButton) {
	        	 String srt = input.getEditableText().toString();
	        	 
	        	 
	        	 DBHelper DBCon = new DBHelper(getApplicationContext());
	     		SQLiteDatabase db = DBCon.getWritableDatabase();
	     		String sql = "select * from Details where TransactionPassword = '"+srt+"'" ;
	     		Cursor c = db.rawQuery(sql, null);
	     		if (c!= null) {
	     			dobackground();
	     			Intent intent = new Intent(TransforFunds.this, TransactionDetails.class);
	     			startActivity(intent);
	     			Toast.makeText(getApplicationContext(), "success",
	     					Toast.LENGTH_LONG).show();

	     		} else {
	     			Toast.makeText(getApplicationContext(), "not success",
	     					Toast.LENGTH_LONG).show();
	     			AlertDialog alertDialog = alert.create();
		        	alertDialog.show();
	     			
	     		}
	        	 
	        	}

				private void dobackground() {
					// TODO Auto-generated method stub
					
					
					
					 getTime();
					 
					 String totalamount = amount.getText().toString();
					 int i = Integer.parseInt( totalamount );
					 int j = Integer.parseInt(tamount);
					 realAmount = ""+(i-j);
					 amount.setText(realAmount);
						DBHelper dbh = new DBHelper(getApplicationContext());
						ContentValues cv = new ContentValues();
						SQLiteDatabase db = dbh.getWritableDatabase();
						cv.put("Name", hname);
						cv.put("AccountNumber", accnumber);
						cv.put("Amount", tamount);
						cv.put("Date", formattedDate);
						db.insert("History", null, cv);
						Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_LONG).show();
						db.close();
					
					
					
					
					
				}
	        }); 
	        	alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
	        	  public void onClick(DialogInterface dialog, int whichButton) {
	        	    
	        		  dialog.cancel();
	        	  }
	        });
	        	AlertDialog alertDialog = alert.create();
	        	alertDialog.show();
	     }
	  });
	 }


	private void getTime() {
		// TODO Auto-generated method stub
		  Calendar c = Calendar.getInstance();
	        System.out.println("Current time => "+c.getTime());

	        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	         formattedDate = df.format(c.getTime());
	        // formattedDate have current date/time
	     //   Toast.makeText(this, formattedDate, Toast.LENGTH_SHORT).show();


	      // Now we display formattedDate value in TextView
	       
	    }
		
	}
	





