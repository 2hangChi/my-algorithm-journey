package class078;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class lgP1352 { // 没有上司的舞会
    public static int MAXN = 6001;

    public static int[] nums = new int[MAXN];

    public static boolean[] boss = new boolean[MAXN];

    // 链式前向星建图
    public static int[] head = new int[MAXN];

    public static int[] next = new int[MAXN];

    public static int[] to = new int[MAXN];

    public static int cnt;

    // 动态规划表
    // no[i] : i为头的整棵树，在i不来的情况下，整棵树能得到的最大快乐值
    public static int[] no = new int[MAXN];

    // no[i] : i为头的整棵树，在i来的情况下，整棵树能得到的最大快乐值
    public static int[] yes = new int[MAXN];

    public static int n, h;

    public static void build(int n) {
        Arrays.fill(boss, 1, n + 1, true);
        Arrays.fill(head, 1, n + 1, 0);
        cnt = 1;
    }

    public static void addEdge(int u, int v) {
        next[cnt] = head[u];
        to[cnt] = v;
        head[u] = cnt++;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            build(n);
            for (int i = 1; i <= n; i++) {
                in.nextToken();
                nums[i] = (int) in.nval;
            }
            for (int i = 1, low, high; i < n; i++) {
                in.nextToken();
                low = (int) in.nval;
                in.nextToken();
                high = (int) in.nval;
                addEdge(high, low);
                boss[low] = false;
            }
            for (int i = 1; i <= n; i++) {
                if (boss[i]) {
                    h = i;
                    break;
                }
            }
            f(h);
            out.println(Math.max(no[h], yes[h]));
        }
        out.flush();
        out.close();
        br.close();
    }

    public static void f(int u) {
        int n = 0;
        int y = 0;
        for (int edge = head[u]; edge != 0; edge = next[edge]) {
            // f(edge);
            // // n = Math.max(n, Math.max(yes[edge], no[edge])); // 一开始写错了算法
            // n += Math.max(yes[edge], no[edge]); // 后来又写错了节点编号
            // // y = Math.max(y, no[edge]);
            // y += no[edge];
            f(to[edge]);
            n += Math.max(yes[to[edge]], no[to[edge]]);
            y += no[to[edge]];
        }
        y += nums[u];
        no[u] = n;
        yes[u] = y;
    }

    public static void f2(int u) { // 应该这样写
        no[u] = 0;
        yes[u] = nums[u];
        for (int ei = head[u], v; ei > 0; ei = next[ei]) {
            v = to[ei];
            f(v);
            no[u] += Math.max(no[v], yes[v]);
            yes[u] += no[v];
        }
    }
}
