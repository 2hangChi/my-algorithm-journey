package class048;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class lgP3397 {
    public static int n, m;
    public static int MAXN = 1002;
    public static int[][] diff = new int[MAXN][MAXN];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            in.nextToken();
            m = (int) in.nval;
            clear();
            for (int i = 0, a, b, c, d; i < m; i++) {
                in.nextToken();
                a = (int) in.nval;
                in.nextToken();
                b = (int) in.nval;
                in.nextToken();
                c = (int) in.nval;
                in.nextToken();
                d = (int) in.nval;
                add(a, b, c, d);
            }
            build();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n - 1; j++) {
                    out.print(diff[i + 1][j + 1] + " ");
                }
                out.println(diff[i + 1][n]);
            }
        }
        out.flush();
        out.close();
        br.close();
    }

    public static void clear() {
        for (int i = 0; i < MAXN; i++) {
            for (int j = 0; j < MAXN; j++) { // 一开始写成了i++
                diff[i][j] = 0;
            }
        }
    }

    public static void build() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                diff[i + 1][j + 1] += diff[i][j + 1] + diff[i + 1][j] - diff[i][j];
            }
        }
    }

    public static void add(int a, int b, int c, int d) {
        diff[a][b] += 1;
        diff[a][d + 1] -= 1;
        diff[c + 1][b] -= 1;
        diff[c + 1][d + 1] += 1;
    }
}