package ljr.Watcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ljr.Watcher.manager.WatcherListManager;
import ljr.watcher.entity.WatcherListEntity;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

public class SmsListActivity extends ListActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_layout);

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		ArrayList<WatcherListEntity> result = mWatcherManager.query(null, null);
		for (int i = 0; i < result.size(); i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			
			map.put("time", result.get(i).getTime());
			map.put("des", result.get(i).getDes());
			map.put("text", result.get(i).getText());

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
