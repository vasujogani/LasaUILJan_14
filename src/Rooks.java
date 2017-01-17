import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Kenny Tang 2017.
 */
class Rooks {

	private static Scanner input;
	private static Stream<String> inputStream;

	public static void main(String[] args) throws Exception {
		input = new Scanner(new File(Rooks.class.getName().toLowerCase() + ".dat"));
		inputStream = new BufferedReader(new FileReader(new File(Rooks.class.getName().toLowerCase() + ".dat"))).lines();
		doIt();
	}

	//Do your code here
	private static void doIt() {
		//Stream Solution
		inputStream.skip(1).mapToInt(Integer::parseInt).forEach(Rooks::fact);

		//Scanner Solution
		int lim = nextIntLine();
		for (int i = 0; i < lim; i++) {
			fact(nextIntLine());
		}
	}

	//Returns the factorial of a num
	private static void fact(int num) {
		//Factorials can be big so we want to use a BigInteger
		BigInteger fact = BigInteger.valueOf(1);
		for (int i = 2; i <= num; i++) {
			fact = fact.multiply(BigInteger.valueOf(i));
		}
		System.out.println(fact);
	}

	//Returns next line as int
	private static int nextIntLine() {
		return Integer.parseInt(input.nextLine());
	}
}