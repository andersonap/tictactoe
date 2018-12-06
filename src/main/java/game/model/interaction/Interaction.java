package game.model.interaction;

import game.model.Board;
import game.model.player.Player;

public interface Interaction {

	String getMoveFrom(Player player);

	void showGameStartMessage();
		
	void showBoard(Board board);

	void showWinner(Player winner);

	void notifyGameCantStart(String cause);

}
