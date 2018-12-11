package game.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import game.exception.game.InvalidBoardSizeException;
import game.exception.move.InvalidMoveFormatException;
import game.exception.move.InvalidPositionException;
import game.exception.move.MoveException;
import game.exception.move.MoveAlreadyMadeException;
import game.model.player.DefaultPlayer;
import game.model.player.Player;

public class Board {

	private Integer size;
	private List<Move> moves;
	
	public Board(Integer size) {
		this.size = size;
		this.moves = new ArrayList<>();
	}
	
	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public List<Move> getMoves() {
		return moves;
	}

	public void setMoves(List<Move> moves) {
		this.moves = moves;
	}
	
	public void addMove(Move move) {
		if (moves == null) {
			this.moves = new ArrayList<>();
		}
		this.moves.add(move);
	}
	
	public Player getWinner() {
		for(Map.Entry<Player, List<Move>> movesByPlayer : getMovesByPlayer().entrySet()) {
	    List<Move> moves = movesByPlayer.getValue();
	    
	    if (completedHorizontalRow(moves) || completedVerticalRow(moves) || completedDiagonalRow(moves)) {
	    	return movesByPlayer.getKey();
	    }
		}
		return null;
	}
	
	private Map<Player, List<Move>> getMovesByPlayer() {
		return this.moves.stream().collect(Collectors.groupingBy(Move::getPlayer));
	}
	
	private boolean completedHorizontalRow(List<Move> moves) {
		Map<Integer, Long> occurrencesByHorizontalPosition = moves.stream()
				.collect(Collectors.groupingBy(Move::getHorizontalPosition, Collectors.counting()));
		return occurrencesByHorizontalPosition.containsValue(Long.valueOf(this.size));
	}

	private boolean completedVerticalRow(List<Move> moves) {
		Map<Integer, Long> occurrencesByVerticalPosition = moves.stream()
				.collect(Collectors.groupingBy(Move::getVerticalPosition, Collectors.counting()));
		return occurrencesByVerticalPosition.containsValue(Long.valueOf(this.size));
	}

	private boolean completedDiagonalRow(List<Move> moves) {
		return completedLeftToRightDiagonal(moves) || completedRightToLeftDiagonal(moves);
	}
	
	private boolean completedLeftToRightDiagonal(List<Move> moves) {
		for(int i = 1; i <= this.size; i++) {
			int position = i;
			
			Optional<Move> matchingObject = moves.stream()
					.filter(move -> move.positionsAreEqualTo(position))
					.findFirst();
			
			if (!matchingObject.isPresent()) {
				return false;
			}
		}
		return true;
	}
	
	private boolean completedRightToLeftDiagonal(List<Move> moves) {
		for(int i = 1, j = this.size; i <= this.size; i++, j--) {
			int horizontalPosition = i;
			int verticalPosition = j;
			
			Optional<Move> matchingObject = moves.stream()
					.filter(move -> move.getHorizontalPosition().equals(horizontalPosition) && move.getVerticalPosition().equals(verticalPosition))
					.findFirst();
			
			if (!matchingObject.isPresent()) {
				return false;
			}
		}
		return true;
	}

	public void addMove(DefaultPlayer player, String position) throws MoveException {
		validate(position);
		if (moves == null) {
			this.moves = new ArrayList<>();
		}
		this.moves.add(new Move(player, this.getHorizontalPosition(position), this.getVerticalPosition(position)));
	}

	private void validate(String position) throws MoveException {
		if (this.invalidFormat(position)) {
			throw new InvalidMoveFormatException();
		}
		if (this.invalidSize(position)) {
			throw new InvalidPositionException();
		}
		if (this.moveAlreadyMade(position)) {
			throw new MoveAlreadyMadeException();
		}
	}

	private boolean invalidSize(String position) {
		if (getHorizontalPosition(position) > this.size || getVerticalPosition(position) > this.size) {
			return true;
		}
		return false;
	}

	private int getHorizontalPosition(String position) {
		return Integer.parseInt(position.split(",")[0]);
	}

	private int getVerticalPosition(String position) {
		return Integer.parseInt(position.split(",")[1]);
	}

	private boolean moveAlreadyMade(String position) {
		return getMoveOptional(getHorizontalPosition(position), getVerticalPosition(position)).isPresent();
	}

	private boolean invalidFormat(String position) {
		if (position != null && position.contains(",")) {
			String[] splittedPosition = position.split(",");
			if (splittedPosition.length == 2) {
				try {
					Integer.parseInt(splittedPosition[0]);
					Integer.parseInt(splittedPosition[1]);
					return false;
				} catch (NumberFormatException e) {
					return true;
				}
			}
		}
		return true;
	}

	public void validateSize() throws InvalidBoardSizeException {
		if (this.size < 3 || this.size > 10) {
			throw new InvalidBoardSizeException();
		}
	}

	private Optional<Move> getMoveOptional(int horizontalPosition, int verticalPosition) {
		return this.moves.stream()
				 .filter(move -> 
				 				 move.getHorizontalPosition().equals(horizontalPosition) &&
				 				 move.getVerticalPosition().equals(verticalPosition))
				 .findFirst();
	}
	
	public Move getMove(int horizontalPosition, int verticalPosition) {
		Optional<Move> moveOptional = getMoveOptional(horizontalPosition, verticalPosition);
		return moveOptional.isPresent() ? moveOptional.get() : null; 
	}
	
	public String stringify() {
		StringBuilder stringifiedBoard = new StringBuilder();
		
		for(int horizontalPosition = 1; horizontalPosition <= this.size; horizontalPosition++) {
			for(int verticalPosition = 1; verticalPosition <= this.size; verticalPosition++) {
				Move move = this.getMove(horizontalPosition, verticalPosition);
				
				if (horizontalPosition != this.size) {
					stringifiedBoard.append("̲");
				}
				
				if (move != null) {
					stringifiedBoard.append(move.getPlayer().getSymbol());
				} else {
					stringifiedBoard.append(" ");
				}
				
				if (verticalPosition != this.size) {
					stringifiedBoard.append("|");
				}
			}
			stringifiedBoard.append("\n");
		}
		
		return stringifiedBoard.toString();
	}

	public boolean isComplete() {
		return (this.size * this.size) == this.moves.size();
	}

}