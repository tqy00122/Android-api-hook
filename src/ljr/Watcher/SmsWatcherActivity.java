package ljr.Watcher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SmsWatcherActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button start = (Button) findViewById(R.id.start);
		Button stop = (Button) findViewById(R.id.stop);
		TextView text = (TextView) findViewById(R.id.text);
		text.setText("This application is a service running in the back to watch the behavior which use the message function.");

		start.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent serIntent = new Intent(SmsWatcherActivity.this,
						SmsWatcherService.class);
				startService(serIntent);
			}
		});

		stop.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				stopService(new Intent(SmsWatcherActivity.this,
						SmsWatcherService.class));
			}
		});
	}
	
}