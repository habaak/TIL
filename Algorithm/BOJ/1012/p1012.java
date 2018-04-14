package acmicpc;

import java.util.Scanner;

public class p1012 {
	public static int T, N, M, K;
	public static boolean[][] farm;
	public static boolean[][] visited;
	public static int dx[] = { -1, 0, 1, 0 };
	public static int dy[] = { 0, -1, 0, 1 };

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int tc = 0; tc < T; tc++) {
			M = sc.nextInt();
			N = sc.nextInt();
			K = sc.nextInt();

			int warm = 0;

			farm = new boolean[M][N];
			visited = new boolean[M][N];

			for (int i = 0; i < K; i++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				farm[x][y] = true;
			}

			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					if (farm[i][j] == true && visited[i][j] == false) {
						dfs(i, j);
						warm++;
					}
				}
			}
			System.out.println(warm);
		}
		sc.close();
	}

	static void dfs(int x, int y) {
		for (int i = 0; i < 4; i++) {
			visited[x][y] = true;
			
			int move_x = x + dx[i];
			int move_y = y + dy[i];
									
			if (move_x >= 0 && move_x < M && move_y >= 0 && move_y < N) {
				if (farm[move_x][move_y] == true && visited[move_x][move_y] == false) {
					
					dfs(move_x, move_y);
				}
			}

		}
	}
}
