package game.model;

import java.util.Collections;
import java.util.List;

import game.exception.game.GameStartException;
import game.exception.game.InvalidPlayerException;
import game.exception.move.MoveException;
import game.model.interaction.Interaction;
import game.model.player.Player;

public class Game {

	private static Interaction interaction;
	private Board board;
	private List<Player> players;
	private Player winner;
	
	
	public Game(Interaction interaction, Board board, List<Player> players) {
		this.interaction = interaction;
		this.board = board;
		this.players = players;
	}
	
	public static Interaction getInteraction() {
		return interaction;
	}

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
		return winner != null;
	}
	
	public void shufflePlayers() {
		if (this.players != null) {
			Collections.shuffle(this.players);
		}
	}

	public void start() {
		validateIfGameIsReadyToStart();
		
		interaction.showGameStartMessage();
		
		while (!this.hasWinner() || !this.board.isComplete()) {
			for (Player player : this.players) {
				this.makeMove(player);
				interaction.showBoard(this.board);
				this.checkIfGameMustEnd();
			}
		}
		
	}
	
	private void validateIfGameIsReadyToStart() {
		try {
			this.board.validateSize();
			this.validatePlayers();
		} catch (GameStartException exception) {
			interaction.notifyGameCantStart(exception.getMessage());
			System.exit(0);
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
	
	private void makeMove(Player player) {
		try {
			player.makeMove(board);
		} catch(MoveException moveException) {
			interaction.showMessage(moveException.getMessage());
			this.makeMove(player);
		}
	}

	private void checkIfGameMustEnd() {
		this.checkWinner();
		
		if (this.hasWinner()) {
			interaction.showWinner(this.winner);
			System.exit(0);
		} else if(this.board.isComplete()) {
			interaction.showDrawMessage();
			System.exit(0);
		}
		
	}
	
	private void checkWinner() {
		this.winner = board.getWinner(); 
	}

}
