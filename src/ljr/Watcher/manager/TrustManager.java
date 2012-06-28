package ljr.Watcher.manager;

import java.util.ArrayList;

import ljr.Watcher.db.DbHelper;
import ljr.Watcher.entity.TrustEntity;
import ljr.Watcher.metadata.TrustMetadata;
import ljr.Watcher.metadata.WatcherListMetadata;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TrustManager {
	private static TrustManager sInstance = null;
	private DbHelper dbHelper = null;

	public static TrustManager getInstance(Context context) {
		if (sInstance == null) {
			sInstance = new TrustManager(context);
		}

		return sInstance;
	}

	private Context mContext;

	private TrustManager(Context context) {
		mContext = context;
		dbHelper = new DbHelper(mContext);
	}


	// query
	public ArrayList<TrustEntity> query(String selection, String[] selectionArgs) {
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cursor = db.query(WatcherListMetadata.TABLE_NAME, null, selection,
				selectionArgs, null, null, null);

		ArrayList<TrustEntity> booksInfo = new ArrayList<TrustEntity>();
		while (cursor.moveToNext()) {
			TrustEntity entity = new TrustEntity();
			entity.setID(cursor.getInt(cursor
					.getColumnIndex(TrustMetadata._ID)));
			entity.setType(cursor.getInt(cursor
					.getColumnIndex(TrustMetadata.TYPE)));
			entity.setContent(cursor.getString(cursor
					.getColumnIndex(TrustMetadata.CONTENT)));

			booksInfo.add(entity);
		}
		return booksInfo;
	}

	// insert
	public long insert(String time, String des, String text) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(TrustMetadata.TYPE, time);
		cv.put(TrustMetadata.CONTENT, des);
		
		long row = db.insert(TrustMetadata.TABLE_NAME, null, cv);
		return row;
	}

	// delete
	public int delete(String where, String[] whereValue) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		return db.delete(TrustMetadata.TABLE_NAME, where, whereValue);
	}

}
