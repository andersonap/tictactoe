package game.model.player;

import game.exception.move.MoveException;
import game.model.Board;

public interface Player {
	
	String getName();
	String getSymbol();
	void makeMove(Board board) throws MoveException;

}
