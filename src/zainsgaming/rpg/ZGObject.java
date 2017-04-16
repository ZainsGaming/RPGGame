package zainsgaming.rpg;

public class ZGObject {
	private static int idCounter;
	private final int uuid;
	
	public ZGObject(){
		uuid = idCounter;
		idCounter++;
	}
}
