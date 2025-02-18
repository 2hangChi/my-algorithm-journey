package class046;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.HashMap;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class nc01 {
    public static int MAXN = 100001;
    public static int[] arr = new int[MAXN];
    public static int n, aim;
    public static HashMap<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            in.nextToken();
            aim = (int) in.nval;
            for (int i = 0; i < n; i++) {
                in.nextToken();
                arr[i] = (int) in.nval;
            }
            out.print(compute());
        }
        out.flush();
        out.close();
        br.close();
    }

    public static int compute() { // 我写的
        map.clear();
        map.put(0, -1);
        for (int i = 0, pre = 0; i < n; i++) {
            pre += arr[i];
            if (!map.containsKey(pre)) {
                map.put(pre, i);
            }
        }
        int ans = 0;
        for (int i = 0, pre = 0; i < n; i++) {
            pre += arr[i];
            if (map.containsKey(pre - aim)) {
                ans = Math.max(ans, i - map.get(pre - aim)); // 应该放在一个for循环里，且这一步先进行。
                // 我这样写，正好ans取max取不到i - map.get(pre - aim)为负数的情况也能得到正确答案，但计算过程不合理
            }
        }
        return ans;
    }

    public static int compute1() { // 答案
        map.clear();
        // 重要 : 0这个前缀和，一个数字也没有的时候，就存在了
        map.put(0, -1);
        int ans = 0;
        for (int i = 0, sum = 0; i < n; i++) {
            sum += arr[i];
            if (map.containsKey(sum - aim)) {
                ans = Math.max(ans, i - map.get(sum - aim));
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return ans;
    }


    public static void main1(String[] args) throws IOException { // 短写法 不应该这样写
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            int n = (int) in.nval;
            in.nextToken();
            int aim = (int) in.nval;
            HashMap<Integer, Integer> map = new HashMap<>(); // 有多组测试数据时多开空间，应该按照老师的写法只声明一个HashMap
            map.put(0, -1);
            int ans = 0;
            for (int i = 0, pre = 0, cur; i < n; i++) {
                in.nextToken();
                cur = (int) in.nval;
                pre += cur;
                if (map.containsKey(pre - aim)) {
                    ans = Math.max(ans, i - map.get(pre - aim));
                }
                if (!map.containsKey(pre)) {
                    map.put(pre, i);
                }
            }
            out.println(ans);
        }
        out.flush();
        out.close();
        br.close();
    }
}
