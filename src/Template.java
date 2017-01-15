import java.io.File;
import java.util.Scanner;

/**
 * Kenny Tang 2017.
 */
public class Template {
    public static void main(String[] args) throws Exception {
        //Scanner input = new Scanner(new File(".dat"));
        //doIt(input);
    }

    public static void doIt(Scanner input) {
        //Do your code here
    }

    private static String[][] parseInput2DString(Scanner input, int lines, String spliter) {
        String[][] parsed = new String[lines][];
        for (int i = 0; i < lines; i++) {
            String[] inString = input.nextLine().split(spliter);
            parsed[i] = new String[inString.length];
            for (int j = 0; j < inString.length; j++) {
                parsed[i][j] = inString[j];
            }
        }
        return parsed;
    }

    private static int[][] parseInput2DInt(Scanner input, int lines, String spliter) {
        int[][] parsed = new int[lines][];
        for (int i = 0; i < lines; i++) {
            String[] inString = input.nextLine().split(spliter);
            parsed[i] = new int[inString.length];
            for (int j = 0; j < inString.length; j++) {
                parsed[i][j] = Integer.parseInt(inString[j]);
            }
        }
        return parsed;
    }
}
