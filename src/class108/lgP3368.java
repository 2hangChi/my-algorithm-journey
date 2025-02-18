package class108;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class lgP3368 {
    public static int MAXN = 500002;
    public static int n, m;
    public static int[] tree = new int[MAXN];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        in.nextToken();
        n = (int) in.nval;
        in.nextToken();
        m = (int) in.nval;
        for (int i = 1, pre = 0, v; i <= n; i++) {
            in.nextToken();
            v = (int) in.nval;
            add(i, v - pre);
            pre = v;
        }
        // 下面这样写也行 不用多一个变量pre 但多调用一次add方法
//        for (int i = 1, v; i <= n; i++) {
//            in.nextToken();
//            v = (int) in.nval;
//            add(i, v);
//            add(i + 1, -v);
//        }
        for (int i = 1, op, x, y, k; i <= m; i++) {
            in.nextToken(); op = (int) in.nval;
            if (op == 1) {
                in.nextToken(); x = (int) in.nval;
                in.nextToken(); y = (int) in.nval;
                in.nextToken(); k = (int) in.nval;
                add(x, k);
                add(y + 1, -k);
            } else {
                in.nextToken(); x = (int) in.nval;
                out.println(sum(x));
            }
        }
        out.flush();
        out.close();
        br.close();
    }

    public static int lowbit(int i) {
        return i & -i;
    }

    public static void add(int i, int v) {
        while (i <= n) {
            tree[i] += v;
            i += lowbit(i);
        }
    }

    public static int sum(int i) {
        int ans = 0;
        while (i > 0) {
            ans += tree[i];
            i -= lowbit(i);
        }
        return ans;
    }

    public static int range(int l, int r) { // 其实没用到
        return sum(r) - sum(l - 1);
    }
}
