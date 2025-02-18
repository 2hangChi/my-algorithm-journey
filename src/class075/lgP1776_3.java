package class075;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class lgP1776_3 { // 单调队列优化
    public static int MAXN = 101;

    public static int MAXW = 40001;

    public static int[] v = new int[MAXN];

    public static int[] w = new int[MAXN];

    public static int[] c = new int[MAXN];

    public static int[] dp = new int[MAXW];

    public static int[] queue = new int[MAXW];

    public static int l, r;

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
                in.nextToken();
                v[i] = (int) in.nval;
                in.nextToken();
                w[i] = (int) in.nval;
                in.nextToken();
                c[i] = (int) in.nval;
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
            for (int mod = Math.min(t, w[i] - 1); mod >= 0; mod--) {
                l = r = 0;
                for (int j = mod; j <= t; j += w[i]) { // j++ -> j += w[i]
                    while (l < r && dp[i - 1][queue[r - 1]] + (j - queue[r - 1]) / w[i] * v[i] <= dp[i - 1][j]) { // c[i] -> v[i]
                        r--;
                    }
                    queue[r++] = j;
                    if (queue[l] == j - w[i] * (c[i] + 1)) {
                        l++;
                    }
                    dp[i][j] = dp[i - 1][queue[l]] + (j - queue[l]) / w[i] * v[i]; // c[i] -> v[i]
                }
            }
        }
        return dp[n][t];
    }
}