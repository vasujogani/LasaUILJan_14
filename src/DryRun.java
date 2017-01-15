import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

/**
 * Kenny Tang 2017.
 */
public class DryRun {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(new File("dryrun.dat"));
        doIt(input);
    }

    public static void doIt(Scanner input) {
        input.nextLine();
        while(input.hasNext())
            System.out.println("Hello " + input.nextLine());
    }
}
