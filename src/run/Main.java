package run;

import java.util.ArrayList;

import language.TextStrings;
import logic.Game;
import desktop_codebehind.Car;
import desktop_fields.Field;
import desktop_fields.Street;
import desktop_resources.GUI;
import entities.Player;
import fields.Ownable;

public class Main {

	public static void main(String[] args) {
		initializeGUI();
		boolean endSelection = false;
		ArrayList<String> names = new ArrayList<String>();
		//always adds atleast two players.
		//ask person 1 to type in name in chance card
		GUI.setChanceCard(TextStrings.write_player+" #"+(names.size()+1)+" "+TextStrings.name+".");
		GUI.displayChanceCard();
		names.add(GUI.getUserString(""));
		//ask person 2 to type in name in chance card
		GUI.setChanceCard(TextStrings.write_player+" #"+(names.size()+1)+" "+TextStrings.name+".");
		GUI.displayChanceCard();
		names.add(GUI.getUserString(""));
		//can choose to add up to 6.
		while(!endSelection){
			//show question to add another player in chance card
			GUI.setChanceCard(TextStrings.add_another_player);
			GUI.displayChanceCard();
 			if(GUI.getUserLeftButtonPressed("", TextStrings.yes, TextStrings.no)){
				//ask person to write player name
				GUI.setChanceCard(TextStrings.write_player+" #"+(names.size()+1)+" "+TextStrings.name+".");
				GUI.displayChanceCard();
				names.add(GUI.getUserString(""));
			}
			else{
				endSelection = true;
			}
			if(names.size() >= 6){
				endSelection = true;
			}
		}
		//make it into an array.
		String[] array = new String[names.size()];
		array = names.toArray(array);
		Game game = new Game(2, 6, array);
		
		//add players to GUI
		for(int i = 0; i < array.length; i++){
			GUI.addPlayer(game.getPlayer(i).getName(), game.getPlayer(i).getBalance());
		}
		
		//Main loop, runs forever, until broken out(when game win conditions are true)
		while(true){
			//keeps rolling until next players turn
			for(int i = 1; i < game.getAmountOfPlayer()+1; i++){
				if(game.getAmountOfPlayer() == 1){
					break;
				}
				while(game.getTurn() == i){
					//checks if a player has been removed.
					int curr = game.getAmountOfPlayer();
					rollPlayer(i, game);
					if(game.getAmountOfPlayer() < curr ){
						i--;
						if(i != 0){
							GUI.setBalance(game.getPlayer(i-1).getName(), 0);
							game.setTurn(i);
						}
						break;
					}
					if(game.getAmountOfPlayer() == 1){
						break;
					}
				}
			}
			String message = game.checkWinningConditions();
			if(game.getIfWon()){
				GUI.setChanceCard(message);
				GUI.displayChanceCard();
				GUI.getUserButtonPressed("", TextStrings.ok);
				break;
			}
		}
		GUI.getUserButtonPressed("", TextStrings.exit);
		GUI.close();
	}
	
	/*
	 * Tells user to roll dices by pressing button, and sets the approriate things after rolling the dices
	 */
	public static boolean rollPlayer(int player, Game game){
		//show turn to roll in chance card
		GUI.setChanceCard(game.getPlayer(player-1).getName()+TextStrings.turn_to_roll);
		GUI.displayChanceCard();
		
		GUI.getUserButtonPressed("", TextStrings.word_roll);
		
		int curr = game.getAmountOfPlayer();
		//roll the player
		boolean desc = game.rollPlayer(game.getPlayer(player-1));
		if(game.getAmountOfPlayer() < curr ){
			return false;
		}
		
		GUI.setDice(game.getCup().getSumOfDice(0), game.getCup().getSumOfDice(1));
		GUI.removeAllCars(game.getPlayer(player-1).getName());
		//GUI is created to set car position not starting from zero indexed, so +1
		GUI.setCar(game.getPlayer(player-1).getPosition()+1, game.getPlayer(player-1).getName());
		
		//Show turn description in chance card
		GUI.setChanceCard(game.getFieldText(game.getPlayer(player-1).getPosition(), game.getPlayer(player-1)));
		GUI.displayChanceCard();
		
		//desc returns false, if its a question. buy etc.
		if(desc == true){
			GUI.getUserButtonPressed("", TextStrings.ok);
		}
		else{
			//ask question to user.
			if(GUI.getUserLeftButtonPressed("", TextStrings.yes, TextStrings.no)){
				if(!game.getGameBoard().getGameBoardIndex(game.getPlayer(player-1).getPosition()).landOn(game.getPlayer(player-1), game)){
					GUI.removeCar(game.getPlayer(player-1).getPosition()+1, game.getPlayer(player-1).getName());
					//remove ownership from player..
					game.getGameBoard().removeOwnerShip(game.getPlayer(player-1));
					game.removePlayer(game.getPlayer(player-1));
					return false;
					
				}
			}
		}
		updateBalanceAllPlayers(game);
		updateFields(game);
		return true;
	}
	
