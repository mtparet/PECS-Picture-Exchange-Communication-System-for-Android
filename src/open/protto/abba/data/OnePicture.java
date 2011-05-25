package open.protto.abba.data;

public class OnePicture {
	
	private int id;
	private String name;
	
	public OnePicture(int id){
		this.id = id;
		this.name = "test";
	}
	
	public OnePicture(OnePicture op){
		this.id = op.id;
		this.name = op.name;
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getName(){
		return this.name;
	}
	

}
