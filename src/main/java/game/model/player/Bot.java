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
		int horizontalPosition = generateRandomNumberBetweenOneAndBoardSize(board.getSize());
		int verticalPosition = generateRandomNumberBetweenOneAndBoardSize(board.getSize());
		
		while(board.hasMove(horizontalPosition, verticalPosition)) {
			horizontalPosition = generateRandomNumberBetweenOneAndBoardSize(board.getSize());
			verticalPosition = generateRandomNumberBetweenOneAndBoardSize(board.getSize());
		}
		
		return formattedPosition(horizontalPosition, verticalPosition);
	}
	
	private int generateRandomNumberBetweenOneAndBoardSize(Integer boardSize) {
		Random random = new Random();
		int low = 1;
		int high = boardSize + 1;
		
		return random.nextInt(high-low) + low;
	}

	private String formattedPosition(int horizontalPosition, int verticalPosition) {
		return String.valueOf(horizontalPosition) + "," + String.valueOf(verticalPosition);
	}
	
}
