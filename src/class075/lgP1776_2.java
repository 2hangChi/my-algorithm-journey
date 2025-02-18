package class075;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class lgP1776_2 {
    public static int MAXN = 1001;

    public static int MAXW = 40001;

    public static int[] v = new int[MAXN];

    public static int[] w = new int[MAXN];

    public static int[] dp = new int[MAXW];

    public static int n, t, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            in.nextToken();
            t = (int) in.nval;
            m = 0;
            for (int i = 1, value, weight, cnt; i <= n; i++) {
                in.nextToken(); value = (int) in.nval;
                in.nextToken(); weight = (int) in.nval;
                in.nextToken(); cnt = (int) in.nval;
                for (int k = 1; k <= cnt; k <<= 1) { // 多重背包通过二进制分组转化成01背包
                    v[++m] = k * value;
                    w[m] = k * weight;
                    cnt -= k;
                }
                if (cnt > 0) {
                    v[++m] = cnt * value;
                    w[m] = cnt * weight;
                }
            }
            out.println(compute());
        }
        out.flush();
        out.close();
        br.close();
    }

    public static int compute() {
        Arrays.fill(dp, 1, t + 1, 0);
        for (int i = 1; i <= m; i++) {
            for (int j = t; j >= w[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - w[i]] + v[i]);
            }
        }
        return dp[t];
    }
}
