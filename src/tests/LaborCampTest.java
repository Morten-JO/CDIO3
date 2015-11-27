package tests;

import logic.Game;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LaborCampTest {

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
		//player 1 buys all laborcamps:
		game.getGameBoard().getGameBoard()[13].landOn(game.getPlayer(0), game);
		game.getGameBoard().getGameBoard()[14].landOn(game.getPlayer(0), game);
		
		//check 10 times if the correct amount is paid based on dice
		for(int i = 0; i < 10; i++){
			game.getCup().rollDices();
			int expected = game.getPlayer(1).getBalance() - game.getCup().getDiceSum()*200;
			game.getGameBoard().getGameBoard()[13].landOn(game.getPlayer(1), game);
			Assert.assertEquals(expected, game.getPlayer(1).getBalance());
		}
	}
	
	@Test
	public void testLandOnCantPay(){
		//remove 29.9k from player1s balance
		game.getPlayer(0).adjustPoints(-29900);
		int expected = 100;
		Assert.assertEquals(expected, game.getPlayer(0).getBalance());
		
		//player2 buys two laborcamps
		game.getGameBoard().getGameBoard()[13].landOn(game.getPlayer(1), game);
		game.getGameBoard().getGameBoard()[14].landOn(game.getPlayer(1), game);
		
		//only add player balance can afford and not total
		game.getCup().rollDices();
		expected = expected + game.getPlayer(1).getBalance();
		game.getGameBoard().getGameBoard()[13].landOn(game.getPlayer(0), game);
		Assert.assertEquals(expected, game.getPlayer(1).getBalance());
	}
	
	@Test
	public void testLandOnSelf(){
		//check if u lose money for landing on your own!
		game.getGameBoard().getGameBoard()[13].landOn(game.getPlayer(0), game);
		int expected = game.getPlayer(0).getBalance();
		game.getGameBoard().getGameBoard()[13].landOn(game.getPlayer(0), game);
		Assert.assertEquals(expected, game.getPlayer(0).getBalance());
	}

}
