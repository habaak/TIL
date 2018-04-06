package baekjoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class w2178 {
	static int row=0;
	static int col=0;
	static int[][] ad;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);	
		row=sc.nextInt();
		col=sc.nextInt();
		ad = new int[row+1][col+1];
		for(int i=1;i<=row;i++){
			String temp=sc.next(); //구분이 없어서 한 줄로 받음, nextLine으로 읽으면 안됨.
			char[] chars = temp.toCharArray();
			for(int j=1;j<=col;j++){
				ad[i][j]=chars[j-1]-'0'; //character to integer
			}
		}
		bfs(ad,row,col);
	}
	//최소 방문횟수(최적의 길 찾기)
	public static void bfs(int[][] ad, int row, int col){
		//해당 길 방문 체크
		boolean[][] visit = new boolean[row+1][col+1];
		//최소 방문 길이 
		int dist[][] = new int[row+1][col+1];
		Queue<Pair> q=new LinkedList<Pair>();
		//찾아야 할 4가지 길
		int[] dx={1,0,-1,0};
		int[] dy={0,1,0,-1};
		
		//(1,1)에서 시작
		q.add(new Pair(1,1));
		//(1,1)위치에서의 길이는 1로 초기화
		dist[1][1]=1;
		while(!q.isEmpty()){
			Pair p = q.poll();
			int px=p.getX();
			int py=p.getY();
			if(px==row && py==col){
				break;
			}
			//4가지 경우 다 체크
			for(int i=0;i<4;i++){
				int visitX=px+dx[i];
				int visitY=py+dy[i];
				//미로에서 out되지 않도록
		        if(visitX >= 1 && visitX <= row && visitY >=1 && visitY <= col){
		        	//그곳이 갈 수 있는 곳이면서 방문한적이 없다면 추가
		            if(ad[visitX][visitY] != 0 && visit[visitX][visitY] != true){
		            	visit[visitX][visitY] = true; // 
		              q.add(new Pair(visitX, visitY)); // 
		              dist[visitX][visitY] = dist[px][py] + 1; // 
		            }
		        }
			}		
		}
		System.out.println(dist[row][col]);		
	}
	//Queue로 풀기 위해서 객체 생성
	static class Pair{
		int x;
		int y;
		Pair(int x, int y){
			this.x=x;
			this.y=y;
		}public int getX(){
			return x;
		}public int getY(){
			return y;
		}	
	}
}
