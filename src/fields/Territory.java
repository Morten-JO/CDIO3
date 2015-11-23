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
			return player.adjustPoints(-price);
		}
		else if(!owner.equals(player)){
			if(!player.adjustPoints(-rent)){
				owner.adjustPoints(player.getBalance());
				return false;
			}
			return owner.adjustPoints(rent);
		}
		return true;
	}

	@Override
	public String toString() {
		return super.toString()+ " Territory [rent=" + rent + "]";
	}
}
