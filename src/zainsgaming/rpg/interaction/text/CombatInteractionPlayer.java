package zainsgaming.rpg.interaction.text;

import java.util.List;

import zainsgaming.rpg.characters.ZGCharacter;
import zainsgaming.rpg.events.CombatEvent;
import zainsgaming.rpg.util.AttackTypes;

public class CombatInteractionPlayer extends CombatInteraction {
	private ZGScanner scan;

	/**
	 * Constructor. Set instance variables.
	 * @param character The current character for this interaction.
	 * @param event The current event.
	 */
	public CombatInteractionPlayer(ZGCharacter character, CombatEvent event){
		super(character, event);
		scan = ZGScanner.getInstance();
	}

	/**
	 * This method presents the main combat menu. 
	 */
	public void combatMenu(){

		System.out.println(getCharacter().getName() + "\'s Turn.");
		
		//Print the possible choices to the user
		System.out.println("1: Melee Attack");
		System.out.println("2: Dodge");
		System.out.print("Choose action: ");

		boolean validInput = false;	
		while (!validInput){ //Loop until we have valid input
			String input = scan.nextLine();

			//Call the appropriate submenu based on the input
			if (input.equals("1")){
				validInput = true;
				meleeAttackMenu();
			} else if (input.equals("2")){
				getCharacter().setIsDefending(true);
			} else {
				System.out.print("Invalid input. Enter value between 1 - 2: ");
			}
		}
		
		System.out.println("----------");
	}

	/**
	 * This method presents the melee attack menu
	 */
	private void meleeAttackMenu(){

		//Get the attack type and call the proper follow method.
		AttackTypes attackType = getCharacter().getAttackTypes();

		switch (attackType){
		case ONE_TARGET:
			oneTargetAttackMenu();
		}
	}

	/**
	 * The one target attack menu
	 */
	private void oneTargetAttackMenu(){

		//Prompt user to pick target
		//Print possible target (enemies, friendlies, and self)
		List<ZGCharacter> characters = getEvent().getCharacters();
		int idx = 0;
		for (ZGCharacter zgChar : characters){
			System.out.println(idx + ": " + zgChar.getName());
			idx++;
		}
		System.out.print("Choose your target: ");
		
		//Assing target based on input
		ZGCharacter target = null;
		idx = 0;
		while (target == null){
			idx = scan.nextInt();
			if (idx >= 0 && idx < characters.size()){
				target = characters.get(idx);
			} else {
				System.out.print("Invalid input. Enter integer between 0 and " + (characters.size()-1) + ": ");
			}
		}
		
		//Attack target
		getCharacter().attackTarget(target);
	}
}
