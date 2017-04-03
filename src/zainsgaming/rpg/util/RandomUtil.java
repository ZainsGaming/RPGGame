package zainsgaming.rpg.util;

import java.util.Random;

public class RandomUtil {
	private static Random random = new Random(1234);
	
	public static Random getRandom(){
		return random;
	}
}
