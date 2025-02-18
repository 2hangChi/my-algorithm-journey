package class078;

public class lc979 {
    class Solution {
        public int distributeCoins(TreeNode root) {
            return f(root).move;
        }

        public static Info f(TreeNode node) {
            if (node == null) {
                return new Info(0, 0, 0);
            }
            Info infol = f(node.left);
            Info infor = f(node.right);
            int cnt = infol.cnt + infor.cnt + 1;
            int sum = infol.sum + infor.sum + node.val;
            int move = infol.move + infor.move + Math.abs(cnt - sum);
            return new Info(cnt, sum, move);
        }

        public static class Info {
            int cnt;
            int sum;
            int move;
            public Info(int a, int b, int c) {
                cnt = a;
                sum = b;
                move = c;
            }
        }
    }

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }
}
