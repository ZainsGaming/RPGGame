package zainsgaming.rpg.util;

/**
 * This class provides the functionality of dice. 
 * @author szrrizvi
 *
 */
public class DiceUtil {
	
	/**
	 * @return roll a d20
	 */
	public static int d20(){
		return RandomUtil.getRandom().nextInt(20) + 1;
	}
	
	/**
	 * @return roll a d12
	 */
	public static int d12(){
		return RandomUtil.getRandom().nextInt(12) + 1;
	}
	
	/**
	 * @return roll a d10
	 */
	public static int d10(){
		return RandomUtil.getRandom().nextInt(10) + 1;
	}
	
	/**
	 * @return roll a d8
	 */
	public static int d8(){
		return RandomUtil.getRandom().nextInt(8) + 1;
	}
	
	/**
	 * @return roll a 6
	 */
	public static int d6(){
		return RandomUtil.getRandom().nextInt(6) + 1;
	}
	
	/**
	 * @return roll a d4
	 */
	public static int d4(){
		return RandomUtil.getRandom().nextInt(4) + 1;
	}

}
