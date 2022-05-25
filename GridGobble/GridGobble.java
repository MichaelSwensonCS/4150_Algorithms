package grid;

import java.util.Arrays;
import java.util.Scanner;

public class GridGobble {
	static int[][] grid;
	static int[][] switchGrid;
	static int[][] tempGrid;
	static int cols;
	static int rows;
	
	
	public static void main(String[] args){
		try(Scanner scan = new Scanner(System.in)){
			String[] parse = scan.nextLine().split(" ");
			 rows = Integer.parseInt(parse[0]);
			 cols = Integer.parseInt(parse[1]);
			grid = new int[rows][cols];
			switchGrid = new int[rows][cols];
			tempGrid = new int[rows][cols];
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < cols; j++){
				grid[i][j] = scan.nextInt();
				switchGrid[i][j] = Integer.MIN_VALUE;
				tempGrid[i][j] = Integer.MIN_VALUE;
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		//System.out.println(Arrays.deepToString(grid));
		
		int test = 0;
		int max = 0;
		for(int i = 0; i < cols; i++){
			test = maxValue(rows - 1, i);
			if(test > max)
				max = test;
		}
		System.out.print(max);
		
	}
	
	
	private static int maxValue(int row, int column) {
		if(row == 0)
			return grid[0][column];
		if(tempGrid[row][column] != Integer.MIN_VALUE)
			return tempGrid[row][column];
		int max;
		int choice =  0;
		int choice1 = 0;
		int choice2 = 0;
		
		max = calculateMax(row, column);
		tempGrid[row][column] = max;
		return max;
	}


	private static int calculateMax(int row, int column) {
		int max;
		int choice;
		int choice1;
		int choice2;
		max = grid[row][column] + maxValue(row -1, column);
		if(column > 0){
			choice = column-1;
		}else{
			choice = cols - 1;
		}
		choice1 = maxSwapValue(row - 1, choice) + grid[row][column];
		if(choice1 > max)
			max = choice1;
		if(column < cols -1){
			choice = column + 1;
		}else{
			choice = 0;
		}
		choice2 = maxSwapValue(row - 1, choice) + grid[row][column];
		if(choice2 > max){
			max = choice2;
		}
		return max;
	}
	private static int maxSwapValue(int row, int column) {
		if(row == 0)
			return -grid[0][column];
		if(switchGrid[row][column] != Integer.MIN_VALUE)
			return switchGrid[row][column];
		
		int max;
		int choice =  0;
		int choice1 = 0;
		int choice2 = 0;
		
		max = cacluateSwappableMax(row, column);
		switchGrid[row][column] = max;
		return max;
	}


	private static int cacluateSwappableMax(int row, int column) {
		int max;
		int choice;
		int choice1;
		int choice2;
		max = maxValue(row - 1, column) - grid[row][column];
		if(column > 0){
			choice = column-1;
		}else{
			choice = cols - 1;
		}
		choice1 = maxSwapValue(row - 1, choice) - grid[row][column];
		if(choice1 > max)
			max = choice1;
		if(column < cols -1){
			choice = column + 1;
		}else{
			choice = 0;
		}
		choice2 = maxSwapValue(row - 1, choice) - grid[row][column];
		if(choice2 > max){
			max = choice2;
		}
		return max;
	}
}
