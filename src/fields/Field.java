package fields;

import entities.Player;

public abstract class Field {

	protected String name = "";
	
	public Field(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public abstract void landOn(Player player);

	@Override
	public String toString() {
		return "Field [name=" + name + "]";
	}
	
	public abstract String fieldText();
}
