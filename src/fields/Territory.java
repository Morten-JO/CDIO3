package fields;

import logic.Game;
import entities.Player;

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
	public boolean landOn(Player player, Game game){
		if(owner == null){
			this.owner = player;
			return player.adjustPoints(-price);
		}
		else if(!owner.equals(player)){
			if(!player.adjustPoints(-rent)){
				owner.adjustPoints(player.getBalance());
				return false;
			}
			return owner.adjustPoints(rent);
		}
		return true;
	}

	@Override
	public String toString() {
		return super.toString()+ " Territory [rent=" + rent + "]";
	}
}
