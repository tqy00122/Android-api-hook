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
public class TabHostActivity extends TabActivity {
	private TabHost tabHost = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.tab_layout);
		setTabView();
	}
	
	private void setTabView(){
		tabHost = (TabHost)findViewById(android.R.id.tabhost);
		tabHost.setup();
		
		TabHost.TabSpec spec1 = tabHost.newTabSpec("tag1");
		spec1.setContent(new Intent(this, SmsWatcherActivity.class));
		spec1.setIndicator("HOME");
		
		tabHost.addTab(spec1);
		
		TabHost.TabSpec spec2 = tabHost.newTabSpec("tag2");
		spec2.setContent(new Intent(this, SmsListActivity.class));
		spec2.setIndicator("WATCHING LIST");
		
		tabHost.addTab(spec2);
		
		TabHost.TabSpec spec3 = tabHost.newTabSpec("tag3");
		spec3.setContent(new Intent(this, SmsSettingActivity.class));
		spec3.setIndicator("SETTING");
		
		tabHost.addTab(spec3);
		
	}

}