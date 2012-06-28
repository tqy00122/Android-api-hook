package ljr.watcher.entity;

public class TrustEntity {
	private int mID;
	private int mType;
	private String mContent;
	
	public void setID(int id){
		mID = id;
	}

	public void setType(int type){
		mType = type;
	}
	
	public void setContent(String content){
		mContent = content;
	}
	
	public int getID(){
		return mID;
	}
	
	public int getType(){
		return mType;
	}
	
	public String getContent(){
		return mContent;
	}
	
}
