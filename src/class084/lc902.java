package class084;

public class lc902 { // 最大为N的数字组合
    class Solution {
        public int atMostNGivenDigitSet(String[] strs, int num) {
            int n = strs.length;
            int[] digits = new int[n];
            for (int i = 0; i < n; i++) {
                digits[i] = Integer.valueOf(strs[i]);
            }
            int tmp = num / 10, offset = 1, len = 1;
            while (tmp != 0) {
                tmp /= 10;
                offset *= 10;
                len++;
            }
            return f(digits, num, offset, len, 0, 0);
        }

        public static int f(int[] digits, int num, int offset, int len, int free, int fix) {
            if (len == 0) {
                return fix == 1 ? 1 : 0;
            }
            int ans = 0;
            int cur = (num / offset) % 10; // 想明白为什么要 / 10
            if (fix == 0) {
                // ans += f(digits, num, offset / 10, len - 1, 0, 0);
                ans += f(digits, num, offset / 10, len - 1, 1, 0); // 写错了
            }
            if (free == 0) {
                for (int d : digits) {
                    if (d < cur) {
                        ans += f(digits, num, offset / 10, len - 1, 1, 1);
                    } else if (d == cur) {
                        ans += f(digits, num, offset / 10, len - 1, 0, 1);
                    } else {
                        break;
                    }
                }
            } else {
                ans += digits.length * f(digits, num, offset / 10, len - 1, 1, 1);
            }
            return ans;
        }
    }
}
