package recursion;

import java.util.*;

public class RecursionPractice {
    private static int step = 0;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int[] arr = {1,2,3,4,6,1,9};
        System.out.println("Is the array sorted: " + checkSorted(arr, 0));
        System.out.println("Sum of numbers in the array: " + findSum(arr, 0));
        int x = 1;
        if(checkNumber(arr, x, 0)){
            System.out.println("First index of : " + x + " is: " + findFirstIndex(arr, x, 0));
            System.out.println("Last index of : " + x + " is: " + findLastIndex(arr, x, arr.length - 1));
            List<Integer> allIndices = findAllIndices(arr, x, 0);
            System.out.print("Printing all indices: ");
            for(int i : allIndices) System.out.print(i + " ");
            System.out.println();
        } else {
            System.out.println("Is " + x + " is not present in the array: ");
        }

//        System.out.println("Enter the term of the fibonacci series you want to find: ");
//        int y = s.nextInt();
//        System.out.println(y + "th term of the fibonacci series is : " + findFibonacciNumber(y));

        int z = 632336736;
        System.out.println("Number of digits in " + z + " is : " + noOfDigits(z));

        System.out.println("Sum of digits of " + z + " : " + sumOfDigits(z));

        int w = 100;
        System.out.println("Number of zeroes in : " + w + " are: " + countNoOfZeroes(w));

        String str = "racecar";
        System.out.println("Given String : " + str + " is a palindrome: "  + checkPalindrome(str, 0, str.length() - 1));

        /*
            Given a string str, replace all occurrences of pi with 3.14
            eg: pip should become 3.14p
                pipi should become 3.143.14
         */
        String str2 = "ssddpipiddddpiddffpi";
        System.out.println("String after replacing pi with 3.14: " + replacePi(str2, 0));

        System.out.println("String after removing consecutive duplicates: " + removeConsecutiveDuplicates(str2, 0));

//        List<String> allSubsequences = findAllSubsequences(str2, 0);
//        System.out.println("Printing all subsequences of : " + str2);
//        for(String subsequence : allSubsequences){
//            System.out.println(subsequence);
//        }

        String str3 = "1123&230";
        System.out.println("Integer value of " + str3 + " is: " + stringToIntegerUsingLastIndex(str3, str3.length() - 1));
        System.out.println("Integer value of " + str3 + " is: " + stringToIntegerUsingStartIndex(str3, 0));

        int number = 234;
        List<String> allKeypadCombinations = findAllKeypadCombinations(number);
        System.out.println("Printing all keypad combinations for " + number + " : ");
        for(String combination : allKeypadCombinations){
            System.out.print(combination + " ");
        }
        System.out.println();

        int[] arr2 = {2, 3, 4, 3, 2, 1, 4};
        System.out.println("Unique number using XOR operator: " + findUnique(arr2));

        /*
            Pair Sum Problem
            For a given array and a sum find all pairs which add up to sum
            e.g: arr = {2,3,4,3,2,1,4} and sum = 5
                 ans = [{2,3},{2,3},{3,2},{3,2},{4,1},{1,4}]
         */
        int sum = 5;
        List<Pair> allPairs = pairSumProblemUsingTwoPointer(arr2, sum);
        System.out.println("Printing all pairs which add up to : " + sum);
        for(Pair pair : allPairs) System.out.println(pair.element1 + "," + pair.element2);

        int k = 2;
        int[] arr3 = {2, 3, 4, 3, 2, 1, 4};
        System.out.println("Printing the array after rotating right by 2 places");
        rotateArrayRight(arr3, k % arr3.length);
        for(int i = 0; i < arr3.length; i++){
            System.out.print(arr3[i] + " ");
        }
        System.out.println();
        int l = arr3.length - k;
        int[] arr4 = {2, 3, 4, 3, 2, 1, 4};
        System.out.println("Printing the array after rotating left by 5 places");
        rotateArrayLeft(arr4, l % arr4.length);
        for(int i = 0; i < arr4.length; i++){
            System.out.print(arr4[i] + " ");
        }
        System.out.println();
        /*
            Tower of Hanoi Problem
            Move all disks from source rod to destination rod using the auxiliary rod.
            1. Only one disk can be moved at a time
            2. A disk can be moved only if it is on the top of a rod
            3. No disk can be placed on top of a smaller disk
         */
        int disks = 5;
        char source = 'A';
        char auxiliary = 'B';
        char destination = 'C';
        //System.out.println("Tower of Hanoi: Printing the order of moving the disks: ");
        //towerOfHanoi(disks, source, auxiliary, destination);

