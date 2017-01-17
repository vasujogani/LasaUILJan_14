import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Kenny Tang 2017.
 */
class Memory {

	private static Scanner input;

	public static void main(String[] args) throws Exception {
		input = new Scanner(new File(Memory.class.getName().toLowerCase() + ".dat"));
		Stream<String> inputStream = new BufferedReader(new FileReader(new File(Memory.class.getName().toLowerCase() + ".dat"))).lines();
		doIt();
	}

	//Do your code here
	private static void doIt() {
		int limit = nextIntLine();
		for (int i = 0; i < limit; i++) {
			//Dimensions of the game
			int w = input.nextInt();
			int h = input.nextInt();
			int d = nextIntLine();
			String[][][] map = new String[d][][];
			//Parse the input to a 3d array
			for (int j = 0; j < d; j++) {
				map[j] = parseInput2DString(h, "");
			}
			//Match cards
			matchCards(map, w, h);
			//Check if the game is possible, if it is all cards in the first layer should be gone
			boolean possible = true;
			for (int j = 0; j < map[0].length; j++) {
				for (int k = 0; k < map[0][j].length; k++) {
					if(map[0][j][k].matches("\\w"))
						possible = false;
				}
			}
			System.out.println(possible ? "solvable" : "impossible");
		}
	}

	//Plays out the game
	private static void matchCards(String[][][] map, int w, int h) {
		//If there were cards matched
		boolean matched = false;
		//Item iterator
		for (int j = 0; j < w * h; j++) {
			for (int k = 0; k < w; k++) {
				for (int l = 0; l < h; l++) {
					//Decode the item iterator to row-column
					int row = j / w;
					int col = (j + 1) % w;
					//Skip if we are looking at the item iterator
					if (k == row && l == col)
						continue;
					//If we can match cards
					if(map[0][k][l].equals(map[0][row][col]) && !map[0][k][l].equals("")){
						matched = true;
						map[0][k][l] = "";
						map[0][row][col] = "";
						popUp(map);
					}
				}
			}
		}
		//Keep matching since it will pop up cards
		if(matched)
			matchCards(map, w, h);
	}

	//Pops up cards if the above layer has no card and the layer below has cards
	private static void popUp(String[][][] map) {
		//If we popped up something
		boolean popped = false;
		for (int i = 0; i < map.length - 1; i++) {
			for (int j = 0; j < map[i].length; j++) {
				for (int k = 0; k < map[i][j].length; k++) {
					//There is a card that can be popped up
					if(map[i][j][k].equals("")) {
						popped = true;
						//Pops up the card
						map[i][j][k] = map[i + 1][j][k];
						map[i + 1][j][k] = "";
						//If we popped up nothing then change it back
						if(map[i][j][k].equals(""))
							popped = false;
					}
				}
			}
		}
		if(popped)
			popUp(map);
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