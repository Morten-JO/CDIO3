package tests;

import logic.Game;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import boards.GameBoard;
import fields.Refuge;

public class GameBoardTest {

	private GameBoard board;
	private Game game;
	
	@Before
	public void setUp() throws Exception {
		board = new GameBoard();
		String[] names = {"testnavn"};
		game = new Game(2, 6, names);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetGameBoardIndex() {
		Assert.assertTrue(this.board.getGameBoardIndex(11) instanceof Refuge);
	}

	@Test
	public void testGetOwnerShipOfLaborCamps() {
		board.getGameBoard()[13].landOn(game.getPlayer(0), game);
		board.getGameBoard()[14].landOn(game.getPlayer(0), game);
		int expected = 2;
		Assert.assertEquals(expected, board.getOwnerShipOfLaborCamps(game.getPlayer(0)));
	}

	@Test
	public void testGetOwnerShipOfFleets() {
		board.getGameBoard()[17].landOn(game.getPlayer(0), game);
		board.getGameBoard()[18].landOn(game.getPlayer(0), game);
		board.getGameBoard()[19].landOn(game.getPlayer(0), game);
		board.getGameBoard()[20].landOn(game.getPlayer(0), game);
		int expected = 4;
		Assert.assertEquals(expected, board.getOwnerShipOfFleets(game.getPlayer(0)));
	}

	@Test
	public void testRemoveOwnerShip() {
		board.getGameBoard()[17].landOn(game.getPlayer(0), game);
		board.getGameBoard()[18].landOn(game.getPlayer(0), game);
		board.getGameBoard()[19].landOn(game.getPlayer(0), game);
		board.getGameBoard()[20].landOn(game.getPlayer(0), game);
		int expected = 4;
		Assert.assertEquals(expected, board.getOwnerShipOfFleets(game.getPlayer(0)));
		board.removeOwnerShip(game.getPlayer(0));
		expected = 0;
		Assert.assertEquals(expected, board.getOwnerShipOfFleets(game.getPlayer(0)));
	}

	@Test
	public void testGetUserValue() {
		board.getGameBoard()[17].landOn(game.getPlayer(0), game);
		board.getGameBoard()[18].landOn(game.getPlayer(0), game);
		board.getGameBoard()[19].landOn(game.getPlayer(0), game);
		board.getGameBoard()[20].landOn(game.getPlayer(0), game);
		int expected = 16000;
		Assert.assertEquals(expected, board.getUserValue(game.getPlayer(0)));
	}

}
