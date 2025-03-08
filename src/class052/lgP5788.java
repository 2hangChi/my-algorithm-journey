package class052;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

public class lgP5788 {
    public static void main(String[] args) throws IOException {
        int n = nextInt();
        int[] arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = nextInt();
        }
        int[] stack = new int[n + 1];
        int r = 0;
        for (int i = 1; i < n + 1; i++) {
            while (r > 0 && arr[stack[r - 1]] < arr[i]) {
                arr[stack[--r]] = i;
            }
            stack[r++] = i;
        }
        while (r > 0) {
            arr[stack[--r]] = 0;
        }
        out.print(arr[1]);
        for (int i = 2; i < n + 1; i++) {
            out.print(" " + arr[i]);
        }
        out.println();
        out.flush();
    }
    public static InputStream in = new BufferedInputStream(System.in);

    public static PrintWriter out = new PrintWriter(System.out);

    public static int nextInt() throws IOException {
        int ch, sign = 1, ans = 0;
        while (!Character.isDigit(ch = in.read())) {
            if (ch == '-')
                sign = -1;
        }
        do {
            ans = ans * 10 + ch - '0';
        } while (Character.isDigit(ch = in.read()));
        return (ans * sign);
    }
}
