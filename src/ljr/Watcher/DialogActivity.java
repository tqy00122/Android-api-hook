package ljr.Watcher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class DialogActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_layout);
		Intent intent = getIntent();
		
		setTitle("WARNING (SmsWatcher)");
		
		TextView tips = (TextView)findViewById(R.id.tips);
		tips.setText("aaa");
		
		TextView des = (TextView)findViewById(R.id.des);
		des.setText(intent.getStringExtra("des"));
		
		TextView test = (TextView)findViewById(R.id.text);
		test.setText(intent.getStringExtra("text"));
		
		TextView hint = (TextView)findViewById(R.id.hint);
		hint.setText("bbb");
		
		Button ok = (Button)findViewById(R.id.confirm);
		ok.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				DialogActivity.this.finish();
			}		
		});
		
		Button setting = (Button)findViewById(R.id.setting);
		setting.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				Intent toSms = new Intent(DialogActivity.this, TabHostActivity.class);
				startActivity(toSms);
			}		
		});
	}

}
