package javaBasics2;

public class Assignment2 {

	static int[] findMax(int mat[][], int row, int col) {
		  // Initializing max element as INT_MIN
        int maxValue = Integer.MIN_VALUE;
        int maxRow = 0;
        int maxCol = 0;
        
        int[] results = new int[3];
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (mat[i][j] > maxValue) {
                	results[0] = maxValue = mat[i][j];
                	results[1] = maxRow = i;
                	results[2] = maxCol = j;
                }
            }
        }
        //  return results
		return results;    
	}
	
	
	
	
	
public static void main(String args[]) {

	 int mat[][] = { { 13, 51, 12, 41 },
             { 32, 41, 101, 54 },
             { 76, 50, 25, 65 },
             { 54, 12, 34, 73 } };

	 int results[] = findMax(mat,4,4);
	 System.out.println("The max number in the matrix is: " + results[0] + "\nAssuming 2D array starts with 0\nPosition: " +  results[1] + " (row), " +  results[2] + " (col)");
	}
}
