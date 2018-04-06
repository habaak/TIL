package baekjoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*10840 KB	276 MS*/
public class w1012_bfs {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);	
		int T_case = sc.nextInt();
		int ad[][];
		int[][] map;
		int[] dx={1,0,-1,0};
		int[] dy={0,1,0,-1};
		Queue<Pair> q;
		int count;
		int px,py,cx,cy;
		Pair p;
		for (int t = 0; t < T_case; t++) {
			int col = sc.nextInt();
			int row = sc.nextInt();
			
			ad = new int[row][col];
			map = new int[row][col];
			int num = sc.nextInt();
			
			int x=0;
			int y=0;
			for(int k=0;k<num;k++){
				y=sc.nextInt();
				x=sc.nextInt();
				ad[x][y]=1;
			}
			q = new LinkedList<Pair>();
			count=0;
			for(int findx=0;findx<row;findx++){
				for(int findy=0;findy<col;findy++){
					if(ad[findx][findy]==1 && map[findx][findy]==0){
						count++;
						q.offer(new Pair(findx,findy));
						map[findx][findy]=count;
						while(!q.isEmpty()){
							p=q.poll();
							px=p.getX();
							py=p.getY();
							for(int cnt=0;cnt<4;cnt++){
								cx = px+dx[cnt];
								cy = py+dy[cnt];
								if(cx>=0&&cx<row&&cy>=0&&cy<col&&ad[cx][cy]==1&&map[cx][cy]==0){
									q.offer(new Pair(cx,cy));
									map[cx][cy]=count;
								}
							}
						}
					}
				}
			}
			System.out.println(count);
		}
	}
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
