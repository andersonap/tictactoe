package game.model.configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import game.model.player.Bot;
import game.model.player.DefaultPlayer;
import game.model.player.Player;

public class FileConfiguration implements GameConfiguration {
	
	private String path;
	private static final String BOARD_SIZE_KEY = "BOARD_SIZE";
	private static final String PLAYER_KEY = "PLAYER";
	private static final String COMPUTER_KEY = "COMPUTER";
	
	public FileConfiguration(String path) {
		this.path = path;
	}

	@Override
	public Integer getBoardSize() {
			try {
				String boardSize = getLines().filter(line -> line.contains(BOARD_SIZE_KEY)).findFirst().orElse(null);
				return getBoardSize(boardSize);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return null;
	}

	private Stream<String> getLines() throws IOException {
		return Files.lines(Paths.get(path));
	}

	private Integer getBoardSize(String boardSize) {
		return Integer.valueOf(boardSize.split(":")[1]);
	}

	@Override
	public List<Player> getPlayers() {
		List<Player> players = new ArrayList<>();
		
		try {
			players.addAll(getDefaultPlayers());
			players.addAll(getBots());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return players;
	}

	private List<Player> getBots() throws IOException {
		List<String> players = getLines().filter(line -> line.contains(COMPUTER_KEY)).collect(Collectors.toList());
		
		List<Player> bots = new ArrayList<>();
		for (String bot : players) {
			Player player = new Bot(this.getName(bot), this.getSymbol(bot));
			bots.add(player);
		}
		
		return bots;
	}

	private List<Player> getDefaultPlayers() throws IOException {
		List<String> players = getLines().filter(line -> line.contains(PLAYER_KEY)).collect(Collectors.toList());
		
		List<Player> defaultPlayers = new ArrayList<>();
		for (String defaultPlayer : players) {
			Player player = new DefaultPlayer(this.getName(defaultPlayer), this.getSymbol(defaultPlayer));
			defaultPlayers.add(player);
		}
		
		return defaultPlayers;
	}
	
	private String getName(String line) {
		String value = line.split(":")[1];
		return value.split(",")[0].trim();
	}
	
	private String getSymbol(String line) {
		String value = line.split(":")[1];
		return value.split(",")[1].trim();
	}

}
