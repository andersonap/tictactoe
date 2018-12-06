package game;

import game.model.Board;
import game.model.Game;
import game.model.interaction.GameConsole;
import game.util.GameConfiguration;

public class Main {

	public static void main(String[] args) {
		Game game = new Game();
		game.setInteraction(new GameConsole());
		game.setBoard(new Board(GameConfiguration.getBoardSize()));
		game.setPlayers(GameConfiguration.getPlayers());
		game.shufflePlayers();
		game.start();
	}

}
