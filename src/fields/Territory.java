package fields;

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
	public void landOn(Player player){
		
	}

}
