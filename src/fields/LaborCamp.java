package fields;

import logic.Game;
import entities.Player;

/**
 * Date: 23/11/2015
 *
 * Project: 25_CDIO3
 *
 * File: LaborCamp.java
 *
 * Created by: Morten Jørvad
 *
 * Github: https://github.com/Mortenbaws
 *
 * Email: morten2094@gmail.com
 */

public class LaborCamp extends Ownable{

	private int baseRent;
	
	public LaborCamp(String name, int price, int baseRent) {
		super(name, price);
		this.baseRent = baseRent;
	}
	
	@Override
	public int getRent(){
		return baseRent;
	}
	
	@Override
	public boolean landOn(Player player, Game game) {
		if(owner == null){
			owner = player;
			System.out.println();
			System.out.println(player.getName()+" will buy: "+name+" for: "+price);
			return player.adjustPoints(-price);
		}
		else if(!owner.equals(player)){
			if(!player.adjustPoints(-(game.getCup().getDiceSum()*game.getGameBoard().getOwnerShipOfLaborCamps(player)))){
				System.out.println();
				System.out.println(player.getName()+" Couldnt pay "+game.getCup().getDiceSum()*game.getGameBoard().getOwnerShipOfLaborCamps(player));
				System.out.println("Player balance is: "+player.getBalance()+", and rent was: "+game.getCup().getDiceSum()*game.getGameBoard().getOwnerShipOfLaborCamps(player));
				System.out.println("owner balance before: "+owner.getBalance());
				owner.adjustPoints(player.getBalance());
				System.out.println("owner balance before: "+owner.getBalance());
				return false;
			}
			System.out.println();
			System.out.println("Player balance is: "+player.getBalance()+", and rent was: "+game.getCup().getDiceSum()*game.getGameBoard().getOwnerShipOfLaborCamps(player));
			return owner.adjustPoints(game.getCup().getDiceSum()*game.getGameBoard().getOwnerShipOfLaborCamps(player));
		}
		return true;
	}

	@Override
	public String toString() {
		return super.toString()+" LaborCamp [baseRent=" + baseRent + "]";
	}
	
}
