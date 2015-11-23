package fields;

import logic.Game;
import entities.Player;

/**
 * Date: 23/11/2015
 *
 * Project: 25_CDIO3
 *
 * File: Ownable.java
 *
 * Created by: Morten Jørvad
 *
 * Github: https://github.com/Mortenbaws
 *
 * Email: morten2094@gmail.com
 */

public class Ownable extends Field{

	protected int price;
	protected Player owner;
	
	public Ownable(String name, int price) {
		super(name);
		this.price = price;
	}
	
	public boolean isTaken(){
		if(owner == null){
			return false;
		}
		return true;
	}
	
	public void setOwner(Player player){
		this.owner = player;
	}
	
	public int getPrice(){
		return price;
	}
	
	public int getRent(){
		return 0;
	}

	@Override
	public boolean landOn(Player player, Game game) {
		return false;
	}

	@Override
	public String fieldText() {
		if(owner == null){
			return "Would you like to buy "+name+" for "+price+"?";
		}
		else{
			return name+" belongs to "+owner.getName()+".";
		}
	}

	@Override
	public String toString() {
		return super.toString()+" Ownable [price=" + price + ", owner=" + owner + "]";
	}
	
	public Player getOwner(){
		return owner;
	}
	
}
