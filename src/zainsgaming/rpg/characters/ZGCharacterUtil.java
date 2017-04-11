package zainsgaming.rpg.characters;

import java.util.Random;


/**
 * This class provides the utility functions and values for the Person class.
 * @author szrrizvi
 *
 */

public class ZGCharacterUtil {
	
	// Default values
	//MAX HP, STR, DEX, CON, INTL, WIS, CHA
	public static final int[] DEFAULT_STATS = {15, 13, 13, 13, 12, 12, 12};
		
	
	/**
	 * Calculates and returns the modifier value of the given stat score.
	 * mod = FLOOR [(score - 10) / 2] 
	 * @param score The score the calculate the modifier for.
	 * @return The modifier value for the given score.
	 */
	public static int calcMod(int score){
		int mod = (score-10) / 2;
		return mod;
	}
	
	/**
	 * Returns the stats bonuses for the given race type.
	 * @param raceType The target race type.
	 * @return The stats bonuses for the given race type.
	 */
	public static int[] raceStatBonus(Race_Type raceType){
		int statsBonus[] = {0, 0, 0, 0, 0, 0, 0};
		
		switch (raceType){
		case HUMAN:
			for (int i = 1; i < 7; i++){
				statsBonus[i] = 1;	//+1 to all stats, except for Max HP
			}
			break;
		case ELF:
			statsBonus[2] = 2;	//DEX +2
			statsBonus[4] = 1;	//INTL +1
			break;
		case DWARF:
			statsBonus[0] = 1;	//MAX HP +1
			statsBonus[3] = 2;	//CON +2
			statsBonus[5] = 1;	//WIS +1
			break;
		}
		
		return statsBonus;
	}

	/**
	 * Returns the movement speed for the given race type.
	 * @param raceType The target race type.
	 * @return The movement speed for the given race type.
	 */
	public static int raceSpeed(Race_Type raceType){
		
		//Return the speed based on race.
		switch (raceType){
		case HUMAN:
			return 3;
		case ELF:
			return 3;
		case DWARF:
			return 2;
		default:
			return 1;
		}
		
	}
}
