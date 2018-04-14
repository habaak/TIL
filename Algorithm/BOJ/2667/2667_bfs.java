import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
 
public class Al2667 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[][] map = new int[n][n];
		for(int i=0; i<n; i++) {
			String line = sc.next();
			for(int j=0; j<n; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}
		bfs(n, map);
	}
 
	public static void bfs(int n, int[][] map) {
		int[][] dir = new int[][] {{-1,0},{0,1},{1,0},{0,-1}};
		int subdivisionNum = 0;
		List<Integer> houseNumList = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] == 1) {
					int houseNum = 0;
					Queue<int[]> q = new LinkedList<>();
					q.offer(new int[] { i, j });
					map[i][j] = 2; //방문했다는 표시
					houseNum++;
					
					while(!q.isEmpty()) {
						int[] nowPos = q.poll();
						for(int d=0; d<dir.length; d++) {
							int[] nextPos = new int[] {nowPos[0] + dir[d][0], nowPos[1]+dir[d][1]};
							if(0<=nextPos[0]&&nextPos[0]<n && 0<=nextPos[1]&&nextPos[1]<n && map[nextPos[0]][nextPos[1]] == 1) {
								q.offer(nextPos);
								map[nextPos[0]][nextPos[1]] = 2;
								houseNum++;
							}
						}
					}
					houseNumList.add(houseNum);
					subdivisionNum++;
				}
			}
		}
		Collections.sort(houseNumList);
		System.out.println(subdivisionNum);
		for(int houseNum : houseNumList) {
			System.out.println(houseNum);
		}
	}
}