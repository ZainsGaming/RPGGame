package zainsgaming.rpg.events;

import java.util.ArrayList;
import java.util.List;

import zainsgaming.rpg.characters.ZGCharacter;

public abstract class Event {

	private List<ZGCharacter> characters;
	
	public Event(List<ZGCharacter> chars){
		characters = new ArrayList<ZGCharacter>();
		characters.addAll(chars);
	}
	
	public List<ZGCharacter> getCharacters(){
		return this.characters;
	}
	
}
