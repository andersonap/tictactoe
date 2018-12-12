package game;

import game.model.Board;
import game.model.Game;
import game.model.configuration.FileConfiguration;
import game.model.configuration.GameConfiguration;
import game.model.interaction.GameConsole;

public class Main {

	public static void main(String[] args) {
		GameConfiguration gameConfiguration = new FileConfiguration("config");
		
		Game game = new Game(new GameConsole(), new Board(gameConfiguration.getBoardSize()), gameConfiguration.getPlayers());
		game.shufflePlayers();
		game.start();
	}

}
