package game.exception.game;

public class InvalidPlayerException extends GameStartException {

	public InvalidPlayerException() {
		super("Your game has invalid players. Please check your game configuration.");
	}

}
