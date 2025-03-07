package class051;

import java.util.PriorityQueue;

public class WaitingTime {
    public static void main(String[] args) {
        System.out.println("测试开始");
        int N = 50;
        int V = 30;
        int M = 3000;
        int testTime = 20000;
        for (int i = 0; i < testTime; i++) {
            int n = (int) (Math.random() * N) + 1;
            int[] arr = randomArray(n, V);
            int m = (int) (Math.random() * M);
            int ans1 = waitingTime1(arr, m);
            int ans2 = waitingTime2(arr, m);
            if (ans1 != ans2) {
                System.out.println("出错了！");
            }
        }
        System.out.println("测试结束");
    }

    private static int waitingTime2(int[] arr, int m) {
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int i = 0; i < arr.length; i++) {
            heap.add(new int[]{0, arr[i]});
        }
        for (int i = 0; i < m; i++) {
            int[] cur = heap.poll();
            cur[0] += cur[1];
            heap.add(cur);
        }
        return heap.poll()[0];
    }

    private static int waitingTime1(int[] arr, int m) {
        int min = Integer.MAX_VALUE;
        for (int t : arr) {
            min = Math.min(min, t);
        }
        int ans = 0;
        for (int l = 0, r = min * m, mid; l <= r;) {
            mid = (l + r) >> 1;
            if (f(arr, mid) >= m + 1) { // 而我是刚来的一个人 前面有m个人 一共m+1个人
                // 要让m+1个人都不等 --> f() >= m+1
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }

    public static int f(int[] arr, int mid) {
        int ans = 0;
        for (int t : arr) {
            ans += mid / t + 1; // 这里把 1.可以开始的服务 和 2.正在进行的服务 也算上
            // --> 服务时间mid可以让多少人不等（至少是正在接受服务 或者可以开始接受服务）
        }
        return ans;
    }

    public static int[] randomArray(int n, int v) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int) (Math.random() * v) + 1;
        }
        return arr;
    }
}
