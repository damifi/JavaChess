/**
 * 
 */
package board;


/**
 * @author Daniel
 * Utility functions for the chess game.
 */

public class BoardUtils {
	
	public static final int numberOfTiles = 64;
	public static final int numberOfTilesPerRow = 8; 
	
	public static final boolean[] firstColumn = initColumn(0);
	public static final boolean[] secondColumn = initColumn(1);
	public static final boolean[] seventhColumn = initColumn(6);
	public static final boolean[] eigthColumn = initColumn(7);
	
	public static final boolean[] secondRow = null;
	public static final boolean[] seventhRow = null;
	
	/**
	 * Fill the columns with true depending on the specifed column number.
	 */
	private BoardUtils() {
		throw new RuntimeException("You cannot call the constructor of the BoardUtil class.");
	}
	
	private static boolean[] initColumn(int columnNumber) {
		
		final boolean[] column = new boolean[numberOfTiles];
		do {
			column[columnNumber] = true;
			columnNumber += numberOfTilesPerRow;
		} while(columnNumber < numberOfTiles);
		
		return column;
	}

	/**
	 * checks if the destination tile id is on the board.
	 * @param destinationTileId id of a tile to be checked.
	 * @return true if on the board, false otherwise.
	 */
	public static boolean isValidCoordinate(int destinationTileId) {
		return destinationTileId >= 0 && destinationTileId < 64;
	}
	
}
