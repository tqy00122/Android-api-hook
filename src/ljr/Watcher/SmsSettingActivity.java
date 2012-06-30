package ljr.Watcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ljr.Watcher.entity.WatcherListEntity;
import ljr.Watcher.manager.TrustManager;
import ljr.Watcher.manager.WatcherListManager;
import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

/**
 * @author liujierui
<<<<<<< HEAD
 * 
=======
 *
>>>>>>> origin/master
 */
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
		
		HashMap<String, String> map4 = new HashMap<String, String>();
		map4.put("title", "Clear watcher list");
		map4.put("content", "If you want to clear the watcher list, click here");
		list.add(map4);

		SimpleAdapter listAdapter = new SimpleAdapter(this, list,
				R.layout.setting_info_layout,
				new String[] { "title", "content" }, new int[] { R.id.title,
						R.id.content });

		this.setListAdapter(listAdapter);

		ListView listView = getListView();
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				if (arg2 == 0) {
					Intent toList = new Intent(SmsSettingActivity.this,
							TrustListDialog.class);
					startActivity(toList);
				}

				if (arg2 == 1) {
					Intent toAdd = new Intent(SmsSettingActivity.this,
							AddTrustDialog.class);
					startActivity(toAdd);
				}

				if (arg2 == 2) {
					getPhoneContacts();
				}

				if (arg2 == 3) {
					clearList();
				}
			}
		});
	}

	private void clearList() {
		List<WatcherListEntity> allEntity = mWatcherManager.query(null, null);
		for (int i = 0; i < allEntity.size(); i++) {
			int id = allEntity.get(i).getID();
			mWatcherManager
					.delete("_id=?", new String[] { String.valueOf(id) });
		}

		Toast.makeText(this, "Watcher list cleared", Toast.LENGTH_SHORT).show();
	}

	private void getPhoneContacts() {
		ContentResolver resolver = getApplicationContext().getContentResolver();
		Cursor phoneCursor = resolver.query(Phone.CONTENT_URI,
				PHONES_PROJECTION, null, null, null);
		mNumList = new ArrayList<String>();
		System.out.println("here-1");
		if (phoneCursor != null) {
			System.out.println("here-2");
			while (phoneCursor.moveToNext()) {
				String phoneNumber = phoneCursor.getString(PHONES_NUMBER_INDEX);
				System.out.println("--" + phoneNumber);
				if (TextUtils.isEmpty(phoneNumber))
					continue;
				mNumList.add(phoneNumber);
			}
			phoneCursor.close();
		}

		for (int i = 0; i < mNumList.size(); i++) {
			String number = "";
			String[] tempNumArray = mNumList.get(i).split("-");
			for (int j = 0; j < tempNumArray.length; j++){
				number += tempNumArray[j];
			}
			mTrustManager.insert(1, number);
		}

		Toast.makeText(SmsSettingActivity.this, "Import contacts completed",
				Toast.LENGTH_SHORT).show();
	}

	private WatcherListManager mWatcherManager = WatcherListManager
			.getInstance(this);
	private TrustManager mTrustManager = TrustManager.getInstance(this);
	private static final String[] PHONES_PROJECTION = new String[] { Phone.NUMBER };
	private static final int PHONES_NUMBER_INDEX = 0;
	private List<String> mNumList = null;		
}
