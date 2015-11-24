package fields;

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
			//ill ocme back to this...
		}
		return 0;
	}
	
	@Override
	public boolean landOn(Player player, Game game) {
		if(owner == null){
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
}
