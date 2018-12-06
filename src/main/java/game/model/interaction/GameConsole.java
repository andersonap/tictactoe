package game.model.interaction;

import java.util.Scanner;

import game.model.Board;
import game.model.player.Player;

public class GameConsole implements Interaction {
	
	private static Scanner scanner = new Scanner(System.in);

	@Override
	public String getMoveFrom(Player player) {
		System.out.println(player.getName() + ", please do your move:");
		return scanner.next();
	}

	@Override
	public void showGameStartMessage() {
		System.out.println("Get ready! The game is about to start!");
	}

	@Override
	public void showBoard(Board board) {
		System.out.println(board.stringify());
	}

	@Override
	public void showWinner(Player winner) {
		System.out.println("We have a winner!");
		System.out.println("Congratulations!");
		System.out.println("Player " + winner.getName() + " is the winner!");
	}

	@Override
	public void notifyGameCantStart(String cause) {
		System.out.print("Houston, we have a problem!");
		System.out.print("The game can't start");
		System.out.print("Cause: " + cause);
	}
	
}
