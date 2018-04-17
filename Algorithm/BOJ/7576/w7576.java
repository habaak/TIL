package baekjoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/*90248 KB	1568 MS*/
public class w7576 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int col = sc.nextInt();
		int row = sc.nextInt();
		int ad[][] = new int[row][col];
		int map[][] = new int[row][col];
		int[] dx = {-1,0,1,0};
		int[] dy = {0,-1,0,1};
		
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				ad[i][j] = sc.nextInt();
			}
		}
		
		Queue<point> q = new LinkedList<point>();
		int count=0;
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				if(ad[i][j]==1 ){
					map[i][j]=-1;
					q.offer(new point(i,j,1));
				}else if(ad[i][j]==-1){
					map[i][j]=-1;
				}
			}
		}
		while(!q.isEmpty()){
			int x =q.peek().getX();
			int y =q.peek().getY();
			int cnt=q.peek().getCnt();	
			q.poll();
			for(int k=0;k<4;k++){
				int point_x = x+dx[k];
				int point_y = y+dy[k];
				if(point_x>=0 && point_x<row && point_y>=0 && point_y<col && ad[point_x][point_y]==0 && map[point_x][point_y]==0){
					map[point_x][point_y]=cnt;
					if(count<cnt){
						count=cnt;
					}
					q.offer(new point(point_x,point_y,cnt+1));
				}
			}
		}
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				//System.out.print(map[i][j]);
				if(map[i][j]==0)
					count=-1;
			}
			//System.out.println();
		}
		System.out.println(count);
	}
	static class point{
		private int x;
		private int y;
		private int cnt;
		public point(int x, int y, int cnt){
			this.x=x;
			this.y=y;
			this.cnt=cnt;
		}
		public int getX(){
			return x;
		}
		public int getY(){
			return y;
		}
		public int getCnt(){
			return cnt;
		}
	}

}
