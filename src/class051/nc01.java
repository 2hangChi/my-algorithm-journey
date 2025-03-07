package class051;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class nc01 {
    public static int[] H = new int[100001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            int n = (int) in.nval;
            int max = 0;
            for (int i = 0; i < n; i++) {
                in.nextToken();
                H[i] = (int) in.nval;
                max = Math.max(max, H[i]);
            }
            int ans = 0;
            for (int l = 0, r = max, m; l <= r;) {
                m = (l + r) >> 1;
                if (f(n, m, max)) {
                    ans = m;
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
            out.print(ans);
        }
        out.flush();
        out.close();
        br.close();
    }
    public static boolean f(int n, int m, int max) {
        for (int i = 0; i < n; i++) {
            if(m >= max) { // 关键
                return true;
            }
            if (m >= H[i]) {
                m += m - H[i];
            } else {
                m -= H[i] - m;
            }
            if (m < 0) {
                return false;
            }
        }
        return true;
    }
}
