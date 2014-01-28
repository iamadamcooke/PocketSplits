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
	private TextView totalTimeText;
	private TextView splitTimeText;
	private Button startButton;
	private Button stopResetButton;
	private Button splitButton;
	private Handler swHandler = new Handler();
	private long lastSplit = 0L;
	private long lastTime = 0L;

	Stopwatch sw;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stopwatch);

		totalTimeText = (TextView) findViewById(R.id.totalTimeText);
		splitTimeText = (TextView) findViewById(R.id.splitTimeText);
		sw = new Stopwatch(swHandler, totalTimeText);

		startButton = (Button) findViewById(R.id.btnStart);
		startButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				swHandler.postDelayed(sw, 0);
			}
		});

		stopResetButton = (Button) findViewById(R.id.btnStopReset);
		stopResetButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				long newTimeSwap = sw.getTimeSwap() + sw.getTimeInMillis();
				sw.setTimeSwap(newTimeSwap);
				sw.toggleStartTimeSet();
				swHandler.removeCallbacks(sw);
			}
		});
		
		splitButton = (Button) findViewById(R.id.btnSplit);
		splitButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				lastSplit = sw.getTotalTime() - lastTime;
				lastTime = sw.getTotalTime();
				splitTimeText.setText(TimeHelpers.millisToStringTime(lastSplit));
				
			}
		});
		

	}

}