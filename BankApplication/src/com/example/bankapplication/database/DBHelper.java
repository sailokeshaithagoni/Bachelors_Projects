package com.example.bankapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{

	public DBHelper(Context context) {
		super(context, "ATM", null, 4);
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(" create table Details( ID INTEGER PRIMARY KEY   AUTOINCREMENT,UserName varchar(20),Password varchar(20),ConformPassword varchar(20),TransactionPassword varchar(20))");
		db.execSQL(" create table History(ID INTEGER PRIMARY KEY   AUTOINCREMENT,Name varchar(20),AccountNumber varchar(20),Amount Varchar(20),Date varchar(20))");
	//	db.execSQL(" create table (ID INTEGER PRIMARY KEY   AUTOINCREMENT,Name varchar(20),AccountNumber varchar(20),Amount Varchar(20),Date varchar(20))");
		db.execSQL("INSERT INTO History VALUES (0,'Ramana' , 905210100008241 ,2500,'2014-01-01')");
		db.execSQL("INSERT INTO History VALUES (1,'vidya' , 905210100008256 ,4000,'2014-03-01')");
		db.execSQL("INSERT INTO History VALUES (2,'Lavanya' , 905210100009876 ,10000,'2014-12-21')");
		db.execSQL("INSERT INTO History VALUES (3,'sudha' , 905210100009245 ,8000,'2014-12-20')");
		
		
		
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXITS Details");
		//db.execSQL("DROP TABLE IF EXITS History");
			
			onCreate (db);
	}

}
