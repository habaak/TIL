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
			String temp=sc.next(); //������ ��� �� �ٷ� ����, nextLine���� ������ �ȵ�.
			char[] chars = temp.toCharArray();
			for(int j=1;j<=col;j++){
				ad[i][j]=chars[j-1]-'0'; //character to integer
			}
		}
		bfs(ad,row,col);
	}
	//�ּ� �湮Ƚ��(������ �� ã��)
	public static void bfs(int[][] ad, int row, int col){
		//�ش� �� �湮 üũ
		boolean[][] visit = new boolean[row+1][col+1];
		//�ּ� �湮 ���� 
		int dist[][] = new int[row+1][col+1];
		Queue<Pair> q=new LinkedList<Pair>();
		//ã�ƾ� �� 4���� ��
		int[] dx={1,0,-1,0};
		int[] dy={0,1,0,-1};
		
		//(1,1)���� ����
		q.add(new Pair(1,1));
		//(1,1)��ġ������ ���̴� 1�� �ʱ�ȭ
		dist[1][1]=1;
		while(!q.isEmpty()){
			Pair p = q.poll();
			int px=p.getX();
			int py=p.getY();
			if(px==row && py==col){
				break;
			}
			//4���� ��� �� üũ
			for(int i=0;i<4;i++){
				int visitX=px+dx[i];
				int visitY=py+dy[i];
				//�̷ο��� out���� �ʵ���
		        if(visitX >= 1 && visitX <= row && visitY >=1 && visitY <= col){
		        	//�װ��� �� �� �ִ� ���̸鼭 �湮������ ���ٸ� �߰�
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
	//Queue�� Ǯ�� ���ؼ� ��ü ����
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
