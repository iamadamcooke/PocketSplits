package com.pocketsplits;

import java.util.ArrayList;
import java.util.Calendar;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 
public class EventsAdapter extends ArrayAdapter<Event> {
	private final Context context;
	private ArrayList<Event> events;
 
	public EventsAdapter(Context context, ArrayList<Event> events) {
		super(context, R.layout.list_events, events);
		this.context = context;
		this.events = events;
	}
 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View rowView = inflater.inflate(R.layout.list_events, parent, false);
		rowView.setBackgroundColor(0x222222);
		
		TextView eventGenderText = (TextView) rowView.findViewById(R.id.event_gender);
		TextView eventNameText = (TextView) rowView.findViewById(R.id.event_name);
		eventNameText.setText(events.get(position).getEventName());
		char eventGender = events.get(position).getEventGender();
		eventGenderText.setText(Character.toString(eventGender));
		if(eventGender == 'W') {
			eventGenderText.setTextColor(Color.parseColor("#FF69B4"));
		}
		else {
			eventGenderText.setTextColor(0xff33b5e5);
		}
		
 
		return rowView;
	}
	
	
}