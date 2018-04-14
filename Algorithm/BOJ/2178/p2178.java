package acmicpc;

import java.util.*;

public class p2178 {
	static int[][] miro = new int[100][100];
	static int[][] visited = new int[100][100];
	static int dx[] = { 0, 0, 1, -1 };
	static int dy[] = { -1, 1, 0, 0 }; // 왼쪽, 오른쪽, 위, 아래
	static int MIN = 10000;
	static int n, m;

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		for (int i = 0; i < n; i++) {
			String str = sc.next();
			char ch[] = str.toCharArray();
			for (int j = 0; j < ch.length; j++) {
				miro[i][j] = ch[j] - '0';
			}
		}
		miro[0][0] = 2;
		bfs(0, 0);
		System.out.println(miro[n - 1][m - 1] - 1);
	}

	static void bfs(int x, int y) {
		Queue<Integer> qx = new LinkedList<Integer>();
		Queue<Integer> qy = new LinkedList<Integer>();

		qx.add(x);
		qy.add(y);

		while (!qx.isEmpty() && !qy.isEmpty()) {
			x = qx.poll();
			y = qy.poll();
			for (int k = 0; k < 4; k++) {
				int move_x = x + dx[k];
				int move_y = y + dy[k];
				if (move_x >= 0 && move_x < n && move_y >= 0 && move_y < m) {
					if (miro[move_x][move_y] == 1) {
						qx.add(move_x);
						qy.add(move_y);
						miro[move_x][move_y] = miro[x][y] + 1;
					}
				}
			}
		}

	}
}
