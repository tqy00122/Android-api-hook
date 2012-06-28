package ljr.Watcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ljr.Watcher.entity.TrustEntity;
import ljr.Watcher.manager.TrustManager;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

public class ContentListActivity extends ListActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.list_layout);

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		ArrayList<TrustEntity> result = mTrustManager.query(null, null);
		for (int i = 0; i < result.size(); i++) {
			if (result.get(i).getType() == TYPE_CONTENT) {
				HashMap<String, String> map = new HashMap<String, String>();

				map.put("content", result.get(i).getContent());

				list.add(map);
			}
		}

		SimpleAdapter listAdapter = new SimpleAdapter(this, list,
				R.layout.list_info_layout, new String[] { "content" },
				new int[] { R.id.content });

		this.setListAdapter(listAdapter);
	}

	private final int TYPE_CONTENT = 2;
	private TrustManager mTrustManager = TrustManager.getInstance(this);
}