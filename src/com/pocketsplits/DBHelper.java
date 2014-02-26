package com.pocketsplits;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{

	public static final String MEETS_TABLE = "Meets";
	public static final String EVENTS_TABLE = "Events";
	
	
	public  DBHelper(Context context) {
		super(context, "PocketSplits", null, 1);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		String createMeetsTable = "create table " + MEETS_TABLE + 
				"(_id integer primary key, meet_name text not null, meet_date text not null);";
		String createEventsTable = "create table " + EVENTS_TABLE + 
				"(_id integer primary key, meet_id integer, event_gender text not null, event_name text not null, " +
				"foreign key(meet_id) references " + MEETS_TABLE +"(_id));";
		
		db.execSQL("PRAGMA foreign_keys=ON;");
		db.execSQL(createMeetsTable);
		db.execSQL(createEventsTable);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		//nothing 
	}
	
	
}
