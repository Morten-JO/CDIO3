package tests;

import logic.Game;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import fields.Field;
import fields.Fleet;
import fields.Refuge;

public class FleetTest {

	private Field fleet1;
	private Field fleet2;
	private Field fleet3;
	private Game game;
	
	@Before
	public void setUp() throws Exception {
		String[] players = {"Testnavn"};
		game = new Game(2, 6, players);
		fleet1 = new Fleet("Testfleet1", 4000);
		fleet2 = new Fleet("Testfleet2", 4000);
		fleet3 = new Fleet("Testfleet3", 4000);
	}

	@After
	public void tearDown() throws Exception {
		game.removePlayer(game.getPlayer(0));
	}

	@Test
	public void testCheckFleetValues() {
		//buy first fleet.
		game.getGameBoard().getGameBoard()[17].landOn(game.getPlayer(0), game);
		int expected = 500;
		Assert.assertEquals(expected, Fleet.RENTS[game.getGameBoard().getOwnerShipOfFleets(game.getPlayer(0))-1]);
		
	}

}
