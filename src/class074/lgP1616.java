package class074;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class lgP1616 { // 完全背包模板
    public static int MAXM = 10001;

    public static int MAXT = 10000001;

    public static int[] cost = new int[MAXM];

    public static int[] val = new int[MAXM];

    public static long[] dp = new long[MAXT];

    public static int t, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            t = (int) in.nval; // 容量
            in.nextToken();
            m = (int) in.nval; // 货物
            for (int i = 1; i <= m; i++) {
                in.nextToken();
                cost[i] = (int) in.nval;
                in.nextToken();
                val[i] = (int) in.nval;
            }
            out.println(compute1());
            // out.println(compute2());
        }
        out.flush();
        out.close();
        br.close();
    }

    public static long compute1() { // 内存不通过
        int[][] dp = new int[m + 1][t + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= t; j++) { // 写成j<t
                dp[i][j] = dp[i - 1][j];
                if (j >= cost[i]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - cost[i]] + val[i]);
                }
            }
        }
        return dp[m][t];
    }

    public static long compute2() { // 空间压缩
        Arrays.fill(dp, 1, t, 0); //
        for (int i = 1; i <= m; i++) {
            for (int j = cost[i]; j <= t; j++) {
                dp[j] = Math.max(dp[j], dp[j - cost[i]] + val[i]);
            }
        }
        return dp[t];
    }
}
