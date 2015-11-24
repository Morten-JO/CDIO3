package fields;

import language.TextStrings;
import logic.Game;
import entities.Player;

public class TaxProcent extends Tax{

	private int taxProcent;
	
	public TaxProcent(String name, int taxAmount, int taxProcent) {
		super(name, taxAmount);
		this.taxProcent = taxProcent;
	}
	
	@Override
	public boolean landOn(Player player, Game game) {
		if(game.askQuestion(TextStrings.you_landed+" "+TextStrings.on+" "+name+", "+TextStrings.how_would_you, String.valueOf(taxAmount), taxProcent+"%")){
			return player.adjustPoints(-taxAmount);
		}
		else{
			int cost = game.getGameBoard().getUserValue(player);
			return player.adjustPoints(-((int)(((cost+player.getBalance())*(0.01*taxProcent)))));
		}
	}
	
	@Override
	public String fieldText() {
		return TextStrings.taxed+".";
	}

}
