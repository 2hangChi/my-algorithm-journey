package class074;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class lgP2918 { // 买足量干草的最小花费
    public static int MAXN = 101;

    public static int MAXM = 55001;

    public static int[] val = new int[MAXN];

    public static int[] cost = new int[MAXN];

    public static int[] dp = new int[MAXM];

    public static int n, h, maxv, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            in.nextToken();
            h = (int) in.nval;
            maxv = 0;
            for (int i = 1; i <= n; i++) {
                in.nextToken();
                val[i] = (int) in.nval;
                in.nextToken();
                cost[i] = (int) in.nval;
                maxv = Math.max(maxv, val[i]);
            }
            m = h + maxv;
            out.println(compute1());
        }
        out.flush();
        out.close();
        br.close();
    }

    public static int compute1() {
        int[][] dp = new int[n + 1][m + 1];
        Arrays.fill(dp[0], 1, m + 1, Integer.MAX_VALUE);
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= val[i] && dp[i][j - val[i]] != Integer.MAX_VALUE) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][j - val[i]] + cost[i]); //
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = h; i <= m; i++) {
            ans = Math.min(ans, dp[n][i]);
        }
        return ans;
    }

    public static int compute2() { // 空间压缩
        Arrays.fill(dp, 1, m + 1, Integer.MAX_VALUE);
        for (int i = 1; i <= n; i++) {
            for (int j = val[i]; j <= m; j++) {
                if (dp[j - val[i]] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j - val[i]] + cost[i]); //
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = h; i <= m; i++) {
            ans = Math.min(ans, dp[i]);
        }
        return ans;
    }
}
