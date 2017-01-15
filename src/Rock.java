import java.io.File;
import java.util.Scanner;

/**
 * Kenny Tang 2017.
 */
public class Rock {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(new File("rock.dat"));
        doIt(input);
    }

    public static void doIt(Scanner input) {
        int limit = input.nextInt();
        input.nextLine();
        for (int i = 0; i < limit; i++) {
            String[] moves = input.nextLine().split("");
            String hisMove = "R";
            int wins = 0;
            int losses = 0;
            int ties = 0;
            for(int j = 0; j < moves.length; j++) {
                 String mymove = moves[j];
                 String happen = win(mymove, hisMove);
                 if(happen.equals("W")){
                     wins++;
                     hisMove = opposite(mymove);
                 } else if(happen.equals("T")){
                     ties++;
                 } else {
                     losses++;
                 }
            }
            System.out.println("Wins: " + wins);
            System.out.println("Losses: " + losses);
            System.out.println("Ties: " + ties);
            System.out.println();
        }
    }

    private static String opposite(String move){
        if(move.equals("R"))
            return "P";
        if(move.equals("P"))
            return "S";
        if(move.equals("S"))
            return "R";
        return "";
    }

    private static String win(String mymove, String hismove){
        switch (mymove){
            case "R":
                switch (hismove){
                    case "R":
                        return "T";
                    case "P":
                        return "L";
                    case "S":
                        return "W";
                }
                break;
            case "P":
                switch (hismove){
                    case "R":
                        return "W";
                    case "P":
                        return "T";
                    case "S":
                        return "L";
                }
                break;
            case "S":
                switch (hismove){
                    case "R":
                        return "L";
                    case "P":
                        return "W";
                    case "S":
                        return "T";
                }
        }
        return "";
    }
}
