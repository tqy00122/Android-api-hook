package ljr.Watcher.metadata;

import android.provider.BaseColumns;

public class TrustMetadata implements BaseColumns{
	private TrustMetadata(){
	}
	
	public static final String TABLE_NAME = "TrustList";
	
	public static final String TYPE= "Type";
	
	public static final String CONTENT = "Content";
}
