package hashmaps;

import java.util.*;

public class HashMapUse {

    public static void main(String[] args) {

        int number = 262732121;
        System.out.println("Digit with maximum frequency: " + findDigitWithMaximumFrequency(number));

        int[] arr = {1,2,1,1,3,2,4,5,7};
        List<Integer> listAfterRemovingDuplicates = removeDuplicates3(arr);
        System.out.println("Printing the array after removing the duplicate elements");
        for(int elem : listAfterRemovingDuplicates) System.out.print(elem + " ");
        System.out.println();

        int[] arr2 = {2,3,5,8,9};
        System.out.println("Printing the intersection of array 1 and array 2: ");
        printIntersection(arr, arr2);
        System.out.println();

        int[] arr3 = {-2,2,6,-2,2,-6,3};
        List<Pair> indicesOfPairsThatSumToZero = pairSumToZero(arr3);
        System.out.println("Printing all pairs that sum to 0: ");
        for(Pair p : indicesOfPairsThatSumToZero){
            System.out.println(arr3[p.element1] + " " + arr3[p.element2]);
        }

        int[] arr4 = {2, 3, 4, 3, 2, 1, 4};
        int k1 = 5;
        List<Pair> indicesOfPairsThatSumToK = pairSumToK(arr4, k1);
        System.out.println("Printing all pairs that sum to " + k1 + " : ");
        for(Pair p : indicesOfPairsThatSumToK){
            System.out.println(arr4[p.element1] + " " + arr4[p.element2]);
        }

        int[] arr5 = {2, 3, 4, 3, 2, 1, 4};
        int k2 = 0;
        List<Pair> indicesOfPairsThatDifferByK = pairsWithDiffK(arr5, k2);
        System.out.println("Printing all pairs that differ by " + k2 + " : ");
        for(Pair p : indicesOfPairsThatDifferByK){
            System.out.println(arr4[p.element1] + " " + arr4[p.element2]);
        }

        int[] arr6 = {9,1,8,6,3,4,2,7,10,11,15};
        List<Integer> longestConsecutiveSubsequence = longestConsecutiveSubsequence(arr6);
        System.out.println("Printing the longest consecutive subsequence: ");
        for(int num : longestConsecutiveSubsequence) System.out.print(num + " ");
        System.out.println();
    }

    /*
        Longest Consecutive Subsequence Problem
        <!-- Approach 2 -->
        1. put all elements in a hashset
        2. initialize an arraylist 'ans' of size 0
        3. loop through the array
           if hashset does not contain num-1, search for consecutive elements in the
           hashset, add all of them in an arraylist 'temp'
           if the size exceeds the size of ans, temp is the new ans
        4. return ans
         */
    private static List<Integer> longestConsecutiveSubsequence2(int[] arr) {

        List<Integer> longestConsecutiveSubsequence = new ArrayList<>();
        Set<Integer> hset = new HashSet<>();
        for(int num: arr) hset.add(num);

        for(int num : arr){
            if(!hset.contains(num - 1)){

                List<Integer> tempConsecutiveSubsequence = new ArrayList<>();
                tempConsecutiveSubsequence.add(num);
                int j = 1;
                while (hset.contains(num + j)){
                    tempConsecutiveSubsequence.add(num + j);
                    j++;
                }

                if(tempConsecutiveSubsequence.size() > longestConsecutiveSubsequence.size()){
                    longestConsecutiveSubsequence = tempConsecutiveSubsequence;
                }
            }
        }

        return longestConsecutiveSubsequence;
    }

    /*
        Longest Consecutive Subsequence Problem
        arr = {9,1,8,6,3,4,2,7,10,15};
        <!-- Approach 1 -->
        1. sort the array: 1,2,3,4,6,7,8,9,10,15
        2. consecutive subsequences are:
           1,2,3,4
           6,7,8,9,10
           15
        3. longest consecutive subsequence: 6,7,8,9,10
         */
    private static List<Integer> longestConsecutiveSubsequence(int[] arr) {
        Arrays.sort(arr);
        List<Integer> longestConsecutiveSubsequence = new ArrayList<>();

        for(int i = 0; i < arr.length; i++){

            List<Integer> tempConsecutiveSubsequence = new ArrayList<>();
            tempConsecutiveSubsequence.add(arr[i]);
            while (i < arr.length - 1 && arr[i] + 1 == arr[i+1]){
                tempConsecutiveSubsequence.add(arr[i+1]);
                i++;
            }

            if(tempConsecutiveSubsequence.size() > longestConsecutiveSubsequence.size()){
                longestConsecutiveSubsequence = tempConsecutiveSubsequence;
            }
        }

        return longestConsecutiveSubsequence;
    }

