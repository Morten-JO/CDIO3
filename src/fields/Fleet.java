package fields;

import logic.Game;
import entities.Player;

/**
 * Date: 23/11/2015
 *
 * Project: 25_CDIO3
 *
 * File: Fleet.java
 *
 * Created by: Morten Jørvad
 *
 * Github: https://github.com/Mortenbaws
 *
 * Email: morten2094@gmail.com
 */

public class Fleet extends Ownable{

	public static int[] RENTS = {500, 1000, 2000, 4000};
	
	public Fleet(String name, int price) {
		super(name, price);
	}
	
	@Override
	public int getRent(){
		if(owner != null){
			//ill ocme back to this...
		}
		return 0;
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
			if(!player.adjustPoints(-RENTS[game.getGameBoard().getOwnerShipOfFleets(owner)-1])){
				System.out.println();
				System.out.println(player.getName()+" Couldnt pay "+RENTS[game.getGameBoard().getOwnerShipOfFleets(owner)-1]);
				System.out.println("Player balance is: "+player.getBalance()+", and rent was: "+RENTS[game.getGameBoard().getOwnerShipOfFleets(owner)-1]);
				System.out.println("owner balance before: "+owner.getBalance());
				owner.adjustPoints(player.getBalance());
				System.out.println("owner balance before: "+owner.getBalance());
				return false;
			}
			System.out.println();
			System.out.println("Player balance is: "+player.getBalance()+", and rent was: "+RENTS[game.getGameBoard().getOwnerShipOfFleets(owner)-1]);
			return owner.adjustPoints(RENTS[game.getGameBoard().getOwnerShipOfFleets(owner)-1]);
		}
		return true;
	}

	@Override
	public String toString() {
		return super.toString()+" Fleet []";
	}
}
