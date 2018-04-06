package backjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
//9880 212
public class bj_11403_dfs {

	public static int map[][];
	public static int check[][];
	public static boolean visited[];

	public static int num;

	public static void dfs(int x) {

		for (int i = 0; i < num; i++) {
			if (map[x][i] == 1 && visited[i] == false) {
				visited[i] = true;
				dfs(i);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		num = Integer.parseInt(br.readLine());
		StringTokenizer st = null;

		map = new int[num][num];
		check = new int[num][num];
		visited = new boolean[num];

		for (int i = 0; i < num; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < num; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

			}
		}
		for (int i = 0; i < num; i++) {
			dfs(i);
			for (int j = 0; j < num; j++) {
				if (visited[j] == true) {
					check[i][j] = 1;
				}
			}
			Arrays.fill(visited,false);
		}

		for (int i = 0; i < num; i++) {
			
			for (int j = 0; j < num; j++) {
				System.out.print(check[i][j]+" ");

			}
			System.out.println();

		}

	}
}