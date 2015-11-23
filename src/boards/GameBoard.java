package boards;

import fields.Field;
import fields.Fleet;
import fields.LaborCamp;
import fields.Ownable;
import fields.Refuge;
import fields.Tax;
import fields.Territory;

/**
 * Date: 23/11/2015
 *
 * Project: 25_CDIO3
 *
 * File: GameBoard.java
 *
 * Created by: Morten Jørvad
 *
 * Github: https://github.com/Mortenbaws
 *
 * Email: morten2094@gmail.com
 */

public class GameBoard {

	private Field[] gameBoard = new Field[21];
	
	public GameBoard(){
		initializeGameBoard();
	}
	
	private void initializeGameBoard(){
		gameBoard[0] = new Territory("Tribe Encampment", 1000, 100);
		gameBoard[1] = new Territory("Crater", 1500, 300);
		gameBoard[2] = new Territory("Mountain", 2000, 500);
		gameBoard[3] = new Territory("Cold Desert", 3000, 700);
		gameBoard[4] = new Territory("Black Cave", 4000, 1000);
		gameBoard[5] = new Territory("The Werewall", 4300, 1300);
		gameBoard[6] = new Territory("Mountain Village", 4750, 1600);
		gameBoard[7] = new Territory("South Citadel", 5000, 2000);
		gameBoard[8] = new Territory("Palace Gates", 5500, 2600);
		gameBoard[9] = new Territory("Tower", 6000, 3200);
		gameBoard[10] = new Territory("Castle", 8000, 4000);
		gameBoard[11] = new Refuge("Walled City", 5000);
		gameBoard[12] = new Refuge("Monastery", 500);
		gameBoard[13] = new LaborCamp("Huts In The Mountain", 2500, 100);
		gameBoard[14] = new LaborCamp("The Pit", 2500, 100);
		gameBoard[15] = new Tax("Goldmine", 2000);
		gameBoard[16] = new Tax("Caravan", 4000);
		gameBoard[17] = new Fleet("Second Sail", 4000);
		gameBoard[18] = new Fleet("Sea Grover", 4000);
		gameBoard[19] = new Fleet("The Buccaneers", 4000);
		gameBoard[20] = new Fleet("Privateer Armade", 4000);
	}
	
	public Field[] getGameBoard(){
		return gameBoard;
	}
	
	public Field getGameBoardIndex(int index){
		if(index >= 0 && index < gameBoard.length){
			return gameBoard[index];
		}
		else{
			return gameBoard[0];
		}
	}
	
	public boolean askQuestion(int index){
		if(index >= 0 && index < gameBoard.length){
			if(index >= 0 && index <= 10  || index >= 12 && index <= 13 || index >= 17 && index <= 20){
				if(!(((Ownable) gameBoard[index]).isTaken())){
					return true;
				}
			}
		}
		return false;
	}
	
	public void printAllFields(){
		for(int i = 0 ; i < gameBoard.length; i++){
			System.out.println(gameBoard[i]);
		}
	}
	
	
	
}
