package ljr.Watcher.db;

import ljr.Watcher.metadata.DbMetadata;
import ljr.Watcher.metadata.TrustMetadata;
import ljr.Watcher.metadata.WatcherListMetadata;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * @author liujierui
<<<<<<< HEAD
 * 
=======
 *
>>>>>>> origin/master
 */
public class DbHelper extends SQLiteOpenHelper {
	public static final String TAG = DbHelper.class.getSimpleName();

	public DbHelper(Context context) {
		super(context, DbMetadata.DATABASE_NAME, null,
				DbMetadata.DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + WatcherListMetadata.TABLE_NAME + "("
<<<<<<< HEAD
				+ WatcherListMetadata._ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
=======
				+ WatcherListMetadata._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
>>>>>>> origin/master
				+ WatcherListMetadata.TIME + " TEXT, "
				+ WatcherListMetadata.DES + " TEXT, "
				+ WatcherListMetadata.TEXT + " TEXT);");

<<<<<<< HEAD
		db.execSQL("CREATE TABLE " + TrustMetadata.TABLE_NAME + "("
				+ TrustMetadata._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ TrustMetadata.TYPE + " INTEGER, " + TrustMetadata.CONTENT
				+ " TEXT);");
=======
		// 创建Income表
		db.execSQL("CREATE TABLE " + TrustMetadata.TABLE_NAME + "("
				+ TrustMetadata._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ TrustMetadata.TYPE + " INTEGER, "
				+ TrustMetadata.CONTENT + " TEXT);");
>>>>>>> origin/master

		initDatabase(db);
	}

<<<<<<< HEAD
=======
	/**
	 * 初始化
	 */
>>>>>>> origin/master
	private void initDatabase(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
				+ newVersion + ", which will destroy all old data");

		db.execSQL("DROP TABLE IF EXISTS " + WatcherListMetadata.TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + TrustMetadata.TABLE_NAME);

		onCreate(db);
	}

}
