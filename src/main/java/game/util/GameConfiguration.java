package game.util;

import java.util.List;

import game.model.player.Player;

public interface GameConfiguration {

	Integer getBoardSize();
	List<Player> getPlayers();

}
