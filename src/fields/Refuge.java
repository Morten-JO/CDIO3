package fields;

import language.TextStrings;
import logic.Game;
import entities.Player;

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
	public String fieldText(Player player, Game game) {
		return TextStrings.you_gain+" "+bonus+".";
	}

	@Override
	public String toString() {
		return super.toString()+" Refuge [bonus=" + bonus + "]";
	}
}