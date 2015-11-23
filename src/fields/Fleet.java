package fields;

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

	public static int RENT_1 = 500;
	public static int RENT_2 = 1000;
	public static int RENT_3 = 2000;
	public static int RENT_4 = 4000;
	
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
	public void landOn(Player player) {
		if(!player.equals(owner)){
			player.adjustPoints(-getRent());
		}
		else if(owner == null){
			owner = player;
			player.adjustPoints(-price);
		}
		
	}

	@Override
	public String toString() {
		return super.toString()+" Fleet []";
	}
}
