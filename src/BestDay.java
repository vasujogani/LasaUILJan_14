import java.io.File;
import java.util.Scanner;

/**
 * Kenny Tang 2017.
 */
public class BestDay {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(new File("bestday.dat"));
        doIt(input);
    }

    public static void doIt(Scanner input) {
        int limit = input.nextInt();
        input.nextLine();
        for (int i = 0; i < limit; i++) {
            String[] sales = input.nextLine().split(" ");
            int bestDay = -1;
            int bestAmt = 0;
            for(int j = 0; j < sales.length; j++) {
                int amount = Integer.parseInt(sales[j]);
                if(bestAmt < amount){
                    bestAmt = amount;
                    bestDay = j + 1;
                }
            }
            switch (bestDay){
                case 1:
                    System.out.println("SUNDAY");
                    break;
                case 2:
                    System.out.println("MONDAY");
                    break;
                case 3:
                    System.out.println("TUESDAY");
                    break;
                case 4:
                    System.out.println("WEDNESDAY");
                    break;
                case 5:
                    System.out.println("THURSDAY");
                    break;
                case 6:
                    System.out.println("FRIDAY");
                    break;
                case 7:
                    System.out.println("SATURDAY");
                    break;

            }
        }
    }
}
