import java.io.File;
import java.io.FileNotFoundException;
import java. util.*;

public class MemoryNoRecur {
	public static int[][] game;
	
	public static void main(String args[]) throws FileNotFoundException{
		Scanner in = new Scanner (new File(System.getProperty("user.dir") +"/src/memory.dat"));
		
		
		int num_set = in.nextInt();
		
		for(int i = 0; i < num_set; i++){
			int w = in.nextInt();
			int h = in.nextInt();
			int d = in.nextInt();
			
			game = new int[d][w*h];
			for(int x = 0; x < game.length; x++){
				for(int y = 0; y < game[0].length; y+=3){
					String s = in.next();
					game[x][y] = s.charAt(0) - 'A';
					game[x][y+1] = s.charAt(1) - 'A';
					game[x][y+2] = s.charAt(2) - 'A';
					
				}
			}
			int[][] old = game;
			while(check(game, -1)){
				for (int j = 0; j < game[0].length-1; j++){
					for(int k = 0; k<game[0].length; k++){
						if((k!=j) && game[0][j] == game[0][k]){
							for(int l = 0; l < d-1; l++){
								game[l][j] = game[l+1][j];
								game[l][k] = game[l+1][k];
								game[l+1][j] = -1;
								game[l+1][k] = -1;
							}
						}
					}
				}
				if(old==game)break;
			}
			
			if(check(game,-1)) {p("impossible");}
			else{ p("solvable");}
		}
		
	}
	
	static boolean check(int[][] g, int x){
		for(int r = 0; r < g.length; r++){
			for(int c = 0; c < g[0].length; c++){
				if(g[r][c] != x)
					return true;
			}
		}
		return false;
	}
	
	static void p(String s){
		System.out.println(s);
	}
}
