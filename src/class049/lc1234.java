package class049;

public class lc1234 {
    class Solution {
        public int balancedString(String str) {
            int n = str.length();
            int[] s = new int[n];
            int[] cnts = new int[4];
            for (int i = 0; i < n; i++) {
                char c = str.charAt(i);
                s[i] = c == 'Q' ? 0 : (c == 'W' ? 1 : (c == 'E' ? 2 : 3));
                cnts[s[i]]++;
            }
            int require = n >> 2;
            int ans = n; // Integer.MAX_VALUE
            for (int l = 0, r = 0; l < n; l++) {
                while (!ok(require, cnts) && r < n) {
                    cnts[s[r++]]--;
                }
                if (ok(require, cnts)) {
                    ans = Math.min(ans, r - l);
                }
                cnts[s[l]]++;
            }
            // 如下也能通过
//            for (int l = 0, r = 0; r < n + 1; r++) { // 允许r=n
//                while (ok(require, cnts)) {
//                    ans = Math.min(ans, r - l);
//                    if (ans == 0) { // ans=0时 l最后会跟着r到n，下面s[l]就就会越界 ans=0一开始就可以返回，这样也就避免了l走到n
//                        return ans;
//                    }
//                    cnts[s[l++]]++; // 如果直接在这里判断l<n才取s[l]的话，ans=0时会无限循环
//                }
//                if (r < n) {
//                    cnts[s[r]]--;
//                }
//            }
            return ans;
        }

        public static boolean ok(int require, int[] cnts) {
            return cnts[0] <= require && cnts[1] <= require && cnts[2] <= require && cnts[3] <= require;
        }
    }
}
