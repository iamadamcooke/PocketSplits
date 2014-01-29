package com.pocketsplits;


import java.util.ArrayList;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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
 
	private ArrayList<String> meetNames;
	private MeetsAdapter meetsAdapter;
	private ListView lv;
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(savedInstanceState == null || !savedInstanceState.containsKey("meetnames")) {
			meetNames = new ArrayList<String>();
		}
		else {
			meetNames = savedInstanceState.getStringArrayList("meetnames");
		}
		meetsAdapter = new MeetsAdapter(this, meetNames);
		setListAdapter(meetsAdapter);
		lv = this.getListView();
		lv.setDivider(new ColorDrawable(0xff33b5e5));
		lv.setDividerHeight(3);
 
	}
	
	@Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putStringArrayList("meetnames", meetNames);
        super.onSaveInstanceState(outState);
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
	        case R.id.action_settings:
	            //openSettings();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
 
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
 
		//get selected items
		String selectedValue = (String) getListAdapter().getItem(position);
		
 
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
                        meetNames.add(input.getText().toString());
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
	
	

}
