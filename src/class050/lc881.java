package class050;

import java.util.Arrays;

public class lc881 {
    class Solution {
        public int numRescueBoats(int[] people, int limit) {
            Arrays.sort(people);
            int ans = 0;
            for (int l = 0, r = people.length - 1; l <= r;) {
                if (people[l] + people[r] <= limit) {
                    l++;
                }
                r--;
                ans++;
            }
            return ans;
        }
    }
}
