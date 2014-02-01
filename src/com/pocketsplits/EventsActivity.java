package com.pocketsplits;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class EventsActivity extends Activity {
	
	private ArrayList<Event> events;
	private EventsAdapter eventsAdapter;
	private ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_events);
		events = new ArrayList<Event>();
		eventsAdapter = new EventsAdapter(this,events);
		
		lv = (ListView)findViewById(R.id.eventList);
		lv.setAdapter(eventsAdapter);
		lv.setDivider(new ColorDrawable(0xff33b5e5));
		lv.setDividerHeight(3);
		lv.setBackgroundColor(0x222222);
		//set meet name
		Intent intent = getIntent();
		String meetName = intent.getStringExtra("Meet");
		TextView meetNameText = (TextView) findViewById(R.id.meet_name_events);
		meetNameText.setText(meetName);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.events, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.action_newEvent:
	            openNewEventDialog();
	            return true;
	        case R.id.action_settings:
	            //openSettings();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	@Override
	public void onBackPressed() {
	    super.onBackPressed();
	    overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
	}
	
	private void openNewEventDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(EventsActivity.this, AlertDialog.THEME_HOLO_DARK);
		String[] genders = {"Men's", "Women's"};
        // Setting Dialog Title
        builder.setTitle("New Event");

        // Setting Dialog Message
        builder.setMessage("Enter Event Details:");
        final RadioButton[] rb = new RadioButton[2];
        final RadioGroup rg = new RadioGroup(this);
        rg.setOrientation(RadioGroup.HORIZONTAL);
        for(int i = 0; i < 2; i++) {
        	rb[i]  = new RadioButton(this);
            rg.addView(rb[i]); //the RadioButtons are added to the radioGroup instead of the layout
            rb[i].setText(genders[i]);
            rb[i].setTextSize(30);

        }
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                              LinearLayout.LayoutParams.WRAP_CONTENT,
                              LinearLayout.LayoutParams.WRAP_CONTENT);
       
        rg.setLayoutParams(new RadioGroup.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
        rg.setOrientation(LinearLayout.HORIZONTAL);
        rg.setGravity(Gravity.CENTER);
        rg.check(rb[0].getId());
        
        final Spinner eventSpinner = new Spinner(this);
        String[] eventsArray = {"400m", "800m", "1000m", "1500m", "1600m","Mile", "3000m", "3200m", "2Mile", 
        		"5000m", "10000m", "4x400m", "4x800m", "DMR"};
        ArrayAdapter<String> eventSpinnerAdapter = new ArrayAdapter<String>(this,R.layout.events_spinner, eventsArray);
        eventSpinner.setAdapter(eventSpinnerAdapter);
        eventSpinner.setGravity(Gravity.CENTER);
        eventSpinner.setLayoutParams(new Spinner.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 
        		LinearLayout.LayoutParams.WRAP_CONTENT));
        layout.addView(rg);
        layout.addView(eventSpinner);
        builder.setView(layout);



        // Setting Positive "Yes" Button
        builder.setPositiveButton("Add",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                    	int selectedGenderId = rg.getCheckedRadioButtonId();
                    	View genderView = rg.findViewById(selectedGenderId);
                    	char gender;
                    	if(genderView == rb[0]) gender = 'M';
                    	else gender = 'W';
                    	String event = eventSpinner.getSelectedItem().toString();
                    	events.add(new Event(event, gender));
                        eventsAdapter.notifyDataSetChanged();
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
	

}
