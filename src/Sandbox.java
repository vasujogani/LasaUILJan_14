import java.io.File;
import java.util.Scanner;

/**
 * Kenny Tang 2017.
 */
public class Sandbox {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(new File("sandbox.dat"));
        doIt(input);
    }

    public static void doIt(Scanner input) {
        int limit = input.nextInt();
        input.nextLine();
        for (int i = 0; i < limit; i++) {
            String[] dim = input.nextLine().split(" ");
            double vol = Integer.parseInt(dim[0]) * Integer.parseInt(dim[1]) * (Integer.parseInt(dim[2]) / 12.0);
            int voll = (int)Math.ceil(vol);
            System.out.println(voll / 2 + voll % 2);

        }
    }
}
