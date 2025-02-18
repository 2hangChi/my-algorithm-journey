package class078;

import java.util.HashMap;

public class lc437 { // 路径总和Ⅲ
    class Solution {
        public static int ans;

        public int pathSum(TreeNode root, int targetSum) {
            HashMap<Long, Integer> presum = new HashMap<>();
            presum.put(0L, 1);
            ans = 0;
            f(root, targetSum, 0, presum);
            return ans;
        }

        public static void f(TreeNode node, int t, long s, HashMap<Long, Integer> presum) {
            if (node == null) {
                return;
            }
            long sum = s + node.val; // s += node.val
            ans += presum.getOrDefault(sum - t, 0);
            presum.put(sum, presum.getOrDefault(sum, 0) + 1);
            f(node.left, t, sum, presum);
            f(node.right, t, sum, presum);
            presum.put(sum, presum.get(sum) - 1);
        }
    }

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }
}
