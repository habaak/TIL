import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.BufferUnderflowException;
import java.util.StringTokenizer;

public class InsertOperator {
	public static int N;
	public static int[] nums = new int[11];
	public static int[] oper = new int[10];
	public static boolean[] visited = new boolean[10];
	public static int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int idx = 0;

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			int cnt = Integer.parseInt(st.nextToken());
			for (int j = 0; j < cnt; j++) {
				oper[idx++] = i + 1;
			}
		}
		dfs(0, 1, nums[0], 0);
		System.out.println(max);
		System.out.println(min);
	}

	public static void dfs(int v, int idx, int num, int len) {
		int res = 0;
		if (len == N - 1) {
			if (num > max) {
				max = num;
			}
			if (num < min) {
				min = num;
			}
		} else {
			for (int i = 0; i < N - 1; i++) {
				if (!visited[i]) {
					switch (oper[i]) {
					case 1:
						res = num + nums[idx];
						break;
					case 2:
						res = num - nums[idx];
						break;
					case 3:
						res = num * nums[idx];
						break;
					case 4:
						res = num / nums[idx];
						break;
					}
					visited[i] = true;
					dfs(i, idx + 1, res, len + 1);
				}
			}
		}
		visited[v] = false;
	}
}
