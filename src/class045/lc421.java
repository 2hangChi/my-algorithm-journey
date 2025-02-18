package class045;

import java.util.Arrays;
import java.util.HashSet;

public class lc421 {
    class Solution1 { // 我的解法
        public int findMaximumXOR(int[] nums) {
            build();
            for (int num : nums) {
                insert(num);
            }
            int ans = 0;
            for (int num : nums) {
                ans = Math.max(ans, maxOr(num));
            }
            clear();
            return ans;
        }

        public static int maxOr(int num) {
            int ans = 0, cur = 1;
            for (int i = 30; i >= 0; i--) {
                if (tree[cur][((num >> i) + 1) & 1] != 0) {
                    cur = tree[cur][((num >> i) + 1) & 1]; // 开始忘了挪cur
                    ans |= 1 << i; // 开始写成了 &= 显然不对 应该是把1或进去
                } else {
                    cur = tree[cur][(num >> i) & 1]; //
                }
            }
            return ans;
        }

        public static int MAXN = 3000001;
        public static int[][] tree = new int[MAXN][2];
        public static int cnt;
        public static void build() {
            cnt = 1;
        }

        public static void clear() {
            for (int i = 1; i <= cnt; i++) {
                Arrays.fill(tree[i], 0);
            }
        }

        public static void insert(int num) {
            int cur = 1;
            for (int i = 30; i >= 0; i--) {
                if (((num >> i) & 1) == 0) {
                    if (tree[cur][0] == 0) {
                        tree[cur][0] = ++cnt;
                    }
                    cur = tree[cur][0];
                } else {
                    if (tree[cur][1] == 0) {
                        tree[cur][1] = ++cnt;
                    }
                    cur = tree[cur][1];
                }
                // if-else 可以简化如下
//                if (tree[cur][(num >> i) & 1] == 0) {
//                    tree[cur][(num >> i) & 1] = ++cnt;
//                }
//                cur = tree[cur][(num >> i) & 1];
            }
        }
    }

    class Solution2 { // 最优解法
        public int findMaximumXOR(int[] nums) {
            int max = Integer.MIN_VALUE;
            for (int num : nums) {
                max = Math.max(max, num);
            }
            int ans = 0, better = 0;
            HashSet<Integer> set = new HashSet<>();
            for (int i = 31 - Integer.numberOfLeadingZeros(max); i >= 0; i--) {
                better = ans | (1 << i);
                set.clear();
                for (int num: nums) {
                    num = (num >> i) << i; // 忘了这个处理
                    set.add(num);
                    if (set.contains(num ^ better)) { // ^写成了|
                        ans = better;
                        break;
                    }
                }
            }
            return ans;
        }
    }
}
