package class109;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class lgP1637 {
    public static int MAXN = 30001;

    public static int[] arr = new int[MAXN];

    public static int[] sort = new int[MAXN];

    // 维护信息 : 课上讲的up1数组
    // tree1不是up1数组，是up1数组的树状数组
    public static long[] tree1 = new long[MAXN];

    // 维护信息 : 课上讲的up2数组
    // tree2不是up2数组，是up2数组的树状数组
    public static long[] tree2 = new long[MAXN];

    public static int n, m;

    public static int lowbit(int i) {
        return i & -i;
    }

    public static void add(long[] tree, int i, long v) { // int v -> long v
        while (i <= n) { // 应该是m
            tree[i] += v;
            i += lowbit(i);
        }
    }

    public static long sum(long[] tree, int i) {
        long ans = 0;
        while (i >= 1) {
            ans += tree[i];
            i -= lowbit(i);
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        in.nextToken();
        n = (int) in.nval;
        for (int i = 1; i <= n; i++) {
            in.nextToken();
            arr[i] = (int) in.nval;
            sort[i] = arr[i];
        }
        out.println(compute());
        out.flush();
        out.close();
        br.close();
    }

    public static long compute() {
        Arrays.sort(sort, 1, n + 1);
        m = 1;
        for (int i = 2; i <= n; i++) {
            if (sort[i] != sort[m]) {
                sort[++m] = sort[i];
            }
        }
        for (int i = 1; i <= n; i++) {
            arr[i] = rank(arr[i]);
        }
        long ans = 0;
        // for(int i = n; i >= 1; i--) {
        // 求逆序对是倒着算 升序则是正着 想清楚
        for(int i = 1; i <= n; i++) {
            ans += sum(tree2, arr[i] - 1);
            add(tree1, arr[i], 1);
            add(tree2, arr[i], sum(tree1, arr[i] - 1));
        }
        return ans;
    }

    public static int rank(int num) {
        int l = 1, r = m, mid;
        int ans = 0;
        while (l <= r) {
            mid = (l + r) / 2;
            if (sort[mid] >= num) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }
}
