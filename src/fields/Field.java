package fields;

import logic.Game;
import entities.Player;

public abstract class Field {

	protected String name = "";
	
	public Field(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public abstract boolean landOn(Player player, Game game);

	@Override
	public String toString() {
		return "Field [name=" + name + "]";
	}
	
	public abstract String fieldText();
}