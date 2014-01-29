package com.pocketsplits;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
//welcome/splash screen activity
public class WelcomeActivity extends Activity {

	// Splash screen timer
    private final int SPLASH_TIME_OUT = 2000;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
 
        new Handler().postDelayed(new Runnable() {
 
 
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this, MeetsActivity.class);
                finish();
				startActivity(intent);

				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        }, SPLASH_TIME_OUT);
        
        new Handler().postDelayed(new Runnable() 
		{
			@Override
			public void run() 
			{
				//Do nothing
			} 
		}, SPLASH_TIME_OUT);
        
    }
    
    @Override
	public void onBackPressed() {
		finish();
		super.onBackPressed();
	}

}
