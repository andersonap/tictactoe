package game.exception.move;

public class InvalidMoveFormatException extends MoveException {

	public InvalidMoveFormatException() {
		super("This is a invalid move. Please, make a move with the pattern: 'number,number'");
	}

}
