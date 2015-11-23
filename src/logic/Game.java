package logic;

import java.util.ArrayList;

import boards.GameBoard;
import entities.*;
import fields.Field;

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
		boolean sameHit = false;
		for(int i = 1; i < cup.getAmountOfDices(); i++){
			if(cup.getSumOfDice(i-1) == cup.getSumOfDice(i)){
				sameHit = true;
			}
			else{
				sameHit = false;
			}
		}
		if(sameHit != true){
			turn++;
			if(turn > players.size()){
				turn = 0;
			}
		}
		player.setPosition(player.getPosition()+cup.getDiceSum());
		if(player.getPosition() >= gameBoard.getGameBoard().length){
			player.setPosition(player.getPosition()-gameBoard.getGameBoard().length);
		}
		
		if(gameBoard.askQuestion(player.getPosition())){
			//ask question before...
			return false;
		}
		else{
			gameBoard.getGameBoardIndex(player.getPosition()).landOn(player);
			return true;
		}
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
}
