package zainsgaming.rpg;

public abstract class ZGObject {
	private static int idCounter;
	private final int uuid;
	private String name;
	
	public ZGObject(String name){
		uuid = idCounter;
		idCounter++;
		
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
}
