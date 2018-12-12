package game.model;

public class BoardPrinter {
	
	public String stringify(Board board) {
		StringBuilder stringifiedBoard = new StringBuilder();
		
		for(int horizontalPosition = 1; horizontalPosition <= board.getSize(); horizontalPosition++) {
			for(int verticalPosition = 1; verticalPosition <= board.getSize(); verticalPosition++) {
				Move move = board.getMove(horizontalPosition, verticalPosition);
				
				if (horizontalPosition != board.getSize()) {
					stringifiedBoard.append("̲");
				}
				
				if (move != null) {
					stringifiedBoard.append(move.getPlayer().getSymbol());
				} else {
					stringifiedBoard.append(" ");
				}
				
				if (verticalPosition != board.getSize()) {
					stringifiedBoard.append("|");
				}
			}
			stringifiedBoard.append("\n");
		}
		
		return stringifiedBoard.toString();
	}

}
