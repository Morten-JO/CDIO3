package fields;

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
	public void landOn(Player player) {
		if(!owner.equals(player)){
			System.out.println("dice land not implemented yet..");
		}
		else if(owner == null){
			owner = player;
			player.adjustPoints(-price);
		}
		
	}

	@Override
	public String toString() {
		return super.toString()+" LaborCamp [baseRent=" + baseRent + "]";
	}
}
