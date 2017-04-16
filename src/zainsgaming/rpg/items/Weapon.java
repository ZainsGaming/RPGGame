package zainsgaming.rpg.items;

import zainsgaming.rpg.ZGObject;

public class Weapon extends ZGObject {
	private int dmg;
	
	public Weapon (int dmg){
		super();
		this.dmg = dmg;
	}
	
	public int rollDmg(){
		return this.dmg;
	}
}
