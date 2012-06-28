package ljr.Watcher.manager;

import java.util.ArrayList;

import ljr.Watcher.metadata.WatcherListMetadata;
import ljr.Wathcer.db.DbHelper;
import ljr.watcher.entity.WatcherListEntity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class WatcherListManager {
	private static WatcherListManager sInstance = null;
	private DbHelper dbHelper = null;

	public static WatcherListManager getInstance(Context context) {
		if (sInstance == null) {
			sInstance = new WatcherListManager(context);
		}

		return sInstance;
	}

	private Context mContext;

	private WatcherListManager(Context context) {
		mContext = context;
		dbHelper = new DbHelper(mContext);
	}


	// query
	public ArrayList<WatcherListEntity> query(String selection, String[] selectionArgs) {
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cursor = db.query(WatcherListMetadata.TABLE_NAME, null, selection,
				selectionArgs, null, null, null);

		ArrayList<WatcherListEntity> booksInfo = new ArrayList<WatcherListEntity>();
		while (cursor.moveToNext()) {
			WatcherListEntity entity = new WatcherListEntity();
			entity.setID(cursor.getInt(cursor
					.getColumnIndex(WatcherListMetadata._ID)));
			entity.setTime(cursor.getString(cursor
					.getColumnIndex(WatcherListMetadata.TIME)));
			entity.setDes(cursor.getString(cursor
					.getColumnIndex(WatcherListMetadata.DES)));
			entity.setText(cursor.getString(cursor
					.getColumnIndex(WatcherListMetadata.TEXT)));

			booksInfo.add(entity);
		}
		return booksInfo;
	}

	// insert
	public long insert(String time, String des, String text) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(WatcherListMetadata.TIME, time);
		cv.put(WatcherListMetadata.DES, des);
		cv.put(WatcherListMetadata.TEXT, text);
		
		long row = db.insert(WatcherListMetadata.TABLE_NAME, null, cv);
		return row;
	}

	// delete
	public int delete(String where, String[] whereValue) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		return db.delete(WatcherListMetadata.TABLE_NAME, where, whereValue);
	}

}
