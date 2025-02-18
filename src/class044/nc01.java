package class044;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class nc01 {
    public static int m, op;
    public static String[] splits;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        String line = null;
        while ((line = in.readLine()) != null) {
            build();
            m = Integer.valueOf(line);
            for (int i = 1; i <= m; i++) {
                splits = in.readLine().split(" ");
                op = Integer.valueOf(splits[0]);
                if (op == 1) {
                    insert(splits[1]);
                } else if (op == 2) {
                    delete (splits[1]);
                } else if (op == 3) {
                    out.println(search(splits[1]) > 0 ? "YES" : "NO");
                } else if (op == 4) {
                    out.println(prefixNumber(splits[1]));
                }
            }
            clear();
        }
        out.flush();
        in.close();
        out.close();
    }
    public static int MAXN = 150001;
    public static int[][] tree = new int[MAXN][26];
    public static int[] pass = new int[MAXN];
    public static int[] end = new int[MAXN];
    public static int cnt;

    public static void build() {
        cnt = 1;
    }

    public static void clear() {
        for (int i = 1; i <= cnt; i++) {
            Arrays.fill(tree[1], 0);
            pass[i] = 0;
            end[i] = 0;
        }
    }

    public static void insert(String word) {
        int cur = 1;
        pass[cur]++;
        for (int i = 0, path; i < word.length(); i++) {
            path = word.charAt(i) - 'a';
            if (tree[cur][path] == 0) {
                tree[cur][path] = ++cnt;
            }
            cur = tree[cur][path];
            pass[cur]++;
        }
        end[cur]++;
    }

    public static int search(String word) {
        int cur = 1;
        for (int i = 0, path; i < word.length(); i++) {
            path = word.charAt(i) - 'a';
            if (tree[cur][path] == 0) {
                return 0;
            }
            cur = tree[cur][path];
        }
        return end[cur];
    }

    public static int prefixNumber(String pre) {
        int cur = 1;
        for (int i = 0, path; i < pre.length(); i++) {
            path = pre.charAt(i) - 'a';
            if (tree[cur][path] == 0) {
                return 0;
            }
            cur = tree[cur][path];
        }
        return pass[cur];
    }

    public static void delete (String word) {
        if (search(word) > 0) {
            int cur = 1;
            for (int i = 0, path; i < word.length(); i++) {
                path = word.charAt(i) - 'a';
                if (--pass[tree[cur][path]] == 0) {
                    tree[cur][path] = 0;
                    return;
                }
                cur = tree[cur][path];
            }
            --end[cur];
        }
    }
}
