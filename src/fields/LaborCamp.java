package fields;

import language.TextStrings;
import logic.Game;
import entities.Player;

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
			if(!(player.getBalance() >= price)){
				return true;
			}
			owner = player;
			return player.adjustPoints(-price);
		}
		else if(!owner.equals(player)){
			if(!player.adjustPoints(-(game.getCup().getDiceSum()*100*game.getGameBoard().getOwnerShipOfLaborCamps(owner)))){
				owner.adjustPoints(player.getBalance());
				return false;
			}
			return owner.adjustPoints(game.getCup().getDiceSum()*100*game.getGameBoard().getOwnerShipOfLaborCamps(owner));
		}
		return true;
	}

	@Override
	public String toString() {
		return super.toString()+" LaborCamp [baseRent=" + baseRent + "]";
	}
	
	@Override
	public String fieldText(Player player, Game game) {
		if(owner == null){
			if(player.getBalance() < price){
				return TextStrings.you_cant_afford+" "+name+".";
			}
			return TextStrings.like_to_buy+name+" "+TextStrings.word_for+" "+price+"?";
		}
		else if(owner.equals(player)){
			return TextStrings.you_own;
		}
		else{
			return name+" belongs to "+owner.getName()+" "+TextStrings.have_to_pay+" "+getRent()+" * "+TextStrings.dice+".";
		}
	}	
}