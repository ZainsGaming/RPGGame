package zainsgaming.rpg.characters;

import java.util.Random;


/**
 * This class provides the utility functions and values for the Person class.
 * @author szrrizvi
 *
 */

public class CharacterUtil {
	
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
		case DWARF_HILL:
			statsBonus[0] = 1;	//MAX HP +1
			statsBonus[3] = 2;	//CON +2
			statsBonus[5] = 1;	//WIS +1
			break;
		case DWARD_MOUNTAIN:
			statsBonus[1] = 2;	//STR +2
			statsBonus[3] = 2;	//CON +2
			break;
		case ELF_HIGH:
			statsBonus[2] = 2;	//DEX +2
			statsBonus[4] = 1;	//INTL +1
			break;
		case ELF_WOOD:
			statsBonus[2] = 2;	//DEX +2
			statsBonus[5] = 1; 	//WIS +1
			break;
		case ELF_DARK:
			statsBonus[2] = 2;	//DEX +2
			statsBonus[6] = 1;	//CHA +1
			break;
		case HALFLING_LIGHTFOOT:
			statsBonus[2] = 2;	//DEX +2
			statsBonus[6] = 1;	//CHA +1
			break;
		case HALFLING_STOUT:
			statsBonus[2] = 2;	//DEX +2
			statsBonus[3] = 1;	//CON +1
			break;
		case HUMAN:
			for (int i = 1; i < 7; i++){
				statsBonus[i] = 1;	//+1 to all stats, except for Max HP
			}
			break;
		case DRAGONBORN:
			statsBonus[1] = 2;	//STR +2
			statsBonus[6] = 1;	//CHA +1
			break;
		case GNOME_FOREST:
			statsBonus[4] = 2;	//INTL +2
			statsBonus[2] = 1;	//DEX +1
			break;
		case GNOME_ROCK:
			statsBonus[4] = 2;	//INTL +2
			statsBonus[3] = 1;	//CON +1
			break;
		case TIEFLING:
			statsBonus[4] = 1;	//INTL +1
			statsBonus[6] = 2;	//CHA +2
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
		case DWARF_HILL:
			return 2;
		case DWARD_MOUNTAIN:
			return 2;
		case ELF_HIGH:
			return 3;
		case ELF_WOOD:
			return 3;
		case ELF_DARK:
			return 3;
		case HALFLING_LIGHTFOOT:
			return 2;
		case HALFLING_STOUT:
			return 2;
		case HUMAN:
			return 3;
		case DRAGONBORN:
			return 3;
		case GNOME_FOREST:
			return 2;
		case GNOME_ROCK:
			return 2;
		case TIEFLING:
			return 3;
		default:
			return 1;
		}
		
	}
}
