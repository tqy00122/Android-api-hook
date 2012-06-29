package ljr.Watcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ljr.Watcher.entity.TrustEntity;
import ljr.Watcher.manager.TrustManager;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

/**
 * @author liujierui
 *
 */
public class NumberListActivity extends ListActivity {
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

	private void showList(){
		final List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		ArrayList<TrustEntity> result = mTrustManager.query(null, null);
		for (int i = 0; i < result.size(); i++) {
			if (result.get(i).getType() == TYPE_NUMBER) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("number", result.get(i).getContent());

				list.add(map);
			}
		}

		SimpleAdapter listAdapter = new SimpleAdapter(this, list,
				R.layout.number_info_layout, new String[] { "number" },
				new int[] { R.id.number });

		this.setListAdapter(listAdapter);
		
		ListView lv = getListView();
		lv.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent toRemove = new Intent(NumberListActivity.this, ListRemoveDialog.class);
				toRemove.putExtra("Content", list.get(arg2).get("number"));
				startActivity(toRemove);
			}		
		});
	}

	private final int TYPE_NUMBER = 1;
	private TrustManager mTrustManager = TrustManager.getInstance(this);
}
