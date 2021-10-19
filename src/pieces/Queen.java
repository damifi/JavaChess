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
import board.Tile;
import game.Alliance;

/**
 * @author Daniel
 *
 */
public class Queen extends Piece {

	private static final int[] moveVectorCoordinates = {-9,-8,-7,-1,1,7,8,9};
	
	/**
	 * @param piecePosition
	 * @param pieceAlliance
	 */
	public Queen(int piecePosition, Alliance pieceAlliance) {
		super(piecePosition, pieceAlliance);
	}

	@Override
	public Collection<Move> findLegalMoves(Board board) {
		final List<Move> legalMoves = new ArrayList<Move>();
		
		for (int i : moveVectorCoordinates) {
			
			int destinationTileId = this.piecePosition;
			while(BoardUtils.isValidCoordinate(destinationTileId)) {
				
				if (queenOnFirstColumn(destinationTileId, i) ||
					queenOnEigthColumn(destinationTileId, i)) {
					break;
				} 
				
				destinationTileId += i;
				if(BoardUtils.isValidCoordinate(destinationTileId)) {
					final Tile destinationTile = board.getTile(destinationTileId);
					if(!destinationTile.isTileOccupied()) {
						legalMoves.add(new NormalMove(board, this, destinationTileId));
					} else {
						if(this.pieceAlliance != destinationTile.getPiece().getAlliance()) {
							legalMoves.add(new AttackMove(board, this, destinationTileId, destinationTile.getPiece()));
						}
						break;
					}
				}
			}
			
		}
		
		
		return legalMoves;
	}
	
	private static boolean queenOnFirstColumn(int currentPosition, int offset) {
		return BoardUtils.firstColumn[currentPosition] && ((offset == -9) || (offset == 7) || (offset == -1));
	}
	
	private static boolean queenOnEigthColumn(int currentPosition, int offset) {
		return BoardUtils.eigthColumn[currentPosition] && ((offset == -7) || (offset == 9) || (offset == 1));
	}

}
