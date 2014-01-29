package com.pocketsplits;

import java.util.ArrayList;
import java.util.Calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 
public class MeetsAdapter extends ArrayAdapter<String> {
	private final Context context;
	private ArrayList<String> meetNames;
 
	public MeetsAdapter(Context context, ArrayList<String> meetNames) {
		super(context, R.layout.list_meets, meetNames);
		this.context = context;
		this.meetNames= meetNames;
	}
 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View rowView = inflater.inflate(R.layout.list_meets, parent, false);
		rowView.setBackgroundColor(0x222222);
		TextView meetNameText = (TextView) rowView.findViewById(R.id.meet_name);
		TextView calendarMonthText = (TextView) rowView.findViewById(R.id.calendar_month_text);
		TextView calendarDateText = (TextView) rowView.findViewById(R.id.calendar_date_text);
		meetNameText.setText(meetNames.get(position));
 
		//set date on calendar to current day
		Calendar cal = Calendar.getInstance();
		String[] months = {"January", "February",
				  "March", "April", "May", "June", "July",
				  "August", "September", "October", "November",
				  "December"};
		calendarMonthText.setText(months[cal.get(Calendar.MONTH)]);
		calendarDateText.setText("" + cal.get(Calendar.DAY_OF_MONTH));
 
		return rowView;
	}
}