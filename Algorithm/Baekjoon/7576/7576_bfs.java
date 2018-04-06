import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_7576_bfs {
	

	//15020 KB	584 MS

	
	public static int[][] box;
	public static int[][] check;
	public static int[] xx={1,-1,0,0};
	public static int[] yy={0,0,1,-1};


	public static int row,col,days,count;
	
	//익은 토마토의 위치를 넣은 큐와 안익은 토마토의 갯수를 아규먼트전달
	public static int bfs(Queue<Point> q, int count){
		int days=0;
		int change=0;
		int cnt=q.size();
		int num=0;
		
		
		while(!q.isEmpty()){
			days++;
			for(int c=0;c<cnt;c++){
			int x= q.peek().x;
			int y= q.peek().y;
			q.poll();
			
			for(int i=0;i<4;i++){
				int nextX=x+xx[i];
				int nextY=y+yy[i];
				
				if(nextX<0||nextX>row-1||nextY<0||nextY>col-1)
					continue;
				if(box[nextX][nextY]==0&&check[nextX][nextY]==0){
					check[nextX][nextY]=1;
					change++;
					q.offer(new Point(nextX,nextY));
					num++;
				}
			}
			}
			
			cnt=num;
			num=0;
		}
		days--;
		
		if(change!=count)
			days=-1;
		
		return days;//모두 익으면 익기까지의 날들, 모두 못익으면 -1 리턴
	}

	
	public static void main(String args[]) throws IOException{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=null;
		
		// 입력받기
		st=new StringTokenizer(br.readLine());
		col=Integer.parseInt(st.nextToken());
		row=Integer.parseInt(st.nextToken());
		box=new int[row][col];
		check=new int[row][col];
		for(int i=0;i<row;i++){
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<col;j++){
				box[i][j]=Integer.parseInt(st.nextToken());
			}
		}
	
		
		//익은 토마토 찾기
		Queue<Point> q= new LinkedList<Point>();
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				if(box[i][j]==1){
					check[i][j]=1;
					q.offer(new Point(i,j));
				}else if(box[i][j]==0){//안익은 토마토
					count++;
				}
			}
	     }
		
		
		if(count==0){
			days=0;			
		}else{
			days=bfs(q,count);
		}
		
		System.out.println(days);
		

}
}
