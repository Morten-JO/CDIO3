package run;

import desktop_fields.Field;
import desktop_fields.Street;
import desktop_resources.GUI;
import logic.Game;

public class Main {

	public static void main(String[] args) {
		initializeGUI();
		
		String playerOneName = GUI.getUserString("Write Player One Name");
		String playerTwoName = GUI.getUserString("Write Player Two Name");
		
		Game game = new Game(2, 6, playerOneName, playerTwoName);
		
		GUI.addPlayer(game.getPlayer(0).getName(), game.getPlayer(0).getBalance());
		GUI.addPlayer(game.getPlayer(1).getName(), game.getPlayer(1).getBalance());
		
		//Main loop, runs untill win conditions are true.
		while(true){
			while(game.getTurn() == 1){
				rollPlayer(1, game);
			}
			while(game.getTurn() == 2){
				rollPlayer(2, game);
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
		String desc = game.rollPlayer(game.getPlayer(player-1));
		GUI.setDice(game.getCup().getSumOfDice(0), game.getCup().getSumOfDice(1));
		GUI.removeAllCars(game.getPlayer(player-1).getName());
		GUI.setCar(game.getCup().getDiceSum()-1, game.getPlayer(player-1).getName());
		GUI.getUserButtonPressed(desc, "Ok");
		GUI.setBalance(game.getPlayer(player-1).getName(), game.getPlayer(player-1).getBalance());
		
	}
	
	public static void initializeGUI(){
		Field[] list = new Field[11];
		list[10] = new Street.Builder().build();
		list[10].setTitle("Goldmine");
		list[10].setSubText("650");
		list[10].setDescription("Goldmine");
		list[9] = new Street.Builder().build();
		list[9].setTitle("The pit");
		list[9].setSubText("-50");
		list[9].setDescription("The pit");
		list[8] = new Street.Builder().build();
		list[8].setTitle("The Werewall");
		list[8].setSubText("-80 + extra turn");
		list[8].setDescription("The Werewall (extra turn)");
		list[7] = new Street.Builder().build();
		list[7].setTitle("Huts in the mountain");
		list[7].setSubText("60");
		list[7].setDescription("Huts in the mountain");
		list[6] = new Street.Builder().build();
		list[6].setTitle("Black cave");
		list[6].setSubText("-70");
		list[6].setDescription("Black cave");
		list[5] = new Street.Builder().build();
		list[5].setTitle("Monastery");
		list[5].setSubText("0");
		list[5].setDescription("Monastery");
		list[4] = new Street.Builder().build();
		list[4].setTitle("Walled city");
		list[4].setSubText("180");
		list[4].setDescription("Walled city");
		list[3] = new Street.Builder().build();
		list[3].setTitle("Cold Desert");
		list[3].setSubText("-20");
		list[3].setDescription("Cold Desert");
		list[2] = new Street.Builder().build();
		list[2].setTitle("Palace gates");
		list[2].setSubText("100");
		list[2].setDescription("Palace gates");
		list[1] = new Street.Builder().build();
		list[1].setTitle("Crater");
		list[1].setSubText("-100");
		list[1].setDescription("Crater");
		list[0] = new Street.Builder().build();
		list[0].setTitle("Tower");
		list[0].setSubText("250");
		list[0].setDescription("Tower");
		GUI.create(list);
	}
}
