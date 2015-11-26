package tests;

import logic.Game;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fields.Field;
import fields.Refuge;

public class RefugeTest {

	private Field refuge1;
	private Field refuge2;
	private Field refuge3;
	private Game game;
	
	@Before
	public void setUp() throws Exception {
		String[] players = {"Testnavn"};
		game = new Game(2, 6, players);
		refuge1 = new Refuge("Testrefuge1", 1000);
		refuge2 = new Refuge("Testrefuge2", 2000);
		refuge3 = new Refuge("Testrefuge3", 3000);
	}

	@After
	public void tearDown() throws Exception {
		game.removePlayer(game.getPlayer(0));
	}

	@Test
	public void testEntities() {
		Assert.assertNotNull(this.game);
		Assert.assertNotNull(this.game.getPlayer(0));
		
		Assert.assertNotNull(this.refuge1);
		Assert.assertNotNull(this.refuge2);
		Assert.assertNotNull(this.refuge3);
		
		Assert.assertTrue(this.refuge1 instanceof Refuge);
		Assert.assertTrue(this.refuge2 instanceof Refuge);
		Assert.assertTrue(this.refuge3 instanceof Refuge);
	}
	
	@Test
	public void testLandOnField(){
		int expected = 30000;
		int actual = game.getPlayer(0).getBalance();
		Assert.assertEquals(expected, actual);
		
		this.refuge1.landOn(this.game.getPlayer(0), this.game);
		expected = 31000;
		Assert.assertEquals(expected, this.game.getPlayer(0).getBalance());
		
		this.refuge2.landOn(this.game.getPlayer(0), this.game);
		expected = 33000;
		Assert.assertEquals(expected, this.game.getPlayer(0).getBalance());
		
		this.refuge3.landOn(this.game.getPlayer(0), this.game);
		expected = 36000;
		Assert.assertEquals(expected, this.game.getPlayer(0).getBalance());
	}
	
	@Test
	public void testTypeCastGetBonus(){
		int expected = 1000;
		Assert.assertEquals(expected, ((Refuge)refuge1).getBonus());
		
		expected = 2000;
		Assert.assertEquals(expected, ((Refuge)refuge2).getBonus());
		
		expected = 3000;
		Assert.assertEquals(expected, ((Refuge)refuge3).getBonus());
	}
}
