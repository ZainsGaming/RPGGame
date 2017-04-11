package zainsgaming.rpg;

import java.util.ArrayList;

import zainsgaming.rpg.characters.ZGCharacter;
import zainsgaming.rpg.events.CombatEvent;
import zainsgaming.rpg.items.Weapon;

public class Driver {

	public static void main(String[] args){
		
		
		ZGCharacter p1 = new ZGCharacter("p1");
		ZGCharacter p2 = new ZGCharacter("p2");
		ZGCharacter p3 = new ZGCharacter("p3");
		p1.setIsFriendly(true);

		Weapon w1 = new Weapon(5);
		Weapon w2 = new Weapon(4);
		Weapon w3 = new Weapon(4);
	
		p1.setEquippedWeapon(w1);
		p2.setEquippedWeapon(w2);
		p3.setEquippedWeapon(w3);
		
		ArrayList<ZGCharacter> list = new ArrayList<ZGCharacter>();
		list.add(p1);
		list.add(p2);
		list.add(p3);
	
		CombatEvent ce = new CombatEvent(list);
		ce.startCombat();
		
	}
}
