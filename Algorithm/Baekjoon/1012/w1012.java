package baekjoon;

import java.util.Scanner;

/*11020 KB	260 MS*/
public class w1012 {
	static int[] dx={1,0,-1,0};
	static int[] dy={0,1,0,-1};
	static int[][] ad;
	static int[][] map;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);	
		int T_case = sc.nextInt();
		for (int t = 0; t < T_case; t++) {
			int col = sc.nextInt();
			int row = sc.nextInt();
			
			ad = new int[row][col];
			int num = sc.nextInt();
			int x=0;
			int y=0;
			for(int j=0;j<num;j++){
				y=sc.nextInt();
				x=sc.nextInt();
				ad[x][y] = 1;
			}
			System.out.println(solution(row,col));
		}
	}
	public static int solution(int row, int col){
		int count=0;
		map = new int[50][50];
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				if(ad[i][j]==1 && map[i][j]==0){
					count++;
					dfs(count,row,col,i,j);
				}
			}
		}
		return count;
	}
	public static void dfs(int key,int row,int col, int s_x,int s_y){
		map[s_x][s_y]=key;
		for(int i=0;i<4;i++){
			int visitX = s_x+dx[i];
			int visitY = s_y+dy[i];
			//함수로 만들어서 써도 가능하지만 시간이 늘어남
			if(visitX>=0 && visitX<row && visitY>=0 && visitY<col && ad[visitX][visitY]==1 && map[visitX][visitY]==0){
				dfs(key,row,col,visitX,visitY);
			}
		}
	}
}
/*public class w1012 {
	static int[] dx={1,0,-1,0};
	static int[] dy={0,1,0,-1};
	static int[][] ad;
	static int[][] map = new int[51][51];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);	
		int T_case = sc.nextInt();
		for (int t = 0; t < T_case; t++) {
			int col = sc.nextInt();
			int row = sc.nextInt();
			
			ad = new int[row+1][col+1];
			int num = sc.nextInt();
			int x=0;
			int y=0;
			for(int j=0;j<num;j++){
				y=sc.nextInt();
				x=sc.nextInt();
				ad[x+1][y+1] = 1;
			}
			System.out.println(solution(row,col));
		}
	}
	public static int solution(int row, int col){
		int count=0;
		for(int i=1;i<=row;i++){
			for(int j=1;j<=col;j++){
				if(ad[i][j]==1 && map[i][j]==0){
					count++;
					dfs(count,row,col,i,j);
				}
			}
		}
		return count;
	}
	public static void dfs(int key,int row,int col, int s_x,int s_y){
		map[s_x][s_y]=key;
		for(int i=0;i<4;i++){
			int visitX = s_x+dx[i];
			int visitY = s_y+dy[i];
			//함수로 만들어서 써도 가능하지만 시간이 늘어남
			if(visitX>=1 && visitX<=row && visitY>=1 && visitY<=col && ad[visitX][visitY]==1 && map[visitX][visitY]==0){
				dfs(key,row,col,visitX,visitY);
			}
		}
	}
}*/
