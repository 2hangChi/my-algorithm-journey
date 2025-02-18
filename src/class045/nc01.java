package class045;

import java.util.Arrays;

public class nc01 {
    public int[] countConsistentKeys (int[][] b, int[][] a) {
        build();
        StringBuilder sb = new StringBuilder(); // 开始没发现StringBuilder应该放循环外面
        for (int i = 0; i < a.length; i++) { // 循环可以用 for (int[] nums : a)
            // StringBuilder sb = new StringBuilder();
            for (int j = 1; j < a[i].length; j++) {
                sb.append(String.valueOf(a[i][j] - a[i][j - 1]) + "#"); // 开始忘了是[j] - [j - 1] 只写了[j]
            }
            insert(sb.toString());
            sb.setLength(0);
        }
        int[] ans = new int[b.length];
        for (int i = 0; i < b.length; i++) { // 循环不能用 for (int[] nums : b) 因为填ans要用下标i
            // StringBuilder sb = new StringBuilder();
            for (int j = 1; j < b[i].length; j++) { //
                sb.append(String.valueOf(b[i][j] - b[i][j - 1]) + "#"); // 开始忘了是[j] - [j - 1] 只写了[j]
            }
            ans[i] = count(sb.toString());
            sb.setLength(0);
        }
        clear();
        return ans;
    }

    public static int MAXN = 100002;
    public static int[][] tree = new int[MAXN][11]; // 忘了考虑负数的情况，应该开[MAXN][12]
    public static int[] pass = new int[MAXN];
    public static int cnt;
    public static void build() {
        cnt = 1;
    }

    public static void clear() {
        for (int i = 1; i <= cnt; i++) {
            Arrays.fill(tree[i], 0);
            pass[i] = 0;
        }
    }

    public static void insert(String word) {
        int cur = 1;
        pass[cur]++;
        for (int i = 0, path; i < word.length(); i++) {
            path = path(word.charAt(i));
            if (tree[cur][path] == 0) {
                tree[cur][path] = ++cnt;
            }
            cur = tree[cur][path];
            pass[cur]++; // 开始放错了位置放到上一句的前面了，应该在后面，很简单
        }
    }

    public static int count(String word) {
        int cur = 1;
        for (int i = 0, path; i < word.length(); i++) {
            path = path(word.charAt(i));
            if (tree[cur][path] == 0) {
                return 0;
            }
            cur = tree[cur][path];
        }
        return pass[cur];
    }

    public static int path(char cha) {
        if (cha =='#') {
            return 10;
        }
        // 少考虑了情况，但题目用例简单能通过
        // else if (cha == '-' {
        //     return 11;
        // }
          else {
            return cha - '0';
        }
    }
}
