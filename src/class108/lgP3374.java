package class108;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class lgP3374 {
    public static int MAXN = 500001;
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
        for (int i = 1, v; i <= n; i++) {
            in.nextToken();
            v = (int) in.nval;
            add(i, v);
        }
        for (int i = 1, a, b, c; i <= m; i++) {
            in.nextToken(); a = (int) in.nval;
            in.nextToken(); b = (int) in.nval;
            in.nextToken(); c = (int) in.nval;
            if (a == 1) {
                add(b, c);
            } else {
                out.println(range(b, c));
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

    public static int range(int l, int r) {
        return sum(r) - sum(l - 1);
    }
}