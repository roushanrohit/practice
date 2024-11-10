package dp;

public class EditDistance {

    public static void main(String[] args) {

        String s1 = "adgei";
        String s2 = "abegi";

        System.out.println("Edit Distance of " + s1 + " and " + s2 + " is: " + editDistanceDp(s1, s2));
    }

    private static int editDistanceDp(String s1, String s2) {

        int m = s1.length();
        int n = s2.length();
        int[][] storage = new int[m + 1][n + 1];

        // base cases
        storage[m][n] = 0;
        for(int i = 0; i <= m; i++){
            storage[i][n] = s2.substring(i).length();
        }
        for(int j = 0; j <= n; j++){
            storage[m][j] = s1.substring(j).length();
        }

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {

                if(s1.charAt(j) == s2.charAt(i)){
                    storage[i][j] = storage[i + 1][j + 1];
                } else {
                    storage[i][j] = 1 + Math.min(storage[i + 1][j + 1],
                            Math.min(storage[i][j + 1], storage[i + 1][j]));
                }
            }
        }

        return storage[0][0];
    }

    private static int editDistance(String s1, String s2) {

        // base cases
        if(s1.isEmpty()) return s2.length();
        if(s2.isEmpty()) return s1.length();

        if(s1.charAt(0) == s2.charAt(0)){
            return editDistance(s1.substring(1), s2.substring(1));
        }

        int op1 = 1 + editDistance(s1, s2.substring(1));
        int op2 = 1 + editDistance(s1.substring(1), s2);
        int op3 = 1 + editDistance(s1.substring(1), s2.substring(1));

        return Math.min(op1, Math.min(op2, op3));
    }
}
