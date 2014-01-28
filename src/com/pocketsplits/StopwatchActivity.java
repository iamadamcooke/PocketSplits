package com.pocketsplits;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StopwatchActivity extends Activity {
	/** Called when the activity is first created. */
	private TextView textTimer;
	private Button startButton;
	private Button pauseButton;
	private Handler swHandler = new Handler();

	Stopwatch sw;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stopwatch);

		textTimer = (TextView) findViewById(R.id.textTimer);
		sw = new Stopwatch(swHandler, textTimer);

		startButton = (Button) findViewById(R.id.btnStart);
		startButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				swHandler.postDelayed(sw, 0);
			}
		});

		pauseButton = (Button) findViewById(R.id.btnPause);
		pauseButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				long newTimeSwap = sw.getTimeSwap() + sw.getTimeInMillis();
				sw.setTimeSwap(newTimeSwap);
				sw.toggleStartTimeSet();
				swHandler.removeCallbacks(sw);
			}
		});

	}

}