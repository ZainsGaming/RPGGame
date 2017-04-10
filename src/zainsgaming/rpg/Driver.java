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
			
		/*
		Random rand = new Random(1234);
		int p1Init = p1.rollInitiative();
		int p2Init = p2.rollInitiative();
		
		ZGCharacter[] order = new ZGCharacter[2];
		if (p1Init >= p2Init){
			order[0] = p1;
			order[1] = p2;
		} else {
			order[0] = p2;
			order[1] = p1;
		}
		
		boolean done = false;
		
		while (!done){
			for (int i = 0; i < 2; i++){
				ZGCharacter attacker = order[i];
				ZGCharacter defender = order[1-i];
				
				int rollHit = attacker.rollHit();
				System.out.println("Roll hit: " + rollHit);
				if (rollHit > defender.getAC()){
					int dmg = attacker.rollAttack();
					if (defender.takeHit(dmg) <= 0){
						System.out.println(attacker.getName() + " wins.");
						done = true;
					}
					
					System.out.println(order[0].getName() + " " + order[0].getCurrentHP());
					System.out.println(order[1].getName() + " " + order[1].getCurrentHP());
				}
				
			}
		}*/
	}
}
