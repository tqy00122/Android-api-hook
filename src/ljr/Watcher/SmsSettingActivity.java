package ljr.Watcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class SmsSettingActivity extends ListActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting_layout);

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		
		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("title", "See the trust list");
		map1.put("content", "You can view the trust list and change the list");
		list.add(map1);
		
		HashMap<String, String> map2 = new HashMap<String, String>();
		map2.put("title", "Add trust list");
		map2.put("content", "You can add the list by the number or the content");
		list.add(map2);
		
		HashMap<String, String> map3 = new HashMap<String, String>();
		map3.put("title", "Import contacts");
		map3.put("content", "Put the number which appear in your contacts in the trust list");
		list.add(map3);
		
		SimpleAdapter listAdapter = new SimpleAdapter(this, list,
				R.layout.setting_info_layout,
				new String[] { "title", "content" }, new int[] { R.id.title,
						R.id.content });

		this.setListAdapter(listAdapter);
		
		ListView listView = getListView();
		listView.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				if (arg2 == 0){
					Intent toList = new Intent(SmsSettingActivity.this, TrustListDialog.class);
					startActivity(toList);
				}
				
				if (arg2 == 1){
					Intent toAdd = new Intent(SmsSettingActivity.this, AddTrustDialog.class);
					startActivity(toAdd);
				}
				
				if (arg2 == 2){
					
				}
			}		
		});
	}
}
