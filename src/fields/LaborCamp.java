package fields;

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
			owner = player;
			return player.adjustPoints(-price);
		}
		else if(!owner.equals(player)){
			if(!player.adjustPoints(-(game.getCup().getDiceSum()*100*game.getGameBoard().getOwnerShipOfLaborCamps(player)))){
				owner.adjustPoints(player.getBalance());
				return false;
			}
			return owner.adjustPoints(game.getCup().getDiceSum()*100*game.getGameBoard().getOwnerShipOfLaborCamps(player));
		}
		return true;
	}

	@Override
	public String toString() {
		return super.toString()+" LaborCamp [baseRent=" + baseRent + "]";
	}
	
}
