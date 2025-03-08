package class054;

public class lc1438 {
    class Solution {
        public static int MAXN = 100001;
        public static int[] maxDeque = new int[MAXN];
        public static int[] minDeque = new int[MAXN];
        public static int minh, mint, maxh, maxt;
        public static int[] arr;
        public int longestSubarray(int[] nums, int limit) {
            maxh = maxt = minh = mint = 0; //
            arr = nums;
            int ans = 0;
            int n = nums.length;
            for (int l = 0, r = 0; l < n; l++) {
                while (r < n && ok(nums[r], limit)) {
                    push(r++);
                }
                ans = Math.max(ans, r - l);
                pop(l);
            }
            return ans;
        }

        public static boolean ok(int num, int limit) {
            int max = maxh < maxt ? Math.max(arr[maxDeque[maxh]], num) : num; // maxDeque[maxh] -> arr[maxDeque[maxh]]
            int min = minh < mint ? Math.min(arr[minDeque[minh]], num) : num; // minDeque[minh] -> arr[minDeque[minh]]
            return (max - min) <= limit;
        }

        public static void push(int r) {
            while (maxh < maxt && arr[maxDeque[maxt - 1]] <= arr[r]) { // maxDeque[maxt - 1] -> arr[maxDeque[maxt - 1]]
                maxt--;
            }
            maxDeque[maxt++] = r;
            while (minh < mint && arr[minDeque[mint - 1]] >= arr[r]) { // minDeque[mint - 1] -> arr[minDeque[mint - 1]]
                mint--;
            }
            minDeque[mint++] = r;
        }

        public static void pop(int l) {
            if (maxh < maxt && maxDeque[maxh] == l) { // push里弹出时总是检查h<t 而pop：上一题固定长度，deque里一定有元素，可以不检测
                                                      // 本题看主流程的调用 不能保证deque不空，有必要检查h<t
                // if (maxh == l) {
                maxh++;
            }
            if (minh < mint && minDeque[minh] == l) {
                // if (minh == l) {
                minh++;
            }
        }
    }
}
