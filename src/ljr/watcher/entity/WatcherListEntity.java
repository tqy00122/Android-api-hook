package ljr.watcher.entity;

public class WatcherListEntity {
	private int mID;
	private String mTime;
	private String mDes;
	private String mText;
	
	public void setID(int id){
		mID = id;
	}

	public void setTime(String time){
		mTime = time;
	}
	
	public void setDes(String des){
		mDes = des;
	}
	
	public void setText(String text){
		mText = text;
	}
	
	public int getID(){
		return mID;
	}
	
	public String getTime(){
		return mTime;
	}
	
	public String getDes(){
		return mDes;
	}
	
	public String getText(){
		return mText;
	}
}
