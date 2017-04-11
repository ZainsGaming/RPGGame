package zainsgaming.rpg.world;

import zainsgaming.rpg.characters.ZGCharacter;

public class Grid {

	private static final int DEFAULT_HEIGHT = 10;
	private static final int DEFAULT_WIDTH = 10;

	private ZGCharacter[][] grid;

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
	public Grid(ZGCharacter[][] sampleGrid){

		if (sampleGrid == null || sampleGrid.length == 0 || sampleGrid[0].length == 0){
			//If the grid is null or either length or width are 0 then create the default grid
			grid = defaultGrid();
		} else {
			//Initialize the grid based on the sample grid.
			int height = sampleGrid.length;
			int width = sampleGrid[0].length;
			grid = new ZGCharacter [height][width];
			for (int i = 0; i < height; i++){
				for (int j = 0; j < width; j++){
					grid[i][j] = null;
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
		for (int i = 0; i < DEFAULT_HEIGHT; i++){
			for (int j = 0; j < DEFAULT_WIDTH; j++){
				//Set all values to null
				defaultGrid[i][j] = null;
			}
		}

		return defaultGrid;
	}
	
	/**
	 * Sets the given character to the given coordinates
	 * @param character The character to set
	 * @param x The x coordinate 
	 * @param y The y coordinate
	 * @return true if the character can be set at the given coordiantes, else false.
	 */
	public boolean setCharacter(ZGCharacter character, int x, int y){
		
		if (grid[y][x] == null){
			//If the coordinate is null, then set the character
			grid[y][x] = character;
			return true;
		} else {
			//If the coordiante already constain a characters, return false.
			return false;
		}
	}
}