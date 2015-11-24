package fields;

import language.TextStrings;
import logic.Game;
import entities.Player;

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
			return TextStrings.like_to_buy+name+" "+TextStrings.word_for+" "+price+"?";
		}
		else{
			return name+" belongs to "+owner.getName()+" "+TextStrings.have_to_pay+" "+getRent()+".";
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
