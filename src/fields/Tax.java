package fields;

import language.TextStrings;
import logic.Game;
import entities.Player;

public class Tax extends Field{

	protected int taxAmount;
	private static int taxRate = -1;
	
	public Tax(String name, int taxAmount) {
		super(name);
		this.taxAmount = taxAmount;
	}
	
	public int getTaxAmount(){
		return taxAmount;
	}

	@Override
	public boolean landOn(Player player, Game game) {
		return player.adjustPoints(-taxAmount);
	}

	@Override
	public String fieldText() {
		return TextStrings.you_have_to_pay+taxAmount+" "+TextStrings.for_landing_on+" "+name+".";
	}

	@Override
	public String toString() {
		return super.toString()+" Tax [taxAmount=" + taxAmount + "]";
	}
}
