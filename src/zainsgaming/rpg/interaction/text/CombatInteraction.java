package zainsgaming.rpg.interaction.text;

import java.util.List;

import zainsgaming.rpg.characters.ZGCharacter;
import zainsgaming.rpg.events.Event;
import zainsgaming.rpg.util.AttackTypes;

/**
 * 
 * @author rizvi
 *
 */
public class CombatInteraction {
	
	// Instance variables
	private ZGCharacter character;
	private Event event;
	private ZGScanner scan;
	
	/**
	 * Constructor. Set instance variables/
	 * @param character The current character for this interaction.
	 * @param event The current event.
	 */
	public CombatInteraction(ZGCharacter character, Event event){
		this.character = character;
		this.event = event;
		scan = ZGScanner.getInstance();
	}
	
	public ZGCharacter getCharacter(){
		return this.character;
	}
	
	/**
	 * This method presents the main combat menu. 
	 */
	public void combatMenu(){
		
		//Print the possible choices to the user
		System.out.println("Choose action:");
		System.out.println("1: Melee Attack");
		System.out.println("2: Dodge");
		
		boolean validInput = false;
		
		while (!validInput){ //Loop until we have valid input
			String input = scan.nextLine();
			
			//Call the appropriate submenu based on the input
			if (input.equals("1")){
				validInput = true;
				attackMenu();
			} else if (input.equals("2")){
				
			}
			
		}
	}
	
	private void attackMenu(){
		AttackTypes attackType = character.getAttackTypes();
		
		switch (attackType){
		case ONE_TARGET:
			oneTargetAttackMenu();
		}
	}
	
	private void oneTargetAttackMenu(){
		
		System.out.println("Choose your target.");
		
		List<ZGCharacter> characters = event.getCharacters();
		int idx = 0;
		
		for (ZGCharacter zgChar : characters){
			System.out.println(idx + ": " + zgChar.getName());
			idx++;
		}
		
		ZGCharacter target = null;
		idx = 0;
		
		while (target == null){
			idx = scan.nextInt();
			
			if (idx >= 0 && idx < characters.size()){
				target = characters.get(idx);
			}
		}
		
		//roll to hit
		if (character.rollHit() >= target.getAC()){
			//If hit was successful, then roll attack
			target.takeHit(character.rollAttack());
		}
	}

}
