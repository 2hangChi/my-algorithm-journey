package class079;

import java.util.ArrayList;
import java.util.Arrays;

public class lc2322 {
    class Solution {
        public static int MAXN = 1001;

        // 下标为原始节点编号
        public static int[] dfn = new int[MAXN];

        // 下标为dfn序号
        public static int[] xor = new int[MAXN];

        // 下标为dfn序号
        public static int[] size = new int[MAXN];

        public static int dfnCnt;

        public static void f(int[] nums, ArrayList<ArrayList<Integer>> graph, int u) {
            int i = ++dfnCnt;
            dfn[u] = i;
            xor[i] = nums[u];
            size[i] = 1;
            for (int next : graph.get(u)) {
                if (dfn[next] == 0) {
                    f(nums, graph, next);
                    xor[i] ^= xor[dfn[next]];
                    size[i] += size[dfn[next]];
                }
            }
        }

        public int minimumScore(int[] nums, int[][] edges) {
            int n = nums.length;
            ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                graph.add(new ArrayList<>());
            }
            for (int[] e : edges) {
                graph.get(e[0]).add(e[1]);
                graph.get(e[1]).add(e[0]);
            }
            dfnCnt = 0;
            Arrays.fill(dfn, 1, n + 1, 0); // 忘了填0 dfn是固定变量 且后面根据dfn是否为0判断父节点
            f(nums, graph, 0);
            //
            int m = edges.length;
            int ans = Integer.MAX_VALUE;
            for (int i = 0, a, b, pre, post, sum1, sum2, sum3; i < m; i++) {
                // a = Math.max(edges[i][0], edges[i][1]);
                a = Math.max(dfn[edges[i][0]], dfn[edges[i][1]]);
                for (int j = i + 1; j < m; j++) {
                    // b = Math.max(edges[j][0], edges[j][1]);
                    b = Math.max(dfn[edges[j][0]], dfn[edges[j][1]]);
                    if (a > b) {
                        pre = b;
                        post = a;
                    } else {
                        pre = a;
                        post = b;
                    }
                    sum1 = xor[post];
                    if (pre + size[pre] > post) { // > 而不是 >=
                        sum2 = xor[pre] ^ sum1;
                        // sum3 = xor[1] ^ sum2;
                        sum3 = xor[1] ^ xor[pre];
                    } else {
                        sum2 = xor[pre];
                        sum3 = xor[1] ^ sum1 ^ sum2;
                    }
                    ans = Math.min(ans, Math.max(sum1, Math.max(sum2, sum3)) - Math.min(sum1, Math.min(sum2, sum3)));
                }
            }
            return ans;
        }
    }
}
