package game.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import game.exception.game.InvalidBoardSizeException;
import game.exception.move.InvalidMoveFormatException;
import game.exception.move.InvalidPositionException;
import game.exception.move.MoveAlreadyMadeException;
import game.exception.move.MoveException;
import game.model.player.Bot;
import game.model.player.DefaultPlayer;

public class BoardTest {
	
	public Board board;
	public DefaultPlayer defaultPlayer;
	public Bot bot;
	
	@Before public void
	setup() {
		this.board = new Board(3);
		this.defaultPlayer = new DefaultPlayer("Player 1", "X");
		this.bot = new Bot("Bot", "B");
	}
	
	@Test public void
	has_winner_when_a_player_completed_a_horizontal_row() throws MoveException {
		board.addMove(defaultPlayer, "3,1");
		board.addMove(defaultPlayer, "3,2");
		board.addMove(defaultPlayer, "3,3");
		
		board.addMove(bot, "2,1");
		board.addMove(bot, "2,3");
		
		assertThat(board.getWinner()).isEqualTo(defaultPlayer);
	}
	
	@Test public void
	has_winner_when_a_player_completed_a_vertical_row() throws MoveException {
		board.addMove(defaultPlayer, "1,2");
		board.addMove(defaultPlayer, "2,2");
		board.addMove(defaultPlayer, "3,2");
		
		board.addMove(bot, "2,1");
		board.addMove(bot, "2,3");
		
		assertThat(board.getWinner()).isEqualTo(defaultPlayer);
	}
	
	@Test public void
	has_winner_when_a_player_completed_a_diagonal_row() throws MoveException {
		board.addMove(defaultPlayer, "1,1");
		board.addMove(defaultPlayer, "2,2");
		board.addMove(defaultPlayer, "3,3");
		
		board.addMove(bot, "2,1");
		board.addMove(bot, "2,3");
		
		assertThat(board.getWinner()).isEqualTo(defaultPlayer);
	}
	
	@Test public void
	has_winner_when_a_player_completed_a_opposite_diagonal_row() throws MoveException {
		board.addMove(defaultPlayer, "1,3");
		board.addMove(defaultPlayer, "2,2");
		board.addMove(defaultPlayer, "3,1");
		
		board.addMove(bot, "2,1");
		board.addMove(bot, "2,3");
		
		assertThat(board.getWinner()).isEqualTo(defaultPlayer);
	}
	
	@Test public void
	when_board_doesnt_have_winner_should_return_null() throws MoveException {
		board.addMove(defaultPlayer, "1,1");
		board.addMove(defaultPlayer, "3,1");
		
		board.addMove(bot, "2,3");
		
		Assert.assertNull(board.getWinner());
	}
	
	@Test public void
	should_not_add_move_when_move_has_invalid_format() throws MoveException {
		List<String> invalidMoves = new ArrayList<>();
		invalidMoves.add(null);
		invalidMoves.add("");
		invalidMoves.add("1");
		invalidMoves.add("abc");
		invalidMoves.add("1.2");
		
		assertThat(board.getMoves()).isEmpty();
		invalidMoves.forEach(invalidMove -> assertInvalidMove(invalidMove));
	}
	
	@Test public void
	should_not_add_move_when_position_is_bigger_than_board_size() {
		assertThatThrownBy(() -> 
			board.addMove(defaultPlayer, "4,1"))
				.isInstanceOf(InvalidPositionException.class);
	}
	
	@Test public void
	should_not_add_move_when_move_has_been_already_made() throws MoveException {
		board.addMove(defaultPlayer, "2,2");
		
		assertThatThrownBy(() -> 
			board.addMove(bot, "2,2"))
				.isInstanceOf(MoveAlreadyMadeException.class);
	}
	
	@Test public void
	should_throw_exception_when_board_size_is_less_than_three() {
		board.setSize(2);
		
		assertThatThrownBy(() -> 
			board.validateSize())
				.isInstanceOf(InvalidBoardSizeException.class);
	}
	
	@Test public void
	should_throw_exception_when_board_size_is_greater_than_ten() {
		board.setSize(11);
		
		assertThatThrownBy(() -> 
			board.validateSize())
				.isInstanceOf(InvalidBoardSizeException.class);
	}
	
	@Test public void
	board_is_complete_when_has_no_moves_to_do() throws MoveException {
		board.addMove(defaultPlayer, "1,1");
		board.addMove(bot, "1,2");
		board.addMove(bot, "1,3");
		
		board.addMove(bot, "2,1");
		board.addMove(bot, "2,2");
		board.addMove(defaultPlayer, "2,3");
		
		board.addMove(defaultPlayer, "3,1");
		board.addMove(defaultPlayer, "3,2");
		board.addMove(bot, "3,3");
		
		assertThat(board.isComplete()).isTrue();
	}
	
	@Test public void
	board_is_not_complete_when_it_still_has_moves_to_do() throws MoveException {
		board.addMove(defaultPlayer, "1,1");
		
		assertThat(board.isComplete()).isFalse();
	}

	private void assertInvalidMove(String invalidMove) {
		assertThatThrownBy(() -> 
			board.addMove(defaultPlayer, invalidMove))
				.isInstanceOf(InvalidMoveFormatException.class);
	}
	
}
