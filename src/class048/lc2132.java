package class048;

public class lc2132 {
    class Solution {
        public boolean possibleToStamp(int[][] grid, int stampHeight, int stampWidth) {
            int n = grid.length;
            int m = grid[0].length;
            int[][] getsum = new int[n + 1][m + 1];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    getsum[i + 1][j + 1] = grid[i][j];
                    getsum[i + 1][j + 1] += getsum[i + 1][j] + getsum[i][j + 1] - getsum[i][j];
                }
            }
            int[][] diff = new int[n + 2][m + 2];
            for (int i = 0; i <= n - stampHeight; i++) {
                for (int j = 0; j <= m - stampWidth; j++) {
                    if (sum(getsum, i + 1, j + 1, i + stampHeight, j + stampWidth) == 0) {
                        diff[i + 1][j + 1] += 1;
                        diff[i + 1][j + stampWidth + 1] -= 1;
                        diff[i + stampHeight + 1][j + 1] -= 1;
                        diff[i + stampHeight + 1][j + stampWidth + 1] += 1;
                    }
                }
            }
            for (int i = 1; i < n + 2; i++) {
                for (int j = 1; j < m + 2; j++) {
                    diff[i][j] += diff[i][j - 1] + diff[i - 1][j] - diff[i - 1][j - 1];
                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j] == 0 && diff[i + 1][j + 1] == 0) {
                        return false;
                    }
                }
            }
            return true;
        }

        public static int sum(int[][] g, int a, int b, int c, int d) {
            return g[c][d] - g[a - 1][d] - g[c][b - 1] + g[a - 1][b - 1];
        }
    }
}
