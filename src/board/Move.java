/**
 * 
 */
package board;

import pieces.Piece;

/**
 * @author Daniel
 *	Base class of a move on the chess board.
 */
public abstract class Move {

	final Board board;
	final Piece pieceToMove;
	final int pieceDestination;
	
	private Move(final Board board, final Piece pieceToMove, final int pieceDestination){
		this.board = board;
		this.pieceDestination = pieceDestination;
		this.pieceToMove = pieceToMove;
	}
	
	public static final class NormalMove extends Move {

		public NormalMove(Board board, Piece pieceToMove, int pieceDestination) {
			super(board, pieceToMove, pieceDestination);
		}
		
	}
	
	public static final class AttackMove extends Move {
		
		final Piece attackedPiece;

		public AttackMove(Board board, Piece pieceToMove, int pieceDestination, Piece attackedPiece) {
			super(board, pieceToMove, pieceDestination);
			this.attackedPiece = attackedPiece;
		}
	}
	
}
