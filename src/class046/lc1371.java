package class046;

import java.util.Arrays;
import java.util.HashMap;

public class lc1371 {
    class Solution {
        public int findTheLongestSubstring(String s) {
            HashMap<Integer, Integer> map = new HashMap<>();
            map.put(0, -1);
            int ans = 0;
            for (int i = 0, state = 0; i < s.length(); i++) {
                state ^= char2path(s.charAt(i));
                if (map.containsKey(state)) {
                    ans = Math.max(ans, i - map.get(state));
                } else {
                    map.put(state, i);
                }
            }
            return ans;
        }

        public static int char2path(char cha) {
            switch (cha) {
                case 'a' : return 1;
                case 'e' : return 2;
                case 'i' : return 4;
                case 'o' : return 8;
                case 'u' : return 16;
                default : return 0;
            }
        }
    }

    class Solution1 {
        public int findTheLongestSubstring(String s) {
            int[] map = new int[32];
            Arrays.fill(map, -2);
            map[0] = -1;
            int ans = 0;
            for (int i = 0, state = 0; i < s.length(); i++) {
                state ^= char2path(s.charAt(i));
                if (map[state] != -2) {
                    ans = Math.max(ans, i - map[state]);
                } else {
                    map[state] = i;
                }
            }
            return ans;
        }

        public static int char2path(char cha) {
            switch (cha) {
                case 'a' : return 1;
                case 'e' : return 2;
                case 'i' : return 4;
                case 'o' : return 8;
                case 'u' : return 16;
                default : return 0;
            }
        }
    }
}
