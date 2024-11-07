package recursion;

import java.util.ArrayList;
import java.util.List;

public class RecursionPractice2 {

    public static void main(String[] args) {

        String str = "abbaaabbbbabb";
        System.out.println(checkAB(str, 0));

        int n = 20;
        System.out.println("No of possible ways to climb up the staircase: " + staircase(n));

        int[] arr = {1, 3, 7, 23, 45, 796, 1234, 4566, 9878, 9999, 12345, 12346};
        int x = 45;
        System.out.println("Index of " + x + " in the sorted array: " + findIndexByBinarySearch(arr, x, 0, arr.length - 1));

        int[] arr2 = {1, 1, 7, 23, 23, 9878};
        List<List<Integer>> allSubsets = findAllUniqueSubsets(arr2, arr2.length - 1);
        System.out.println("Printing all subsets: ");
        for(List<Integer> subset : allSubsets){
            for(int ele : subset) System.out.print(ele + " ");
            System.out.println();
        }

        int[] arr3 = {3, 2, 5, 1, 4, 6};
        int k = 8;
        List<List<Integer>> subsetsSumToK = findSubsetsSumToK(arr3, k, 0);
        System.out.println("Printing all subsets which sum to " + k + ": ");
        for(List<Integer> subset : subsetsSumToK){
            for(int ele : subset) System.out.print(ele + " ");
            System.out.println();
        }
    }

    private static List<List<Integer>> findSubsetsSumToK(int[] arr, int sum, int startIndex) {

        // base cases
        if (startIndex == arr.length || sum < 0) return List.of();

        int currentElement = arr[startIndex];
        List<List<Integer>> allSubsets = new ArrayList<>();
        List<List<Integer>> subsetsWithoutCurrentElement = findSubsetsSumToK(arr, sum, startIndex + 1);
        allSubsets.addAll(subsetsWithoutCurrentElement);
        if (currentElement == sum) {
            allSubsets.add(List.of(currentElement));
        } else if(currentElement < sum){
            List<List<Integer>> subsetsWithCurrentElement = findSubsetsSumToK(arr, sum - currentElement, startIndex + 1);
            for (List<Integer> subset : subsetsWithCurrentElement) {
                List<Integer> newList = new ArrayList<>();
                newList.add(currentElement);
                newList.addAll(subset);
                allSubsets.add(newList);
            }
        }

        return allSubsets;
    }

    private static List<List<Integer>> findAllUniqueSubsets(int[] arr, int lastIndex) {

        // skip the duplicates
        while(lastIndex > 0 && arr[lastIndex] == arr[lastIndex - 1]) lastIndex--;

        // base case
        if(lastIndex == 0) {
            return List.of(List.of(), List.of(arr[lastIndex]));
        }

        List<List<Integer>> allSubsets = new ArrayList<>();
        List<List<Integer>> subsetsFromRemainingArray = findAllUniqueSubsets(arr,  lastIndex - 1);
        allSubsets.addAll(subsetsFromRemainingArray);
        for(List<Integer> subsets : subsetsFromRemainingArray){
            List<Integer> newList = new ArrayList<>();
            newList.addAll(subsets);
            newList.add(arr[lastIndex]);
            allSubsets.add(newList);
        }
        return allSubsets;
    }

    private static int findIndexByBinarySearch(int[] arr, int x, int si, int ei) {

        // base case
        if(si > ei) return -1;

        int mi = (si + ei)/2;
        if(arr[mi] > x) return findIndexByBinarySearch(arr, x, si, mi - 1);
        else if(arr[mi] < x) return findIndexByBinarySearch(arr, x, mi + 1, ei);
        else return mi;
    }

    private static int staircase(int n) {

        // base cases
        if(n == 1 || n == 2) return n;
        if(n == 3) return 4;

        return  staircase(n - 1) + staircase(n - 2) + staircase(n - 3);
    }

    private static boolean checkAB(String str, int startIndex) {

        // edge case
        if(str == null || str.isEmpty()) return false;

        // base case
        if(startIndex >= str.length()) return true;

        if(str.charAt(startIndex) == 'a') return checkAB(str, startIndex + 1);
        else if (str.charAt(startIndex) == 'b') {
            if(startIndex + 1 == str.length()) return false;
            else return checkAB(str, startIndex + 2);
        }

        return false;
    }
}
