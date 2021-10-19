/**
 * 
 */
package pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import board.Board;
import board.Move;
import board.Move.AttackMove;
import board.Move.NormalMove;
import board.Tile;
import board.BoardUtils;


import game.Alliance;

/**
 * Class representing the knight chess piece.
 * @author Daniel
 *
 */
public class Knight extends Piece {

	//array containing the offsets to calculate the possible moves for the knight
	private final static int[] moveOffsets = {-17, -15, -10, -6, 6, 10, 15, 17};
	
	public Knight(final int piecePosition, final Alliance piecePlayer) {
		super(piecePosition, piecePlayer);
	}

	@Override
	public Collection<Move> findLegalMoves(Board board) {
		
		List<Move> legalMoves = new ArrayList<>();
		for (int i : moveOffsets) {
			int destinationTileId = this.piecePosition + i;
			
			if(knightOnFirstColumn(this.piecePosition, i) ||
			   knightOnSecondColumn(this.piecePosition, i) ||
			   knightOnSeventhColumn(this.piecePosition, i) ||
			   knightOfEigthColumn(this.piecePosition, i)){
				continue;
			}
			
			if(BoardUtils.isValidCoordinate(destinationTileId)) {
				final Tile destinationTile = board.getTile(destinationTileId);
				if(!destinationTile.isTileOccupied()) {
					legalMoves.add(new NormalMove(board, this, destinationTileId));
				}
				else {					
					if (this.pieceAlliance != destinationTile.getPiece().getAlliance()) {
						legalMoves.add(new AttackMove(board, this, destinationTileId, destinationTile.getPiece()));
					}
				}
			}
		}
		return legalMoves;
	}
	
	/**
	 * Check if the knight is on the first column.
	 * @param currentPosition current position of the knight
	 * @param offset offset to be checked
	 * @return 
	 */
	private static boolean knightOnFirstColumn(int currentPosition, int offset) {
		return BoardUtils.firstColumn[currentPosition] && ((offset == -17) || (offset == -10) || (offset == 6) || (offset == 15));
	}
	
	/**
	 * check if the knight is on the second column.
	 * @param currentPosition
	 * @param offset
	 * @return
	 */
	private static boolean knightOnSecondColumn(int currentPosition, int offset) {
		return BoardUtils.secondColumn[currentPosition] && ((offset == -10) || (offset == 6));
	}
	
	private static boolean knightOnSeventhColumn(int currentPosition, int offset) {
		return BoardUtils.seventhColumn[currentPosition] && ((offset == -6) || (offset == 10));
	}
	
	private static boolean knightOfEigthColumn(int currentPosition, int offset) {
		return BoardUtils.eigthColumn[currentPosition] && ((offset == -15) || (offset == -6) || (offset == 10) || (offset == 17));
	}


}
