package class079;

import java.util.ArrayList;

public class lc2477 { // 到达首都的最少油耗
    class Solution {
        public long minimumFuelCost(int[][] roads, int seats) {
            int n = roads.length + 1; //
            ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                graph.add(new ArrayList<>());
            }
            for (int[] road : roads) {
                graph.get(road[0]).add(road[1]);
                graph.get(road[1]).add(road[0]);
            }
            int[] size = new int[n];
            long[] cost = new long[n]; //
            f(seats, graph, 0, -1, size, cost);
            return cost[0];
        }

        public static void f(int seats, ArrayList<ArrayList<Integer>> graph, int cur, int f, int[] size, long[] cost) {
            if (cur != 0) {
                size[cur] = 1;
            }
            for (int next : graph.get(cur)) {
                if (next == f) {
                    continue;
                }
                f(seats, graph, next, cur, size, cost);
                size[cur] += size[next];
                cost[cur] += cost[next];
                cost[cur] += (size[next] + seats - 1) / seats; // size[cur] -> size[next]
            }
        }
    }
}
