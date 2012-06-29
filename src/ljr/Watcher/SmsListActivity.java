package ljr.Watcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ljr.Watcher.entity.WatcherListEntity;
import ljr.Watcher.manager.WatcherListManager;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

/**
 * @author liujierui
 *
 */
public class SmsListActivity extends ListActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_layout);
		
		showList();
	}
	
	
	@Override
	protected void onResume() {
		super.onResume();
		
		showList();
	}


	public void showList(){
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		
		ArrayList<WatcherListEntity> result = mWatcherManager.query(null, null);
		for (int i = 0; i < result.size(); i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			
			map.put("time", "time: " + result.get(i).getTime());
			map.put("des", "des: " + result.get(i).getDes());
			map.put("text", "content: " + result.get(i).getText());
			
			list.add(map);
		}
		
		SimpleAdapter listAdapter = new SimpleAdapter(this, list,
				R.layout.list_info_layout,
				new String[] { "time", "des", "text" }, new int[] { R.id.time,
				R.id.des, R.id.text });
		
		this.setListAdapter(listAdapter);		
	}
	private WatcherListManager mWatcherManager = WatcherListManager
			.getInstance(this);
}
