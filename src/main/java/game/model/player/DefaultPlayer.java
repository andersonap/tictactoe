package game.model.player;

import game.exception.MoveException;
import game.model.Board;
import game.model.Game;

public class DefaultPlayer implements Player {

	private String name;
	private String symbol;
	
	public DefaultPlayer(String name, String symbol) {
		this.name = name;
		this.symbol = symbol;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DefaultPlayer other = (DefaultPlayer) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		return true;
	}

	@Override
	public void makeMove(Board board) throws MoveException {
		try {
			board.addMove(this, Game.interaction.getMoveFrom(this));
		} catch (MoveException e) {
			e.printStackTrace();
		}
	}
	
}
