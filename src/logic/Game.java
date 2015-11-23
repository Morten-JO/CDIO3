package logic;

import java.util.ArrayList;

import entities.*;

public class Game {

	private Cup cup;
	private ArrayList<Player> players = new ArrayList<Player>();
	private Field[] fields = new Field[11];
	private boolean won;
	private int turn = 1;
	
	//Adds players without names
	public Game(int dices, int diceSides, int players){
		for(int i = 0; i < players; i++){
			this.players.add(new Player());
		}
		cup = new Cup(dices, diceSides);
		initializeArray();
	}
	
	//Adds players with names
	public Game(int dices, int diceSides, String[] names){
		cup = new Cup(dices, diceSides);
		for(int i = 0; i < names.length; i++){
			this.players.add(new Player());
			this.players.get(i).setName(names[i]);
		}
		initializeArray();
	}
	
	//Inserts the fields into the array
	private void initializeArray(){
		fields[0] = new Field("Tower", 250);
		fields[1] = new Field("Crater", -100);
		fields[2] = new Field("Palace gates", 100);
		fields[3] = new Field("Cold Desert", -20);
		fields[4] = new Field("Walled city", 180);
		fields[5] = new Field("Monastery", 0);
		fields[6] = new Field("Black cave", -70);
		fields[7] = new Field("Huts in the mountain", 60);
		fields[8] = new Field("The Werewall (werewolf-wall)", -80);
		fields[9] = new Field("The pit", -50);
		fields[10] = new Field("Goldmine", 650);
	}
	
	//rolls dices for player, and changes turn if not rolled "Werewall"
	public String rollPlayer(Player player){
		cup.rollDices();
		if(cup.getDiceSum() != 10){
			if(turn == 1){
				turn = 2;
			}
			else{
				turn = 1;
			}
		}
		//apply points and check if it was possible
		if(!fields[cup.getDiceSum()-2].landOn(player)){
			won = true;
		}
		return getOutputString(cup.getDiceSum());
	}
	
	//Checks win conditions and returns String based on result(won is set true if game is won)
	public String checkWinningConditions(){
		if(players.size() == 1){
			won = true;
			return players.get(0).getName()+" has won the game, with a balance of: "+players.get(0).getBalance()+".";
		}
		else if(players.size() < 0){
			won = true;
			return "Nobody won, all has lost their balance at the same round.";
		}
		else{
			return "";
		}
	}
	
	public boolean getIfWon(){
		return won;
	}
	
	public Player getPlayer(int i){
		return players.get(i);
	}
	
	public Cup getCup(){
		return cup;
	}
	
	public int getTurn(){
		return turn;
	}
	
	public String getOutputString(int index){
		String name = fields[index-2].getName();
		int points = fields[index-2].getPoints();
		if(index == 10){
			return "You landed on "+name+" and lost "+(-points)+" points, but gained an extra turn!";
		}
		if(index == 12){
			return "You landed on "+name+". You are RICH!";
		}
		if(points > 0){
			return "You landed on "+name+" and gain "+points+" points";
		}
		else if(points < 0){
			return "You landed on "+name+" and lose "+(-points)+" points";
		}
		else{
			return "You landed on "+name+" and nothing happens";
		}
	}
	
	public int getAmountOfPlayer(){
		return players.size();
	}
	
}
