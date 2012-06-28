package ljr.Watcher;

import ljr.Watcher.manager.TrustManager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class ListRemoveDialog extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.remove_layout);
		
		Intent intent = getIntent();
		final String listContent = intent.getStringExtra("Content");
		
		Button remove = (Button)findViewById(R.id.rm_bt);
		remove.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
					mTrustManager.delete("Content=?", new String[] {listContent});
					ListRemoveDialog.this.finish();
			}			
		});
	}
	
	private TrustManager mTrustManager = TrustManager.getInstance(this);
}
