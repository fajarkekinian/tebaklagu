package aboot.tebaklagu.model;

public class Lagu {

	private int id ;
	private int id_cate ;
	private String musik ;
	private String judul ;
	private int level ;
	private String status ;
	
	public static final String KEY_ID = "_id";
	public static final String KEY_cate = "id_cate" ;
	public static final String KEY_musik = "musik" ;
	public static final String KEY_judul = "judul" ;
	public static final String KEY_level = "level" ;
	public static final String KEY_status = "status" ;
		

	public Lagu(){}
	
	
	
	public int getID(){
		return this.id;
	}
	public void setID(int id){
		this.id = id;
	}
	
	public int getIDCate(){
		return this.id_cate;
	}
	public void setIDCate(int id_cate){
		this.id_cate = id_cate;
	}
	
	public String getMusik(){
		return this.musik;
	}
	public void setMusik(String musik){
		this.musik = musik;
	}
	
	public String getJudul(){
		return this.judul;
	}
	public void setJudul(String judul){
		this.judul = judul;
	}
	
	public int getLevel(){
		return this.level;
	}
	public void setLevel(int level){
		this.level = level;
	}
	
	public String getStatus(){
		return this.status;
	}
	public void setStatus(String status){
		this.status = status;
	}
	
	
	
}