	//intialize the graphical things for the GUI
	public static void initializeGUI(){
		String[] names = {TextStrings.tribe_enc, TextStrings.crater, TextStrings.mountain, TextStrings.cold_desert, TextStrings.black_cave, TextStrings.the_werewall, TextStrings.mountain_village,
						  TextStrings.south_citadel, TextStrings.palace_gates, TextStrings.tower, TextStrings.castle, TextStrings.walled_city, TextStrings.monastery, TextStrings.huts_in_the_mountain, 
						  TextStrings.the_pit, TextStrings.goldmine, TextStrings.caravan, TextStrings.second_sail, TextStrings.sea_grover, TextStrings.the_buccaneers, TextStrings.privateer_armade};
		String[] subText = {TextStrings.rent+": 100", TextStrings.rent+": 300", TextStrings.rent+": 500", TextStrings.rent+": 700", TextStrings.rent+": 1000", TextStrings.rent+": 1300", TextStrings.rent+": 1600", TextStrings.rent+": 2000",
							TextStrings.rent+": 2600", TextStrings.rent+": 3200", TextStrings.rent+": 4000", TextStrings.receive+": 5000", TextStrings.receive+":: 500", "100 * "+TextStrings.dice, "100 * "+TextStrings.dice, TextStrings.pay+" 2000",
							TextStrings.pay+" 4000 "+TextStrings.or+" 10%", TextStrings.pay+" 500-4000", TextStrings.pay+" 500-4000", TextStrings.pay+" 500-4000", TextStrings.pay+" 500-4000"};
		String[] desc = {TextStrings.big_price+": 1000", TextStrings.big_price+": 1500", TextStrings.big_price+": 2000", TextStrings.big_price+": 3000", TextStrings.big_price+": 4000", TextStrings.big_price+": 4300",
						TextStrings.big_price+": 4750", TextStrings.big_price+": 5000", TextStrings.big_price+": 5500", TextStrings.big_price+": 6000", TextStrings.big_price+": 8000", "", "", TextStrings.big_price+": 2500",
						TextStrings.big_price+": 2500", "", "", TextStrings.big_price+": 4000", TextStrings.big_price+": 4000", TextStrings.big_price+": 4000", TextStrings.big_price+": 4000"};
		Field[] list = new Field[21];
		for(int i = 0; i < list.length; i++){
			list[i] = new Street.Builder().build();
			list[i].setTitle(names[i]);
			list[i].setSubText(subText[i]);
			list[i].setDescription(desc[i]);
		}
		GUI.create(list);
	}
	
	//updates GUI balances so its possible to see when somebody lands on bought place
	public static void updateBalanceAllPlayers(Game game){
		for(int i = 0; i < game.getAmountOfPlayer(); i++){
			GUI.setBalance(game.getPlayer(i).getName(), game.getPlayer(i).getBalance());
		}
	}
	
	public static void updateFields(Game game){
		for(int i = 0; i < game.getGameBoard().getGameBoard().length; i++){
			if(i >= 0 && i <= 10  || i >= 13 && i <= 14 || i >= 17 && i <= 20){
				if((((Ownable) game.getGameBoard().getGameBoardIndex(i)).isTaken())){
					Player player = ((Ownable) game.getGameBoard().getGameBoardIndex(i)).getOwner();
					//GUI isnt zero indexed, so its +1
					GUI.setOwner(i+1, player.getName());
				}
				else{
					GUI.removeOwner(i+1);
				}
			}
		}
	}
}