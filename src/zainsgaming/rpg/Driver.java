package zainsgaming.rpg;

import java.util.ArrayList;

import zainsgaming.rpg.characters.ZGCharacter;
import zainsgaming.rpg.events.CombatEvent;
import zainsgaming.rpg.items.Weapon;
import zainsgaming.rpg.world.Grid;

public class Driver {

	public static void main(String[] args){
		
		
		ZGCharacter p1 = new ZGCharacter("1");
		ZGCharacter p2 = new ZGCharacter("2");
		ZGCharacter p3 = new ZGCharacter("3");
		p1.setIsFriendly(true);

		Weapon w1 = new Weapon("4", 5);
		Weapon w2 = new Weapon("5", 4);
		Weapon w3 = new Weapon("6", 4);
	
		p1.setEquippedWeapon(w1);
		p2.setEquippedWeapon(w2);
		p3.setEquippedWeapon(w3);
		
		ArrayList<ZGCharacter> list = new ArrayList<ZGCharacter>();
		list.add(p1);
		list.add(p2);
		list.add(p3);
	
		Grid grid = new Grid();
		grid.setObject(p1, 0, 0);
		grid.setObject(p2, 9, 9);
		grid.setObject(p3, 4, 5);
		
		
		System.out.println(grid);
		
		grid.move(p2, Grid.Direction.NORTH);
		
		System.out.println();
		System.out.println(grid);
		
		//CombatEvent ce = new CombatEvent(list, grid);
		//ce.startCombat();
		
	}
}
