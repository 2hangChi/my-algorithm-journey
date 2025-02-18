package class077;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class lgP3205 { // 合唱队
    public static int MAXN = 1001;

    public static int[] nums = new int[MAXN];

    public static int[][] dp = new int[MAXN][2];

    public static int n;

    public static int MOD = 19650827;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            for (int i = 1; i <= n; i++) {
                in.nextToken();
                nums[i] = (int) in.nval;
            }
            if (n == 1) {
                out.println(1);
            } else {
                out.println(compute1());
            }
        }
        out.flush();
        out.close();
        br.close();
    }

    public static int compute1() {
        int[][][] dp = new int[n + 1][n + 1][2];
        for (int i = 1; i <= n - 1; i++) {
            dp[i][i][0] = 1; // 对角线其实不用填 用不上
            dp[i][i][1] = 1;
            dp[i][i + 1][0] = nums[i] < nums[i + 1] ? 1 : 0;
            dp[i][i + 1][1] = nums[i] < nums[i + 1] ? 1 : 0;
        }
        dp[n][n][0] = 1;
        dp[n][n][1] = 1;
        for (int i = n - 2; i >= 1; i--) {
            for (int j = i + 2; j <= n; j++) {
                if (nums[i] < nums[i + 1]) {
                    dp[i][j][0] = (dp[i][j][0] + dp[i + 1][j][0]) % MOD;
                }
                if (nums[i] < nums[j]) {
                    dp[i][j][0] = (dp[i][j][0] + dp[i + 1][j][1]) % MOD;
                }
                if (nums[j] > nums[j - 1]) {
                    dp[i][j][1] = (dp[i][j][1] + dp[i][j - 1][1]) % MOD;
                }
                if (nums[j] > nums[i]) {
                    dp[i][j][1] = (dp[i][j][1] + dp[i][j - 1][0]) % MOD;
                }
            }
        }
        return (dp[1][n][0] + dp[1][n][1]) % MOD;
    }

    public static int compute2() {
        // for (int i = 1; i <= n - 1; i++) {
        //     dp[i][i][0] = 1;
        //     dp[i][i][1] = 1;
        //     dp[i][i + 1][0] = nums[i] < nums[i + 1] ? 1 : 0;
        //     dp[i][i + 1][1] = nums[i] < nums[i + 1] ? 1 : 0;
        // }
        // dp[n][n][0] = 1;
        // dp[n][n][1] = 1;
        for (int i = n - 1; i >= 1; i--) {
            dp[i + 1][0] = nums[i] < nums[i + 1] ? 1 : 0;
            dp[i + 1][1] = nums[i] < nums[i + 1] ? 1 : 0;
            // for (int j = i + 2, ij0 = 0, ij1 = 0; j <= n; j++) {
            for (int j = i + 2, ij0, ij1; j <= n; j++) {
                ij0 = 0; // 对于每个j 都要重新置零
                ij1 = 0;
                if (nums[i] < nums[i + 1]) {
                    ij0 = (ij0 + dp[j][0]) % MOD;
                }
                if (nums[i] < nums[j]) {
                    ij0 = (ij0 + dp[j][1]) % MOD;
                }
                if (nums[j] > nums[j - 1]) {
                    ij1 = (ij1 + dp[j - 1][1]) % MOD;
                }
                if (nums[j] > nums[i]) {
                    ij1 = (ij1 + dp[j - 1][0]) % MOD;
                }
                dp[j][0] = ij0;
                dp[j][1] = ij1;
            }
        }
        return (dp[n][0] + dp[n][1]) % MOD;
    }
}
