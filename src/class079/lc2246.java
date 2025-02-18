package class079;

import java.util.ArrayList;

public class lc2246 { // 相邻字符不同的最长路径
    class Solution {
        public int longestPath(int[] parent, String str) {
            char[] s = str.toCharArray();
            int n = parent.length;
            ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                graph.add(new ArrayList<>());
            }
            for (int i = 1; i < n; i++) { //
                graph.get(parent[i]).add(i);
            }
            return f(0, s, graph).max;
        }

        public static Info f(int cur, char[] s, ArrayList<ArrayList<Integer>> graph) {
//            if (graph.get(cur).size() == 0) {
            if (graph.get(cur).isEmpty()) {
                return new Info(1, 1);
            }
            int max = 1;
            // int maxFromHead = 1;
            int max1 = 0, max2 = 0;
            for (int next : graph.get(cur)) {
                Info nextInfo = f(next, s, graph);
                max = Math.max(max, nextInfo.max);
                if (s[cur] != s[next]) {
                    if (nextInfo.maxFromHead > max1) {
                        max2 = max1;
                        max1 = nextInfo.maxFromHead;
                    } else if (nextInfo.maxFromHead > max2) {
                        max2 = nextInfo.maxFromHead;
                    }
                }
            }
            // maxFromHead = max1 + max2 + 1;
            // max = Math.max(max, maxFromHead);
            int maxFromHead = max1; // maxFromHead = Math.max(max1, max2) + 1;
            max = Math.max(max, max1 + max2 + 1);
            return new Info(max, maxFromHead);
        }

        public static class Info {
            int max;
            int maxFromHead;
            public Info(int a, int b) {
                max = a;
                maxFromHead = b;
            }
        }
    }
}
