package dynamicProgramming;

public class PaintHouseTwo {
    public int minCostII(int[][] costs) {
        if (costs.length == 0 || costs[0].length == 0) {
            return 0;
        }
        int n = costs.length, k = costs[0].length, m1 = 0, m2 = 0;
        int[] dp = new int[k];
        for (int i = 0; i < n; i++) {
            // t1/t2 holds smallest from previous round
            int t1 = m1, t2 = m2;
            m1 = Integer.MAX_VALUE;
            m2 = Integer.MAX_VALUE;
            for (int j = 0; j < k; j++) {

                // core line, t1/t2 holds
                dp[j] = (dp[j] == t1 ? t2 : t1) + costs[i][j];

                // m1 the smallest, m2 the 2nd smallest
                if (m1 <= dp[j]) {
                    m2 = Math.min(dp[j], m2);
                }
                else {
                    m2 = m1;
                    m1 = dp[j];
                }
            }
        }

        return m1;
    }
}
