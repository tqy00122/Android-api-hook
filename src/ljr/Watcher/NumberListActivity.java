package ljr.Watcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ljr.Watcher.manager.TrustManager;
import ljr.watcher.entity.TrustEntity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

public class NumberListActivity extends ListActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.list_layout);

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		ArrayList<TrustEntity> result = mTrustManager.query(null, null);
		for (int i = 0; i < result.size(); i++) {
			if (result.get(i).getType() == TYPE_NUMBER) {
				HashMap<String, String> map = new HashMap<String, String>();

				map.put("number", result.get(i).getContent());

				list.add(map);
			}
		}

		SimpleAdapter listAdapter = new SimpleAdapter(this, list,
				R.layout.list_info_layout, new String[] { "number" },
				new int[] { R.id.number });

		this.setListAdapter(listAdapter);
	}

	private final int TYPE_NUMBER = 1;
	private TrustManager mTrustManager = TrustManager.getInstance(this);
}
