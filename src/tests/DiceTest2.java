package tests;

import entities.Dice;

public class DiceTest2 {

	public static void main(String[] args) {
		
	//		Test om terning er symetrisk.
		Dice dice = new Dice(6);
		double n=10000;
		int one =0;
		int two =0;
		int tree=0;
		int four=0;
		int five=0;
		int six=0;
		for (int i=0; i<n; i++){
			dice.hitDice();
			if (dice.getSum()==1) one++;
			if (dice.getSum()==2) two++;
			if (dice.getSum()==3) tree++;
			if (dice.getSum()==4) four++;
			if (dice.getSum()==5) five++;
			if (dice.getSum()==6) six++;
		}
		System.out.println("Number of 1's: "+one);
		System.out.println("Number of 2's: "+two);
		System.out.println("Number of 3's: "+tree);
		System.out.println("Number of 4's: "+four);
		System.out.println("Number of 5's: "+five);
		System.out.println("Number of 6's: "+six);
	}

}
