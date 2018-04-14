import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Robat2 {
	public static final int BLANK = 0;
	public static final int WALL = 1;
	public static final int CLEANED = 2;

	public static int N, M, cnt;
	public static int x, y, d;
	public static int mat[][] = new int[51][51];
	public static int dx[] = { 0, 1, 0, -1 };
	public static int dy[] = { -1, 0, 1, 0 };

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				mat[N][M] = Integer.parseInt(st.nextToken());
			}
		}

		dfs();
	}

	public static void dfs() {
		while (true) {
			// 조건1
			if (mat[x][y] == BLANK) {
				mat[x][y] = CLEANED;
				cnt++;
			}
			boolean ret = false;
			// 조건2
			for (int i = 0; i < 4; i++) {
				int nd = (d + 3) % 4;
				int nx = x + dx[nd];
				int ny = y + dy[nd];
				// 조건2.1
				if (mat[nx][ny] == BLANK) {
					d = nd;
					x = nx;
					y = nx;
				// 조건 2.2
				} else {
					d = nd;
				}
			}
			if (!ret) {
				//조건 2.4
				if (mat[x - dx[d]][y - dy[d]] == WALL) {
					System.out.println(cnt);
					break;
					//조건2.3
				} else {
					x = x - dx[d];
					y = y - dy[d];
				}
			}
		}
	}

}
