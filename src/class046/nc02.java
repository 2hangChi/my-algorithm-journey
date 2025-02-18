package class046;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.HashMap;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class nc02 { // 应该按照老师的写法只声明一个HashMap
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            // in.nextToken(); // while循环里已经nextToken() 重复了
            int n = (int) in.nval;
            HashMap<Integer, Integer> map = new HashMap<>();
            map.put(0, -1);
            int ans = 0;
            for (int i = 0, pre = 0, cur; i < n; i++) {
                in.nextToken();
                cur = (int) in.nval;
                if (cur != 0) {
                    cur = cur > 0 ? 1 : -1;
                }
                pre += cur;
                if (map.containsKey(pre)) {
                    ans = Math.max(ans, i - map.get(pre));
                } else {
                    map.put(pre, i);
                }
                // if (!map.containsKey(pre)) {
                //     map.put(pre, i);
                // }
                // 和nc01中一样，nc01中是if (map.containsKey(sum - aim))和if (!map.containsKey(sum))
                // 本题aim=0，所以能写成if-else
            }
            out.println(ans);
        }
        out.flush();
        out.close();
        br.close();
    }
}