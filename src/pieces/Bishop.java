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
import board.Move.NormalMove;
import board.Move.AttackMove;
import board.Tile;
import game.Alliance;

/**
 * @author Daniel
 *
 */
public class Bishop extends Piece {

	
	private static final int[] moveVectorCoordinates = {-9, -7, 7, 9};
	
	public Bishop(int piecePosition, Alliance pieceAlliance) {
		super(piecePosition, pieceAlliance);
	}

	@Override
	public Collection<Move> findLegalMoves(Board board) {
		final List<Move> legalMoves = new ArrayList<Move>();
		
		//move through all diagonals
		for (int i : moveVectorCoordinates) {
			
			int destinationTileId = this.piecePosition;
			//while the destination id of the tile is still on the chess board
			while(BoardUtils.isValidCoordinate(destinationTileId)) {
				if (bishopOnFirstColumn(destinationTileId, i) ||
					bishopOnEigthColumn(destinationTileId, i)) {
						break;
					}
				destinationTileId += i;
				
				if (BoardUtils.isValidCoordinate(destinationTileId)) {
				
					final Tile destinationTile = board.getTile(destinationTileId);
				
					if (!destinationTile.isTileOccupied()) {
						legalMoves.add(new NormalMove(board, this, destinationTileId));
					} else {
						if (this.pieceAlliance != destinationTile.getPiece().getAlliance()) {
							legalMoves.add(new AttackMove(board, this, destinationTileId, destinationTile.getPiece()));
						}
						//a chess piece is blocking the path, therefore break the search in this direction
						break;
					}
				
				
				}
			}
		}
		
		return legalMoves;	
	}
	
	private static boolean bishopOnFirstColumn(int currentPosition, int offset) {
		return BoardUtils.firstColumn[currentPosition] && ((offset == -9) || (offset == 7));
	}
	
	private static boolean bishopOnEigthColumn(int currentPosition, int offset) {
		return BoardUtils.eigthColumn[currentPosition] && ((offset == -7) || (offset == 9));
	}

}
