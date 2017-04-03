package zainsgaming.rpg.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import zainsgaming.rpg.characters.ZGCharacter;

public class CombatEvent extends Event {
	private ArrayList<ZGCharacter> characters;
	private ArrayList<ZGCharacter> initOrder;

	public CombatEvent(ArrayList<ZGCharacter> chars){
		characters = new ArrayList<ZGCharacter>();
		characters.addAll(chars);
		initOrder = new ArrayList<ZGCharacter>();
		initializeOrder();
	}

	/**
	 * Initialize the combat by rolling initiative and ordering the characters.
	 */
	private void initializeOrder(){
		
		//Roll the initiatives and store them in the map
		Map<ZGCharacter, Integer> initMap = new HashMap<ZGCharacter, Integer>();
		for (ZGCharacter zgChar : characters){
			int initiative = zgChar.rollInitiative();
			initMap.put(zgChar, initiative);
		}

		/*
		for (ZGCharacter zgchar : initMap.keySet()){
			System.out.println(zgchar.getName() + " " + initMap.get(zgchar));
		}
		System.out.println();
		*/

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
		
		/*
		for (ZGCharacter zgchar : initOrder){
			System.out.println(zgchar.getName() + " " + initMap.get(zgchar));
		}
		*/

	}

}
