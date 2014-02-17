package GameOfLife;

import java.util.Random;

public class Patrones {

	private static int maxFil = 0;
	private static int maxCol = 0;
	
	public static int getMaxFil() {
		return maxFil;
	}
	
	public static int getMaxCol() {
		return maxCol;
	}
	
	public static void setMaxFil(int fil) {
		maxFil = fil;
	}
	
	public static void setMaxCol(int col) {
		maxCol = col;
	}
	
	public static boolean[][] gosperGliderGun() {
		boolean patron[][] = new boolean[maxFil][maxCol];
		
		for (int f=0; f<maxFil; f++) {
			for (int c=0; c<maxCol; c++) {
				patron[f][c] = false;
			}
		}
		
		patron[13][9]  = true;
		patron[14][9]  = true;
		patron[13][10] = true;
		patron[14][10] = true;
		patron[13][19] = true;
		patron[14][19] = true;
		patron[15][19] = true;
		patron[12][20] = true;
		patron[16][20] = true;
		patron[11][21] = true;
		patron[11][22] = true;
		patron[17][21] = true;
		patron[17][22] = true;
		patron[14][23] = true;
		patron[12][24] = true;
		patron[16][24] = true;
		patron[13][25] = true;
		patron[14][25] = true;
		patron[15][25] = true;
		patron[14][26] = true;
		patron[13][29] = true;
		patron[12][29] = true;
		patron[11][29] = true;
		patron[13][30] = true;
		patron[12][30] = true;
		patron[11][30] = true;
		patron[10][31] = true;
		patron[14][31] = true;
		patron[10][33] = true;
		patron[9][33]  = true;
		patron[14][33] = true;
		patron[15][33] = true;
		patron[11][43] = true;
		patron[11][44] = true;
		patron[12][43] = true;
		patron[12][44] = true;
		
		return patron;
	}
	
	
	public static boolean[][] reptor() {
		boolean patron[][] = new boolean[maxFil][maxCol];
		
		for (int f=0; f<maxFil; f++) {
			for (int c=0; c<maxCol; c++) {
				patron[f][c] = false;
			}
		}
		
		patron[23][maxCol-29] = true;
		patron[24][maxCol-29] = true;
		patron[25][maxCol-29] = true;
		patron[23][maxCol-28] = true;
		patron[24][maxCol-28] = true;
		patron[25][maxCol-28] = true;
		
		patron[30][maxCol-29] = true;
		patron[31][maxCol-29] = true;
		patron[32][maxCol-29] = true;
		patron[30][maxCol-28] = true;
		patron[31][maxCol-28] = true;
		patron[32][maxCol-28] = true;
		
		patron[22][maxCol-24] = true;
		patron[23][maxCol-24] = true;
		patron[24][maxCol-24] = true;
		patron[25][maxCol-24] = true;
		patron[26][maxCol-24] = true;
		
		patron[29][maxCol-24] = true;
		patron[30][maxCol-24] = true;
		patron[31][maxCol-24] = true;
		patron[32][maxCol-24] = true;
		patron[33][maxCol-24] = true;
		
		patron[26][maxCol-23] = true;
		patron[29][maxCol-23] = true;
		patron[27][maxCol-22] = true;
		patron[28][maxCol-22] = true;
		
		patron[24][maxCol-22] = true;
		patron[24][maxCol-21] = true;
		patron[23][maxCol-21] = true;
		patron[22][maxCol-20] = true;
		patron[21][maxCol-19] = true;
		
		patron[31][maxCol-22] = true;
		patron[31][maxCol-21] = true;
		patron[32][maxCol-21] = true;
		patron[33][maxCol-20] = true;
		patron[34][maxCol-19] = true;
		
		patron[19][maxCol-17] = true;
		patron[20][maxCol-17] = true;
		patron[21][maxCol-17] = true;
		patron[20][maxCol-16] = true;
		patron[21][maxCol-16] = true;
		patron[22][maxCol-16] = true;
		
		patron[34][maxCol-17] = true;
		patron[35][maxCol-17] = true;
		patron[36][maxCol-17] = true;
		patron[33][maxCol-16] = true;
		patron[34][maxCol-16] = true;
		patron[35][maxCol-16] = true;
		
		patron[22][maxCol-15] = true;
		patron[22][maxCol-14] = true;
		patron[23][maxCol-14] = true;
		patron[24][maxCol-14] = true;
		
		patron[33][maxCol-15] = true;
		patron[33][maxCol-14] = true;
		patron[32][maxCol-14] = true;
		patron[31][maxCol-14] = true;
		
		patron[25][maxCol-13] = true;
		patron[23][maxCol-12] = true;
		patron[22][maxCol-11] = true;
		patron[21][maxCol-10] = true;
		
		patron[30][maxCol-13] = true;
		patron[32][maxCol-12] = true;
		patron[33][maxCol-11] = true;
		patron[34][maxCol-10] = true;
		
		patron[25][maxCol-11] = true;
		patron[24][maxCol-10] = true;
		
		patron[30][maxCol-11] = true;
		patron[31][maxCol-10] = true;
		
		patron[21][maxCol-8] = true;
		patron[24][maxCol-8] = true;
		
		patron[34][maxCol-8] = true;
		patron[31][maxCol-8] = true;
		
		patron[26][maxCol-8] = true;
		patron[29][maxCol-8] = true;
		patron[27][maxCol-9] = true;
		patron[28][maxCol-9] = true;
		patron[27][maxCol-7] = true;
		patron[28][maxCol-7] = true;
		
		patron[24][maxCol-6] = true;
		patron[23][maxCol-6] = true;
		patron[23][maxCol-5] = true;
		patron[23][maxCol-4] = true;
		patron[22][maxCol-6] = true;
		
		patron[31][maxCol-6] = true;
		patron[32][maxCol-6] = true;
		patron[32][maxCol-5] = true;
		patron[32][maxCol-4] = true;
		patron[33][maxCol-6] = true;
		
		patron[21][maxCol-3] = true;
		patron[21][maxCol-2] = true;
		patron[25][maxCol-3] = true;
		patron[25][maxCol-2] = true;
		
		patron[30][maxCol-3] = true;
		patron[30][maxCol-2] = true;
		patron[34][maxCol-3] = true;
		patron[34][maxCol-2] = true;
		
		patron[20][maxCol-1] = true;
		patron[23][maxCol-1] = true;
		patron[26][maxCol-1] = true;
		
		patron[29][maxCol-1] = true;
		patron[32][maxCol-1] = true;
		patron[35][maxCol-1] = true;
		
		return patron;
	}
	
	
	public static boolean[][] rectangle() {
		boolean patron[][] = new boolean[maxFil][maxCol];
		
		for (int f=0; f<maxFil; f++) {
			for (int c=0; c<maxCol; c++) {
				if (f >= maxFil/4 && f <= 3*maxFil/4 &&
				    c >= maxCol/4 && c <= 3*maxCol/4) {
					patron[f][c] = true;
				} else {
					patron[f][c] = false;
				}
			}
		}
		
		return patron;
	}
	
	
	public static boolean[][] allBlack() {
		boolean patron[][] = new boolean[maxFil][maxCol];
		
		for (int f=1; f<maxFil-1; f++) {
			for (int c=1; c<maxCol-1; c++) {
				patron[f][c] = true;
			}
		}
		// extremos
		for (int f=0; f<maxFil; f++) {
			patron[f][0] = false;
			patron[f][maxCol-1] = false;
		}
		for (int c=0; c<maxCol; c++) {
			patron[0][c] = false;
			patron[maxFil-1][c] = false;
		}
		
		return patron;
	}
	
	
	public static boolean[][] random() {
		boolean patron[][] = new boolean[maxFil][maxCol];
		
		Random r = new Random();
		
		for (int f=0; f<maxFil; f++) {
			for (int c=0; c<maxCol; c++) {
				patron[f][c] = (r.nextInt() % 2 == 0);
			}
		}
		
		return patron;
	}
}
