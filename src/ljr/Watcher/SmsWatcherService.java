package ljr.Watcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ljr.Watcher.entity.TrustEntity;
import ljr.Watcher.manager.TrustManager;
import ljr.Watcher.manager.WatcherListManager;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * @author liujierui
 *
 */
public class SmsWatcherService extends IntentService {
	public SmsWatcherService() {
		super("BackWatcherService");
	}

	@Override
	public void onDestroy() {
		Log.i(TAG, "Service Destroy");
		super.onDestroy();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		try {
			Runtime.getRuntime().exec(new String[] { "logcat", "-c" });
		} catch (IOException e) {
			e.printStackTrace();
		}

		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Process mLogcatProc = null;
		BufferedReader reader = null;

		Log.i(TAG, "I'm running");
		try {
			mLogcatProc = Runtime.getRuntime().exec(
					new String[] { "logcat", "SendMessage:I *:S" });
			reader = new BufferedReader(new InputStreamReader(
					mLogcatProc.getInputStream()));
			String line;

			while ((line = reader.readLine()) != null) {
				final String info;
				String[] infoArray = line.split("\\$");

				// info = "Warning:a process is sending message now.\n"
				// + "The destinantion:" + infoArray[1] + "\n"
				// + "The Text:" + infoArray[2];
				//
				// Handler toastHandler = new Handler(getMainLooper());
				// toastHandler.post(new Runnable() {
				// public void run() {
				// Toast.makeText(getApplicationContext(), info,
				// Toast.LENGTH_LONG).show();
				// }
				// });
				
				List<TrustEntity> trustList = mTrustManager.query(null, null);
				for (int i = 0; i < trustList.size(); i++){
					int type = trustList.get(i).getType();
					if (type == TYPE_NUMBER){
						if (trustList.get(i).getContent().equals(infoArray[1])){
							mIsTrust = true;
							break;
						}
					}
					if (type == TYPE_CONTENT){
						if (trustList.get(i).getContent().equals(infoArray[2])){
							mIsTrust = true;
							break;
						}
					}
				}
				
				if (mIsTrust == false) {
					final SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy-MM-dd hh:mm");
					String nowTime = sdf.format(new Date());

					addDataToDb(nowTime, infoArray[1], infoArray[2]);

					Bundle bundle = new Bundle();
					bundle.putString("des", infoArray[1]);
					bundle.putString("text", infoArray[2]);
					Intent toDialog = new Intent(SmsWatcherService.this,
							DialogActivity.class);
					toDialog.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					toDialog.putExtras(bundle);
					startActivity(toDialog);
				}
				
				mIsTrust = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void addDataToDb(String time, String des, String text) {
		mWatcherManager.insert(time, des, text);
	}

	private String TAG = "BackWatcherService";
	private WatcherListManager mWatcherManager = WatcherListManager
			.getInstance(this);
	private TrustManager mTrustManager = TrustManager.getInstance(this);
	private boolean mIsTrust = false;
	
	private final int TYPE_NUMBER = 1;
	private final int TYPE_CONTENT = 2;
}
