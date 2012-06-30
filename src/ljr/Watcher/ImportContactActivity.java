package ljr.Watcher;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;

/**
 * @author xuhe
 *
 */
public class ImportContactActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact_layout);
		
		// TODO：读取手机的通讯录，找出里面的号码，保存到mNumList中
	}
	
	List<String> mNumList = new ArrayList<String>();
}
