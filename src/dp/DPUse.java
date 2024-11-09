package dp;

import java.math.BigInteger;
import java.util.Date;

public class DPUse {

    public static void main(String[] args) {

        int n = 45;
        //int n = 500000;
        Date startTime = new Date();
        System.out.println(n + "th Fibonacci Number is: " + fibonacci(n));
        Date endTime = new Date();
        System.out.println("Time taken: " + (endTime.getTime() - startTime.getTime()));
    }

    private static BigInteger fibonacci(int n) {

        BigInteger[] storage = new BigInteger[n + 1];
        for(int i = 0; i < n + 1; i++){
            storage[i] = BigInteger.valueOf(-1);
        }
        return fibonacci(n, storage);
    }

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
