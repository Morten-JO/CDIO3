package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import entities.Dice;

/**
 * Date: 03/11/2015
 *
 * Project: CDIO2
 *
 * File: DiceTest.java
 *
 * Created by: Morten Jørvad
 */

public class DiceTest {

	@Test
	public void testHitDice() {
		Dice dice = new Dice(6);
		dice.hitDice();
		int sum = dice.getSum();
		dice.hitDice();
		assertEquals(dice.getLastSum(), sum);
		//på denne måde kan der ses at værdien skifter!
	}

	@Test
	public void testGetSum() {
		Dice dice = new Dice(6);
		int old = dice.getSum();
		dice.hitDice();
		assertTrue(old != dice.getSum());
	}

	@Test
	public void testGetLastSum() {
		Dice dice = new Dice(6);
		dice.hitDice();
		int old = dice.getSum();
		dice.hitDice();
		assertEquals(old, dice.getLastSum());
	}

	@Test
	public void testGetSides() {
		Dice dice = new Dice(6);
		assertEquals(dice.getSides(), 6);
	}
}
