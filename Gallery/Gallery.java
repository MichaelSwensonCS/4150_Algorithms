package art;

import java.util.Arrays;
import java.util.Scanner;

public class Gallery {
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		String firstLine = scan.nextLine();
		String[] tokens = firstLine.split(" ");
		int N = Integer.parseInt(tokens[0]);
		int K = Integer.parseInt(tokens[1]);

		for(;;){
		int[][] halls = new int[N][2];

		// Process each case.
		for(int i = 0; i < N; i++) {

			// Read in the rooms.
			firstLine = scan.nextLine();
			tokens = firstLine.split(" ");
			halls[i][0] = Integer.parseInt(tokens[0]);
			halls[i][1] = Integer.parseInt(tokens[1]);
		}

			// 0 = none 1 = left 2 = right
			//from piazza suggestion
			int[][][] computed = new int[N][K+1][3];
			for (int[][] table : computed)
				for (int[] row : table)
					Arrays.fill(row, -1);
			
			//System.out.println(Arrays.deepToString(computed));
					
			computed[0][0][0] = halls[0][0] + halls[0][1];
			if (K > 0) computed[0][1][1] = halls[0][1];
			if (K > 0) computed[0][1][2] = halls[0][0];

			//Start after first 
			for (int i = 1; i < N; i++) {
				//Close while there are still rooms left to close
				for (int j = 0; j <= i+1 && j <= K; j++) {
					
					
					if (findMax(computed[i-1][j]) >= 0){
						computed[i][j][0] = halls[i][0] + halls[i][1] + findMax(computed[i-1][j]);
						
					}if (j > 0 && Math.max(computed[i-1][j-1][1] , computed[i-1][j-1][0]) >= 0){
						computed[i][j][1] = halls[i][1] + Math.max(computed[i-1][j-1][1], computed[i-1][j-1][0]);
						
					}if (j > 0 && Math.max(computed[i-1][j-1][2], computed[i-1][j-1][0]) >= 0)
						computed[i][j][2] = halls[i][0] + Math.max(computed[i-1][j-1][2], computed[i-1][j-1][0]);
				}
			}

			System.out.println(findMax(computed[N-1][K]));

			//Finished one check for next
			if(scan.hasNext())
				firstLine = scan.nextLine();
				tokens = firstLine.split(" ");
			N = Integer.parseInt(tokens[0]);
			K = Integer.parseInt(tokens[1]);
			
			if(N == 0) break;
		}
	}
	
	//Max Helper
	public static int findMax(int[] row) {
		int temp = row[0];
		for (int i = 1; i < row.length; i++){
			temp = Math.max(temp, row[i]);
		}
		return temp;
	}
}

/*
6 4
3 1
2 1
1 2
1 3
3 3
0 0
10 5
7 8
4 9
3 7
5 9
7 2
10 3
0 10
3 2
6 3
7 9
0 0
4 3
3 4
1 1
1 1
5 6
0 0

*/

//17 17 102