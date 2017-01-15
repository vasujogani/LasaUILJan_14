import java.io.File;
import java.util.Scanner;

/**
 * Kenny Tang 2017.
 */
public class Rock {
	private static Scanner input;

	public static void main(String[] args) throws Exception {
		input = new Scanner(new File("rock.dat"));
		doIt();
	}

	//Do your code here
	public static void doIt() {
		int limit = nextIntLine();
		for (int i = 0; i < limit; i++) {
			//Get each move individually
			String[] moves = input.nextLine().split("");
			//He starts off with rock every time
			String hisMove = "R";
			//Counting number of each
			int wins = 0;
			int losses = 0;
			int ties = 0;
			//Loop through each move
			for (String mymove : moves) {
				switch (win(mymove, hisMove)) {
					case "W":
						wins++;
						//When he losses he switches to the opposite of what you played
						hisMove = opposite(mymove);
						break;
					case "T":
						ties++;
						break;
					case "L":
						losses++;
				}
			}
			//Print each amount
			System.out.printf("Wins: %d\r\nLosses: %d\r\nTies: %d\r\n\r\n", wins, losses, ties);
		}
	}

	//Return the opposite move
	private static String opposite(String move) {
		return move.equals("R") ? "P" : move.equals("P") ? "S" : "R";
	}

	//Return what happened
	private static String win(String mymove, String hismove) {
		if (hismove.equals(opposite(mymove)))
			return "W";
		else if (mymove.equals(opposite(hismove)))
			return "L";
		else
			return "T";
	}

	//Return next line as int
	private static int nextIntLine() {
		return Integer.parseInt(input.nextLine());
	}
}