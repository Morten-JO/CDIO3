package logic;

import java.util.ArrayList;

import language.TextStrings;
import boards.GameBoard;
import entities.Cup;
import entities.Player;

public class Game {

	private GameBoard gameBoard;
	private Cup cup;
	private ArrayList<Player> players = new ArrayList<Player>();
	private boolean won;
	private int turn = 1;
	
	//Adds players without names
	public Game(int dices, int diceSides, int players){
		for(int i = 0; i < players; i++){
			this.players.add(new Player());
		}
		cup = new Cup(dices, diceSides);
		gameBoard = new GameBoard();
	}
	
	//Adds players with names
	public Game(int dices, int diceSides, String[] names){
		cup = new Cup(dices, diceSides);
		for(int i = 0; i < names.length; i++){
			this.players.add(new Player());
			this.players.get(i).setName(names[i]);
		}
		gameBoard = new GameBoard();
	}
	
	
	//rolls dices for player, and changes turn if not rolled same dices
	public boolean rollPlayer(Player player){
		cup.rollDices();
		
		//check if user hit same dice
		boolean sameHit = false;
		for(int i = 1; i < cup.getAmountOfDices(); i++){
			if(cup.getSumOfDice(i-1) == cup.getSumOfDice(i)){
				sameHit = true;
			}
			else{
				sameHit = false;
			}
		}
		
		//if same hit isnt the same, change turn
		if(sameHit != true){
			turn++;
			if(turn > players.size()){
				turn = 1;
			}
		}
		
		//sets positions, and if its over fields, start from 0
		player.setPosition(player.getPosition()+cup.getDiceSum());
		if(player.getPosition() >= gameBoard.getGameBoard().length){
			player.setPosition(player.getPosition()-gameBoard.getGameBoard().length);
		}
		
		//a question will be needed from user.
		if(gameBoard.askQuestion(player.getPosition())){
			return false;
		}
		else{
			//Do lands on field, and check if user can pay.
			if(!gameBoard.getGameBoardIndex(player.getPosition()).landOn(player, this)){
				//remove ownership from player
				gameBoard.removeOwnerShip(player);
				players.remove(player);
			}
			return true;
		}
	}
	
	//Checks win conditions and returns String based on result(won is set true if game is won)
	public String checkWinningConditions(){
		if(players.size() == 1){
			won = true;
			return players.get(0).getName()+TextStrings.has_won_game+players.get(0).getBalance()+".";
		}
		else if(players.size() < 0){
			won = true;
			return TextStrings.nobody_won;
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
		return null;
	}
	
	public int getAmountOfPlayer(){
		return players.size();
	}
	
	public String getFieldText(int index){
		return gameBoard.getGameBoardIndex(index).fieldText();
	}
	
	public GameBoard getGameBoard(){
		return gameBoard;
	}
	
	public void removePlayer(Player player){
		players.remove(player);
	}
	
	public void setTurn(int turn){
		this.turn = turn;
	}
}
