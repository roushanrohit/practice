package dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class KnapsackWithPath {

    public static void main(String[] args) {

        int maxWeight = 6;
        int[] weights = {6,1,2,4,5};
        int[] values = {10,5,4,8,6};

        Cell[][] storage = new Cell[weights.length + 1][maxWeight + 1];
        for (int i = 0; i <= weights.length; i++) {
            for (int j = 0; j <= maxWeight; j++) {
                storage[i][j] = new Cell();
            }
        }
        Map<Integer, Integer> profitMap = new HashMap<>();
        for(int i = 0; i < weights.length; i++){
            profitMap.put(weights[i], values[i]);
        }
        Cell cell = knapsackWithPath(weights, storage, maxWeight, weights.length, profitMap);
        System.out.println("Maximum profit that can be achieved : " + cell.maxProfit + " , weights selected: " + cell.weightsSelected);
    }

    private static Cell knapsackWithPath(int[] weights, Cell[][] storage, int maxWeight, int n, Map<Integer, Integer> profitMap) {
        Arrays.sort(weights);

        for(int i = 1; i <= n; i++){
            for(int w = 1; w <= maxWeight; w++){

                if(w < weights[i - 1]){
                    storage[i][w].maxProfit = storage[i - 1][w].maxProfit;
                    storage[i][w].weightsSelected = storage[i - 1][w].weightsSelected;
                } else {
                    int op1 = storage[i - 1][w].maxProfit;
                    int op2 = storage[i - 1][w - weights[i - 1]].maxProfit + profitMap.get(weights[i - 1]);
                    if (op1 > op2) {
                        storage[i][w].maxProfit = op1;
                        storage[i][w].weightsSelected = storage[i - 1][w].weightsSelected;
                    } else {
                        storage[i][w].maxProfit = op2;
                        storage[i][w].weightsSelected = storage[i - 1][w - weights[i - 1]].weightsSelected + i;
                    }
                }
            }
        }

        return storage[n][maxWeight];
    }
}
