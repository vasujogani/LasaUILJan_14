import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Kenny Tang 2017.
 * ~30 min
 */
class Othello {

	private static Scanner input;

	public static void main(String[] args) throws Exception {
		input = new Scanner(new File(Othello.class.getName().toLowerCase() + ".dat"));
		Stream<String> inputStream = new BufferedReader(new FileReader(new File(Othello.class.getName().toLowerCase() + ".dat"))).lines();
		doIt();
	}

	//Do your code here
	private static void doIt() {
		int limit = nextIntLine();
		for (int i = 0; i < limit; i++) {
			//The game map as a 2D Array of strings
			String[][] map = parseInput2DString(8, "");
			//The given move's row and column
			int moveR = input.nextInt();
			int moveC = input.nextInt();
			//The given move's color
			String color = input.nextLine().trim();
			//Check if anything was flipped
			if(checkAdjacent(map, color, moveR, moveC) > 0) {
				//Print out the new game board
				for (String[] aMap : map) {
					for (String anAMap : aMap) {
						System.out.print(anAMap);
					}
					System.out.println();
				}
				System.out.println();
			} else {
				//Nothing was flipped
				System.out.println("Invalid Move");
			}
		}
	}

	//Returns how many directions had a flip
	private static int checkAdjacent(String[][] map, String myColor, int x, int y) {
		//Set the played move
		map[x][y] = myColor;
		//Number of flips so far
		int flips = 0;
		//Loop through all possible directions
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				//Direction of 0, 0 would be meaningless and create a bug
				if(i == 0 && j == 0)
					continue;
				//Direction to check
				Point direction = new Point(i, j);
				//Current position
				Point position = new Point(x, y);
				//If we can flip in this direction
				if(findInLine(map, myColor, position, direction, 0)) {
					//Reset position because it is modified in the findInLine method
					position = new Point(x, y);
					//Flip the pieces
					changeAdjacent(map, myColor, position, direction);
					flips++;
				}
			}
		}
		return flips;
	}

	//"Flips" the pieces in the headed direction to the played color
	private static void changeAdjacent(String[][] map, String myColor, Point position, Point direction) {
		String nextColor;
		try {
			//The next color in the direction we are going
			nextColor = map[position.x + direction.x][position.y + direction.y];
		} catch (ArrayIndexOutOfBoundsException e) {
			//If we reached the edge of the map
			return;
		}
		//If we found the right color
		if(nextColor.equals(myColor))
			return;
		//Keep changing in the same direction
		position.translate(direction.x, direction.y);
		map[position.x][position.y] = myColor;
		changeAdjacent(map, myColor, position, direction);
	}

	//Returns if it is possible to play in a certain direction
	private static boolean findInLine(String[][] map, String myColor, Point position, Point direction, int distance) {
		//The next color in the direction we're going
		String nextColor;
		try {
			nextColor = map[position.x + direction.x][position.y + direction.y];
		} catch (ArrayIndexOutOfBoundsException e) {
			//If we reached the edge of the map
			return false;
		}
		//If we found the right color
		if(distance == 0 && nextColor.equals(myColor))
			return false;
		else if(nextColor.equals(myColor))
			return true;
		else if(nextColor.equals("."))
			return false;
		//Keep checking in the same direction
		position.translate(direction.x, direction.y);
		return findInLine(map, myColor, position, direction, distance + 1);
	}

	//Parse the input into a 2d array of strings specifying # lines and the regex to split
	private static String[][] parseInput2DString(int lines, String spliter) {
		String[][] parsed = new String[lines][];
		for (int i = 0; i < lines; i++) {
			String[] inString = input.nextLine().split(spliter);
			parsed[i] = new String[inString.length];
			System.arraycopy(inString, 0, parsed[i], 0, inString.length);
		}
		return parsed;
	}

	//Returns next line as int
	private static int nextIntLine() {
		return Integer.parseInt(input.nextLine().trim());
	}
}