package class083;

public class lc514 {
    class Solution {
        public static int MAXN = 101;

        public static int MAXC = 26;

        public static int[] ring = new int[MAXN]; //

        public static int[] key = new int[MAXN]; //

        public static int[] size = new int[MAXC]; //

        public static int[][] where = new int[MAXC][MAXN]; //

        public static int[][] dp = new int[MAXN][MAXN]; //

        public static int n, m; //

        public int findRotateSteps(String ring, String key) {
            build(ring, key);
            return f(0, 0);
        }

        public static void build(String r, String k) {
            n = r.length();
            m = k.length();
            for (int i = 0; i < MAXC; i++) {
                size[i] = 0;
            }
            for (int i = 0, cur; i < n; i++) {
                cur = r.charAt(i) - 'a';
                ring[i] = cur;
                where[cur][size[cur]++] = i;
            }
            for (int i = 0; i < m; i++) {
                key[i] = k.charAt(i) - 'a';
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    dp[i][j] = -1;
                }
            }
        }

        public static int f(int i, int j) {
            if (j == m) {
                return 0; //
            }
            if (dp[i][j] != -1) {
                return dp[i][j];
            }
            int ans;
            if (ring[i] == key[j]) {
                ans = 1 + f(i, j + 1);
            } else {
                int jump1 = clock(i, key[j]);
                int distance1 = jump1 > i ? jump1 - i : jump1 + n - i;
                int jump2 = counterClock(i, key[j]);
                int distance2 = i > jump2 ? i - jump2 : i + n - jump2;
                ans = Math.min(distance1 + f(jump1, j), distance2 + f(jump2, j));
            }
            dp[i][j] = ans;
            return ans;
        }

        public static int clock(int i, int v) {
            int find = -1;
            int l = 0, r = size[v] - 1, m;
            while (l <= r) {
                m = (l + r) / 2;
                if (where[v][m] >= i) { // 严格来说应该不带等号 但也没影响
                    find = m;
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
            // return find != -1 ? find : where[v][0];
            return find != -1 ? where[v][find] : where[v][0]; // 忘了where[v][ ]
        }

        public static int counterClock(int i, int v) {
            int find = -1;
            int l = 0, r = size[v] - 1, m;
            while (l <= r) {
                m = (l + r) / 2;
                if (where[v][m] <= i) { // 严格来说应该不带等号 但也没影响
                    find = m;
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            // return find != -1 ? find : where[v][size[v] - 1];
            return find != -1 ? where[v][find] : where[v][size[v] - 1];
        }
    }
}
