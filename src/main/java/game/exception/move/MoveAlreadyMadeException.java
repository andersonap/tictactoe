package game.exception.move;

public class MoveAlreadyMadeException extends MoveException {

	public MoveAlreadyMadeException() {
		super("This move has been already made. Please, choose another one.");
	}

}
