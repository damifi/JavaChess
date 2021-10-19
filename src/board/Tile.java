/**
 * 
 */
package board;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import pieces.Piece;

/**
 * @author Daniel
 *	A single chess tile. A chess board consists of 64 tiles.
 */
public abstract class Tile {
	
	//Id of the tile
	protected final int tileId;
	
	//map containing all possible empty tiles of the chess board.
	//This is done so no new empty tile has to be created again.
	private static final Map<Integer, EmptyTile> emptyTiles = createAllEmptyTiles();
	
	/**
	 * 
	 * @param tileId Coordinate/Id of the tile.
	 * @param piece Piece on the tile.
	 * @return if there is a piece on the tile return a new OccupiedTile, otherwise return an EmptyTile
	 */
	public static Tile createTile(final int tileId, final Piece piece) {
		return piece != null ? new OccupiedTile(tileId, piece) : emptyTiles.get(tileId);
	}
	
	/**
	 * Constructor of the tile class.
	 * @param tileId Id of the tile.
	 */
	private Tile(int tileId) {
		this.tileId = tileId;
	}
	
	/**
	 * Fill the empty tile list with empty tile objects.
	 * @return returns the list with empty tiles.
	 */
	private static Map<Integer, EmptyTile> createAllEmptyTiles() {
		final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();
		for (int i = 0; i < BoardUtils.numberOfTiles; i++) {
			emptyTileMap.put(i, new EmptyTile(i));
		}
		return Collections.unmodifiableMap(emptyTileMap);
	}

	public abstract boolean isTileOccupied();
	
	public abstract Piece getPiece();
	
	/**
	 * Subclass of the Tile class. Represents
	 * an empty tile on the chess board.
	 * @author Daniel
	 *
	 */
	public static final class EmptyTile extends Tile{
		
		EmptyTile(final int tileId) {
			super(tileId);
		}
		
		@Override
		public boolean isTileOccupied() {
			return false;
		}
		
		@Override
		public Piece getPiece() {
			return null;
		}
	}
	/**
	 * Subclass of the Tile class. Represents
	 * an occupied tile on the chess board. 
	 * @author Daniel
	 *
	 */
	public static final class OccupiedTile extends Tile {
		
		private final Piece piece;
		
		public OccupiedTile(final int tileId, Piece piece) {
			super(tileId);
			this.piece = piece;
		}
		
		@Override
		public boolean isTileOccupied() {
			return true;
		}
		
		@Override
		public Piece getPiece() {
			return this.piece;
		}
	}
}
