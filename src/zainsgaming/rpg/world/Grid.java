package zainsgaming.rpg.world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
			for (int row = 0; row < height; row++){
				for (int col = 0; col < width; col++){
					grid[row][col] = sampleGrid[row][col];
					if (grid[row][col] != null){
						objLocations.put(grid[row][col], new Pair<Integer, Integer>(row, col));
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
		for (int row = 0; row < DEFAULT_HEIGHT; row++){
			for (int col = 0; col < DEFAULT_WIDTH; col++){
				//Set all values to null
				defaultGrid[row][col] = null;
			}
		}

		return defaultGrid;
	}

	/**
	 * Sets the given object to the given coordinates
	 * @param object The object to set 
	 * @param row The row coordinate
	 * @param col The column coordinate
	 * @return true if the character can be set at the given coordiantes, else false.
	 */
	public boolean setObject(ZGObject obj, int row, int col){

		if (grid[row][col] == null){
			//If the coordinate is null, then set the object
			grid[row][col] = obj;
			objLocations.put(obj, new Pair<Integer, Integer>(row, col));
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
			int row = objLocations.get(obj).first;
			int col = objLocations.get(obj).second;
			//Variables to store the new location
			int newCol = -1;
			int newRow = -1;

			//Calculate the new location. Make sure that the object is not crossing a border, and 
			//the new location is empty.
			switch(direction){
			case NORTH:
				if (row != 0 && grid[row-1][col] == null){
					newRow = row - 1;
					newCol = col;
				} else {
					return false;
				}
				break;
			case SOUTH:
				if (row < (grid.length-1) && grid[row+1][col] == null){
					newRow = row + 1;
					newCol = col;
				} else {
					return false;
				}
				break;
			case EAST:
				if (col < (grid[0].length-1) && grid[row][col+1] == null){
					newRow = row;
					newCol = col + 1;
				} else {
					return false;
				}
				break;
			case WEST:
				if (col != 0 && grid[row][col-1] == null){
					newRow = row;
					newCol = col - 1;
				} else {
					return false;
				}
				break;
			default:
				return false;
			}
			
			//Update the grid and objLocations.
			grid[row][col] = null;
			grid[newRow][newCol] = obj;
			objLocations.put(obj, new Pair<Integer, Integer>(newRow, newCol));
			return true;
		}

		//Return false because the grid does not contain the object.
		return false;
	}
	
	public List<ZGObject> getNeighbours (ZGObject source, int range){
		List<ZGObject> neighbours = new ArrayList<ZGObject>();
		if (range < 0){
			return neighbours;
		}
		
		int srcRow = objLocations.get(source).first;
		int srcCol = objLocations.get(source).second;
		
		for (int i = range*-1; i <= range; i++){
			for (int j = range*-1; j <= range; j++){
				if ((srcCol+i) < grid.length){
					if (grid[srcCol+i][srcRow] != null){
						neighbours.add(grid[srcCol+i][srcRow]);
					}
				}
			}
		}
		
		
		return neighbours;
	}
	
	/**
	 * Returns human readable version of the grid.
	 */
	public String toString(){
		StringBuilder sb = new StringBuilder();
		
		//Build the string based on the contents of the grid array
		for (int row = 0; row < grid.length; row++){
			for (int col = 0; col < grid[0].length; col++){
				if (grid[row][col] == null){
					sb.append("[ ]");
				} else {
					//Only take the first character of the name. This is to keep the
					//printed version aligned.
					sb.append("[" + grid[row][col].getName().charAt(0) +"]");
				}
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}
}