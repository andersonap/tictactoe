package game.model;

import java.util.Collections;
import java.util.List;

import game.exception.GameStartException;
import game.exception.InvalidPlayerException;
import game.exception.MoveException;
import game.model.interaction.Interaction;
import game.model.player.Player;

public class Game {

	public static Interaction interaction;
	private Board board;
	private List<Player> players;
	private Player winner;

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public Player getWinner() {
		return winner;
	}

	public void setWinner(Player winner) {
		this.winner = winner;
	}

	public boolean hasWinner() {
		if (winner == null) {
			this.checkWinner();
		}
		return winner != null;
	}
	
	private void checkWinner() {
		this.winner = board.getWinner(); 
	}

	public void shufflePlayers() {
		Collections.shuffle(this.players);
	}

	public void start() {
		validateIfGameIsReadyToStart();
		
		interaction.showGameStartMessage();
		
		while (!this.hasWinner()) {
			for (Player player : this.players) {
				try {
					player.makeMove(board);
					interaction.showBoard(board);
				} catch(MoveException invalidMoveException) {
					invalidMoveException.printStackTrace();
				}
			}
		}
		
		interaction.showWinner(winner);
	}

	private void validateIfGameIsReadyToStart() {
		try {
			this.board.validateSize();
			this.validatePlayers();
		} catch (GameStartException e) {
			interaction.notifyGameCantStart(e.getMessage());
			e.printStackTrace();
		}
	}

	private void validatePlayers() throws InvalidPlayerException {
		if (this.players == null || this.players.isEmpty() || this.hasInvalidPlayer()) {
			throw new InvalidPlayerException();
		}
	}

	private boolean hasInvalidPlayer() {
		for (Player player : players) {
			if (player.getName() == null || player.getName().isEmpty() ||
					player.getSymbol() == null || player.getSymbol().isEmpty()) {
				return true;
			}
		}
		return false;
	}

	public void setInteraction(Interaction interaction) {
		this.interaction = interaction;
	}

}
