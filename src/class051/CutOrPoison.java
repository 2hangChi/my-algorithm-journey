package class051;

public class CutOrPoison {
    public static void main(String[] args) {
        System.out.println("测试开始");
        int N = 30;
        int V = 20;
        int H = 200;
        int testTimes = 10000;
        for (int i = 0; i < testTimes; i++) {
            int n = (int) (Math.random() * N) + 1;
            int[] cuts = randomArray(n, V);
            int[] poisons = randomArray(n, V);
            int hp = (int) (Math.random() * H) + 1;
            int ans1 = fast1(cuts, poisons, hp);
            int ans2 = fast2(cuts, poisons, hp);
            if (ans1 != ans2) {
                System.out.println("出错了！");
            }
        }
        System.out.println("测试结束");
    }

    public static int fast1(int[] cuts, int[] poisons, int hp) {
        int sum = 0;
        for (int num : poisons) {
            sum += num;
        }
        int[][][] dp = new int[cuts.length][hp + 1][sum + 1];
        return f1(cuts, poisons, 0, hp, 0, dp);
    }

    public static int f1(int[] cuts, int[] poisons, int i, int r, int p, int[][][] dp) {
        r -= p;
        if (r <= 0) {
            return i + 1;
        }
        if (i == cuts.length) {
            if (p == 0) {
                return Integer.MAX_VALUE;
            } else {
                return cuts.length + 1 + (r + p - 1) / p;
            }
        }
        if (dp[i][r][p] != 0) {
            return dp[i][r][p];
        }
        int p1 = r <= cuts[i] ? (i + 1) : f1(cuts, poisons, i + 1, r - cuts[i], p, dp);
        int p2 = f1(cuts, poisons, i + 1, r, p + poisons[i], dp);
        int ans = Math.min(p1, p2);
        dp[i][r][p] = ans;
        return ans;
    }

    public static int fast2(int[] cuts, int[] poisons, int hp) {
        int ans = 0;
        for (int l = 1, r = hp + 1, m; l <= r;) {
            m = (l + r) >> 1;
            if (f(cuts, poisons, hp, m)) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }

    public static boolean f(int[] cuts, int[] poisons, int hp, int m) {
        int n = Math.min(cuts.length, m); // 一开始忘了这一行 下面for() 直接用i<cuts.length了
        for (int i = 0; i < n; i++) {
            hp -= Math.max(cuts[i], poisons[i] * (m - i - 1));
        }
        return hp <= 0;
    }

    public static int[] randomArray(int n, int v) {
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = (int) (Math.random() * v) + 1;
        }
        return ans;
    }
}
