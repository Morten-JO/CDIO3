package fields;

import logic.Game;
import entities.Player;

/**
 * Date: 23/11/2015
 *
 * Project: 25_CDIO3
 *
 * File: Refuge.java
 *
 * Created by: Morten J�rvad
 *
 * Github: https://github.com/Mortenbaws
 *
 * Email: morten2094@gmail.com
 */

public class Refuge extends Field{

	private int bonus;
	
	public Refuge(String name, int bonus) {
		super(name);
		this.bonus = bonus;
	}
	
	public int getBonus(){
		return bonus;
	}

	@Override
	public boolean landOn(Player player, Game game) {
		return player.adjustPoints(bonus);
	}

	@Override
	public String fieldText() {
		return "You gain "+bonus+".";
	}

	@Override
	public String toString() {
		return super.toString()+" Refuge [bonus=" + bonus + "]";
	}

	

}
