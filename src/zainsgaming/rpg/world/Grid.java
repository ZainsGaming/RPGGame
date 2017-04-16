package zainsgaming.rpg.world;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import zainsgaming.rpg.ZGObject;
import zainsgaming.rpg.characters.ZGCharacter;
import zainsgaming.rpg.util.Pair;

public class Grid {

	//Directions used for movement.
	public enum Direction{
		NORTH, SOUTH, EAST, WEST;
	}

	private static final int DEFAULT_HEIGHT = 10;
	private static final int DEFAULT_WIDTH = 10;


	private ZGObject[][] grid;
	private Map<ZGObject, Pair<Integer, Integer>> objLocations;

	/**
	 * Default constructor. Create empty default height and width grid (10X10).
	 */
	public Grid(){
		//Initialize the grid as default grid, with all null values.
		grid = defaultGrid();
		//Initialize the object locations map
		objLocations = new HashMap<ZGObject, Pair<Integer, Integer>>();
	}

	/**
	 * Default constructor. Create empty 10X10 grid.
	 */
	public Grid(ZGObject[][] sampleGrid){

		if (sampleGrid == null || sampleGrid.length == 0 || sampleGrid[0].length == 0){
			//If the grid is null or either length or width are 0 then create the default grid
			grid = defaultGrid();
		} else {
			//Initialize the grid and charLocations based on the sample grid.
			int height = sampleGrid.length;
			int width = sampleGrid[0].length;
			grid = new ZGObject [height][width];
			for (int col = 0; col < height; col++){
				for (int row = 0; row < width; row++){
					grid[col][row] = sampleGrid[col][row];
					if (grid[col][row] != null){
						objLocations.put(grid[col][row], new Pair<Integer, Integer>(col, row));
					}
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
	 * Sets the given object to the given coordinates
	 * @param object The object to set
	 * @param col The y coordinate 
	 * @param row The x coordinate
	 * @return true if the character can be set at the given coordiantes, else false.
	 */
	public boolean setObject(ZGObject obj, int col, int row){

		if (grid[col][row] == null){
			//If the coordinate is null, then set the object
			grid[col][row] = obj;
			objLocations.put(obj, new Pair<Integer, Integer>(col, row));
			return true;
		} else {
			//If the coordiante already constain an object, return false.
			return false;
		}
	}

	/**
	 * Moves the given object in the new location. The new location must be empty.
	 * @param obj The target object.
	 * @param direction The direction to move.
	 * @return True is the object moves successfully, else false.
	 */
	public boolean move(ZGObject obj, Direction direction){
		if (objLocations.containsKey(obj)){				//Ensure that the object is already in the grid
			
			//Get the objects current location
			int col = objLocations.get(obj).first;
			int row = objLocations.get(obj).second;
			//Variables to store the new location
			int newCol = -1;
			int newRow = -1;

			//Calculate the new location. Make sure that the object is not crossing a border, and 
			//the new location is empty.
			switch(direction){
			case NORTH:
				if (col != 0 && grid[col-1][row] == null){
					newCol = col-1;
					newRow = row;
				} else {
					return false;
				}
				break;
			case SOUTH:
				if (col != (grid.length-1) && grid[col+1][row] == null){
					newCol = col+1;
					newRow = row;
				} else {
					return false;
				}
				break;
			case EAST:
				if (row != (grid[0].length-1) && grid[col][row+1] == null){
					newCol = col;
					newRow = row+1;
				} else {
					return false;
				}
				break;
			case WEST:
				if (row != 0 && grid[col][row-1] == null){
					newCol = col;
					newRow = row-1;
				} else {
					return false;
				}
				break;
			default:
				return false;
			}
			
			//Update the grid and objLocations.
			grid[col][row] = null;
			grid[newCol][newRow] = obj;
			objLocations.put(obj, new Pair<Integer, Integer>(newCol, newRow));
			return true;
		}

		//Return false because the grid does not contain the object.
		return false;
	}
	
	/**
	 * Returns human readable version of the grid.
	 */
	public String toString(){
		StringBuilder sb = new StringBuilder();
		
		//Build the string based on the contents of the grid array
		for (int col = 0; col < grid.length; col++){
			for (int row = 0; row < grid[0].length; row++){
				if (grid[col][row] == null){
					sb.append("[ ]");
				} else {
					//Only take the first character of the name. This is to keep the
					//printed version aligned.
					sb.append("[" + grid[col][row].getName().charAt(0) +"]");
				}
			}
			sb.append("\n");
		}
		
		
		return sb.toString();
	}
}