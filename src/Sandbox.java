import java.io.File;
import java.util.Scanner;

/**
 * Kenny Tang 2017.
 */
class Sandbox {

	public static void main(String[] args) throws Exception {
		Scanner input = new Scanner(new File("sandbox.dat"));
		doIt(input);
	}

	//Do your code here
	private static void doIt(Scanner input) {
		//# of sandboxes
		int limit = Integer.parseInt(input.nextLine());
		for (int i = 0; i < limit; i++) {
			//Getting dimensions
			int l = input.nextInt();
			int w = input.nextInt();
			double d = input.nextInt() / 12.0;
			//Calculate volume (ceiling), depth is in inches so divide by 12
			int vol = (int)Math.ceil(l * w * d);
			//# of bags necessary, 7 -> 4
			System.out.println(vol / 2 + vol % 2);
		}
	}
}