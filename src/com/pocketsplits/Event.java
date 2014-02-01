package com.pocketsplits;

public class Event {

	private String eventName;
	private char eventGender;
	
	public Event(String eventName, char eventGender) {
		this.eventName = eventName;
		this.eventGender = eventGender;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public char getEventGender() {
		return eventGender;
	}

	public void setEventGender(char eventGender) {
		this.eventGender = eventGender;
	}
	
	
	
	
	
	
}
