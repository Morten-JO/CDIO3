package entities;

public class Field {

	private String name = "";
	private int points = 0;
	
	public Field(String name, int points){
		this.name = name;
		this.points = points;
	}
	
	public int getPoints(){
		return points;
	}
	
	public String getName(){
		return name;
	}
	
	public boolean landOn(Player player){
		return player.adjustPoints(points);
	}

	@Override
	public String toString() {
		return "Field [name=" + name + ", points=" + points + "]";
	}
}
