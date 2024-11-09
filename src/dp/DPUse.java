package dp;

import java.math.BigInteger;
import java.util.Date;

public class DPUse {

    public static void main(String[] args) {

        int n = 5;
        //int n = 500000;
        Date startTime = new Date();
        System.out.println(n + "th Fibonacci Number is: " + fibonacci(n));
        Date endTime = new Date();
        System.out.println("Time taken: " + (endTime.getTime() - startTime.getTime()));

        int x = 1000;
        System.out.println("Minimum steps to reach 1: " + minStepsTo1Memoization(x));


    }

    private static int minStepsTo1Dp(int x) {

        int[] storage = new int[x + 1];
        storage[1] = 0;

        for(int i = 2; i <= x; i++){
            int op1 = 1 + storage[i-1];
            int op2 = Integer.MAX_VALUE;
            int op3 = Integer.MAX_VALUE;
            if(i % 2 == 0){
                op2 = 1 + storage[i/2];
            }
            if(i % 3 == 0){
                op3 = 1 + storage[i/3];
            }
            storage[i] = Math.min(op1, Math.min(op2, op3));
        }

        return storage[x];
    }

    private static int minStepsTo1(int x) {

        // edge case
        if(x < 1) return -1;

        // base case
        if(x == 1) return 0;

        int op1 = Integer.MAX_VALUE;
        int op2 = Integer.MAX_VALUE;
        if(x % 3 == 0) op1 = 1 + minStepsTo1(x/3);
        if(x % 2 == 0) op2 = 1 + minStepsTo1(x/2);
        int op3 = 1 + minStepsTo1(x - 1);

        return Math.min(op3, Math.min(op1, op2));
    }

    private static int minStepsTo1Memoization(int x) {

        int[] storage = new int[x + 1];
        for(int i = 0; i <= x; i++) storage[i] = -1;
        return minStepsTo1Memoization(x, storage);
    }

    private static int minStepsTo1Memoization(int x, int[] storage) {

        // base case
        if(x == 1) {
            storage[1] = 0;
            return storage[1];
        }

        if(storage[x] != -1) return storage[x];

        int op1 = Integer.MAX_VALUE;
        int op2 = Integer.MAX_VALUE;
        if(x % 3 == 0) {
            op1 = 1 + minStepsTo1Memoization(x / 3);
        }
        if(x % 2 == 0) {
            op2 = 1 + minStepsTo1Memoization(x / 2);
        }
        int op3 = 1 + minStepsTo1Memoization(x - 1);

        storage[x] =  Math.min(op3, Math.min(op1, op2));
        return storage[x];
    }

    private static BigInteger fibonacci(int n) {

        BigInteger[] storage = new BigInteger[n + 1];
        for(int i = 0; i < n + 1; i++){
            storage[i] = BigInteger.valueOf(-1);
        }
        return fibonacci(n, storage);
    }

    // O(N) -- Both Time and Space Complexity
    private static BigInteger fibonacci(int n, BigInteger[] storage) {

        // base case
        if(n == 0 || n == 1) {
            storage[n] = BigInteger.valueOf(n);
            return storage[n];
        }

        // if it is already calculated, simply return it
        if(!storage[n].equals(BigInteger.valueOf(-1))) return storage[n];

        storage[n] = fibonacci(n - 1, storage).add(fibonacci(n - 2, storage));
        return storage[n];
    }

    private static BigInteger fibonacciIterative(int n) {

        BigInteger[] fibArray = new BigInteger[n + 1];
        fibArray[0] = BigInteger.valueOf(0);
        fibArray[1] = BigInteger.valueOf(1);

        for(int i = 2; i < n + 1; i++){
            fibArray[i] = fibArray[i - 1].add(fibArray[i - 2]);
        }

        return fibArray[n];
    }
}
