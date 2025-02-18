package class078;

public class lc333 { // 最大二叉搜索子树
    class Solution {
        public int largestBSTSubtree(TreeNode root) {
            return f(root).maxBstSize;
        }

        public static class Info {
            public long max;
            public long min;
            public boolean isBst;
            public int maxBstSize;
            public Info(long a, long b, boolean c, int d) {
                max = a;
                min = b;
                isBst = c;
                maxBstSize = d;
            }
        }

        public static Info f(TreeNode node) {
            if (node == null) {
                return new Info(Long.MIN_VALUE, Long.MAX_VALUE, true, 0);
            }
            Info infoL = f(node.left);
            Info infoR = f(node.right);
            boolean bst = infoL.isBst && infoR.isBst && node.val > infoL.max && node.val < infoR.min;
            if (bst) {
                return new Info(infoR.maxBstSize == 0 ? node.val : infoR.max, infoL.maxBstSize == 0 ? node.val : infoL.min, true, 1 + infoL.maxBstSize + infoR.maxBstSize);
            } else {
                return new Info(Long.MAX_VALUE, Long.MIN_VALUE, false, Math.max(infoL.maxBstSize, infoR.maxBstSize));
            }
        }
    }

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }
}
