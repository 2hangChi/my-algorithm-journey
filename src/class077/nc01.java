package class077;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class nc01 { // 括号区间匹配
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        String str = br.readLine();
        out.println(compute(str));
        out.flush();
        out.close();
        br.close();
    }

    public static int compute(String str) {
        char[] s = str.toCharArray();
        int n = s.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }
        return f(s, 0, n - 1, dp);
    }

    public static int f(char[] s, int l, int r, int[][] dp) {
        if (dp[l][r] != -1) {
            return dp[l][r];
        }
        int ans = Integer.MAX_VALUE;
        // if (l > r) { // 不会走这个分支
        //     ans = 0;
        // } else
        if (l == r) {
            ans = 1;
        } else if (l + 1 == r) {
            ans = ((s[l] == '(') && (s[r] == ')')) || ((s[l] == '[') &&
                    (s[r] == ']')) ? 0 : 2;
        } else {
            int p1 = Integer.MAX_VALUE;
            if (((s[l] == '(') && (s[r] == ')')) || ((s[l] == '[') && (s[r] == ']'))) { // 之前这个情况直接取了ans = f(l + 1, r - 1);
                                                                                        // 肯定不对 如：()() 的情况 l,r能配上 不一定直接配就是最优
                p1 = f(s, l + 1, r - 1, dp);
            }
            int p2 = Integer.MAX_VALUE;
            for (int m = l; m < r; m++) {
                p2 = Math.min(p2, f(s, l, m, dp) + f(s, m + 1, r, dp));
            }
            ans = Math.min(p1, p2);
        }
        dp[l][r] = ans;
        return ans;
    }
}
