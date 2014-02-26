package com.pocketsplits;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
	
	private DBHelper dbHelper;
	private SQLiteDatabase db;
	private static final String MEET_NAME = "meet_name";
	private static final String MEET_DATE = "meet_date";
	private static final String MEET_ID = "_id";
	private static final String EVENT_GENDER = "event_gender";
	private static final String EVENT_NAME = "event_name";
	private static final String EVENT_ID = "_id";
	private static final String MEET_FOREIGN_ID = "meet_id";
	
	
	public DBManager(Context context) {
		dbHelper = new DBHelper(context);
		db = dbHelper.getWritableDatabase();
	}
	
	public long createMeet(Meet meet) {
		ContentValues values = new ContentValues();
		values.put(MEET_NAME, meet.getMeetName());
		values.put(MEET_DATE, meet.getCalAsString());
		return db.insert(DBHelper.MEETS_TABLE, null, values);
	}
	
	public long deleteMeet(Meet meet) {
		return db.delete(DBHelper.MEETS_TABLE, (MEET_NAME + "=?"), new String[] {meet.getMeetName()});
	}
	
	public long updateMeetDate(String name, Calendar cal) {
		
		ContentValues values = new ContentValues();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		String newDate = dateFormat.format(cal.getTime());
		values.put(MEET_DATE, newDate);
		String where = MEET_NAME + "=?";
		return db.update(DBHelper.MEETS_TABLE, values, where, new String[]{name});
		
	}
	
	public long createEvent(int meetId, Event event) {
		ContentValues values = new ContentValues();
		values.put(MEET_FOREIGN_ID, meetId);
		values.put(EVENT_GENDER, (""+ event.getEventGender()));
		values.put(EVENT_NAME, event.getEventName());
		return db.insert(DBHelper.EVENTS_TABLE, null, values);
	}
	
	
	public ArrayList<Meet> getMeets() throws ParseException {
		ArrayList<Meet> meets = new ArrayList<Meet>();
		Cursor c = db.rawQuery("SELECT * FROM " +
		DBHelper.MEETS_TABLE, null);
		if(c != null) {
			if(c.moveToFirst()) {
				while(c.isAfterLast() == false) {
					String meetName = c.getString(c.getColumnIndex(MEET_NAME));
					Calendar cal = Calendar.getInstance();
					SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
					String date = c.getString(c.getColumnIndex(MEET_DATE));
					cal.setTime(dateFormat.parse(date));
					meets.add(new Meet(meetName, cal));
					c.moveToNext();
				}
			}
			else return null;
		}
		else return null;
		
		
		return meets;
	}
	
	
	
	

}
