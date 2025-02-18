package class109;

import java.util.Arrays;

public class lc673 {
}

class Solution {
    public static int MAXN = 2001;

    public static int[] sort = new int[MAXN];

    // 维护信息 : 以数值i结尾的最长递增子序列，长度是多少
    // 维护的信息以树状数组组织
    public static int[] treeMaxLen = new int[MAXN];

    // 维护信息 : 以数值i结尾的最长递增子序列，个数是多少
    // 维护的信息以树状数组组织
    public static int[] treeMaxLenCnt = new int[MAXN];

    public static int m;

    // 查询结尾数值<=i的最长递增子序列的长度，赋值给maxLen
    // 查询结尾数值<=i的最长递增子序列的个数，赋值给maxLenCnt
    public static int maxLen, maxLenCnt;

    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        for (int i = 0, j = 1; i < n; i++, j++) {
            sort[j] = nums[i];
        }
        Arrays.sort(sort, 1, n + 1);
        m = 1;
        for (int i = 2; i <= n; i++) {
            if (sort[i] != sort[m]) {
                sort[++m] = sort[i];
            }
        }
        Arrays.fill(treeMaxLen, 1, m + 1, 0);
        Arrays.fill(treeMaxLenCnt, 1, m + 1, 0);
        for (int i = 0; i < n; i++) {
            int v = rank(nums[i]);
            query(v - 1); // -1
            // add(v, maxLen + 1, maxLenCnt); // +1
            if (maxLen == 0) {
                add(v, 1, 1);
            } else {
                add(v, maxLen + 1, maxLenCnt);
            }
        }
        query(m);
        return maxLenCnt;
    }

    public static void query(int i) {
        maxLen = 0;
        maxLenCnt = 0;
        while (i > 0) {
            if (treeMaxLen[i] > maxLen) {
                maxLen = treeMaxLen[i];
                maxLenCnt = treeMaxLenCnt[i];
            } else if (treeMaxLen[i] == maxLen) {
                maxLenCnt += treeMaxLenCnt[i];
            }
            i -= lowbit(i);
        }
    }

    // 以数值i结尾的最长递增子序列，长度达到了len，个数增加了cnt
    // 更新树状数组
    public static void add(int i, int len, int cnt) {
        while (i <= m) {
            if (treeMaxLen[i] < len) {
                treeMaxLen[i] = len;
                treeMaxLenCnt[i] = cnt;
            } else if (treeMaxLen[i] == len) {
                treeMaxLenCnt[i] += cnt;
            } else {
                break;
            }
            i += lowbit(i);
        }
    }

    public static int lowbit(int i) {
        return i & -i;
    }

    public static int rank(int v) {
        int l = 1, r = m, mid;
        int ans = 0;
        while (l <= r) {
            mid = (l + r) / 2;
            if (sort[mid] >= v) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }
}