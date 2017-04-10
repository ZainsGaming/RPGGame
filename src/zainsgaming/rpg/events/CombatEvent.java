package zainsgaming.rpg.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import zainsgaming.rpg.characters.ZGCharacter;
import zainsgaming.rpg.interaction.text.CombatInteraction;
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

	public void startCombat(){

		List<ZGCharacter> characters = getCharacters();
		List<CombatInteraction> cis = new ArrayList<CombatInteraction>();

		for (ZGCharacter zgChar : characters){
			CombatInteraction ci = null;
			
			if (zgChar.getIsFriendly()){
				ci = new CombatInteractionPlayer(zgChar, this);
			cis.add(ci);
			} else {
				
			}
		}


		boolean done = false;
		int idx = 0;

		while (!done){
			CombatInteraction ci = cis.get(idx);
			ci.combatMenu();

			idx++;

			for (CombatInteraction ciTemp : cis){
				if (ciTemp.getCharacter().getCurrentHP() <= 0){
					characters.remove(ciTemp.getCharacter());
					if (characters.size() == 1){
						System.out.println("DONE");
						done = true;
					}
				}
			}

			if (idx == cis.size()){
				idx = 0;
			}
		}

	}

}
