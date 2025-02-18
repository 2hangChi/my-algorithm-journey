package class125;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class lgP1879 {

    public static int MAXN = 12;

    public static int MAXM = 12;

    public static int MOD = 100000000;

    public static int[][] grid = new int[MAXN][MAXM];

    public static int[][] dp = new int[MAXM + 1][1 << MAXM];

    public static int[] prepare = new int[1 << MAXM];

    public static int n, m, maxs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        in.nextToken();
        n = (int) in.nval;
        in.nextToken();
        m = (int) in.nval;
        maxs = 1 << m;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                in.nextToken();
                grid[i][j] = (int) in.nval;
            }
        }
        out.println(compute());
        out.flush();
        out.close();
        br.close();
    }

    public static int compute() {
        Arrays.fill(dp[0], 0, maxs, 1);
        for (int i = n - 1; i >= 0; i--) {
            // j == m
            for (int s = 0; s < maxs; s++) {
                dp[m][s] = dp[0][s];
            }
            // 普通位置
            for (int j = m - 1; j >= 0; j--) {
                for (int s = 0; s < maxs; s++) {
                    int ans = dp[j + 1][set(s, j, 0)];
                    if (grid[i][j] == 1 && (j == 0 || get(s, j - 1) == 0) && get(s, j) == 0) {
                        ans = (ans + dp[j + 1][set(s, j, 1)]) % MOD;
                    }
                    dp[j][s] = ans;
                }
            }
        }
        return dp[0][0];
    }

    public static int get(int s, int j) {
        return (s >> j) & 1;
    }

    public static int set(int s, int j, int v) {
        return v == 0 ? (s & (~(1 << j))) : (s | (1 << j));
    }

}
