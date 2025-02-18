package class075;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class lgP1776 {
    public static int MAXN = 101;

    public static int MAXW = 40001;

    public static int[] v = new int[MAXN];

    public static int[] w = new int[MAXN];

    public static int[] c = new int[MAXN];

    public static int[] dp = new int[MAXW];

    public static int n, t;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            in.nextToken();
            t = (int) in.nval;
            for (int i = 1; i <= n; i++) {
                in.nextToken(); v[i] = (int) in.nval;
                in.nextToken(); w[i] = (int) in.nval;
                in.nextToken(); c[i] = (int) in.nval;
            }
            out.println(compute1());
        }
        out.flush();
        out.close();
        br.close();
    }

    public static int compute1() {
        int[][] dp = new int[n + 1][t + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= t; j++) {
                dp[i][j] = dp[i - 1][j];
                for (int k = 1; k <= c[i] && k * w[i] <= j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - k * w[i]] + k * v[i]);
                }
            }
        }
        return dp[n][t];
    }

    public static int compute2() { // 空间压缩
        Arrays.fill(dp, 1, t + 1, 0);
        for (int i = 1; i <= n; i++) {
            for (int j = t; j >= 1; j--) {
                for (int k = 1; k <= c[i] && k * w[i] <= j; k++) {
                    dp[j] = Math.max(dp[j], dp[j - k * w[i]] + k * v[i]);
                }
            }
        }
        return dp[t];
    }
}