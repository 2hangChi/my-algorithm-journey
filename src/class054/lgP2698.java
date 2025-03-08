package class054;

import java.io.*;
import java.util.Arrays;

public class lgP2698 {
    public static int MAXN = 100001;
    public static int[][] arr = new int[MAXN][2];
    public static int n, d;
    public static int[] maxDeque = new int[MAXN];
    public static int[] minDeque = new int[MAXN];
    public static int maxh, maxt, minh, mint;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            in.nextToken();
            d = (int) in.nval;
            for (int i = 0; i < n; i++){
                in.nextToken();
                arr[i][0] = (int) in.nval;
                in.nextToken();
                arr[i][1] = (int) in.nval;
            }
            int ans = compute();
            out.println(ans == Integer.MAX_VALUE ? -1 : ans);
        }
        out.flush();
        out.close();
        br.close();
    }

    public static int compute() {
        Arrays.sort(arr, 0, n, (a, b) -> a[0] - b[0]);
        maxh = maxt = minh = mint = 0;
        int ans = Integer.MAX_VALUE;
        for (int l = 0, r = 0; l < n; l++) { // 注意水滴的编号 [l, r)
            while (r < n && !ok()) {
                push(r++);
            }
            if (ok()) {
                // ans = Math.min(ans, r - l);
                ans = Math.min(ans, arr[r - 1][0] - arr[l][0]); // r -> r-1
            }
            pop(l);
        }
        return ans;
    }

    public static boolean ok() {
        int max = maxh < maxt ? arr[maxDeque[maxh]][1] : 0;
        int min = minh < mint ? arr[minDeque[minh]][1] : 0;
        return max - min >= d;
    }

    public static void push(int r) {
        while (maxh < maxt && arr[maxDeque[maxt - 1]][1] <= arr[r][1]) {
            maxt--;
        }
        maxDeque[maxt++] = r;
        while (minh < mint && arr[minDeque[mint - 1]][1] >= arr[r][1]) {
            mint--;
        }
        minDeque[mint++] = r;
    }

    public static void pop(int l) {
        if (maxh < maxt && maxDeque[maxh] == l) {
            maxh++;
        }
        if (minh < mint && minDeque[minh] == l) {
            minh++;
        }
    }
}
