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
public class Pawn extends Piece {
	
	private static final int[] moveOffsets = {7, 8, 9, 16};

	/**
	 * @param piecePosition
	 * @param pieceAlliance
	 */
	public Pawn(int piecePosition, Alliance pieceAlliance) {
		super(piecePosition, pieceAlliance);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Collection<Move> findLegalMoves(Board board) {
		final List<Move> legalMoves = new ArrayList<Move>();
		
		for (int i : moveOffsets) {
			int destinationTileId = this.piecePosition + (this.getAlliance().getDirection() * i);
			
			if(!BoardUtils.isValidCoordinate(destinationTileId)) {
				continue;
			}
			
			//No piece infront of pawn
			if(i == 8 && !board.getTile(destinationTileId).isTileOccupied()) {
				//TODO promotions
				legalMoves.add(new NormalMove(board, this, destinationTileId));
			}
			// pawn jump
			else if (i == 16 && this.isUnmoved() && 
					  (BoardUtils.secondRow[this.piecePosition] && this.pieceAlliance.isBlack()) ||
					  (BoardUtils.seventhRow[this.piecePosition] && this.pieceAlliance.isWhite()))  {
				final int behindDestinationTileId = this.piecePosition + (this.pieceAlliance.getDirection() * 8);
				if(!board.getTile(behindDestinationTileId).isTileOccupied() && 
				   !board.getTile(destinationTileId).isTileOccupied()) {
					legalMoves.add(new NormalMove(board, this, destinationTileId));
				}
			}
			// attack move, if white pawn and is on the eigth column -> cannot attack to the right
			// attack move, if black pawn and is on the first column -> cannot attack to the left
			else if (i == 7 &&
					!((BoardUtils.eigthColumn[this.piecePosition] && this.pieceAlliance.isWhite() ||
					  (BoardUtils.firstColumn[this.piecePosition] && this.pieceAlliance.isBlack())))) {
				//if the tile is occupied
				if(board.getTile(destinationTileId).isTileOccupied()) {
					// get the piece on the targeted tile.
					final Piece pieceOnTile = board.getTile(destinationTileId).getPiece();
					if(this.pieceAlliance != pieceOnTile.getAlliance()) {
						//TODO
						legalMoves.add(new AttackMove(board, this, destinationTileId, pieceOnTile));
					}
				}
			}
			// attack move
			else if (i == 9 &&
					!((BoardUtils.eigthColumn[this.piecePosition] && this.pieceAlliance.isBlack() ||
					 (BoardUtils.firstColumn[this.piecePosition] && this.pieceAlliance.isWhite())))) {
				//if the tile is occupied
				if(board.getTile(destinationTileId).isTileOccupied()) {
					// get the piece on the targeted tile.
					final Piece pieceOnTile = board.getTile(destinationTileId).getPiece();
					if(this.pieceAlliance != pieceOnTile.getAlliance()) {
						//TODO
						legalMoves.add(new AttackMove(board, this, destinationTileId, pieceOnTile));
					}
				}
			}
		}
		return legalMoves;
	}

}
