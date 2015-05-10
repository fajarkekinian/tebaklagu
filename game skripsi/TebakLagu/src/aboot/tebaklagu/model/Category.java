package aboot.tebaklagu.model;

public class Category {

	private String kate;
	private int lepel;
	
	public Category(){}
	public Category(String kate, int lepel){
		super();
		this.kate = kate;
		this.lepel = lepel;
	}
	public String getKate(){
		return this.kate;
	}
	public void setKate(String kate){
		this.kate = kate;
	}
	public int getLepel(){
		return this.lepel;
	}
	public void setLepel(int lepel){
		this.lepel = lepel;
	}
}
