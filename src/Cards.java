import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class LASA_8Cards {
	public static void main(String[] args) throws Exception{
		Scanner input = new Scanner(new File("cards.dat"));
		doIt(input);
	}
	public static void doIt(Scanner input){
		int cases = input.nextInt();
		for(int i = 0; i < cases; i++){
			int handSize = input.nextInt();
			int goal = input.nextInt();
			input.nextLine();
			String[] a = input.nextLine().split(" ");
			ArrayList<String> alex = new ArrayList();
			for(String card : a)
				alex.add(card);
			String[] b = input.nextLine().split(" ");
			ArrayList<String> ben = new ArrayList();
			for(String card : b)
				ben.add(card);
			
			int cardsA = findLeastCards(alex, goal);
			int cardsB = findLeastCards(ben, goal);
			
			if(cardsA < cardsB)
				System.out.println("Alex Wins!");
			else if(cardsA > cardsB)
				System.out.println("Ben Wins!");
			else
				System.out.println("Tie Game!");
		}
	}
	public static int findLeastCards(ArrayList<String> hand, int goal){
		int cards = 0;
		int aceCard = Integer.MAX_VALUE;
		int leftover = goal;
		int oldVal = leftover;
		if(hand.contains("A")){
			ArrayList<String> dup = (ArrayList<String>)hand.clone();
			aceCard = 0;
			while(dup.size() > 0){
				leftover -= maxVal2(dup);
				if(leftover < 0)
					leftover = oldVal;
				else{
					aceCard++;
					oldVal = leftover;
				}
			}
		}
		if(leftover != 0)
			aceCard = Integer.MAX_VALUE;
		leftover = goal;
		while(hand.size() > 0){
			leftover -= maxVal(hand);
			if(leftover < 0)
				leftover = oldVal;
			else{
				cards++;
				oldVal = leftover;
			}
		}
		if(leftover != 0)
			cards = Integer.MAX_VALUE;
		return cards < aceCard ? cards : aceCard;
	}
	public static int maxVal(ArrayList<String> x){
		int curVal = getVal(x.get(0));
		int maxIndex = 0;
		for(int i = 1; i < x.size(); i++){
			int val = getVal(x.get(i));
			if(val > curVal){
				curVal = val;
				maxIndex = i;
			}
		}
		x.remove(maxIndex);
		return curVal;
	}
	public static int getVal(String s){
		if(s.equals("A"))
			return 1;
		try{
			return Integer.parseInt(s);
		}catch (NumberFormatException e){ return 10; }
	}
	public static int maxVal2(ArrayList<String> x){
		int curVal = getVal2(x.get(0));
		int maxIndex = 0;
		for(int i = 1; i < x.size(); i++){
			int val = getVal2(x.get(i));
			if(val > curVal){
				curVal = val;
				maxIndex = i;
			}
		}
		x.remove(maxIndex);
		return curVal;
	}
	public static int getVal2(String s){
		if(s.equals("A"))
			return 11;
		try{
			return Integer.parseInt(s);
		}catch (NumberFormatException e){ return 10; }
	}
}
