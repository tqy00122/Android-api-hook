package ljr.Watcher;

import ljr.Watcher.manager.TrustManager;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class AddTrustDialog extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_trust_layout);
		setTitle("Add Trust list");
		
		Spinner spinner = (Spinner) findViewById(R.id.spinner);
		String itemData[] = { "Number", "Content" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, itemData);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		
		EditText content = (EditText)findViewById(R.id.content);
	}
	
	private final int TYPE_NUMBER = 1;
	private final int TYPE_CONTENT = 2;
	private TrustManager mTrustManager = TrustManager.getInstance(this);
}
