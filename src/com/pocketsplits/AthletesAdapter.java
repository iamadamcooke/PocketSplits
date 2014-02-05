package com.pocketsplits;

import java.util.ArrayList;
import java.util.Calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AthletesAdapter extends ArrayAdapter<Athlete>{
	private final Context context;
	private final ArrayList<Athlete> athletes;
	
	public AthletesAdapter(Context context, ArrayList<Athlete> athletes) {
		super(context, R.layout.list_athletes, athletes);
		this.context = context;
		this.athletes= athletes;
	}
 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View rowView = inflater.inflate(R.layout.list_athletes, parent, false);
		rowView.setBackgroundColor(0x222222);
		TextView athleteNameText = (TextView) rowView.findViewById(R.id.athlete_name);
		athleteNameText.setText(athletes.get(position).getName());
 
		return rowView;
	}
	

}
