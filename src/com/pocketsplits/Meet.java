package com.pocketsplits;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Meet {

	private String meetName;
	private Calendar meetDate;
	
	
	public Meet(String meetName, Calendar meetDate) {
		this.meetName = meetName;
		this.meetDate = meetDate;
	}
	
	
	public String getMeetName() {
		return meetName;
	}
	public void setMeetName(String meetName) {
		this.meetName = meetName;
	}
	public Calendar getMeetDate() {
		return meetDate;
	}
	public void setMeetDate(Calendar meetDate) {
		this.meetDate = meetDate;
	}
	
	public String getCalAsString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		return dateFormat.format(meetDate.getTime());
	}
	
	@Override 
	public String toString() {
		String[] months = {"January", "February",
				  "March", "April", "May", "June", "July",
				  "August", "September", "October", "November",
				  "December"};
		return this.meetName + " - " + months[meetDate.get(Calendar.MONTH)] + " " +
				  meetDate.get(Calendar.DAY_OF_MONTH) + ", " + meetDate.get(Calendar.YEAR);
	}
}
