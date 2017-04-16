package zainsgaming.rpg.world;

import zainsgaming.rpg.ZGObject;
import zainsgaming.rpg.characters.ZGCharacter;

public class Grid {

	private static final int DEFAULT_HEIGHT = 10;
	private static final int DEFAULT_WIDTH = 10;

	private ZGObject[][] grid;

	/**
	 * Default constructor. Create empty default height and width grid (10X10).
	 */
	public Grid(){
		//Initialize the grid as default grid, with all null values.
		grid = defaultGrid();
	}

	/**
	 * Default constructor. Create empty 10X10 grid.
	 */
	public Grid(ZGObject[][] sampleGrid){

		if (sampleGrid == null || sampleGrid.length == 0 || sampleGrid[0].length == 0){
			//If the grid is null or either length or width are 0 then create the default grid
			grid = defaultGrid();
		} else {
			//Initialize the grid based on the sample grid.
			int height = sampleGrid.length;
			int width = sampleGrid[0].length;
			grid = new ZGObject [height][width];
			for (int col = 0; col < height; col++){
				for (int row = 0; row < width; row++){
					grid[col][row] = sampleGrid[col][row];
				}
			}
		}
	}

	/**
	 * Creates and returns a default grid. 10X10, with all null values.
	 * @return The default grid.
	 */
	private ZGCharacter[][] defaultGrid(){
		//Create the default grid
		ZGCharacter[][] defaultGrid = new ZGCharacter [DEFAULT_HEIGHT][DEFAULT_WIDTH];
		for (int col = 0; col < DEFAULT_HEIGHT; col++){
			for (int row = 0; row < DEFAULT_WIDTH; row++){
				//Set all values to null
				defaultGrid[col][row] = null;
			}
		}

		return defaultGrid;
	}
	
	/**
	 * Sets the given character to the given coordinates
	 * @param character The character to set
	 * @param col The y coordinate 
	 * @param row The x coordinate
	 * @return true if the character can be set at the given coordiantes, else false.
	 */
	public boolean setCharacter(ZGCharacter character, int col, int row){
		
		if (grid[col][row] == null){
			//If the coordinate is null, then set the character
			grid[col][row] = character;
			return true;
		} else {
			//If the coordiante already constain a characters, return false.
			return false;
		}
	}
}