    private static List<Pair> pairsWithDiffK(int[] arr, int k) {
        List<Pair> indicesOfPairsThatDifferByK = new ArrayList<>();

        Map<Integer, List<Integer>> hmap = new HashMap<>();
        for(int i = 0; i < arr.length; i++) {
            int complement1 = arr[i] - k;
            if(hmap.containsKey(complement1)) {
                for (int index : hmap.get(complement1)) {
                    indicesOfPairsThatDifferByK.add(new Pair(i, index));
                }
            }
            if(k != 0){
                int complement2 = arr[i] + k;
                if(hmap.containsKey(complement2)) {
                    for (int index : hmap.get(complement2)) {
                        indicesOfPairsThatDifferByK.add(new Pair(i, index));
                    }
                }
            }
            hmap.putIfAbsent(arr[i], new ArrayList<>());
            hmap.get(arr[i]).add(i);
        }

        return indicesOfPairsThatDifferByK;
    }

    private static List<Pair> pairSumToK(int[] arr, int k) {
        List<Pair> indicesOfPairsThatSumToK = new ArrayList<>();

        Map<Integer, List<Integer>> hmap = new HashMap<>();
        for(int i = 0; i < arr.length; i++) {
            int complement = k - arr[i];
            if(hmap.containsKey(complement)) {
                for (int index : hmap.get(complement)) {
                    indicesOfPairsThatSumToK.add(new Pair(i, index));
                }
            }
            hmap.putIfAbsent(arr[i], new ArrayList<>());
            hmap.get(arr[i]).add(i);
        }

        return indicesOfPairsThatSumToK;
    }

    private static List<Pair> pairSumToZero(int[] arr) {

        List<Pair> indicesOfPairsThatSumToZero = new ArrayList<>();
        Map<Integer, List<Integer>> hmap = new HashMap<>();
        for(int i = 0; i < arr.length; i++){
            int complement = arr[i] * -1;
            if(hmap.containsKey(complement)){
                for(int index : hmap.get(complement)){
                    indicesOfPairsThatSumToZero.add(new Pair(i, index));
                }
            }
            hmap.putIfAbsent(arr[i], new ArrayList<>());
            hmap.get(arr[i]).add(i);
        }

        return indicesOfPairsThatSumToZero;
    }

    private static void printIntersection(int[] arr, int[] arr2) {

        Map<Integer, Integer> hmap = new HashMap<>();
        for(int i : arr){
            if(hmap.containsKey(i)){
                hmap.put(i, hmap.get(i) + 1);
            } else hmap.put(i, 1);
        }

        for(int j : arr2){
            if(hmap.containsKey(j) && hmap.get(j) > 0){
                System.out.print(j + " ");
                hmap.put(j, hmap.get(j) - 1);
            }
        }
    }

    private static List<Integer> removeDuplicates3(int[] arr) {

        List<Integer> listAfterRemovingDuplicates = new ArrayList<>();
        Map<Integer, Boolean> hmap = new HashMap<>();

        for(int j : arr){
            if(!hmap.containsKey(j)){
                hmap.put(j, true);
                listAfterRemovingDuplicates.add(j);
            }
        }

        return listAfterRemovingDuplicates;
    }

    private static List<Integer> removeDuplicates2(int[] arr) {

        int max = arr[0];
        for(int i = 0; i < arr.length; i++){
            if(arr[i] > max) max = arr[i];
        }

        boolean[] availabilityArray = new boolean[max + 1];
        for(int i : arr) {
            availabilityArray[i] = true;
        }

        List<Integer> listAfterRemovingDuplicates = new ArrayList<>();
        for(int i = 0; i < availabilityArray.length; i++){
            if(availabilityArray[i]) listAfterRemovingDuplicates.add(i);
        }

        return listAfterRemovingDuplicates;
    }

    public static List<Integer> removeDuplicates(int[] arr){
        List<Integer> listAfterRemovingDuplicates = new ArrayList<>();

        Arrays.sort(arr);
        for(int i = 0; i < arr.length; i++){
            listAfterRemovingDuplicates.add(arr[i]);
            while (i != arr.length - 1 && arr[i] == arr[i+1]) i++;
        }

        return listAfterRemovingDuplicates;
    }

    private static int findDigitWithMaximumFrequency(int number) {

        int[] arr = new int[10];
        while (number != 0) {
            arr[number % 10]++;
            number = number/10;
        }
        int numberWithMaxFrequency = 0;
        int maxFrequency = arr[0];
        for(int i = 1; i < arr.length; i++){
            if(arr[i] > maxFrequency){
                maxFrequency = arr[i];
                numberWithMaxFrequency = i;
            }
        }
        return numberWithMaxFrequency;
    }
}
