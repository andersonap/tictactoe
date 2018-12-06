package game.model.player;

import game.exception.MoveException;
import game.model.Board;

public class Bot extends DefaultPlayer {
	
	public Bot(String name, String symbol) {
		super(name, symbol);
	}

	@Override
	public void makeMove(Board board) throws MoveException {
		board.addMove(this, generatePosition(board));
	}
	
	private String generatePosition(Board board) {
		return "";
	}

}
