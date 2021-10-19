/**
 * 
 */
package pieces;

import game.Alliance;

import java.util.Collection;

import board.Board;
import board.Move;
/**
 * @author Daniel
 *
 */
public abstract class Piece {

	//every piece has a position, i.e., tileId
	
	protected final int piecePosition;
	protected final Alliance pieceAlliance;
	protected final boolean unmoved;
	
	Piece(final int piecePosition, final Alliance pieceAlliance) {
		this.piecePosition = piecePosition;
		this.pieceAlliance = pieceAlliance;
		//TODO
		this.unmoved = false;
	}
	
	public Alliance getAlliance() {
		return this.pieceAlliance;
	}
	
	/**
	 * 
	 * @return unmoved variable.
	 */
	public boolean isUnmoved() {
		return this.unmoved;
	}
	
	/**
	 * Calculates the legal moves. Every piece overwrites this function.
	 * @param board chess board
	 * @return list of legal moves for the chess piece.
	 */
	public abstract Collection<Move> findLegalMoves(final Board board);

	public int getPosition() {
		return this.piecePosition;
	}
	
}
