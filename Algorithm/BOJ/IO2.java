import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class IO2 {
	static int N;
	static int nums[] = new int[11];
	static int oper[] = new int[10];
	static boolean visited[] = new boolean[10];
	static int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		int cnt = 0;
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			int tmp = Integer.parseInt(st.nextToken());
			for (int j = 0; j < tmp; j++) {
				oper[cnt++] = i+1;
			}
		}
		dfs(0, 1, nums[0], 0);
		System.out.println(max);
		System.out.println(min);
	}

	public static void dfs(int v, int idx, int num, int len) {
		int res = 0;
		if (len == N - 1) {
			if (max < res) {
				max = num;
			}
			if (min > res) {
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
