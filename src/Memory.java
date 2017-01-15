import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Kenny Tang 2017.
 */
public class Memory {
	static int[][][] game;

	public static void main(String[] args) throws Exception {
		Scanner input = new Scanner(new File("memory.dat"));
		doIt(input);
	}

	public static void doIt(Scanner input) {
		int limit = input.nextInt();
		input.nextLine();
		for (int i = 0; i < limit; i++) {
			int w = input.nextInt();
			int h = input.nextInt();
			int d = input.nextInt();
			input.nextLine();
			game = new int[d][w][h];
			for (int j = 0; j < d; j++) {
				for (int k = 0; k < h; k++) {
					String[] chars = input.nextLine().split("");
					for (int l = 0; l < chars.length; l++) {
						game[j][k][l] = chars[l].charAt(0) - 'A';
						System.out.print(game[j][k][l] + " ");
					}
					System.out.println();
				}
			}
			List<Integer> av = pairsAvailable(game[0]);
			while (av.contains(2)) {
				List<Point> p = removePair(game[0], d, av);
				int hh = 0;
				while (hh < d - 1) {
					for (int j = 0; j < p.size(); j++) {
						System.out.println(p.get(j).x + " p " + p.get(j).y);
						game[hh][p.get(j).x][p.get(j).y] = game[hh + 1][p.get(j).x][p.get(j).y];
					}
					hh++;
				}

				av = pairsAvailable(game[0]);
			}
			int extra = 0;
			for (int f = 0; f < w; f++) {
				for (int j = 0; j < h; j++) {
					System.out.print(game[0][f][j] + " ");
					if (game[0][f][j] != -1)
						extra++;
				}
				System.out.println();
			}
			if (extra > 0)
				System.out.println("impossible");
			else
				System.out.println("solvable");

		}
	}

	private static List<Integer> pairsAvailable(int[][] g) {
		List<Integer> l = new ArrayList<>();
		for (int i = 0; i < 60; i++) {
			l.add(0);
		}
		for (int i = 0; i < g.length; i++) {
			for (int j = 0; j < g[i].length; j++) {
				System.out.println(g[i][j]);
				if (g[i][j] < 0)
					continue;
				l.set(g[i][j], l.get(g[i][j]) + 1);
			}
		}
		System.out.println("lis: " + l);
		return l;
	}

	private static List<Point> removePair(int[][] g, int d, List<Integer> l) {
		List<Point> p = new ArrayList<>();
		for (int i = 0; i < l.size(); i++) {
			if (l.get(i) == 2) {
				for (int j = 0; j < g.length; j++) {
					for (int k = 0; k < g[j].length; k++) {
						if (g[j][k] == i) {
							p.add(new Point(j, k));
							game[0][j][k] = -1;
						}
					}
				}
			}
		}
		return p;
	}
}