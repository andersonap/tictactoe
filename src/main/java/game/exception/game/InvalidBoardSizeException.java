package game.exception.game;

public class InvalidBoardSizeException extends GameStartException {

	public InvalidBoardSizeException() {
		super("Your game has a invalid board size. Please check your game configuration.");
	}

}
