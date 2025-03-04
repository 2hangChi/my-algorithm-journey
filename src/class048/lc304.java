package class048;

public class lc304 {
    class NumMatrix {
        public int[][] pre;

        public NumMatrix(int[][] matrix) {
            pre = new int[matrix.length + 1][matrix[0].length + 1];
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    pre[i + 1][j + 1] = matrix[i][j];
                    // pre[i + 1][j + 1] += pre[i][j - 1] + pre[i - 1][j] - pre[i - 1][j - 1];
                    // 开始忘了等号右边i j也要+1
                    pre[i + 1][j + 1] += pre[i + 1][j] + pre[i][j + 1] - pre[i][j];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            row1 += 1;
            col1 += 1;
            row2 += 1;
            col2 += 1; // 开始忘了调整坐标值+1
            return pre[row2][col2] - pre[row1 - 1][col2] - pre[row2][col1 - 1] + pre[row1 - 1][col1 - 1];
            // 只调整row2 col2即可 这样更简单
//            row2 += 1;
//            col2 += 1;
//            return pre[row2][col2] - pre[row1][col2] - pre[row2][col1] + pre[row1][col1];
        }
    }
}

