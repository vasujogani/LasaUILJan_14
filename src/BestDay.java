import java.io.File;
import java.util.Scanner;

/**
 * Kenny Tang 2017.
 */
public class BestDay {

	private static Scanner input;

	public static void main(String[] args) throws Exception {
		input = new Scanner(new File("bestday.dat"));
		doIt();
	}

	//Do your code here
	public static void doIt() {
		//# of weeks to check
		int limit = nextIntLine();
		for (int i = 0; i < limit; i++) {
			//Each individual sale
			String[] sales = input.nextLine().split(" ");
			//Best day and the amt of that day
			int bestDay = -1;
			int bestAmt = 0;
			//Loop through each sale to find the best one
			for (int j = 0; j < sales.length; j++) {
				int amount = Integer.parseInt(sales[j]);
				if (bestAmt < amount) {
					bestAmt = amount;
					//Weeks start at day 1
					bestDay = j + 1;
				}
			}
			//Print what day bestDay corresponds to, 1 is sunday 7 is saturday
			switch (bestDay) {
				case 1:
					System.out.println("SUNDAY");
					break;
				case 2:
					System.out.println("MONDAY");
					break;
				case 3:
					System.out.println("TUESDAY");
					break;
				case 4:
					System.out.println("WEDNESDAY");
					break;
				case 5:
					System.out.println("THURSDAY");
					break;
				case 6:
					System.out.println("FRIDAY");
					break;
				case 7:
					System.out.println("SATURDAY");
					break;

			}
		}
	}

	//Returns next line as int
	private static int nextIntLine() {
		return Integer.parseInt(input.nextLine());
	}
}