import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj_1012 {
	public static int[] xx = { 1, -1, 0, 0 };
	public static int[] yy = { 0, 0, 1, -1 };
	public static int map[][];
	public static int check[][];
	public static int count, row, col, bachu, x, y, num, bx, by;

	public static void bfs(int x, int y, int num) {
		Queue<Point> q = new LinkedList<Point>();
		q.offer(new Point(x, y));
		check[x][y] = num;

		while (!q.isEmpty()) {
			bx = q.peek().x;
			by = q.peek().y;
			q.poll();
			for (int i = 0; i < 4; i++) {
				int nextX = bx + xx[i];
				int nextY = by + yy[i];

				if (nextX < 0 || nextX > row - 1 || nextY < 0 || nextY > col - 1) {
					continue;
				}
				if (map[nextX][nextY] == 1 && check[nextX][nextY] == 0) {
					check[nextX][nextY] = num;
					q.offer(new Point(nextX, nextY));
				}
			}
		}
		num++;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		count = sc.nextInt();
		for (int i = 0; i < count; i++) {
			row = sc.nextInt();
			col = sc.nextInt();
			bachu = sc.nextInt();
			num = 1;

			map = new int[row][col];
			check = new int[row][col];

			for (int j = 0; j < bachu; j++) {
				x = sc.nextInt();
				y = sc.nextInt();
				map[x][y] = 1;
			}

			for (int v = 0; v < row; v++) {
				for (int j = 0; j < col; j++) {
					if (map[v][j] == 1 && check[v][j] == 0) {
						bfs(v, j, num);
					}
				}
			}
			System.out.println(num - 1);

		}

		sc.close();

	}

}
