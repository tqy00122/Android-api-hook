package ljr.Watcher;

import ljr.Watcher.manager.TrustManager;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * @author liujierui
 * 
 */
public class AddTrustDialog extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_trust_layout);
		setTitle("Add Trust list");

		final Spinner spinner = (Spinner) findViewById(R.id.spinner);
		String itemData[] = { "Number", "Content" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, itemData);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);

		final EditText content = (EditText) findViewById(R.id.add_content);

		Button confirm = (Button) findViewById(R.id.confirm_bt);
		confirm.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				if (content.getText().length() == 0) {
					Toast.makeText(AddTrustDialog.this,
							"The content can't be empty", Toast.LENGTH_SHORT)
							.show();
				} else {
					if (spinner.getSelectedItemPosition() == 0){
						mTrustManager.insert(TYPE_NUMBER, content.getText().toString());
					} else {
						mTrustManager.insert(TYPE_CONTENT, content.getText().toString());
					}
					
					AddTrustDialog.this.finish();
				}
			}
		});

		Button cancel = (Button) findViewById(R.id.cancel_bt);
		cancel.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				AddTrustDialog.this.finish();
			}
		});
	}

	private final int TYPE_NUMBER = 1;
	private final int TYPE_CONTENT = 2;
	private TrustManager mTrustManager = TrustManager.getInstance(this);
}
