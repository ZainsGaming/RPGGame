package zainsgaming.rpg.interaction.text;

import java.util.Scanner;

/**
 * This class represents the custom scanner class.
 * This is used to localize the code for directly interacting with the user.
 * This class uses the singleton design pattern.
 * @author rizvi
 *
 */

public class ZGScanner {
	
	private static ZGScanner instance = null;	//The instance of the object
	private Scanner scanner = null;				//The instance variable scanner
	
	/**
	 * Constructor. Initialize the scanner. 
	 * This is set private for the singleton design pattern.
	 */
	private ZGScanner(){
		scanner = new Scanner(System.in);
	}
	
	/**
	 * @return The instance of the object.
	 */
	public static ZGScanner getInstance(){
		//If the instance hasn't been initialized, then initialize it.
		if (instance == null){
			instance = new ZGScanner();
		}
		return instance;
	}
	
	/**
	 * @return The next line from the input stream.
	 */
	public String nextLine(){
		//Delegate task to scanner, and trim the result
		return scanner.nextLine().trim();
	}
	
}
