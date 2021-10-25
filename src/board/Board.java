/**
 * 
 */
package board;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import game.Alliance;
import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;

/**
 * @author Daniel
 * This class represents game board.
 */
public class Board {
	
	//state of the game board
	private final List<Tile> gameBoard;
	//keeps track of white pieces
	Collection<Piece> whitePieces;
	//keeps track of black pieces
	Collection<Piece> blackPieces;
	
	private Board(Builder builder) {
		this.gameBoard = createGameBoard(builder);
		this.whitePieces = getActivePieces(this.gameBoard, Alliance.WHITE);
		this.blackPieces = getActivePieces(this.gameBoard, Alliance.BLACK);
		
		final Collection<Move> whiteNormalLegalMoves;
		final Collection<Move> blackNormalLegalMoves;
		
	}
	
	private Collection<Piece> getActivePieces(List<Tile> gameBoard, Alliance alliance) {
		List<Piece> activePieces = new ArrayList<Piece>();
		
		for(Tile tile : gameBoard) {
			if(tile.isTileOccupied()) {
				Piece pieceOnTile = tile.getPiece();
				
				if(pieceOnTile.getAlliance() == alliance) {
					activePieces.add(pieceOnTile);
				}
			}
		}
		
		return activePieces;
	}

	public Tile getTile(final int tileId) {
		return gameBoard.get(tileId);
	}
	
	private static List<Tile> createGameBoard(Builder builder) {
		//#14
		//final Tile[] tiles = new Tile[BoardUtils.numberOfTiles];
		final List<Tile> tiles = new ArrayList<Tile>();
		for(int i = 0; i < BoardUtils.numberOfTiles; i++) {
			tiles.add(Tile.createTile(i, builder.boardState.get(i)));
		}
		
		return tiles;
	}
	
	
	/**
	 * Creates the game board with the initial position for the chess pieces.
	 * @param builder Builder of the Board class.
	 * @return initial state of the game board.
	 */
	public static Board createInitialBoard(Builder builder) {
		//initial position for the black chess pieces
		//pieces
		builder.setPiece(new Rook(0, Alliance.BLACK));
		builder.setPiece(new Knight(1, Alliance.BLACK));
		builder.setPiece(new Bishop(2, Alliance.BLACK));
		builder.setPiece(new Queen(3, Alliance.BLACK));
		builder.setPiece(new King(4, Alliance.BLACK));
		builder.setPiece(new Bishop(5, Alliance.BLACK));
		builder.setPiece(new Knight(6, Alliance.BLACK));
		builder.setPiece(new Rook(7, Alliance.BLACK));
		//black pawns
		builder.setPiece(new Pawn(8, Alliance.BLACK));
		builder.setPiece(new Pawn(9, Alliance.BLACK));
		builder.setPiece(new Pawn(10, Alliance.BLACK));
		builder.setPiece(new Pawn(11, Alliance.BLACK));
		builder.setPiece(new Pawn(12, Alliance.BLACK));
		builder.setPiece(new Pawn(13, Alliance.BLACK));
		builder.setPiece(new Pawn(14, Alliance.BLACK));
		builder.setPiece(new Pawn(15, Alliance.BLACK));
		////initial position for the white chess pieces
		//pawns
		builder.setPiece(new Pawn(48, Alliance.WHITE));
		builder.setPiece(new Pawn(49, Alliance.WHITE));
		builder.setPiece(new Pawn(50, Alliance.WHITE));
		builder.setPiece(new Pawn(51, Alliance.WHITE));
		builder.setPiece(new Pawn(52, Alliance.WHITE));
		builder.setPiece(new Pawn(53, Alliance.WHITE));
		builder.setPiece(new Pawn(54, Alliance.WHITE));
		builder.setPiece(new Pawn(55, Alliance.WHITE));
		//pieces
		builder.setPiece(new Rook(56, Alliance.WHITE));
		builder.setPiece(new Knight(57, Alliance.WHITE));
		builder.setPiece(new Bishop(58, Alliance.WHITE));
		builder.setPiece(new Queen(59, Alliance.WHITE));
		builder.setPiece(new King(60, Alliance.WHITE));
		builder.setPiece(new Bishop(61, Alliance.WHITE));
		builder.setPiece(new Knight(62, Alliance.WHITE));
		builder.setPiece(new Rook(63, Alliance.WHITE));
		
		//first player to move is WHITE
		builder.setPlayer(Alliance.WHITE);
		return builder.build();
	}
	
	public static class Builder {
		
		Map<Integer, Piece> boardState;
		Alliance nextPlayer;
		
		public Builder() {
			
		}
		
		public Builder setPiece(Piece piece) {
			boardState.put(piece.getPosition(), piece);
			return this;
		}
		
		public Builder setPlayer(final Alliance player) {
			this.nextPlayer = player;
			return this;
		}
		
		public Board build() {
			return new Board(this);
		}
	}

}
