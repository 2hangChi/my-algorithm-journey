package class048;

import java.util.Arrays;

public class LCP74 {
    class Solution {
        public int fieldOfGreatestBlessing(int[][] forceField) {
            int n = forceField.length;
            long[] xs = new long[n << 1];
            long[] ys = new long[n << 1];
            for (int i = 0, k = 0; i < n; i++) {
                xs[k] = (long) (forceField[i][0] << 1) + forceField[i][2]; // ×2这一下要用long接着 否则会溢出
                ys[k++] = ((long) forceField[i][1] << 1) + forceField[i][2];
                xs[k] = (long) (forceField[i][0] << 1) - forceField[i][2];
                ys[k++] = (long) (forceField[i][1] << 1) - forceField[i][2];
                // 显然下面这样写更好 应该直接使用long进行计算防止溢出
//                long x = fields[i][0];
//                long y = fields[i][1];
//                long r = fields[i][2];
//                xs[k++] = (x << 1) - r;
//                xs[k++] = (x << 1) + r;
//                ys[p++] = (y << 1) - r;
//                ys[p++] = (y << 1) + r;
            }
            int xsize = sort(xs);
            int ysize = sort(ys);
            int[][] diff = new int[xsize + 2][ysize + 2];
            for (int i = 0, a, b, c, d; i < n; i++) {
                a = rank(xs, xsize, (long) (forceField[i][0] << 1) - forceField[i][2]); // ×2这一下要用long接着 否则会溢出
                b = rank(ys, ysize, (long) (forceField[i][1] << 1) - forceField[i][2]); // 一开始写成+
                c = rank(xs, xsize, (long) (forceField[i][0] << 1) + forceField[i][2]);
                d = rank(ys, ysize, (long) (forceField[i][1] << 1) + forceField[i][2]); // 一开始写成-
                // 想清楚在二维数组中，坐标ab是较小的xy，cd是较大的xy 所以ab是-号 cd是+号
                add(diff, a + 1, b + 1, c + 1, d + 1);
            }
            int ans = 0; // 1
            for (int i = 1; i < diff.length; i++) {
                for (int j = 1; j < diff[0].length; j++) {
                    diff[i][j] += diff[i][j - 1] + diff[i - 1][j] - diff[i - 1][j - 1];
                    ans = Math.max(ans, diff[i][j]);
                }
            }
            return ans;
        }

        public static void add(int[][] diff, int a, int b, int c, int d) {
            diff[a][b] += 1;
            diff[a][d + 1] -= 1;
            diff[c + 1][b] -= 1;
            diff[c + 1][d + 1] += 1;
        }

        public static int rank(long[] arr, int size, long num) { // 一开始写成int num
            int l = 0;
            int r = size - 1;
            int m ,ans = 0;
            while (l <= r) { // 一开始写成< 忘了二分怎么写的？
                m = (l + r) >> 1;
                if (arr[m] <= num) {
                    ans = m;
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            return ans;
        }

        public static int sort(long[] arr) {
            Arrays.sort(arr);
            int size = 1;
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] != arr[size - 1]) {
                    arr[size++] = arr[i];
                }
            }
            return size;
        }
    }
}
