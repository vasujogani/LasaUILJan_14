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
		//# of sandboxes
		int limit = Integer.parseInt(input.nextLine());
		for (int i = 0; i < limit; i++) {
			//Array of the dimensions
			String[] dim = input.nextLine().split(" ");
			//Calculate volume (ceiling), depth is in inches so divide by 12
			int vol = (int) Math.ceil(Integer.parseInt(dim[0]) * Integer.parseInt(dim[1]) * (Integer.parseInt(dim[2]) / 12.0));
			//# of bags necessary, 7 -> 4
			System.out.println(vol / 2 + vol % 2);
		}
	}
}