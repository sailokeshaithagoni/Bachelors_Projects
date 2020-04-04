package com.example.bankapplication;

import android.app.Activity;
import android.app.AlertDialog;
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
import android.widget.Toast;

public class Bank extends Activity implements OnClickListener{
	
	
	Button balenceEnquery,transforeFunds,miniStatement;
	Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bank);
		init();
		clickEvents();
	}
	private void clickEvents() {
		// TODO Auto-generated method stub
		balenceEnquery.setOnClickListener(this);
		transforeFunds.setOnClickListener(this);
		miniStatement.setOnClickListener(this);
	}
	private void init() {
		// TODO Auto-generated method stub
		balenceEnquery = (Button) findViewById(R.id.balenceEnquiry);
		transforeFunds = (Button) findViewById(R.id.TransforeFunds);
		miniStatement = (Button) findViewById(R.id.ministatement);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.balenceEnquiry:
			showbal();
			break;
			case R.id.TransforeFunds:
				
				Intent tranfoer = new Intent(this,TransforFunds.class);
				startActivity(tranfoer);
				break;
			case R.id.ministatement:
				Intent min = new Intent(this,MiniStatement.class);
				startActivity(min);
				
				break;
		}
		
	}
	private void showbal() {
		// TODO Auto-generated method stub
		final AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("Account Balance"); //Set Alert dialog title here
    	alert.setMessage("1000.00cr");

    	alert.setPositiveButton("Trasactions", new DialogInterface.OnClickListener() {
    	public void onClick(DialogInterface dialog, int whichButton) {
    		Intent tr = new Intent(Bank.this,TransforFunds.class);
    		startActivity(tr);
    	 
    	 
    	 
    	}
    }); 
    	alert.setNegativeButton("Back", new DialogInterface.OnClickListener() {
    	  public void onClick(DialogInterface dialog, int whichButton) {
    	    
    		  dialog.cancel();
    	  }
    });
    	AlertDialog alertDialog = alert.create();
    	alertDialog.show();
 
}
}

