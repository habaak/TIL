package acmicpc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p1012_bfs {
	static int warm;
	static int M, N, K, T;
	static boolean[][] farm;
	static boolean[][] visited;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static Queue<int[]> queue;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String in_readLine = br.readLine();

		T = Integer.parseInt(in_readLine);

		for (int tc = 0; tc < T; tc++) {
			// input
			in_readLine = br.readLine();

			StringTokenizer st = new StringTokenizer(in_readLine, " ");
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			// init
			warm = 0;
			farm = new boolean[M][N];
			visited = new boolean[M][N];
			queue = new LinkedList<int[]>();
			// input
			for (int i = 0; i < K; i++) {
				in_readLine = br.readLine();
				st = new StringTokenizer(in_readLine, " ");

				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());

				farm[x][y] = true;
			}
			// start
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					boolean val = farm[i][j];
					boolean visitval = visited[i][j];
					//
					if (visitval == true)
						continue;

					if (val == false)
						continue;

					int[] xy = new int[2];
					xy[0] = i;
					xy[1] = j;

					queue.add(xy);

					bfs();
					warm++;
				}
			}
			System.out.println(warm);
		}
	}

	static void bfs() {
		while (!queue.isEmpty()) {
			int xy[] = queue.poll();
			int x = xy[0];
			int y = xy[1];

			for (int i = 0; i < 4; i++) {
				int next_x = x + dx[i];
				int next_y = y + dy[i];
				//범위 체크
				if (next_x < 0 || next_x >= M || next_y < 0 || next_y >= N)
					continue;

				boolean val = farm[next_x][next_y];
				boolean visitVal = visited[next_x][next_y];

				// 방문 체크
				if (visitVal == true)
					continue;

				// 배추 체크
				if (val == false)
					continue;

				// 방문
				visited[next_x][next_y] = true;
				int[] arrFindXY = { next_x, next_y };
				queue.add(arrFindXY);
			}
		}
	}
}
