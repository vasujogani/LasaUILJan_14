import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Kenny Tang 2017.
 */
public class NewTemplate {

    private NewTemplate() { }
    private static Scanner input;
    private static Stream<String> inputStream;

    public static void main(String[] args) throws Exception {
        input = new Scanner(new File(NewTemplate.class.getName().toLowerCase() + ".dat"));
        inputStream = new BufferedReader(new FileReader(new File("dryrun.dat"))).lines();
        doIt();
    }

    //Do your code here
    public static void doIt() {

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

    //Parse the input into a 2d array of int specifying # lines and the regex to split
    private static int[][] parseInput2DInt(int lines, String spliter) {
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

    //Check if a 2d array contains a value o
    private static <E> boolean contains2D(E[][] array, E o){
        for (E[] row : array)
            for (E val : row)
                if(val.equals(o))
                    return true;
        return false;
    }

    //Returns next line as int
    private static int nextIntLine(){
        return Integer.parseInt(input.nextLine());
    }
}
