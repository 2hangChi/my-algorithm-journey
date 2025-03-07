package class050;

import java.util.Arrays;

public class lc475 {
    class Solution {
        public int findRadius(int[] houses, int[] heaters) {
            Arrays.sort(houses); // 一开始忘了排序
            Arrays.sort(heaters);
            int ans = 0;
            for (int a = 0, b = 0; a < houses.length;) { // 一开始a++放在for()里面了
                if (best(houses, heaters, a, b)) {
                    ans = Math.max(ans, Math.abs(heaters[b] - houses[a]));
                    a++;
                } else {
                    b++;
                }
            }
            return ans;
        }

        public boolean best(int[] houses, int[] heaters, int a, int b) {
            return b == heaters.length - 1 || Math.abs(heaters[b] - houses[a]) < Math.abs(heaters[b + 1] - houses[a]);
        }
    }
}
