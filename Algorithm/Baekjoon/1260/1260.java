package backjun;

import java.util.*;

public class Main10 {
	/* * https://www.acmicpc.net/problem/1260 */ 
	public static int N, M, V;
	public static int x, y;
	public static int[][] graph = new int[1001][1001];
	public static boolean visited[] = new boolean[10001];

	public static void DFS(int V) {
		System.out.print(V + " ");
		visited[V] = true;
		for (int i = 1; i <= N; i++) {
			if (graph[V][i] == 1 && visited[i] == false) {
				DFS(i);
			}
		}
	}


	public static void BFS(int V) {
		Queue<Integer> queue = new LinkedList<Integer>();
		int out; // 큐에 시적점 추가
		queue.offer(V);
		visited[V] = true;
		System.out.print(V + " ");
		// 큐에 값이 없을때까지 실행
		while (!queue.isEmpty()) {
			// 큐에서 값 가져옴
			out = queue.poll();
			for (int i = 1; i <= N; i++) {
				if (graph[out][i] == 1 && visited[i] == false) {
					// 방문안한 점을 찾으면, 큐에 저장
					queue.offer(i);
					visited[i] = true;
					System.out.print(i + " ");
				}
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		V = sc.nextInt();
		// 두 정점을 2차원 배열에 저장
		// 양방향이므로graph[x][y] = graph[y][x] = 1 로 저장
		for (int i = 1; i <= M; i++) {
			x = sc.nextInt();
			y = sc.nextInt();
			graph[x][y] = graph[y][x] = 1;
		}
		DFS(V);
		// reset visited
		for (int i = 1; i <= N; i++) {
			visited[i] = false;
		}
		System.out.println();
		BFS(V);
		sc.close();
	}
}