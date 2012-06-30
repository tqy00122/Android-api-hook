package ljr.Watcher.metadata;

import android.provider.BaseColumns;

/**
 * @author liujierui
 *
 */
public class WatcherListMetadata implements BaseColumns{
	private WatcherListMetadata(){
	}
	
	public static final String TABLE_NAME = "WatcherList";
	
	public static final String TIME = "Time";
	
	public static final String DES = "Des";
	
	public static final String TEXT = "Text";
	
}
