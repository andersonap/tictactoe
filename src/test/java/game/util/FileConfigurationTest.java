package game.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import game.model.player.Player;

public class FileConfigurationTest {
	
  @Rule
  public TemporaryFolder temporaryFolder = new TemporaryFolder();

  @Rule
  public ExpectedException expectedException = ExpectedException.none();
  
  @Test
  public void board_size_should_be_the_same_as_the_config_file() throws IOException {
  	Integer boardSize = new FileConfiguration("config").getBoardSize();
  	
  	Assert.assertEquals(Integer.valueOf(3), boardSize);
  }
  
  @Test
  public void players_should_be_the_same_as_the_config_file() throws IOException {
  	List<Player> players = new FileConfiguration("config").getPlayers();
  	
  	Assert.assertEquals(3, players.size());
  }
	
	@Test(expected = NoSuchFileException.class)
	public void should_throw_an_exception_when_file_is_not_found() throws IOException {
		new FileConfiguration("invalid_path").getBoardSize();
	}
	
	@Test
	public void should_throw_an_exception_when_board_size_is_not_a_number() throws IOException {
		File tempFile = temporaryFolder.newFile("invalid_board_size");
		new FileWriter(tempFile).write("BOARD_SIZE:invalid");
		
		System.out.println(tempFile);
		
		new FileConfiguration(tempFile.getPath()).getBoardSize();
	}
}
