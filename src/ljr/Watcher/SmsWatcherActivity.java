package ljr.Watcher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author liujierui
 *
 */
public class SmsWatcherActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button start = (Button) findViewById(R.id.start);
		Button stop = (Button) findViewById(R.id.stop);
		TextView text1 = (TextView) findViewById(R.id.text1);
		text1.setText("In fact, we just add a Log in the SmsManager.java file in framework layer. Press START to test this app.");

		TextView text2 = (TextView) findViewById(R.id.text2);
		text2.setText("We modify the code of framework layer, so this app just run in the specific device (or virtual device). So this app is just for fun and test.");

		TextView text3 = (TextView) findViewById(R.id.text3);
		text3.setText("Welcome to use the SmsWatcher app. This application is a service running in the back to watch the behavior which use the message function.");

		start.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Toast.makeText(SmsWatcherActivity.this, "Service start",
						Toast.LENGTH_SHORT).show();
				
				Intent serIntent = new Intent(SmsWatcherActivity.this,
						SmsWatcherService.class);
				startService(serIntent);
			}
		});

		stop.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Toast.makeText(SmsWatcherActivity.this, "Service may be not stopped",
						Toast.LENGTH_SHORT).show();
				
				stopService(new Intent(SmsWatcherActivity.this,
						SmsWatcherService.class));
			}
		});
	}

}