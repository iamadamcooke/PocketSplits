package com.pocketsplits;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
//welcome/splash screen activity
public class WelcomeActivity extends Activity {

	// Splash screen timer
    private final int SPLASH_TIME_OUT = 5000;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
 
        new Handler().postDelayed(new Runnable() {
 
 
            @Override
            public void run() {
                Intent i = new Intent(WelcomeActivity.this, StopwatchActivity.class);
                startActivity(i);
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}
