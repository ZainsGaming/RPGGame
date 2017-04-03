package zainsgaming.rpg.items;

public class Weapon {
	private int dmg;
	
	public Weapon (int dmg){
		this.dmg = dmg;
	}
	
	public int rollDmg(){
		return this.dmg;
	}
}
