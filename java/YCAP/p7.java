import java.util.*;
class p7 {
    public static int matrixChainMultiplication(int[] p) {
        int n = p.length - 1; // number of matrices
        int[][] dp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            dp[i][i] = 0;
        }
        // chain length
        for (int len = 2; len <= n; len++) {
            for (int i = 1; i <= n - len + 1; i++) {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;

                for (int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k + 1][j]
                            + p[i - 1] * p[k] * p[j];

                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }

        return dp[1][n];
    }
    public static void main(String[] args) {
        int[] p = {10, 20,14};
        int result = matrixChainMultiplication(p);
        System.out.println("Minimum multiplications: " + result);
    }
}