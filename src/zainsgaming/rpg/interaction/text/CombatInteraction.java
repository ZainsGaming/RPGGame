package zainsgaming.rpg.interaction.text;

import zainsgaming.rpg.characters.ZGCharacter;
import zainsgaming.rpg.events.CombatEvent;
import zainsgaming.rpg.events.Event;

/**
 * 
 * @author rizvi
 *
 */
public abstract class CombatInteraction {
	
	// Instance variables
	private ZGCharacter character;
	private CombatEvent event;
	
	
	/**
	 * Constructor. Set instance variables.
	 * @param character The current character for this interaction.
	 * @param event The current event.
	 */
	public CombatInteraction(ZGCharacter character, CombatEvent event){
		this.character = character;
		this.event = event;
	}
	
	/**
	 * @return the character
	 */
	public ZGCharacter getCharacter(){
		return this.character;
	}
	
	/**
	 * @return the event
	 */
	public CombatEvent getEvent(){
		return this.event;
	}
	
	/**
	 * Runs the combat menu
	 */
	public abstract void combatMenu();
	
}
