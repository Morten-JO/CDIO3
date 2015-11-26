package tests;

import logic.Game;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fields.Field;
import fields.Ownable;
import fields.Territory;

public class TerritoryTest {

	private Field territory1;
	private Field territory2;
	private Field territory3;
	private Game game;
	
	@Before
	public void setUp() throws Exception {
		String[] players = {"Testnavn", "Testnavn2"};
		game = new Game(2, 6, players);
		territory1 = new Territory("Testrefuge1", 1000, 950);
		territory2 = new Territory("Testrefuge2", 2000, 1900);
		territory3 = new Territory("Testrefuge", 3000, 2850);
	}

	@After
	public void tearDown() throws Exception {
		game.removePlayer(game.getPlayer(0));
	}
	
	@Test
	public void testEntities() {
		Assert.assertNotNull(this.game);
		Assert.assertNotNull(this.game.getPlayer(0));
		
		Assert.assertNotNull(this.territory1);
		Assert.assertNotNull(this.territory2);
		Assert.assertNotNull(this.territory3);
		
		Assert.assertTrue(this.territory1 instanceof Territory);
		Assert.assertTrue(this.territory2 instanceof Territory);
		Assert.assertTrue(this.territory3 instanceof Territory);
	}
	
	@Test
	public void testLandOn() {
		//buy the property
		territory1.landOn(game.getPlayer(0), game);
		Assert.assertTrue(((Ownable)territory1).getOwner().equals(game.getPlayer(0)));
		int expected = 30000 - 1000;
		Assert.assertEquals(expected, game.getPlayer(0).getBalance());
		
		//land on bought property
		territory1.landOn(game.getPlayer(1), game);
		expected = 30000-950;
		Assert.assertEquals(expected, game.getPlayer(1).getBalance());
		
		
	}
	
	@Test
	public void testLandOnCantPay(){
		//remove 29k from player1s balance
		game.getPlayer(0).adjustPoints(-29000);
		int expected = 1000;
		Assert.assertEquals(expected, game.getPlayer(0).getBalance());
		
		//player2 buys property
		territory2.landOn(game.getPlayer(1), game);
		
		//only add player balance can afford and not total
		expected = expected + game.getPlayer(1).getBalance();
		territory2.landOn(game.getPlayer(0), game);
		Assert.assertEquals(expected, game.getPlayer(1).getBalance());
	}
	
	@Test
	public void testLandOnSelf(){
		//check if u lose money for landing on your own!
		territory3.landOn(game.getPlayer(0), game);
		int expected = game.getPlayer(0).getBalance();
		territory3.landOn(game.getPlayer(0), game);
		Assert.assertEquals(expected, game.getPlayer(0).getBalance());
	}
	
	

}
