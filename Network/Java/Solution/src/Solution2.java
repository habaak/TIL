import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Solution2 {
    public static int min = 9;
    public static int[] arr;
    public static Stack<Integer> stack = new Stack<>();
    
    public int solution(int[] numbers) {
        arr = numbers;
        int n = numbers.length;
        int m = 3;
        int answer = 0;
        dfs(0, n, m, new boolean[n + 1]);
        return answer;
    }
    
    public static void dfs(int depth, int n, int m, boolean[] k) {

      boolean[] vistied = new boolean[n + 1];

      if (depth == m) {
         int sum = 0;
         int t = 1;
         for (int num : stack) {
            sum += num * t;
            t *= 10;
         }

         if (sum < 100) {
            return;
           
         double d = ((double) sum / 7 - sum / 7) * 10;
         if (min > (int) d && (int) d != 0)
            min = (int) d;

         return;
      }

      for (int i = 1; i <= n; i++) {
         if (!vistied[i] && !k[i]) {
            stack.push(arr[i]);
            k[i] = true;
            dfs(depth + 1, n, m, k);
            stack.pop();
            k[i] = false;
         }
      }
   }
}