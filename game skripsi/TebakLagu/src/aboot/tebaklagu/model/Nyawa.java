package aboot.tebaklagu.model;

public class Nyawa {

	private int id ;
	private int cate ;
	private int level ;
	private int jiwa;
	
	public static final String KEY_ID = "_id";
	public static final String KEY_CATE = "cate";
	public static final String KEY_LEVEL = "level";
	public static final String KEY_JIWA = "jiwa";
		

	public Nyawa(){}
	
	
	
	public int getID(){
		return this.id;
	}
	public void setID(int id){
		this.id = id;
	}
	public int getCate(){
		return this.cate;
	}
	public void setCate(int cate){
		this.cate = cate;
	}
	public int getLevel(){
		return this.level;
	}
	public void setLevel(int level){
		this.level = level;
	}
	public int getJiwa(){
		return this.jiwa;
	}
	public void setJiwa(int jiwa){
		this.jiwa = jiwa;
	}
	
}
