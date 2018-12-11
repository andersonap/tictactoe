package game.exception.move;

public class InvalidPositionException extends MoveException {

	public InvalidPositionException() {
		super("This position is not valid. It exceeds the board size.");
	}

}
