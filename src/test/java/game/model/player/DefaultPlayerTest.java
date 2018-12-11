package game.model.player;


import org.junit.Test;

import game.exception.move.MoveException;
import game.model.Board;

public class DefaultPlayerTest {
	
	@Test
	public void board_should_have_move_by_player_when_player_makes_move() throws MoveException {
		Player player = new DefaultPlayer("Test Player", "T");
		
		Board testBoard = new Board(3);
		player.makeMove(testBoard);
		
	}

}
