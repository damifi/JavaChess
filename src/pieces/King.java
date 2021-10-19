/**
 * 
 */
package pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import board.Board;
import board.BoardUtils;
import board.Move;
import board.Move.AttackMove;
import board.Move.NormalMove;
import game.Alliance;

/**
 * @author Daniel
 *
 */
public class King extends Piece {
	
	private static final int[] moveVectorCoordinates = {-9,-8,-7,-1,1,7,8,9};

	/**
	 * @param piecePosition
	 * @param pieceAlliance
	 */
	public King(int piecePosition, Alliance pieceAlliance) {
		super(piecePosition, pieceAlliance);
	}

	@Override
	public Collection<Move> findLegalMoves(Board board) {
		final List<Move> legalMoves = new ArrayList<Move>();
		
		
		
		for (int i : moveVectorCoordinates) {
			
			if(kingOnFirstColumn(this.piecePosition, i) ||
			   kingOnEigthColumn(this.piecePosition, i)) {
				continue;	
			}
			
			
			int tileDestinationId = this.piecePosition + i;
			
			if(BoardUtils.isValidCoordinate(tileDestinationId)) {
				if(!board.getTile(tileDestinationId).isTileOccupied()) {
					legalMoves.add(new NormalMove(board, this, tileDestinationId));
				} else {
					if(this.pieceAlliance != board.getTile(tileDestinationId).getPiece().getAlliance()) {
						legalMoves.add(new AttackMove(board, this, tileDestinationId, board.getTile(tileDestinationId).getPiece()));
					}
				}
			}
		}
		
		
		return legalMoves;
	}
	
	private static boolean kingOnFirstColumn(int currentPosition, int offset) {
		return BoardUtils.firstColumn[currentPosition] && ((offset == -9) || (offset == 7) || (offset == -1));
	}
	
	private static boolean kingOnEigthColumn(int currentPosition, int offset) {
		return BoardUtils.eigthColumn[currentPosition] && ((offset == -7) || (offset == 9) || (offset == 1));
	}

}
