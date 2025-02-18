package class109;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class lgP1908 {
    public static int MAXN = 500001;

    public static int[] arr = new int[MAXN];

    public static int[] help = new int[MAXN];

    public static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        in.nextToken();
        n = (int) in.nval;
        for (int i = 1; i <= n; i++) {
            in.nextToken();
            arr[i] = (int) in.nval;
        }
        out.println(compute());
        out.flush();
        out.close();
        br.close();
    }

    public static long compute() {
        return f(1, n);
    }

    public static long f(int l, int r) {
        if (l == r) return 0;
        int m = (l + r) / 2;
        return f(l , m) + f(m + 1, r) + merge(l, m, r);
    }

    public static long merge(int l, int m, int r) {
        long ans = 0;
        for (int i = m, j = r; i >= l; i--) {
            while (j > m && arr[j] >= arr[i]) {
                j--;
            }
            ans += j - m;
        }
        int k = l;
        int a = l, b = m + 1;
        while (a <= m && b <= r) {
            help[k++] = arr[a] <= arr[b] ? arr[a++] : arr[b++];
        }
        while (a <= m) {
            help[k++] = arr[a++];
        }
        while (b <= r) {
            help[k++] = arr[b++];
        }
        for (int i = l; i <= r; i++) {
            arr[i] = help[i];
        }
        return ans;
    }
}


class Main { // 树状数组
    public static int MAXN = 500001;

    public static int[] arr = new int[MAXN];

    public static int[] sort = new int[MAXN];

    public static int[] tree = new int[MAXN];

    public static int n, m;

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
        Arrays.sort(sort, 1, n + 1); // Arrays.sort(int[] a, int fromIndex, int toIndex) 从fromIndex到toIndex-1的元素排序
        m = 1;
        for (int i = 2; i <= n; i++) {
            // 两种去重都可以
            // if (sort[i] != sort[i - 1]) {
            //     sort[++m] = sort[i];
            // }
            if (sort[i] != sort[m]) {
                sort[++m] = sort[i];
            }
        }
        for (int i = 0; i <= n; i++) {
            arr[i] = rank(arr[i]);
        }
        long ans = 0;
        for (int i = n; i >= 1; i--) { // i >= 0 -> i >= 1
            // i = 0时 arr[0] = 0 调add(0)会死循环 注意！！！
            ans += sum(arr[i] - 1); // arr[i] -> arr[i] - 1
            // v的词频++之前 查询的是1到v-1的词频之和 调 sum(v - 1) 而不是 sum(v)
            add(arr[i]);
        }
        return ans;
    }


    public static int lowbit(int i) {
        return i & -i;
    }

    public static void add(int i) {
        while (i <= n) {  // 应该是m
            tree[i] += 1;
            i += lowbit(i);
        }
    }

    public static long sum(int i) {
        long ans = 0;
        while (i >= 1) {
            ans += tree[i];
            i -= lowbit(i);
        }
        return ans;
    }

    public static int rank(int num) {
        int l = 1, r = m, mid;
        int ans = 0;
        while (l <= r) {
            mid = (l + r) / 2;
            if (sort[mid] <= num) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }
}