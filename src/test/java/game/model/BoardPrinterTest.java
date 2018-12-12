package game.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import game.exception.move.MoveException;
import game.model.player.DefaultPlayer;

public class BoardPrinterTest {
	
	private DefaultPlayer playerOne = new DefaultPlayer("One", "X");
	private DefaultPlayer playerTwo = new DefaultPlayer("Two", "O");
	private BoardPrinter boardPrinter = new BoardPrinter();

	@Test public void
	should_print_board_with_size_three_correctly() throws MoveException {
		Board board = new Board(3);
		
		board.addMove(playerOne, "1,1");
		board.addMove(playerOne, "2,1");
		board.addMove(playerOne, "3,1");
		
		board.addMove(playerTwo, "2,2");
		board.addMove(playerTwo, "2,3");
		
		String expectedBoard = new StringBuilder()
				.append("̲X|̲ |̲ ").append("\n")
				.append("̲X|̲O|̲O").append("\n")
				.append("X| | ").append("\n")
				.toString();
		
		assertThat(expectedBoard).isEqualTo(boardPrinter.stringify(board));
	}

}
