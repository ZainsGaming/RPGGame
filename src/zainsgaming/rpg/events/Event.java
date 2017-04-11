package zainsgaming.rpg.events;

import java.util.ArrayList;
import java.util.List;

import zainsgaming.rpg.characters.ZGCharacter;

/**
 * Generic event. Each event contains 1 or more characters.
 * @author rizvi
 *
 */

public abstract class Event {

	private List<ZGCharacter> characters;
	
	/**
	 * Constructor. Sets the characters.
	 * @param chars The characters to set
	 */
	public Event(List<ZGCharacter> chars){
		characters = new ArrayList<ZGCharacter>();
		characters.addAll(chars);
	}
	
	/**
	 * @return The characters
	 */
	public List<ZGCharacter> getCharacters(){
		return this.characters;
	}
	
}
