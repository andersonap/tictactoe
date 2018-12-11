package game;

import game.model.Board;
import game.model.Game;
import game.model.interaction.GameConsole;
import game.util.FileConfiguration;
import game.util.GameConfiguration;

public class Main {

	public static void main(String[] args) {
		GameConfiguration gameConfiguration = new FileConfiguration("config");
		
		Game game = new Game(new GameConsole(), new Board(gameConfiguration.getBoardSize()), gameConfiguration.getPlayers());
		game.shufflePlayers();
		game.start();
	}

}
