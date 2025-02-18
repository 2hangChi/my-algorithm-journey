package class045;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class lc212 {
    class Solution { // 我默写的
        public List<String> findWords(char[][] board, String[] words) {
            build(words);
            List<String> ans = new ArrayList<>();
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    dfs(board, i, j, 1, ans);
                }
            }
            clear(); //
            return ans;
        }

        public static int MAXN = 100001;
        public static int[][] tree = new int[MAXN][26];
        public static int[] pass = new int[MAXN];
        public static String[] end = new String[MAXN];
        public static int cnt;
        public static void build(String[] words) {
            cnt = 1; // 一开始忘了 cnt和cur差点搞混了
            for (String word : words) {
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
                end[cur] = word;
            }
        }

        public static void clear() {
            for (int i = 1; i <= cnt; i++) {
                Arrays.fill(tree[i], 0);
                pass[i] = 0;
                end[i] = null;
            }
        }

        public static int dfs(char[][] board, int i, int j, int c, List<String> ans) {
            if (i < 0 || j < 0 || i == board.length || j == board[0].length || board[i][j] == 0) {
                return 0;
            }
            int path = board[i][j] - 'a';
            c = tree[c][path]; // 之前已经到达节点c，然后看在当前格子的字母有没有路，复用了变量c
            if (pass[c] == 0) {
                return 0;
            }
            // 这之后，说明到当前格子，字母都用上了（i，j有必要来）
            char tmp = board[i][j];
            int fix = 0;
            board[i][j] = 0;
            if (end[c] != null) {
                ans.add(end[c]);
                end[c] = null;
                fix += 1;
            }
            fix += dfs(board, i, j + 1, c, ans);
            fix += dfs(board, i, j - 1, c, ans);
            fix += dfs(board, i + 1, j, c, ans);
            fix += dfs(board, i - 1, j, c, ans);
            board[i][j] = tmp;
            pass[c] -= fix; // 一开始忘了
            return fix;
        }
    }
}
