package ljr.Watcher;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.TabHost;

/**
 * @author liujierui
 *
 */
public class TrustListDialog extends TabActivity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.trust_tab_layout);
		
		tabHost = (TabHost)findViewById(android.R.id.tabhost);
		tabHost.setup();
		
		TabHost.TabSpec spec1 = tabHost.newTabSpec("tag1");
		spec1.setContent(new Intent(this, NumberListActivity.class));
		spec1.setIndicator("NUMBER");
		
		tabHost.addTab(spec1);
		
		TabHost.TabSpec spec2 = tabHost.newTabSpec("tag2");
		spec2.setContent(new Intent(this, ContentListActivity.class));
		spec2.setIndicator("CONTENT");
		
		tabHost.addTab(spec2);
	}
	
	private TabHost tabHost = null;
}
