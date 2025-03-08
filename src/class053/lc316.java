package class053;

import java.util.Arrays;

public class lc316 {
    class Solution {
        public static int N = 26;
        public static int[] cnts = new int[N];
        public static char[] stack = new char[N];
        public static boolean[] inStack = new boolean[N];
        public static int r;
        public String removeDuplicateLetters(String str) {
            Arrays.fill(cnts, 0); // 一开始忘了清空
            Arrays.fill(inStack, false); //
            r = 0;
            char[] s = str.toCharArray();
            for (char cha : s) {
                cnts[cha - 'a']++;
            }
            for (char cha : s) {
                if (!inStack[cha - 'a']) { // 之前想起来加这个if()判断 但没把while()放进去
                    while (r > 0 && stack[r - 1] >= cha && cnts[stack[r - 1] - 'a'] > 0) {
                        inStack[stack[--r] - 'a'] = false;
                    }
                    stack[r++] = cha;
                    inStack[cha - 'a'] = true;
                }
                cnts[cha - 'a']--;
            }
            return String.valueOf(stack, 0, r);
        }
    }
}
