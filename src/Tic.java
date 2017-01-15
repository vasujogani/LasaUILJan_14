import java.io.File;
import java.util.Scanner;

/**
 * Kenny Tang 2017.
 */
public class Tic {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(new File("tic.dat"));
        doIt(input);
    }

    public static void doIt(Scanner input) {
        int lim1 = input.nextInt();
        input.nextLine();
        for (int z = 0; z < lim1; z++) {
            boolean xTurn = true;
            String[][] map = new String[3][3];
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    map[i][j] = Character.toString((char)('A' + (i + 1) * (j + 1) + j));
                }
            }
            int moves = input.nextInt();
            input.nextLine();
            for (int j = 0; j < moves; j++) {
                String[] move = input.nextLine().split(" ");
                int row = Integer.parseInt(move[0]);
                int col = Integer.parseInt(move[1]);
                map[row][col] = xTurn ? "X" : "O";
                xTurn = !xTurn;
            }
            String winner = checkWin(map);
            emptyNon(map);
            for (int k = 0; k < map.length; k++) {
                for (int g = 0; g < map[k].length; g++) {
                    System.out.print(map[k][g] + (g != 2 ? "|" : ""));
                }
                System.out.print(k != 2 ? "\n-----\n" : "\n");
            }
            boolean filled = isFilled(map);
            if(filled && winner.equals(""))
                System.out.println("Tie Game!");
            else if(!winner.equals(""))
                System.out.println(winner + " wins!");
            else if(!filled && winner.equals(""))
                System.out.println("Incomplete");
            System.out.println();
        }
    }

    private static String checkWin(String[][] map) {
        if(map[0][0].equals(map[0][1]) && map[0][1].equals(map[0][2]))
            return map[0][0];
        if(map[1][0].equals(map[1][1]) && map[1][1].equals(map[1][2]))
            return map[1][0];
        if(map[2][0].equals(map[2][1]) && map[2][1].equals(map[2][2]))
            return map[2][0];
        if(map[0][0].equals(map[1][0]) && map[1][0].equals(map[2][0]))
            return map[0][0];
        if(map[0][1].equals(map[1][1]) && map[1][1].equals(map[2][1]))
            return map[0][1];
        if(map[0][2].equals(map[1][2]) && map[1][2].equals(map[2][2]))
            return map[0][2];
        if(map[0][0].equals(map[1][1]) && map[1][1].equals(map[2][2]))
            return map[0][0];
        if(map[0][2].equals(map[1][1]) && map[1][1].equals(map[2][0]))
            return map[0][2];
        return "";
    }

    private static boolean isFilled(String[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if(!map[i][j].equals("X") && !map[i][j].equals("O"))
                    return false;
            }
        }
        return true;
    }

    private static void emptyNon(String[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if(!map[i][j].equals("X") && !map[i][j].equals("O"))
                    map[i][j] = " ";
            }
        }
    }
}
