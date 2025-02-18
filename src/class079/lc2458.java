package class079;

public class lc2458 { // 移除子树后的二叉树高度
    class Solution {
        public static final int MAXN = 100010;

        // 下标为节点的值
        public static int[] dfn = new int[MAXN];

        // 下标为dfn序号
        public static int[] deep = new int[MAXN];

        // 下标为dfn序号
        public static int[] size = new int[MAXN];

        public static int[] maxl = new int[MAXN];

        public static int[] maxr = new int[MAXN];

        public static int dfnCnt;

        public static void f(TreeNode x, int k) {
            int i = ++dfnCnt;
            dfn[x.val] = i;
            deep[i] = k;
            size[i] = 1;
            if (x.left != null) {
                f(x.left, k + 1);
                size[i] += size[dfn[x.left.val]];
            }
            if (x.right != null) {
                f(x.right, k + 1);
                size[i] += size[dfn[x.right.val]];
            }
        }

        public int[] treeQueries(TreeNode root, int[] queries) {
            dfnCnt = 0;
            f(root, 0);
            for (int i = 1; i <= dfnCnt; i++) {
                maxl[i] = Math.max(maxl[i - 1], deep[i]);
            }
            maxr[dfnCnt + 1] = 0;
            for (int i = dfnCnt; i >= 1; i--) {
                maxr[i] = Math.max(maxr[i + 1], deep[i]);
            }
            int n = queries.length;
            int[] ans = new int[n];
            for (int i = 0, q, l, r; i < n; i++) {
                q = queries[i];
                // l = dfn[q];
                // r = l + size[l] - 1;
                l = dfn[q] - 1;
                // r = l + size[l];
                r = dfn[q] + size[dfn[q]];
                ans[i] = Math.max(maxl[l], maxr[r]);
            }
            return ans;
        }
    }

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }
}
