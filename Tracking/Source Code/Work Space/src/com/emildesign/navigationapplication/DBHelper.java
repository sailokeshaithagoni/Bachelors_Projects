package com.emildesign.navigationapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{

	public DBHelper(Context context) {
		super(context, "Details", null, 1);
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table Details( ID INTEGER PRIMARY KEY   AUTOINCREMENT,UserName varchar(20),Phone varchar(20))");
		//db.execSQL("DROP TABLE IF EXITS create table History(ID INTEGER PRIMARY KEY   AUTOINCREMENT,Name varchar(20),AccountNumber varchar(20),Amount Varchar(20),Date varchar(20))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXITS Details");
		//db.execSQL("DROP TABLE IF EXITS History");
			
			onCreate (db);
	}

	

}
