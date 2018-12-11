package game.model;

import org.junit.Assert;
import org.junit.Test;

import game.model.player.DefaultPlayer;

public class BoardTest {
	
	@Test public void
	has_winner_when_a_player_completed_a_horizontal_row() {
		Board board = new Board(3);
		
		DefaultPlayer playerOne = new DefaultPlayer("Player 1", "X");
		DefaultPlayer playerTwo = new DefaultPlayer("Player 2", "X");
		
		board.addMove(new Move(playerOne, 3, 1));
		board.addMove(new Move(playerOne, 3, 2));
		board.addMove(new Move(playerOne, 3, 3));
		
		board.addMove(new Move(playerTwo, 2, 1));
		board.addMove(new Move(playerTwo, 2, 3));
		
		Assert.assertEquals(playerOne, board.getWinner());
	}
	
	@Test public void
	has_winner_when_a_player_completed_a_vertical_row() {
		Board board = new Board(3);
		
		DefaultPlayer playerOne = new DefaultPlayer("Player 1", "X");
		DefaultPlayer playerTwo = new DefaultPlayer("Player 2", "X");
		
		board.addMove(new Move(playerOne, 1, 2));
		board.addMove(new Move(playerOne, 2, 2));
		board.addMove(new Move(playerOne, 3, 2));
		
		board.addMove(new Move(playerTwo, 2, 1));
		board.addMove(new Move(playerTwo, 2, 3));
		
		Assert.assertEquals(playerOne, board.getWinner());
	}
	
	@Test public void
	has_winner_when_a_player_completed_a_diagonal_row() {
		Board board = new Board(3);
		
		DefaultPlayer playerOne = new DefaultPlayer("Player 1", "X");
		DefaultPlayer playerTwo = new DefaultPlayer("Player 2", "X");
		
		board.addMove(new Move(playerOne, 1, 1));
		board.addMove(new Move(playerOne, 2, 2));
		board.addMove(new Move(playerOne, 3, 3));
		
		board.addMove(new Move(playerTwo, 2, 1));
		board.addMove(new Move(playerTwo, 2, 3));
		
		Assert.assertEquals(playerOne, board.getWinner());
	}
	
	@Test public void
	has_winner_when_a_player_completed_a_opposite_diagonal_row() {
		Board board = new Board(3);
		
		DefaultPlayer playerOne = new DefaultPlayer("Player 1", "X");
		DefaultPlayer playerTwo = new DefaultPlayer("Player 2", "X");
		
		board.addMove(new Move(playerOne, 1, 3));
		board.addMove(new Move(playerOne, 2, 2));
		board.addMove(new Move(playerOne, 3, 1));
		
		board.addMove(new Move(playerTwo, 2, 1));
		board.addMove(new Move(playerTwo, 2, 3));
		
		Assert.assertEquals(playerOne, board.getWinner());
	}
	
	@Test public void
	should_stringify_board_correctly() {
		Board board = new Board(3);
		
		DefaultPlayer playerOne = new DefaultPlayer("Player 1", "X");
		DefaultPlayer playerTwo = new DefaultPlayer("Player 2", "O");
		
		board.addMove(new Move(playerOne, 1, 1));
		board.addMove(new Move(playerOne, 2, 1));
		board.addMove(new Move(playerOne, 3, 1));
		
		board.addMove(new Move(playerTwo, 1, 2));
		board.addMove(new Move(playerTwo, 2, 3));
		board.addMove(new Move(playerTwo, 3, 3));
		
		System.out.println(board.stringify());
	}

}
