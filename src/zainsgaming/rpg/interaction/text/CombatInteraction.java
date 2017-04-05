package zainsgaming.rpg.interaction.text;

import zainsgaming.rpg.characters.ZGCharacter;
import zainsgaming.rpg.events.Event;

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
				attackMenu();
			} else if (input.equals("2")){
				
			}
			
		}
	}
	
	private void attackMenu(){
		
	}

}
