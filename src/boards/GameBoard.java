package boards;

import language.TextStrings;
import entities.Player;
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
		gameBoard[0] = new Territory(TextStrings.tribe_enc, 1000, 100);
		gameBoard[1] = new Territory(TextStrings.crater, 1500, 300);
		gameBoard[2] = new Territory(TextStrings.mountain, 2000, 500);
		gameBoard[3] = new Territory(TextStrings.cold_desert, 3000, 700);
		gameBoard[4] = new Territory(TextStrings.black_cave, 4000, 1000);
		gameBoard[5] = new Territory(TextStrings.the_werewall, 4300, 1300);
		gameBoard[6] = new Territory(TextStrings.mountain_village, 4750, 1600);
		gameBoard[7] = new Territory(TextStrings.south_citadel, 5000, 2000);
		gameBoard[8] = new Territory(TextStrings.palace_gates, 5500, 2600);
		gameBoard[9] = new Territory(TextStrings.tower, 6000, 3200);
		gameBoard[10] = new Territory(TextStrings.castle, 8000, 4000);
		gameBoard[11] = new Refuge(TextStrings.walled_city, 5000);
		gameBoard[12] = new Refuge(TextStrings.monastery, 500);
		gameBoard[13] = new LaborCamp(TextStrings.huts_in_the_mountain, 2500, 100);
		gameBoard[14] = new LaborCamp(TextStrings.the_pit, 2500, 100);
		gameBoard[15] = new Tax(TextStrings.goldmine, 2000);
		gameBoard[16] = new Tax(TextStrings.caravan, 4000);
		gameBoard[17] = new Fleet(TextStrings.second_sail, 4000);
		gameBoard[18] = new Fleet(TextStrings.sea_grover, 4000);
		gameBoard[19] = new Fleet(TextStrings.the_buccaneers, 4000);
		gameBoard[20] = new Fleet(TextStrings.privateer_armade, 4000);
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
			if(index >= 0 && index <= 10  || index >= 13 && index <= 14 || index >= 17 && index <= 20){
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
	
	public int getOwnerShipOfLaborCamps(Player player){
		int ownerShip = 0;
		for(int i = 13 ; i <15; i++){
			Player tempPlayer = ((Ownable)(gameBoard[i])).getOwner();
			if(tempPlayer != null){
				if(tempPlayer.equals(player)){
					ownerShip++;
				}
			}
		}
		return ownerShip;
	}
	
	public int getOwnerShipOfFleets(Player player){
		int ownerShip = 0;
		for(int i = 17 ; i < 21; i++){
			Player tempPlayer = ((Ownable)(gameBoard[i])).getOwner();
			if(tempPlayer != null){
				if(tempPlayer.equals(player)){
					ownerShip++;
				}
			}
		}
		return ownerShip;
	}
	
	public void removeOwnerShip(Player player){
		for(int i = 0; i < gameBoard.length; i++){
			if(i <= 10 || i >= 13 && i <= 14 || i >= 17 && i <= 20){
				Player tempPlayer = ((Ownable)gameBoard[i]).getOwner();
				if(tempPlayer != null){
					if(tempPlayer.equals(player)){
						((Ownable)gameBoard[i]).setOwner(null);
					}
				}
			}
		}
	}
}
