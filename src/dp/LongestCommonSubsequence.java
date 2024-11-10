package dp;

public class LongestCommonSubsequence {

    public static void main(String[] args) {

        String s1 = "adgei";
        String s2 = "abegi";

        System.out.println("LCS of " + s1 + " and " + s2 + " is: " + longestCommonSubsequenceDp(s1, s2));
    }

    private static String longestCommonSubsequenceDp(String s1, String s2) {

        int m = s1.length();
        int n = s2.length();
        String[][] storage = new String[m + 1][n + 1];

        // base cases
        storage[m][n] = "";
        for(int i = 0; i <= m; i++){
            storage[i][n] = "";
        }
        for(int j = 0; j <= n; j++){
            storage[m][j] = "";
        }

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {

                if(s1.charAt(i) == s2.charAt(j)){
                    storage[i][j] = s1.charAt(i) + storage[i + 1][j + 1];
                } else {
                    storage[i][j] = storage[i + 1][j].length() > storage[i][j + 1].length()
                            ? storage[i + 1][j] : storage[i][j + 1];
                }
            }
        }

        return storage[0][0];
    }

    private static String longestCommonSubsequence(String s1, String s2) {

        // base case
        if(s1.isEmpty() || s2.isEmpty()) return "";

        if(s1.charAt(0) == s2.charAt(0)){
            return s1.charAt(0) + longestCommonSubsequence(s1.substring(1), s2.substring(1));
        } else {
            String op1 = longestCommonSubsequence(s1.substring(1), s2);
            String op2 = longestCommonSubsequence(s1, s2.substring(1));
            return op1.length() > op2.length() ? op1 : op2;
        }
    }
}
