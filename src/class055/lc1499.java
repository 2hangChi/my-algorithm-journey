package class055;

public class lc1499 {
    class Solution {
        public static int MAXN = 100001;
        public static int[] deque = new int[MAXN];
        public static int h, t;
        public int findMaxValueOfEquation(int[][] points, int k) {
            h = t = 0;
            int ans = Integer.MIN_VALUE;
            int n = points.length;
            for (int i = 0; i < n; i++) {
                // if (h < t && i - k > deque[h]) { // 注意是points里的坐标值之间的差<=k 而不是数组下标的差，也正是因为这个误会 一开始用的if而不是while
                // 对于下标值，每次都是i每+1淘汰一个 if就行，对于points里的坐标值 是可能连续淘汰的
                while (h < t && points[i][0] - k > points[deque[h]][0]) { // 一开始只改了括号里的内容，后来才想起来if改成while
                    h++;
                }
                if (h < t) { // 答案只需要和头算一次 if就行
                    ans = Math.max(ans, points[i][0] + points[i][1] + points[deque[h]][1] - points[deque[h]][0]);
                }
                while (h < t && points[deque[t - 1]][1] - points[deque[t - 1]][0] <= points[i][1] - points[i][0]) {
                    t--;
                }
                // 一直在取points[deque[]] 本题可以直接用deque装x y值
                deque[t++] = i;
            }
            return ans;
        }
    }
}
