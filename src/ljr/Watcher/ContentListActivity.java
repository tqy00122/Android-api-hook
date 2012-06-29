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
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

/**
 * @author liujierui
 *
 */
public class ContentListActivity extends ListActivity {
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
			if (result.get(i).getType() == TYPE_CONTENT) {
				HashMap<String, String> map = new HashMap<String, String>();
				
				map.put("content", result.get(i).getContent());
				
				list.add(map);
			}
		}
		
		SimpleAdapter listAdapter = new SimpleAdapter(this, list,
				R.layout.content_info_layout, new String[] { "content" },
				new int[] { R.id.content });
		
		this.setListAdapter(listAdapter);
		
		ListView lv = getListView();
		lv.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent toRemove = new Intent(ContentListActivity.this, ListRemoveDialog.class);
				toRemove.putExtra("Content", list.get(arg2).get("content"));
				startActivity(toRemove);
			}		
		});		
	}

	private final int TYPE_CONTENT = 2;
	private TrustManager mTrustManager = TrustManager.getInstance(this);
}