        s.close();
    }

    /*
        1. After rotating (arr.length) times, we get the same array
        2. Rotating right by k places is same as rotating left by (arr.length - k) places
     */
    private static void rotateArrayRight(int[] arr, int k) {

        int j = 0;
        int[] temp = new int[k];
        for(int i = arr.length - k; i < arr.length; i++){
            temp[j++] = arr[i];
        }
        for(int i = arr.length - 1; i >= k; i--){
            arr[i] = arr[i - k];
        }
        for(int i = 0; i < k; i++){
            arr[i] = temp[i];
        }
    }

    private static void rotateArrayLeft(int[] arr, int k) {

        int[] temp = new int[k];
        for(int i = 0; i < k; i++){
            temp[i] = arr[i];
        }
        for(int i = 0; i < arr.length - k; i++){
            arr[i] = arr[i + k];
        }
        int j = 0;
        for(int i = arr.length - k; i < arr.length; i++){
            arr[i] = temp[j++];
        }
    }

    /*
        Better approach since we're not changing the input(preserving the indices)
     */
    private static List<Pair> pairSumProblemUsingHashMap(int[] arr, int sum) {
        List<Pair> allPairs = new ArrayList<>();

        Map<Integer, List<Integer>> hmap = new HashMap<>();
        for(int i = 0; i < arr.length; i++) {
            int complement = sum - arr[i];
            if(hmap.containsKey(complement)) {
                for (int index : hmap.get(complement)) {
                    allPairs.add(new Pair(i, index));
                }
            }
            hmap.putIfAbsent(arr[i], new ArrayList<>());
            hmap.get(arr[i]).add(i);
        }

        return allPairs;
    }

    /*
        Here indices are not preserved since we'll sort the array
        We can only return the elements but not the indices
     */
    private static List<Pair> pairSumProblemUsingTwoPointer(int[] arr, int sum) {
        Arrays.sort(arr);
        List<Pair> allPairs = new ArrayList<>();
        int si = 0;
        int ei = arr.length - 1;
        Map<Integer, Integer> countOfElements = new HashMap<>();
        for(int i = 0; i < arr.length; i++){
            if(countOfElements.containsKey(arr[i]))
                countOfElements.put(arr[i], countOfElements.get(arr[i]) + 1);
            else
                countOfElements.put(arr[i], 1);
        }

        while(si < ei){
            if(arr[si] + arr[ei] > sum) ei--;
            else if(arr[si] + arr[ei] < sum) si++;
            else {
                int countOfElementsSi = countOfElements.get(arr[si]);
                if(arr[si] == arr[ei]){
                    int noOfPairs = countOfElementsSi * (countOfElementsSi - 1)/2;
                    for(int i = 0; i < noOfPairs; i++){
                        allPairs.add(new Pair(arr[si], arr[si]));
                    }
                    break;
                }
                int countOfElementsEi = countOfElements.get(arr[ei]);
                int noOfPairs = countOfElementsSi * countOfElementsEi;
                for(int i = 0; i < noOfPairs; i++){
                    allPairs.add(new Pair(arr[si], arr[ei]));
                }
                si += countOfElementsSi;
                ei -= countOfElementsEi;
            }
        }

        return allPairs;
    }

    private static int findUnique(int[] arr) {

        int unique = 0;
        for(int i = 0; i < arr.length; i++) {

            /*
                XOR of two same numbers is 0
                XOR of a number with 0 is the number itself
             */
            unique = unique^arr[i];
        }
        return unique;
    }

    private static List<String> findAllKeypadCombinations(int number) {

        // edge case
        if(number < 2) return null;

        // base case -- single digit number
        if(number/10 == 0) return getKeypadCombinationsForDigit(number);

        List<String> keypadCombinationsFromRemainingNumber = findAllKeypadCombinations(number/10);
        List<String> keypadCombinationsForRemainder = getKeypadCombinationsForDigit(number%10);
        List<String> allKeypadCombinations = new ArrayList<>();

        for(String combination: keypadCombinationsFromRemainingNumber){
            for(String remainderKeypadCombination : keypadCombinationsForRemainder){
                allKeypadCombinations.add(combination + remainderKeypadCombination);
            }
        }
        return allKeypadCombinations;
    }

    private static List<String> getKeypadCombinationsForDigit(int number) {

        List<String> keypadCombinations = new ArrayList<>();
        switch(number){
            case 2: keypadCombinations.addAll(List.of("a", "b", "c"));
                    break;
            case 3: keypadCombinations.addAll(List.of("d", "e", "f"));
                    break;
            case 4: keypadCombinations.addAll(List.of("g", "h", "i"));
                    break;
            case 5: keypadCombinations.addAll(List.of("j", "k", "l"));
                    break;
            case 6: keypadCombinations.addAll(List.of("m", "n", "o"));
                    break;
            case 7: keypadCombinations.addAll(List.of("p", "q", "r", "s"));
                    break;
            case 8: keypadCombinations.addAll(List.of("t", "u", "v"));
                    break;
            case 9: keypadCombinations.addAll(List.of("w", "x", "y", "z"));
                    break;
            default:
        }
        return keypadCombinations;
    }

    private static List<String> findAllSubsequences(String str, int startIndex) {

        // edge case
        if(str == null) return null;

        // base case
        if(startIndex == str.length()) return List.of("");

        List<String> subsequencesFromRemainingString = findAllSubsequences(str, startIndex + 1);
        List<String> newSubsequences = new ArrayList<>();
        for(String s : subsequencesFromRemainingString){
            newSubsequences.add(str.charAt(startIndex) + s);
        }
        newSubsequences.addAll(subsequencesFromRemainingString);

        return newSubsequences;
    }

    private static String removeConsecutiveDuplicates(String str, int startIndex) {

        // edge case
        if(str == null || str.isEmpty()) return str;

        // base case
        if(startIndex == str.length() - 1) return str;

        if(str.charAt(startIndex) == str.charAt(startIndex + 1)){
            str = str.substring(0, startIndex) + str.substring(startIndex + 1);
        } else {
            startIndex += 1;
        }
        return removeConsecutiveDuplicates(str, startIndex);
    }

    private static void towerOfHanoi(int disks, char source, char auxiliary, char destination) {

        if(disks > 0){
            towerOfHanoi(disks - 1, source, destination, auxiliary);
            step++;
            System.out.println("Step :" + step + " -  Moving from " + source + " to " + destination);
            towerOfHanoi(disks - 1, auxiliary, source, destination);
        }
    }

    private static int stringToIntegerUsingLastIndex(String str, int lastIndex) {

        // edge case
        if(str == null || str.isEmpty()) return -1;

        // check for a valid character
        int intAtLastIndex = str.charAt(lastIndex) - '0';
        if(intAtLastIndex < 0 || intAtLastIndex > 9) return -1;

        // base case
        if(lastIndex == 0) return intAtLastIndex;

        int ansFromRecursion = stringToIntegerUsingLastIndex(str, lastIndex - 1);
        if(ansFromRecursion == -1) return -1;

        return ansFromRecursion * 10 + intAtLastIndex;
    }

    private static int stringToIntegerUsingStartIndex(String str, int startIndex) {

        // edge case
        if(str == null || str.isEmpty()) return -1;

        int intAtStartIndex = str.charAt(startIndex) - '0';
        if(intAtStartIndex < 0 || intAtStartIndex > 9) return -1;

        // base case
        if(startIndex == str.length() - 1) return intAtStartIndex;

        int ansFromRecursion = stringToIntegerUsingStartIndex(str, startIndex + 1);
        if(ansFromRecursion == -1) return -1;

        return intAtStartIndex * (int)Math.pow(10, str.length() - 1 - startIndex) + ansFromRecursion;
    }

    private static String replacePi(String str, int startIndex) {

        // edge case
        if(str == null || str.isEmpty()) return str;

        // base case
        if(startIndex >= str.length()) return str;

        if(str.charAt(startIndex) == 'p' && str.charAt(startIndex + 1) == 'i'){
            str = str.substring(0, startIndex) + "3.14" + str.substring(startIndex + 2);
        }

        return replacePi(str, startIndex + 1);
    }

    private static boolean checkPalindrome(String str, int startIndex, int lastIndex) {

        // edge case
        if(str == null || str.isEmpty()) return true;

        // base case
        if(startIndex >= lastIndex) return true;

        if(str.charAt(startIndex) != str.charAt(lastIndex)) return false;

        return checkPalindrome(str, ++startIndex, --lastIndex);
    }

    private static int noOfDigits(int x) {

        // edge case
        if(x < 0) return -1;

        // base case ... for 0 to 9 number of digits is 1
        if(x/10 == 0) return 1;

        return 1 + noOfDigits(x/10);
    }

    private static int sumOfDigits(int x) {

        // edge case
        if(x < 0) return -1;

        // base case -- single digit number
        if(x/10 == 0) return x;

        return x % 10 + sumOfDigits(x/10);
    }

    private static int countNoOfZeroes(int x) {

        // edge cases
        if(x < 0) return -1;
        if(x == 0) return 1;

        // base case -- single digit number
        if(x/10 == 0) return 0;

        // if the last digit is 0
        if(x%10 == 0){
            return 1 + countNoOfZeroes(x/10);
        } else {
            return countNoOfZeroes(x/10);
        }
    }

    private static List<Integer> findAllIndices(int[] arr, int x, int startIndex) {

        // edge case
        if(arr == null) return null;

        // base case
        if(startIndex == arr.length) return new ArrayList<>();

        List<Integer> allIndices = findAllIndices(arr, x, startIndex + 1);
        if(arr[startIndex] == x) {
            allIndices.add(startIndex);
        }

        return allIndices;
    }

    private static int findLastIndex(int[] arr, int x, int lastIndex) {

        // edge case
        if(arr == null) return -1;

        // base case
        if(lastIndex == -1) return -1;

        if(arr[lastIndex] == x) return lastIndex;

        return findLastIndex(arr, x, lastIndex - 1);
    }

    private static int findFirstIndex(int[] arr, int x, int startIndex) {

        // edge case
        if(arr == null) return -1;

        // base case
        if(startIndex == arr.length) return -1;

        if(arr[startIndex] == x) return startIndex;
        return findFirstIndex(arr, x, startIndex + 1);
    }

    private static int findFibonacciNumber(int x) {

        // edge case
        if(x < 0) return -1;

        // base case
        if(x == 0 || x == 1) return x;

        return findFibonacciNumber(x - 1) + findFibonacciNumber(x - 2);
    }

    private static boolean checkNumber(int[] arr, int number, int startIndex) {

        // edge case
        if(arr == null) return false;

        // base case
        if(startIndex == arr.length) return false;
        if(arr[startIndex] == number) return true;

        return checkNumber(arr, number, startIndex + 1);
    }

    private static int findSum(int[] arr, int startIndex) {

        // edge case
        if(arr == null) return Integer.MIN_VALUE;

        // base case
        if(startIndex == arr.length - 1) return arr[startIndex];

        return arr[startIndex] + findSum(arr, startIndex + 1);
    }

    private static boolean checkSorted(int[] arr, int startIndex) {

        // edge case
        if(arr == null || arr.length <= 1) return true;

        // base case
        if(startIndex == arr.length - 1) return true;

        if(arr[startIndex] > arr[startIndex + 1]) return false;

        return checkSorted(arr, startIndex + 1);

    }
}
