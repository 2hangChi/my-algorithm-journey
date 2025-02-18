package class078;

public class lc968 { // 监控二叉树
    class Solution {
        public static int ans;

        public int minCameraCover(TreeNode root) {
            ans = 0;
            if (f(root) == 0) {
                ans++;
            }
            return ans;
        }

        public static int f(TreeNode node) {
            if (node == null) {
                return 1;
            }
            int l = f(node.left);
            int r = f(node.right);
            if (l == 0 || r == 0) {
                ans++;
                return 2;
            }
            if (l == 2 || r == 2) {
                return 1;
            }
            return 0;
        }
    }

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }
}
