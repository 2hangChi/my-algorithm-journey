package class074;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class lgP1757 { // // 分组背包模板
    public static int MAXN = 1001;

    public static int MAXM = 1001;

    public static int[][] arr = new int[MAXN][3];

    public static int[] dp = new int[MAXM];

    public static int m, n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            m = (int) in.nval;
            in.nextToken();
            n = (int) in.nval;
            for (int i = 1; i <= n; i++) {
                in.nextToken();
                arr[i][0] = (int) in.nval;
                in.nextToken();
                arr[i][1] = (int) in.nval;
                in.nextToken();
                arr[i][2] = (int) in.nval;
            }
            Arrays.sort(arr, 1, n + 1, (a, b) -> a[2] - b[2]);
            out.println(compute1());
        }
        out.flush();
        out.close();
        br.close();
    }

    public static int compute1() { // 有一个数据组不通过 没找到为什么
        int teams = 1;
        for (int i = 2; i <= n; i++) {
            if (arr[i][2] != arr[i - 1][2]) {
                teams++;
            }
        }
        int[][] dp = new int[teams + 1][m + 1];
        for (int i = 1, team = 1; i <= n; i++) {
            for (int j = m; j >= arr[i][0]; j--) {
                dp[team][j] = Math.max(Math.max(dp[team - 1][j], dp[team][j]), dp[team - 1][j - arr[i][0]] + arr[i][1]);
            }
            if (i + 1 <= n && arr[i][2] != arr[i + 1][2]) {
                team++;
            }
        }
        return dp[teams][m];
    }

    public static int compute2() {
        int teams = 1;
        for (int i = 2; i <= n; i++) {
            if (arr[i][2] != arr[i - 1][2]) {
                teams++;
            }
        }
        int[][] dp = new int[teams + 1][m + 1];
        for (int i = 1, start = 1, end = 2; start <= n; i++) {
            while (end <= n && arr[start][2] == arr[end][2]) {
                end++;
            }
            for (int j = 0; j <= m; j++) {
                dp[i][j] = dp[i - 1][j];
                for (int k = start; k < end; k++) {
                    if (j >= arr[k][0]) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - arr[k][0]] + arr[k][1]);
                    }
                }
            }
            start = end++;
        }
        return dp[teams][m];
    }

    public static int compute3() { // 空间压缩
        // int teams = 1;
        // for (int i = 2; i <= n; i++) {
        //     if (arr[i][2] != arr[i - 1][2]) {
        //         teams++;
        //     }
        // }
        int[] dp = new int[m + 1];
        for (int i = 1, start = 1, end = 2; start <= n; i++) {
            while (end <= n && arr[start][2] == arr[end][2]) {
                end++;
            }
            for (int j = m; j >= 0; j--) { // int j = 0; j <= m; j++
                // dp[i][j] = dp[i - 1][j];
                for (int k = start; k < end; k++) {
                    if (j >= arr[k][0]) {
                        dp[j] = Math.max(dp[j], dp[j - arr[k][0]] + arr[k][1]);
                    }
                }
            }
            start = end++;
        }
        return dp[m];
    }
}
