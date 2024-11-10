package dp;

public class Knapsack {

    public static void main(String[] args) {

        int maxWeight = 6;
        int[] weights = {6,1,2,4,5};
        int[] values = {10,5,4,8,6};

        System.out.println("Maximum profit that can be achieved : " + knapsackDp(weights, values, weights.length, maxWeight));
        System.out.println("Maximum profit that can be achieved : " + knapsack(weights, values, maxWeight, 0));
    }

    public static int knapsackDp(int[] weights, int[] profits, int n, int maxWeight){

        int[][] storage = new int[n + 1][maxWeight + 1];

        for(int i = 1; i <= n; i++){
            for(int w = 1; w <= maxWeight; w++){

                if(weights[i - 1] > w)
                    storage[i][w] = storage[i - 1][w];
                else {
                    int op1 = storage[i - 1][w];
                    int op2 = storage[i - 1][w - weights[i - 1]] + profits[i - 1];
                    storage[i][w] = Math.max(op1, op2);
                }
            }
        }

        return storage[n][maxWeight];
    }

    private static int knapsack(int[] weights, int[] values, int maxWeight, int si) {

        // base case
        if(si == weights.length || maxWeight == 0) return 0;

        if(weights[si] > maxWeight) return knapsack(weights, values, maxWeight, si + 1);

        int op1 = values[si] + knapsack(weights, values, maxWeight - weights[si], si + 1);
        int op2 = knapsack(weights, values, maxWeight, si + 1);

        return Math.max(op1, op2);
    }
}
