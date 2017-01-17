import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Cards {
	public static void main(String[] args) throws Exception{
		Scanner input = new Scanner(new File("cards.dat")); //initial pulling data from file
		doIt(input);
	}
	public static void doIt(Scanner input){
		int cases = input.nextInt(); //number of test cases 
		for(int i = 0; i < cases; i++){
			int handSize = input.nextInt(); //number of cards in each hand, variable not used
			int goal = input.nextInt(); //sum goal for cards in each hand (values must sum to exactly the goal)
			input.nextLine(); //due to the skip glitch in scanner, this is used to allow the scanner to switch to nextLine
			//get each player's hand and store in an arraylist
			String[] a = input.nextLine().split(" "); 
			ArrayList<String> alex = new ArrayList();
			for(String card : a)
				alex.add(card);
			String[] b = input.nextLine().split(" ");
			ArrayList<String> ben = new ArrayList();
			for(String card : b)
				ben.add(card);
			//use the method to find the least amount of cards needed to reach the goal for each player
			int cardsA = findLeastCards(alex, goal);
			int cardsB = findLeastCards(ben, goal);
			//check and see who has the least cards, and therefore is the winner (or if there's a tie)
			if(cardsA < cardsB)
				System.out.println("Alex Wins!");
			else if(cardsA > cardsB)
				System.out.println("Ben Wins!");
			else
				System.out.println("Tie Game!");
		}
	}
	//Returns the least number of cards in the hand needed to reach the goal as an integer
	/* Method: Find the max value in the hand and subtract it from the goal and store this in a variable. If the difference is 
	 * negative, then reset the leftover variable back to the previous leftover variable and find the next max value. Repeat this
	 * until the hand has no more cards in it. If the hand has an ace in it, then find the least number of cards when the ace has
	 * has a value of 1 and 11, then see which ace value yields a lower number of cards to reach the goal. Return the least number
	 * of cards to reach the goal.
	 */
	public static int findLeastCards(ArrayList<String> hand, int goal){
		int cards = 0; //original value for least number of cards needed, assuming the hand has no aces
		int aceCard = Integer.MAX_VALUE; //this value is changed if the hand has an ace in it
		int leftover = goal; //leftover variable
		int oldVal = leftover; //reset to old value for leftover variable
		//do this only if the hand has an ace; this checks the least number of cards with ace value 11
		if(hand.contains("A")){
			ArrayList<String> dup = (ArrayList<String>)hand.clone(); //create a copy of the hand to use in this if only
			aceCard = 0;
			while(dup.size() > 0){ //continue to subtract the max value as long as there are still cards in the hand
				leftover -= maxVal2(dup);
				if(leftover < 0)
					leftover = oldVal; //if the difference between leftover and the next max is negative, reset to the previous value
				else{
					aceCard++; //if difference is positive, add one to the number of cards
					oldVal = leftover; //set the previous value of leftover to its current value
				}
			}
		}
		if(leftover != 0) //if the leftover is not zero, then this means no combination of cards sum exactly to the goal
			aceCard = Integer.MAX_VALUE; //set least number of cards with ace value 11 to max value to show no possible combination
		leftover = goal; //reset the leftover value
		//this finds least number cards to meet goal if the hand has no ace, or if it does, with an ace value of 1
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
		return cards < aceCard ? cards : aceCard; //check and see which ace value combo had the least number of cards required
	}
	/* Returns the maximum value in the hand as an integer.
	 * Method: set a variable to the value in the first index of the hand. Compare the value at the variable index to the other 
	 * values in the list and set the variable to the index of the loop if the value in the list is greater than the value at the
	 * variable index's value. After finding the maximum value, remove the maximum value and return that value. This version of
	 * maxVal() uses getVal(), which returns a value of 1 for an ace card
	 */
	public static int maxVal(ArrayList<String> x){
		int maxIndex = 0; //index of the current max value in the hand
		for(int i = 1; i < x.size(); i++)
			if(x.get(i) > x.get(maxIndex))
				maxIndex = i;
		return x.remove(maxIndex); //remove the value at the maxindex, remove() returns the value removed. return the value removed
	}
	/* Returns the integer value of a String given to it as determined by the rules of this game.
	 * Method: String values "2" to "9" will return its integer value. If the string passed is an ace, "A", this version of 
	 * getVal() returns a value of 1. If the string given will not properly convert a numeric value to an integer value, it is
	 * a string of "10", "J", "Q", or "K", indicating ten, jack, queen, or king. These cards all are determined to have a value
	 * of 10, which will be returned.
	 */
	public static int getVal(String s){
		if(s.equals("A"))
			return 1;
		try{
			return Integer.parseInt(s);
		}catch (NumberFormatException e){ return 10; }
	}
	 
	//Same method as maxVal(), but uses getVal2(), which returns a value of 11 for an ace
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
	//Same method as getVal(), but returns a value of 11 for a string "A" for ace
	public static int getVal2(String s){
		if(s.equals("A"))
			return 11;
		try{
			return Integer.parseInt(s);
		}catch (NumberFormatException e){ return 10; }
	}
}
