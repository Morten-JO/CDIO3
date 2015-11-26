package tests;

import logic.Game;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import fields.Fleet;

public class FleetTest {

	private Game game;
	
	@Before
	public void setUp() throws Exception {
		String[] players = {"Testnavn", "Testnavn2"};
		game = new Game(2, 6, players);
	}

	@After
	public void tearDown() throws Exception {
		game.removePlayer(game.getPlayer(0));
	}
	
	

	@Test
	public void testCheckFleetValuesAndLandOn() {
		//buy first fleet.
		game.getGameBoard().getGameBoard()[17].landOn(game.getPlayer(0), game);
		int expected = 500;
		Assert.assertEquals(expected, Fleet.RENTS[game.getGameBoard().getOwnerShipOfFleets(game.getPlayer(0))-1]);
		
		//buy second fleet
		game.getGameBoard().getGameBoard()[18].landOn(game.getPlayer(0), game);
		expected = 1000;
		Assert.assertEquals(expected, Fleet.RENTS[game.getGameBoard().getOwnerShipOfFleets(game.getPlayer(0))-1]);
		
		//buy third fleet
		game.getGameBoard().getGameBoard()[19].landOn(game.getPlayer(0), game);
		expected = 2000;
		Assert.assertEquals(expected, Fleet.RENTS[game.getGameBoard().getOwnerShipOfFleets(game.getPlayer(0))-1]);
		
		//buy fourth fleet
		game.getGameBoard().getGameBoard()[20].landOn(game.getPlayer(0), game);
		expected = 4000;
		Assert.assertEquals(expected, Fleet.RENTS[game.getGameBoard().getOwnerShipOfFleets(game.getPlayer(0))-1]);
	}
	
	@Test
	public void testLandOnCantPay(){
		//remove 29k from player1s balance
		game.getPlayer(0).adjustPoints(-29600);
		int expected = 400;
		Assert.assertEquals(expected, game.getPlayer(0).getBalance());
		
		//player2 buys property
		game.getGameBoard().getGameBoard()[19].landOn(game.getPlayer(1), game);
		
		//only add player balance can afford and not total
		expected = expected + game.getPlayer(1).getBalance();
		game.getGameBoard().getGameBoard()[19].landOn(game.getPlayer(0), game);
		Assert.assertEquals(expected, game.getPlayer(1).getBalance());
	}
	
	@Test
	public void testLandOnSelf(){
		//check if u lose money for landing on your own!
		game.getGameBoard().getGameBoard()[17].landOn(game.getPlayer(0), game);
		int expected = game.getPlayer(0).getBalance();
		game.getGameBoard().getGameBoard()[17].landOn(game.getPlayer(0), game);
		Assert.assertEquals(expected, game.getPlayer(0).getBalance());
	}
	
}