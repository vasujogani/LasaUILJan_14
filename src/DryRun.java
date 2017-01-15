import java.io.*;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Kenny Tang 2017.
 */
public class DryRun {

    private static Stream<String> inputStream;
    private static Scanner input;

    public static void main(String[] args) throws Exception {
        input = new Scanner(new File("dryrun.dat"));
        inputStream = new BufferedReader(new FileReader(new File("dryrun.dat"))).lines();
        doIt();
    }

    public static void doIt() {
        inputStream.skip(1).forEach((String s) -> System.out.println("Hello " + s));
    }
}