import java.io.File;
import java.util.Scanner;

/**
 * Kenny Tang 2017.
 */
class Tic {

	private static Scanner input;

	public static void main(String[] args) throws Exception {
		input = new Scanner(new File("tic.dat"));
		doIt();
	}

	private static void doIt() {
		//# of games in input
		int lim = nextIntLine();
		for (int z = 0; z < lim; z++) {
			//x goes first
			boolean xTurn = true;
			//Game map
			String[][] map = new String[3][3];
			//Fill map with random chars
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[i].length; j++) {
					map[i][j] = Character.toString((char) ('A' + (i + 1) * (j + 1) + j));
				}
			}
			//# of moves to parse
			int moves = nextIntLine();
			//Play out the game
			for (int j = 0; j < moves; j++) {
				//The given move
				int row = input.nextInt();
				int col = nextIntLine();
				map[row][col] = xTurn ? "X" : "O";
				xTurn = !xTurn;
			}
			//Get the winner or no winner
			String winner = checkWin(map);
			//Empty any random chars
			emptyNon(map);
			//Print out the game using correct formatting (don't print extra | or - for last line)
			for (int k = 0; k < map.length; k++) {
				for (int g = 0; g < map[k].length; g++) {
					System.out.print(map[k][g] + (g != 2 ? "|" : ""));
				}
				System.out.print(k != 2 ? "\n-----\n" : "\n");
			}
			//Check if the board is full
			boolean filled = isFilled(map);
			//Print out the game result
			if (filled && winner.equals("")) //Board is filled and no winner
				System.out.println("Tie Game!");
			else if (!winner.equals("")) //There is a winner
				System.out.println(winner + " wins!");
			else if (!filled && winner.equals("")) //Board is not filled and there is no winner
				System.out.println("Incomplete");
			System.out.println();
		}
	}

	//Check for a win
	private static String checkWin(String[][] map) {
		//Check across, up & down, diagonal
		if (map[0][0].equals(map[0][1]) && map[0][1].equals(map[0][2]))
			return map[0][0];
		if (map[1][0].equals(map[1][1]) && map[1][1].equals(map[1][2]))
			return map[1][0];
		if (map[2][0].equals(map[2][1]) && map[2][1].equals(map[2][2]))
			return map[2][0];
		if (map[0][0].equals(map[1][0]) && map[1][0].equals(map[2][0]))
			return map[0][0];
		if (map[0][1].equals(map[1][1]) && map[1][1].equals(map[2][1]))
			return map[0][1];
		if (map[0][2].equals(map[1][2]) && map[1][2].equals(map[2][2]))
			return map[0][2];
		if (map[0][0].equals(map[1][1]) && map[1][1].equals(map[2][2]))
			return map[0][0];
		if (map[0][2].equals(map[1][1]) && map[1][1].equals(map[2][0]))
			return map[0][2];
		return "";
	}

	//Check if the map is filled
	private static boolean isFilled(String[][] map) {
		for (String[] aMap : map) {
			for (String anAMap : aMap) {
				if (!anAMap.equals("X") && !anAMap.equals("O"))
					return false;
			}
		}
		return true;
	}

	//Replace all random characters with a space
	private static void emptyNon(String[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (!map[i][j].equals("X") && !map[i][j].equals("O"))
					map[i][j] = " ";
			}
		}
	}

	//Return next line as int
	private static int nextIntLine() {
		return Integer.parseInt(input.nextLine());
	}
}