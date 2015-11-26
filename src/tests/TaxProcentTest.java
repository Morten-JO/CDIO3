package tests;

import logic.Game;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fields.Field;
import fields.Tax;
import fields.TaxProcent;

public class TaxProcentTest {

	private Field tax1;
	private Field tax2;
	private Field tax3;
	private Game game;
	
	@Before
	public void setUp() throws Exception {
		String[] players = {"Testnavn"};
		game = new Game(2, 6, players);
		tax1 = new TaxProcent("TestTax1", 1000, 10);
		tax2 = new TaxProcent("TestTax2", 2000, 20);
		tax3 = new TaxProcent("TestTax3", 3000, 30);
	}

	@After
	public void tearDown() throws Exception {
		game.removePlayer(game.getPlayer(0));
	}

	@Test
	public void testEntities() {
		Assert.assertNotNull(this.game);
		Assert.assertNotNull(this.game.getPlayer(0));
		
		Assert.assertNotNull(this.tax1);
		Assert.assertNotNull(this.tax2);
		Assert.assertNotNull(this.tax3);
		
		Assert.assertTrue(this.tax1 instanceof TaxProcent);
		Assert.assertTrue(this.tax2 instanceof TaxProcent);
		Assert.assertTrue(this.tax3 instanceof TaxProcent);
	}

	@Test
	public void testLandOnField(){
		int expected = 30000;
		int actual = game.getPlayer(0).getBalance();
		Assert.assertEquals(expected, actual);
		
		//will be pressed on %
		this.tax1.landOn(this.game.getPlayer(0), this.game);
		expected = 27000;
		Assert.assertEquals(expected, this.game.getPlayer(0).getBalance());
		
		this.tax2.landOn(this.game.getPlayer(0), this.game);
		expected = 21600;
		Assert.assertEquals(expected, this.game.getPlayer(0).getBalance());
		
		this.tax3.landOn(this.game.getPlayer(0), this.game);
		expected = 15120;
		Assert.assertEquals(expected, this.game.getPlayer(0).getBalance());
		
		//will be pressed on 3k
		this.tax3.landOn(this.game.getPlayer(0), this.game);
		expected = 12120;
		Assert.assertEquals(expected, this.game.getPlayer(0).getBalance());
	}
	
	@Test
	public void testTypeCastGetTaxAmount(){
		int expected = 1000;
		Assert.assertEquals(expected, ((Tax)tax1).getTaxAmount());
		
		expected = 2000;
		Assert.assertEquals(expected, ((Tax)tax2).getTaxAmount());
		
		expected = 3000;
		Assert.assertEquals(expected, ((Tax)tax3).getTaxAmount());
	}

}
