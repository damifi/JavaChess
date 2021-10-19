/**
 * 
 */
package game;

/**
 * Enum to represent the two possible colors of chess pieces, black and white.
 * @author Daniel
 *
 */
public enum Alliance {
	BLACK
 {
		@Override
		public int getDirection() {
			return 1;
		}

		@Override
		public boolean isWhite() {
			return false;
		}

		@Override
		public boolean isBlack() {
			return true;
		}
	},
 	WHITE	
 {
		@Override
		public int getDirection() {
			return -1;
		}

		@Override
		public boolean isWhite() {
			return true;
		}

		@Override
		public boolean isBlack() {
			return false;
		}
	};	
	public abstract int getDirection();
	
	public abstract boolean isWhite();
	public abstract boolean isBlack();
}
