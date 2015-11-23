package fields;

/**
 * Date: 23/11/2015
 *
 * Project: 25_CDIO3
 *
 * File: LaborCamp.java
 *
 * Created by: Morten Jørvad
 *
 * Github: https://github.com/Mortenbaws
 *
 * Email: morten2094@gmail.com
 */

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

}
