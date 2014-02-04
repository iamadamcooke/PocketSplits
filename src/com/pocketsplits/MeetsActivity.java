package com.pocketsplits;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
 
public class MeetsActivity extends ListActivity {
 
	private ArrayList<Meet> meetNames;
	private MeetsAdapter meetsAdapter;
	private ListView lv;
	private int mMonth;
	private int mYear;
	private int mDay;
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		meetNames = new ArrayList<Meet>();
		meetsAdapter = new MeetsAdapter(this, meetNames);
		
		lv = this.getListView();
		lv.setAdapter(meetsAdapter);
		lv.setDivider(new ColorDrawable(0xff33b5e5));
		lv.setDividerHeight(3);
 
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.meets, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.action_newMeet:
	            openNewMeetDialog();
	            return true;
	        case R.id.action_discardMeet:
	        	if(!meetNames.isEmpty()) {
	        		openDiscardMeetDialog();
	        	}
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
 
	public void OnMeetClicked(View view) {
 
		//get selected items
		TextView selectedMeet = (TextView) view.findViewById(R.id.meet_name);
		Intent eventIntent = new Intent(getApplicationContext(), EventsActivity.class);
		eventIntent.putExtra("Meet", selectedMeet.getText());
		startActivity(eventIntent);
	
		
 
	}
 
	private void openNewMeetDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(MeetsActivity.this, AlertDialog.THEME_HOLO_DARK);

        // Setting Dialog Title
        builder.setTitle("New Meet");

        // Setting Dialog Message
        builder.setMessage("Enter Meet Name:");
        final EditText input = new EditText(MeetsActivity.this);  
        input.setTextColor(Color.WHITE);
        input.setHint("Meet Name");
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                              LinearLayout.LayoutParams.MATCH_PARENT,
                              LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(30, 0, 30, 0);
        layout.addView(input, lp);
        builder.setView(layout);



        // Setting Positive "Yes" Button
        builder.setPositiveButton("Add",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                        meetNames.add(new Meet(input.getText().toString(), Calendar.getInstance()));
                        meetsAdapter.notifyDataSetChanged();
                    }});
        // Setting Negative "NO" Button
        builder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to execute after dialog
                        dialog.cancel();
                    }
                });

        // closed

        // Showing Alert Message
        AlertDialog dialog = builder.show();
        TextView messageText = (TextView)dialog.findViewById(android.R.id.message);
        messageText.setGravity(Gravity.CENTER);
        dialog.show();
      
    }
	
	public void openDiscardMeetDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(MeetsActivity.this, AlertDialog.THEME_HOLO_DARK);

        // Setting Dialog Title
        builder.setTitle("Discard Meet(s)");

        // Setting Dialog Message
        builder.setMessage("Check the meet(s) you would like to discard:");
        
        LinearLayout layout = new LinearLayout(this);
        
        layout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                              LinearLayout.LayoutParams.WRAP_CONTENT,
                              LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(30, 0, 0, 15);
        Object[] currentMeets = meetNames.toArray();
        final Map<CheckBox, Meet> boxesMap = new HashMap<CheckBox, Meet>();
        for(Object meet : currentMeets) {
        	CheckBox cb = new CheckBox(getApplicationContext());
        	cb.setTextSize(25);
        	cb.setText(((Meet)meet).toString());
        	boxesMap.put(cb, (Meet)meet);
        	layout.addView(cb, lp);
        }
        builder.setView(layout);



        // Setting Positive "Yes" Button
        builder.setPositiveButton("Remove",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                        for(Map.Entry<CheckBox, Meet> e : boxesMap.entrySet()) {
                        	if(e.getKey().isChecked()) {
                        		meetNames.remove(e.getValue());
                        	}
                        }
                        meetsAdapter.notifyDataSetChanged();
                    }});
        // Setting Negative "NO" Button
        builder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to execute after dialog
                        dialog.cancel();
                    }
                });

        // closed

        // Showing Alert Message
        AlertDialog dialog = builder.show();
        TextView messageText = (TextView)dialog.findViewById(android.R.id.message);
        messageText.setGravity(Gravity.CENTER);
        dialog.show();
	}
	
	public void onDateClicked(View view) {
		//TextView calendarMonthText = (TextView) view.findViewById(R.id.calendar_date_text);
		//calendarMonthText.setText("hi");
		final int position = lv.getPositionForView((View) view.getParent());
		showDatePickerDialog(view, meetNames, position);
		//meetsAdapter.notifyDataSetChanged();
		
		
		
		
		
		//calendarMonthText.setText(months[cal.get(Calendar.MONTH)]);
		//calendarDateText.setText("" + cal.get(Calendar.DAY_OF_MONTH));
      } 
	
	public void showDatePickerDialog(View view, ArrayList<Meet> meets , int position) {
	    DialogFragment newFragment = new DatePickerFragment(view, meets, position);
	    newFragment.show(getFragmentManager(), "datePicker");
	}
	
	@SuppressLint("ValidFragment")
	public static class DatePickerFragment extends DialogFragment
    implements DatePickerDialog.OnDateSetListener {

		TextView calendarDateText;
		TextView calendarMonthText;
		int position;
		ArrayList<Meet> meets;
		
		
		public DatePickerFragment(View view, ArrayList<Meet> meets, int position) {
			calendarDateText = (TextView) view.findViewById(R.id.calendar_date_text);
			calendarMonthText = (TextView) view.findViewById(R.id.calendar_month_text);
			this.position = position;
			this.meets = meets;
		}
		
		
		
		
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			
			
			// Use the current date as the default date in the picker
			final Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			int day = c.get(Calendar.DAY_OF_MONTH);

			// Create a new instance of DatePickerDialog and return it
			return new DatePickerDialog(DatePickerFragment.this.getActivity(), this, year, month, day);
		}

		public void onDateSet(DatePicker view, int year, int month, int day) {
			String[] months = {"January", "February",
					  "March", "April", "May", "June", "July",
					  "August", "September", "October", "November",
					  "December"};
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, year);
			cal.set(Calendar.MONTH, month);
			cal.set(Calendar.DAY_OF_MONTH, day);
			meets.set(position, new Meet(meets.get(position).getMeetName(),cal));
			calendarMonthText.setText(months[month]);
			calendarDateText.setText("" + day);
			
		}
	}
	


}
