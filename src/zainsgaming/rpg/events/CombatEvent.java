package zainsgaming.rpg.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import zainsgaming.rpg.characters.ZGCharacter;
import zainsgaming.rpg.interaction.text.CombatInteraction;
import zainsgaming.rpg.interaction.text.CombatInteractionAI;
import zainsgaming.rpg.interaction.text.CombatInteractionPlayer;

public class CombatEvent extends Event {

	//Lists - 
	//initOrder - The initiative order
	//enemies - The player's enemies
	//team - The player's team
	private List<ZGCharacter> initOrder, enemies, team;

	/**
	 * Constructor. Sets the list of characters, initiative order, team, and the enemies.
	 * @param chars The characters involved in the event
	 */
	public CombatEvent(ArrayList<ZGCharacter> chars){
		super(chars);

		//Set the initiative order
		initOrder = new ArrayList<ZGCharacter>();
		initiativeOrder();

		//Set the list of team and enemies
		
		enemies = new ArrayList<ZGCharacter>();
		team = new ArrayList<ZGCharacter>();
		for (ZGCharacter zgChar : getCharacters()){
			if (zgChar.getIsFriendly()){
				team.add(zgChar);
			} else {
				enemies.add(zgChar);
			}
		}
	}
	
	/**
	 * @return The list of enemies
	 */
	public List<ZGCharacter> getEnemies(){
		return this.enemies;
	}
	
	/**
	 * @return The list of the player's team
	 */
	public List<ZGCharacter> getTeam(){
		return this.team;
	}

	/**
	 * Initialize the combat by rolling initiative and ordering the characters.
	 */
	private void initiativeOrder(){

		List<ZGCharacter> characters = getCharacters();

		//Roll the initiatives and store them in the map
		Map<ZGCharacter, Integer> initMap = new HashMap<ZGCharacter, Integer>();
		for (ZGCharacter zgChar : characters){
			int initiative = zgChar.rollInitiative();
			initMap.put(zgChar, initiative);
		}

		// Add in initiative order the characters to initOrder
		// Using insertion sort algorithm
		for (ZGCharacter zgChar : characters){
			//If initOrder is empty, then simply add the character as first element
			if (initOrder.isEmpty()){
				initOrder.add(zgChar);
			} else {
				//If the current character's initiative is greater than or equal to the first character in the order,
				//Then set the current character as the first element. 
				if (initMap.get(zgChar) >= initMap.get(initOrder.get(0))){
					initOrder.add(0, zgChar);	//This method automatically shift the rest of the elements
				} else {
					boolean added = false;
					//Loop through the order list, and add the first place where the current character belongs
					for (int i = 1; i < initOrder.size(); i++){
						if (initMap.get(zgChar) >= initMap.get(initOrder.get(i))){
							initOrder.add(i, zgChar);
							//Specify the character was added and end loop.
							added = true;
							i = initOrder.size();
						}
					}

					//If character was not added in the order during the for loop, that means that the character belongs
					//at the end of the order
					if (!added){
						initOrder.add(zgChar);
					}
				}
			}
		}		
	}

	/**
	 * Runs the combat sequence.
	 * @return True if the player's team defeats the enemies, else false.
	 */
	public boolean startCombat(){

		//Initialize the combat interactions
		List<ZGCharacter> characters = getCharacters();
		List<CombatInteraction> cis = new ArrayList<CombatInteraction>();

		for (ZGCharacter zgChar : characters){
			CombatInteraction ci = null;
			
			if (zgChar.getIsFriendly()){
				ci = new CombatInteractionPlayer(zgChar, this);
			} else {
				ci = new CombatInteractionAI(zgChar, this);
			}
			
			cis.add(ci);
		}


		//Loop flag and index. 
		boolean done = false;
		int idx = 0;

		
		List<ZGCharacter> teamCopy = new ArrayList<ZGCharacter>();
		List<ZGCharacter> enemiesCopy = new ArrayList<ZGCharacter>();
		teamCopy.addAll(team);
		enemiesCopy.addAll(enemies);
		
		//Keep looping until one side is defeated
		//Loop through the characters, call the combatMenu for the corresponding 
		//combat interaction for the character, remove defeated characters (hp <= 0),
		//check if one side is defeated, and finally update the index.
		while (!done){
			//Call combatMenu for the current character
			CombatInteraction ci = cis.get(idx);
			ci.combatMenu();

			//Update cis and copy lists by removing defeated character
			for (int i = 0; i < characters.size(); i++){
				ZGCharacter charTemp = characters.get(i);
				if (charTemp.getCurrentHP() <= 0){
					cis.remove(i);
					
					if (charTemp.getIsFriendly()){
						teamCopy.remove(charTemp);
					} else {
						enemiesCopy.remove(charTemp);
					}
				}
			}
			
			//If either copy lists are empty, then combat is done
			if (teamCopy.isEmpty() || enemiesCopy.isEmpty()){
				done = true;
			}
			
			
			//Update index
			idx++;
			if (idx >= cis.size()){
				idx = 0;
			}
		}
		
		
		//Return the team is defeated, return false, if the enemies are defeated then return true.
		//Even if both sides are defeated, the player's team being defeated counts as a fail.
		if (teamCopy.isEmpty()){
			return false;
		} else {
			return true;
		}

	}

}
