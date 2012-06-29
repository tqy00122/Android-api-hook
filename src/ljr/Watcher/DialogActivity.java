package ljr.Watcher;

import ljr.Watcher.manager.TrustManager;
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
public class DialogActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_layout);
		final Intent intent = getIntent();

		setTitle("WARNING (SmsWatcher)");

		TextView tips = (TextView) findViewById(R.id.tips);
		tips.setText("Some app is sending a message. If this isn't your action, please note the running apps.");

		TextView des = (TextView) findViewById(R.id.des);
		des.setText("The destination: " + intent.getStringExtra("des"));

		TextView test = (TextView) findViewById(R.id.text);
		test.setText("The content: " + intent.getStringExtra("text"));

		TextView hint = (TextView) findViewById(R.id.hint);
		hint.setText("Press OK to close this dialog\nPress Num Trust to set the number in the trust list\nPress Content Trust to set the content in the trust list\nMore setting in SmsWatcher");

		Button ok = (Button) findViewById(R.id.confirm);
		ok.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				DialogActivity.this.finish();
			}
		});

		Button numTrust = (Button) findViewById(R.id.nm_trust);
		numTrust.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				mTrustManager.insert(1, intent.getStringExtra("des"));				
				DialogActivity.this.finish();
			}
		});

		Button contentTrust = (Button) findViewById(R.id.ct_trust);
		contentTrust.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				mTrustManager.insert(2, intent.getStringExtra("text"));
				DialogActivity.this.finish();
			}
		});
	}
	
	private TrustManager mTrustManager = TrustManager.getInstance(this);
}
