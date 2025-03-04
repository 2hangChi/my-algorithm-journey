package class048;

public class lc1139 {
    class Solution {
        public int largest1BorderedSquare(int[][] grid) {
            int n = grid.length;
            int m = grid[0].length;
            build(grid);
            //if (sum(grid, n - 1, m - 1, 0, 0) == 0) { // 一开始写错了 ab应该是左上角，cd右下
            if (sum(grid, 0, 0, n - 1, m - 1) == 0) {
                return 0;
            }
            int ans = 1;
            for (int a = 0; a < n; a++) {
                for (int b = 0; b < m; b++) {
                    for (int c = a + ans, d = b + ans, i = ans; c < n && d < m; c++, d++, i++) {
                        if (sum(grid, a, b, c, d) - sum(grid, a + 1, b + 1, c - 1, d - 1) == i << 2) {
                            ans = i + 1; // 这里应该加一
                            // 更好的写法是for()里面i = ans + 1 然后 ==(i-1)<<2 然后 ans=i 这样i表示正在尝试的边长
                        }
                    }
                }
            }
            // return ans;
            return ans * ans;
        }

        public static void build(int[][] g) {
            for (int i = 0; i < g.length; i++) {
                for (int j = 0; j < g[0].length; j++) {
                    g[i][j] += get(g, i, j - 1) + get(g, i - 1, j) - get(g, i - 1, j - 1);
                }
            }
        }

        public static int get(int[][] g, int i, int j) {
            return i < 0 || j < 0 ? 0 : g[i][j]; // 一开始||写成了|，最好再加个括号 return (i < 0 || j < 0) ? 0 : g[i][j];
        }

        public static int sum(int[][] g, int a, int b, int c, int d) {
            // return get(g, c, d) - get(g, a - 1, d) - get(g, c, b - 1) + get(g, a - 1, b - 1);
            return a > c ? 0 : get(g, c, d) - get(g, a - 1, d) - get(g, c, b - 1) + get(g, a - 1, b - 1); // a = c的情况（一个点）都可以算
        }
    }
}
