package run;

import java.util.ArrayList;

import desktop_fields.Field;
import desktop_fields.Street;
import desktop_resources.GUI;
import logic.Game;

public class Main {

	public static void main(String[] args) {
		initializeGUI();
		boolean endSelection = false;
		ArrayList<String> names = new ArrayList<String>();
		names.add(GUI.getUserString("Write player #"+(names.size()+1)+" name."));
		names.add(GUI.getUserString("Write player #"+(names.size()+1)+" name."));
		while(!endSelection){
			if(GUI.getUserLeftButtonPressed("Add another player?", "Yes", "No")){
				names.add(GUI.getUserString("Write player #"+(names.size()+1)+" name."));
			}
			else{
				endSelection = true;
			}
			if(names.size() >= 6){
				endSelection = true;
			}
		}
		String[] array = new String[names.size()];
		array = names.toArray(array);
		Game game = new Game(2, 6, array);
		
		for(int i = 0; i < array.length; i++){
			GUI.addPlayer(game.getPlayer(i).getName(), game.getPlayer(i).getBalance());
		}
		
		//Main loop, runs untill win conditions are true.
		while(true){
			//keeps rolling untill next players turn
			for(int i = 1; i < game.getAmountOfPlayer()+1; i++){
				while(game.getTurn() == i){
					rollPlayer(i, game);
				}
			}
			String message = game.checkWinningConditions();
			if(game.getIfWon()){
				GUI.showMessage(message);
				break;
			}
		}
		
		GUI.getUserButtonPressed("", "Exit");
		GUI.close();
	}
	
	/*
	 * Tells user to roll dices by pressing button, and sets the approriate things after rolling the dices
	 */
	public static void rollPlayer(int player, Game game){
		GUI.getUserButtonPressed(game.getPlayer(player-1).getName()+"'s turn to roll!", "Roll");
		boolean desc = game.rollPlayer(game.getPlayer(player-1));
		GUI.setDice(game.getCup().getSumOfDice(0), game.getCup().getSumOfDice(1));
		GUI.removeAllCars(game.getPlayer(player-1).getName());
		GUI.setCar(game.getCup().getDiceSum()-1, game.getPlayer(player-1).getName());
		//it did the round
		if(desc == true){
			GUI.getUserButtonPressed(game.getFieldText(game.getPlayer(player-1).getPosition()), "Ok");
		}
		else{
			if(GUI.getUserLeftButtonPressed(game.getFieldText(game.getPlayer(player-1).getPosition()), "Yes", "No")){
				game.getGameBoard().getGameBoardIndex(game.getPlayer(player-1).getPosition()).landOn(game.getPlayer(player-1));
				GUI.getUserButtonPressed(game.getFieldText(game.getPlayer(player-1).getPosition()), "ok");
			}
		}
	}
	
	
	public static void initializeGUI(){
		String[] names = {"Tribe Encampment", "Crater", "Mountain", "Cold Desert", "Black Cave", "The Werewall", "Mountain village",
						  "South Citadel", "Palace gates", "Tower", "Castle", "Walled city", "Monastery", "Huts in the mountain", 
						  "The pit", "Goldmine", "Caravan", "Second Sail", "Sea Grover", "The Bucaneers", "Privateer armade"};
		String[] subText = {"Rent: 100", "Rent: 300", "Rent: 500", "Rent: 700", "Rent: 1000", "Rent: 1300", "Rent: 1600", "Rent: 2000",
							"Rent: 2600", "Rent: 3200", "Rent: 4000", "Receive: 5000", "Receive: 500", "100 x dice", "100 x dice", "Pay 2000",
							"Pay 4000 or 10%", "Pay 500-4000", "Pay 500-4000", "Pay 500-4000", "Pay 500-4000"};
		String[] desc = {"Price: 1000", "Price: 1500", "Price: 2000", "Price: 3000", "Price: 4000", "Price: 4300",
						 "Price: 4750", "Price: 5000", "Price: 5500", "Price: 6000", "Price: 8000", "", "", "Price: 2500",
						 "Price: 2500", "", "", "Price: 4000", "Price: 4000", "Price: 4000", "Price: 4000"};
		Field[] list = new Field[21];
		for(int i = 0; i < list.length; i++){
			list[i] = new Street.Builder().build();
			list[i].setTitle(names[i]);
			list[i].setSubText(subText[i]);
			list[i].setDescription(desc[i]);
		}
		GUI.create(list);
	}
}
