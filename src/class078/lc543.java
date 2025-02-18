package class078;

public class lc543 { // 二叉树的直径
    class Solution {
        public int diameterOfBinaryTree(TreeNode root) {
            return f(root).diameter - 1; // 这里就不用减 1
        }

        public static Info f(TreeNode node) {
            if (node == null) {
                return new Info(0, 0);
            }
            Info infol = f(node.left);
            Info infor = f(node.right);
            int height = 1 + Math.max(infol.height, infor.height);
            int diameter = Math.max(infol.height + infor.height + 1, Math.max(infol.diameter, infor.diameter)); // 这里如果不加 1
            return new Info(height, diameter);
        }

        public static class Info {
            int height;
            int diameter;
            public Info(int a, int b) {
                height = a;
                diameter = b;
            }
        }
    }

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }
}
