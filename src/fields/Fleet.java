package fields;

import language.TextStrings;
import logic.Game;
import entities.Player;

public class Fleet extends Ownable{

	public static int[] RENTS = {500, 1000, 2000, 4000};
	
	public Fleet(String name, int price) {
		super(name, price);
	}
	
	@Override
	public int getRent(){
		if(owner != null){
			return 500;
		}
		return 0;
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
			if(!player.adjustPoints(-RENTS[game.getGameBoard().getOwnerShipOfFleets(owner)-1])){
				owner.adjustPoints(player.getBalance());
				return false;
			}
			return owner.adjustPoints(RENTS[game.getGameBoard().getOwnerShipOfFleets(owner)-1]);
		}
		return true;
	}

	@Override
	public String toString() {
		return super.toString()+" Fleet []";
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
			return name+" belongs to "+owner.getName()+" "+TextStrings.have_to_pay+" "+RENTS[game.getGameBoard().getOwnerShipOfFleets(owner)-1]+".";
		}
	}
}