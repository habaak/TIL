package acmicpc;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class p1260 {
	public static int N, M, V;
	public static boolean matrix[][]= new boolean[1001][1001];
	public static boolean visited[] = new boolean[10001];

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		V = sc.nextInt();
		
		int x,y;
		for (int i = 0; i < M; i++) {
			x = sc.nextInt();
			y = sc.nextInt();
			
			matrix[x][y] = true;
			matrix[y][x] = true;
		}
		dfs(V,N);
		System.out.println();
		for(int i=1 ; i<=N ; i++){
			visited[i] = false; 
		}

		bfs(V,N);
	}
	static void dfs(int v, int n) {
		visited[v]=true;
		System.out.print(v+" ");
		
		for (int i = 1; i <= n; i++) {
			if(matrix[v][i]==true && visited[i]==false) {
				dfs(i,n);
			}
		}
	}
	static void bfs(int v, int n) {
		Queue<Integer> queue = new LinkedList<Integer>();
		int out;
		System.out.print(v+" ");
		queue.offer(v);
		visited[v] = true;
		
		while(!queue.isEmpty()) {
			out = queue.poll();
			for (int i = 1; i <= n; i++) {
				if(matrix[out][i]==true && visited[i]==false) {
					queue.offer(i);
					visited[i]=true;
					System.out.print(i+" ");
				}
			}
		}
	}
}
