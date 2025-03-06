package class049;

import java.util.Arrays;

public class lc395 {
    class Solution {
        public int longestSubstring(String str, int k) {
            char[] s = str.toCharArray();
            int[] cnts= new int[256];
            int ans = 0;
            for (int require = 1; require <= 26; require++) {
                Arrays.fill(cnts, 0); // 一开始忘了清空词频
                for (int l = 0, r = 0, collect = 0, satisfy = 0; r < s.length; r++) {
                    if (cnts[s[r]] == 0) {
                        collect++;
                    } // 一开始用了else if 是不对的
                    if (cnts[s[r]] == k - 1) {
                        satisfy++;
                    }
                    cnts[s[r]]++;
                    while (collect > require) {
                        if (cnts[s[l]] == 1) {
                            collect--;
                        } // 一开始用了else if 是不对的
                        if (cnts[s[l]] == k) {
                            satisfy--;
                        }
                        cnts[s[l++]]--;
                    }
                    if (satisfy == require) {
                        ans = Math.max(ans, r - l + 1);
                    }
                }
            }
            return ans;
        }
    }
}
