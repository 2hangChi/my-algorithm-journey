package class052;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class nc01 {
    public static int MAXN = 1000001;
    public static int[] arr = new int[MAXN];
    public static int[] stack = new int[MAXN];
    public static int[][] ans = new int[MAXN][2];
    public static int n, r;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            for (int i = 0; i < n; i++) {
                in.nextToken();
                arr[i] = (int) in.nval;
            }
            compute();
            for (int i = 0; i < n; i++) {
                out.println(ans[i][0] + " " + ans[i][1]);
            }
        }
        out.flush();
        out.close();
        br.close();
    }

    public static void compute() {
        r = 0;
        int cur;
        for (int i = 0; i < n; i++) {
            while (r > 0 && arr[stack[r - 1]] >= arr[i]) { // stack[r - 1] >= arr[i] --> arr[stack[r - 1]] >= arr[i]
                cur = stack[--r];
                ans[cur][0] = r > 0 ? stack[r - 1] : -1;
                ans[cur][1] = i;
            }
            stack[r++] = i;
        }
        while (r > 0) {
            cur = stack[--r];
            ans[cur][0] = r > 0 ? stack[r - 1] : -1;
            ans[cur][1] = -1;
        }
        for (int i = n - 2; i >= 0; i--) {
            if (ans[i][1] != -1 && arr[ans[i][1]] == arr[i]) {
                ans[i][1] = ans[ans[i][1]][1];
            }
        }
    }
}
