package dp;

public class MinimumCostPath {

    public static void main(String[] args) {

        int[][] input = new int[][]{{1,2,4,8},{9,5,1,6},{3,2,4,5},{1,9,9,1}};
        System.out.println("Minimum Cost Path of reaching to the last cell of the matrix: " + minimumCostPathDp(input));
    }

    private static int minimumCostPathDp(int[][] input) {

        int m = input.length;
        int n = input[0].length;
        int[][] storage = new int[m][n];

        // base cases
        storage[m - 1][n - 1] = input[m - 1][n - 1];

        for(int i = m - 1, j = n - 2; j >= 0; j--){
            storage[i][j] = input[i][j] + input[i][j + 1];
        }
        for(int j = n - 1, i = m - 2; i >= 0; i--){
            storage[i][j] = input[i][j] + input[i + 1][j];
        }

        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                int op1 = input[i][j] + storage[i + 1][j];
                int op2 = input[i][j] + storage[i][j + 1];
                int op3 = input[i][j] + storage[i + 1][j + 1];
                storage[i][j] = Math.min(op1, Math.min(op2, op3));
            }
        }

        return storage[0][0];
    }

    private static int minimumCostPath(int[][] input) {

        int rows = input.length;
        int columns = input[0].length;
        return minimumCostPath(input, rows, columns, 0, 0);
    }

    private static int minimumCostPath(int[][] input, int m, int n, int i, int j) {

        // base cases
        if(i == m - 1 && j == n - 1) return input[i][j];
        if(i == m - 1) {
            int minCost = 0;
            while (j < n){
                minCost += input[i][j];
                j++;
            }
            return minCost;
        }
        if(j == n - 1) {
            int minCost = 0;
            while (i < m){
                minCost += input[i][j];
                i++;
            }
            return minCost;
        }

        int op1 = input[i][j] + minimumCostPath(input, m, n, i + 1, j);
        int op2 = input[i][j] + minimumCostPath(input, m, n, i, j + 1);
        int op3 = input[i][j] + minimumCostPath(input, m, n, i + 1, j + 1);

        return Math.min(op1, Math.min(op2, op3));
    }
}
