package fields;

import logic.Game;
import entities.Player;

/**
 * Date: 23/11/2015
 *
 * Project: 25_CDIO3
 *
 * File: Territory.java
 *
 * Created by: Morten Jørvad
 *
 * Github: https://github.com/Mortenbaws
 *
 * Email: morten2094@gmail.com
 */

public class Territory extends Ownable{

	private int rent;
	
	public Territory(String name, int price, int rent) {
		super(name, price);
		this.rent = rent;
	}
	
	@Override
	public int getRent(){
		return rent;
	}
	
	@Override
	public boolean landOn(Player player, Game game){
		if(owner == null){
			this.owner = player;
			System.out.println();
			System.out.println(player.getName()+" will buy: "+name+" for: "+price);
			return player.adjustPoints(-price);
		}
		else if(!owner.equals(player)){
			if(!player.adjustPoints(-rent)){
				System.out.println();
				System.out.println(player.getName()+" Couldnt pay "+rent);
				System.out.println("Player balance is: "+player.getBalance()+", and rent was: "+rent);
				System.out.println("owner balance before: "+owner.getBalance());
				owner.adjustPoints(player.getBalance());
				System.out.println("owner balance after: "+owner.getBalance());
				return false;
			}
			System.out.println();
			System.out.println("Player balance is: "+player.getBalance()+", and rent was: "+rent);
			return owner.adjustPoints(rent);
		}
		return true;
	}

	@Override
	public String toString() {
		return super.toString()+ " Territory [rent=" + rent + "]";
	}
}
