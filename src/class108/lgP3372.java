package class108;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class lgP3372 { // 要用long
    public static int MAXN = 500001;
    public static int n, m;
    public static int[] tree1 = new int[MAXN]; // long
    public static int[] tree2 = new int[MAXN]; // long

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
            add(tree1, i, v);
            add(tree1, i + 1, -v);
            add(tree2, i, v * (i - 1));
            add(tree2, i + 1, - v * i); // i - 1 -> i
        }
        for (int i = 1, op, x, y, k; i <= m; i++) {
            in.nextToken(); op = (int) in.nval;
            if (op == 1) {
                in.nextToken(); x = (int) in.nval;
                in.nextToken(); y = (int) in.nval;
                in.nextToken(); k = (int) in.nval;
                add(tree1, x, k);
                add(tree1, y + 1, -k);
                add(tree2, x, k * (x - 1));
                add(tree2, y + 1, - k * y);
            } else {
                in.nextToken(); x = (int) in.nval;
                in.nextToken(); y = (int) in.nval;
                out.println(range(x, y));
            }
        }
        out.flush();
        out.close();
        br.close();
    }

    public static int lowbit(int i) {
        return i & -i;
    }

    public static void add(int[] tree, int i, int v) {
        while (i <= n) {
            tree[i] += v;
            i += lowbit(i);
        }
    }

    public static int sum(int[] tree, int i) { // long
        int ans = 0; // long
        while(i > 0) {
            ans += tree[i];
            i -= lowbit(i);
        }
        return ans;
    }

    public static int range(int l, int r) { // long
        return r * sum(tree1, r) - sum(tree2, r) - (l - 1) * sum(tree1, l - 1) + sum(tree2, l - 1);
    }
}



class Main { // 改成了long 集成了add方法
    public static int MAXN = 500001;
    public static int n, m;
    public static long[] tree1 = new long[MAXN];
    public static long[] tree2 = new long[MAXN];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        in.nextToken();
        n = (int) in.nval;
        in.nextToken();
        m = (int) in.nval;
        long v; //
        for (int i = 1; i <= n; i++) {
            in.nextToken();
            v = (long) in.nval;
            // add(tree1, i, v);
            // add(tree1, i + 1, -v);
            // add(tree2, i, v * (i - 1));
            // add(tree2, i + 1, - v * i); // i - 1 -> i
            add(i, i, v);
        }
        long k; //
        for (int i = 1, op, x, y; i <= m; i++) {
            in.nextToken(); op = (int) in.nval;
            if (op == 1) {
                in.nextToken(); x = (int) in.nval;
                in.nextToken(); y = (int) in.nval;
                in.nextToken(); k = (long) in.nval;
                // add(tree1, x, k);
                // add(tree1, y + 1, -k);
                // add(tree2, x, k * (x - 1));
                // add(tree2, y + 1, - k * y);
                add(x, y, k);
            } else {
                in.nextToken(); x = (int) in.nval;
                in.nextToken(); y = (int) in.nval;
                out.println(range(x, y));
            }
        }
        out.flush();
        out.close();
        br.close();
    }

    public static void add(int l, int r, long v) {
        add(tree1, l, v);
        add(tree1, r + 1, -v);
        add(tree2, l, v * (l - 1));
        add(tree2, r + 1, - v * r);
    }

    public static int lowbit(int i) {
        return i & -i;
    }

    public static void add(long[] tree, int i, long v) {
        while (i <= n) {
            tree[i] += v;
            i += lowbit(i);
        }
    }

    public static long sum(long[] tree, int i) {
        long ans = 0;
        while(i > 0) {
            ans += tree[i];
            i -= lowbit(i);
        }
        return ans;
    }

    public static long range(int l, int r) {
        return r * sum(tree1, r) - sum(tree2, r) - (l - 1) * sum(tree1, l - 1) + sum(tree2, l - 1);
    }
}