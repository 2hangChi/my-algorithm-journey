package class049;

public class lc76 {
    class Solution {
        public String minWindow(String str, String tar) {
            // 忘了加上特判
//            if (str.length() < tar.length()) {
//                return "";
//            }
            char[] s = str.toCharArray();
            char[] t = tar.toCharArray();
            int[] cnt = new int[256];
            for (char cha : t) {
                cnt[cha]--;
            }
            int ans = Integer.MAX_VALUE, start = 0;
            for (int l = 0, r = 0, debt = t.length; r < s.length; r++) {
                if (cnt[s[r]]++ < 0) {
                    debt--;
                }
                if (debt == 0) {
                    while (cnt[s[l]] > 0) {
                        cnt[s[l++]]--;
                    }
                    if (r - l + 1 < ans) {
                        ans = r - l + 1;
                        start = l;
                    }
                }
            }
            return ans == Integer.MAX_VALUE ? "" : String.valueOf(s, start, ans);
        }
    }
}
