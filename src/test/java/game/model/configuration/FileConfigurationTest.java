package game.model.configuration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import game.model.player.Player;

public class FileConfigurationTest {
	
  @Rule
  public TemporaryFolder temporaryFolder = new TemporaryFolder();

  @Test public void
  board_size_should_be_the_same_as_the_config_file() throws IOException {
  	File tempFile = temporaryFolder.newFile();
		this.writeOnFile(tempFile, "BOARD_SIZE:5");
  	
  	Integer boardSize = new FileConfiguration(tempFile.getAbsolutePath()).getBoardSize();
  	
  	assertThat(boardSize).isEqualTo(5);
  }

	@Test public void 
	players_should_be_the_same_as_the_config_file() throws IOException {
		File tempFile = temporaryFolder.newFile();
		this.writeOnFile(tempFile, "PLAYER:Test Player,T");
		this.writeOnFile(tempFile, "COMPUTER:Test Computer,C");
  	
  	List<Player> players = new FileConfiguration(tempFile.getAbsolutePath()).getPlayers();
  	
  	assertThat(players).hasSize(2);
  }
	
	@Test	public void 
	should_return_null_when_board_size_is_not_a_number() throws IOException {
		File tempFile = temporaryFolder.newFile();
		
		this.writeOnFile(tempFile, "BOARD_SIZE:invalidSize");

		assertNull(new FileConfiguration(tempFile.getAbsolutePath()).getBoardSize());
	}
	
	@Test	public void 
	should_return_empty_list_of_players_when_config_file_doesnt_have_players() throws IOException {
		File tempFile = temporaryFolder.newFile();
		
		assertThat(new FileConfiguration(tempFile.getAbsolutePath()).getPlayers()).isEmpty();
	}
	
	private void writeOnFile(File tempFile, String string) {
  	try {
			Files.write(Paths.get(tempFile.getPath()), string.getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
