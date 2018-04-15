import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NM2 {
	public static int N, M;
	public static boolean visited[] = new boolean[8];
	public static int mat[] = new int[8];

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		dfs(0);
	}

	public static boolean check() {
		for (int i = 0; i < M - 1; i++) 
			if (mat[i] >= mat[i + 1])
				return false;
			return true;
		
	}

	public static void dfs(int n) {
		if (n == M) {
			if (check()) {
				for (int i = 0; i < M; i++) {
					System.out.print(mat[i] + " ");
				}
				System.out.println();
			}
		}
		// นÝบน
		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				mat[n] = i + 1;
				dfs(n + 1);
				visited[i] = false;
			}
		}
	}
}
