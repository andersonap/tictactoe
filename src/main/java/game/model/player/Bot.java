package game.model.player;

import java.util.Random;

import game.exception.move.MoveException;
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
		Random random = new Random();
		int low = 1;
		int high = board.getSize() + 1;
		
		int horizontalPosition = random.nextInt(high-low) + low;
		int verticalPosition = random.nextInt(high-low) + low;
		
		while(board.getMove(horizontalPosition, verticalPosition) != null) {
			horizontalPosition = random.nextInt(high-low) + low;
			verticalPosition = random.nextInt(high-low) + low;
		}
		
		return String.valueOf(horizontalPosition) + "," + String.valueOf(verticalPosition);
	}
	
}
