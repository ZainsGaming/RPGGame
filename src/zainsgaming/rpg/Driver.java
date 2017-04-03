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
		ZGCharacter p4 = new ZGCharacter("p4");
		ZGCharacter p5 = new ZGCharacter("p5");
		ZGCharacter p6 = new ZGCharacter("p6");
		ZGCharacter p7 = new ZGCharacter("p7");
		ZGCharacter p8 = new ZGCharacter("p8");
		ZGCharacter p9 = new ZGCharacter("p9");
		ZGCharacter p10 = new ZGCharacter("p10");
		Weapon w1 = new Weapon(5);
		Weapon w2 = new Weapon(4);
		
		ArrayList<ZGCharacter> list = new ArrayList<ZGCharacter>();
		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		list.add(p5);
		list.add(p6);
		list.add(p7);
		list.add(p8);
		list.add(p9);
		list.add(p10);
		
		CombatEvent ce = new CombatEvent(list);
		
		p1.setEquippedWeapon(w1);
		p2.setEquippedWeapon(w2);
		
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
