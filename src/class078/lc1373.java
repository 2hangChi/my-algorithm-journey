package class078;

public class lc1373 {

    class Solution {
        public int maxSumBST(TreeNode root) {
            return f(root).maxBstSum;
        }

        public static Info f(TreeNode node) {
            if (node == null) {
                return new Info(Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 0, true);
            }
            Info infol = f(node.left);
            Info infor = f(node.right);
            int max = Math.max(infol.max, Math.max(infor.max, node.val));
            int min = Math.min(infol.min, Math.min(infor.min, node.val));
            int sum = infol.sum + infor.sum + node.val;
            int maxBstSum = Math.max(infol.maxBstSum, infor.maxBstSum);
            boolean isBst = infol.isBst && infor.isBst && node.val > infol.max && node.val < infor.min;
            if (isBst) {
                maxBstSum = Math.max(maxBstSum, infol.sum + infor.sum + node.val);
            }
            return new Info(max, min, sum, maxBstSum, isBst);
        }

        public static class Info {
            int max;
            int min;
            int sum;
            int maxBstSum;
            boolean isBst;
            public Info(int a, int b, int c, int d, boolean e) {
                max = a;
                min = b;
                sum = c;
                maxBstSum = d;
                isBst = e;
            }
        }
    }

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }
}
