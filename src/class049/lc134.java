package class049;

public class lc134 {
    class Solution { // 通过部分，会超时
        // 这是每次都重新扩 肯定不行
        public int canCompleteCircuit(int[] gas, int[] cost) {
            int n = gas.length;
            int[] go = new int[n];
            for (int i = 0; i < n; i++) {
                go[i] = gas[i] - cost[i];
            }
            for (int l = 0, r = 0, sum; l < n; l++) {
                if (go[l] >= 0) {
                    sum = go[l];
                    r = (l + 1) % n;
                    while (sum + go[r] >= 0) {
                        sum += go[r];
                        r = (r + 1) % n; // r++
                        if (l == r % n) { // r + 1 -> r
                            return l;
                        }
                    }
                }
            }
            return -1;
        }
    }

    class Solution1 {
        public int canCompleteCircuit(int[] gas, int[] cost) {
            int n = gas.length;
            for (int l = 0, r = 0, sum = 0, len = 0; l < n; l++) { // [l, r]
                while (sum >= 0) {
                    if (len == n) { // 不能放到两行后，因为sum+=...之后sum不一定还>=0
                        return l;
                    }
                    r = (l + (len++)) % n;
                    sum += gas[r] - cost[r];
                }
                len--; // 开始忘了
                sum -= gas[l] - cost[l];
            }
            return -1;
        }
    }

    class Solution2 { // 加速了，可以直接跳到l = r
        public int canCompleteCircuit(int[] gas, int[] cost) {
            int n = gas.length;
            for (int l = 0, r = 0, sum = 0; l < n; l = r, sum = 0) { // [l, r)
                while (sum >= 0) {
                    if (r - l == n) {
                        return l;
                    }
                    sum += gas[r % n] - cost[r % n];
                    r++;
                }
            }
            return -1;
        }
    }
}
