package game.model;

import game.model.player.Player;

public class Move {

	private Player player;
	private Integer horizontalPosition;
	private Integer verticalPosition;

	public Move(Player player, Integer horizontalPosition, Integer verticalPosition) {
		this.player = player;
		this.horizontalPosition = horizontalPosition;
		this.verticalPosition = verticalPosition;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public Integer getHorizontalPosition() {
		return horizontalPosition;
	}

	public void setHorizontalPosition(Integer horizontalPosition) {
		this.horizontalPosition = horizontalPosition;
	}

	public Integer getVerticalPosition() {
		return verticalPosition;
	}

	public void setVerticalPosition(Integer verticalPosition) {
		this.verticalPosition = verticalPosition;
	}

	public boolean positionsAreEqualTo(Integer position) {
		return this.horizontalPosition.equals(position) && this.verticalPosition.equals(position);
	}

}
