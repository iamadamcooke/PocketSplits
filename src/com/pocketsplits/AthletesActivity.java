package com.pocketsplits;

import java.util.ArrayList;
import java.util.Calendar;

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
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class AthletesActivity extends Activity {

	private ArrayList<Athlete> athletes;
	private AthletesAdapter athletesAdapter;
	private ListView lv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		setContentView(R.layout.activity_athletes);
		athletes = new ArrayList<Athlete>();
		athletesAdapter = new AthletesAdapter(this,athletes);
		
		lv = (ListView)findViewById(R.id.athleteList);
		lv.setAdapter(athletesAdapter);
		lv.setDivider(new ColorDrawable(0xff33b5e5));
		lv.setDividerHeight(3);
		lv.setBackgroundColor(0x222222);
		//set meet name
		Intent intent = getIntent();
		String eventName = intent.getStringExtra("Event");
		TextView eventNameText = (TextView) findViewById(R.id.event_name_athletes);
		eventNameText.setText(eventName);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.athletes, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.action_newAthlete:
	            openNewAthleteDialog();
	            return true;
	        case R.id.action_discardAthlete:
	        	if(!athletes.isEmpty()) {
	        		//openDiscardEventDialog();
	        	}
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	public void openNewAthleteDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(AthletesActivity.this, AlertDialog.THEME_HOLO_DARK);

        // Setting Dialog Title
        builder.setTitle("New Athlete");

        // Setting Dialog Message
        builder.setMessage("Enter Meet Name:");
        final EditText input = new EditText(AthletesActivity.this);  
        input.setTextColor(Color.WHITE);
        input.setHint("Athlete Name");
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
                        athletes.add(new Athlete(input.getText().toString()));
                        athletesAdapter.notifyDataSetChanged();
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
