import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Kenny Tang 2017.
 */
public class Cards {
	public static void main(String[] args) throws Exception {
		Scanner input = new Scanner(new File("cards.dat"));
		doIt(input);
	}

	public static void doIt(Scanner input) {
		int limit = input.nextInt();
		input.nextLine();
		for (int i = 0; i < limit; i++) {
			int cards = input.nextInt();
			int goal = input.nextInt();
			input.nextLine();
			String[] handa = input.nextLine().split(" ");
			ArrayList<String> handA = new ArrayList<>();
			for (String card : handa)
				handA.add(card);
			String[] handb = input.nextLine().split(" ");
			ArrayList<String> handB = new ArrayList<>();
			for (String card : handb)
				handB.add(card);

			ArrayList<String> aceHandA = null;
			ArrayList<String> aceHandB = null;
			if (handA.contains("A"))
				aceHandA = (ArrayList<String>) handA.clone();
			if (handB.contains("A"))
				aceHandB = (ArrayList<String>) handB.clone();

			int cardsA = 0;
			int leftover = goal - maxVal(handA);
			int oldVal = goal;
			while (handA.size() > 0) {
				if (leftover < 0)
					leftover = oldVal;
				else {
					cardsA++;
					oldVal = leftover;
				}
				leftover -= maxVal(handA);
			}
			int cardsAceA = 0;
			if (aceHandA != null) {
				int leftoverAce = goal - maxVal2(aceHandA);
				int oldValA = goal;
				while (aceHandA.size() > 0) {
					if (leftoverAce < 0)
						leftoverAce = oldValA;
					else {
						cardsAceA++;
						oldValA = leftover;
					}
					leftoverAce -= maxVal2(aceHandA);
				}
			}
			int cardsAceB = 0;
			if (aceHandB != null) {
				int leftoverAce = goal - maxVal2(aceHandB);
				int oldValA = goal;
				while (aceHandA.size() > 0) {
					if (leftoverAce < 0)
						leftoverAce = oldValA;
					else {
						cardsAceB++;
						oldValA = leftoverAce;
					}
					leftoverAce -= maxVal2(aceHandB);
				}
			}
			int cardsB = 0;
			leftover = goal - maxVal(handB);
			oldVal = goal;
			while (handB.size() > 0) {
				if (leftover < 0)
					leftover = oldVal;
				else {
					cardsB++;
					oldVal = leftover;
				}
				leftover -= maxVal(handB);
			}
			if (cardsA >= cardsAceA)
				cardsA = cardsAceA;
			if (cardsB >= cardsAceB)
				cardsB = cardsAceB;
			if (cardsA < cardsB)
				System.out.println("Alex Wins!");
			else if (cardsB < cardsA)
				System.out.println("Ben Wins!");
			else
				System.out.println("Tie Game!");

			System.out.println(cardsA + " " + cardsAceA + " " + cardsB + " " + cardsAceB);
		}
	}

	private static int getVal(String s) {
		if (s.equals("A"))
			return 1;
		try {
			return Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return 10;
		}
	}

	private static int maxVal(ArrayList<String> x) {
		int curval = getVal(x.get(0));
		int maxIndex = 0;
		for (int i = 1; i < x.size(); i++) {
			int val = getVal(x.get(i));
			if (val > curval) {
				curval = val;
				maxIndex = i;
			}
		}
		x.remove(maxIndex);
		return curval;
	}

	private static int getVal2(String s) {
		if (s.equals("A"))
			return 11;
		try {
			return Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return 10;
		}
	}

	private static int maxVal2(ArrayList<String> x) {
		int curval = getVal2(x.get(0));
		int maxIndex = 0;
		for (int i = 1; i < x.size(); i++) {
			int val = getVal2(x.get(i));
			if (val > curval) {
				curval = val;
				maxIndex = i;
			}
		}
		x.remove(maxIndex);
		return curval;
	}
}