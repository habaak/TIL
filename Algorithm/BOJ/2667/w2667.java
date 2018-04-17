package baekjoon;


import java.util.Arrays;
import java.util.Scanner;

public class w2667 {
	static int[] dx={1,0,-1,0};
	static int[] dy={0,1,0,-1};
	static int count=0;
	static int[][] ad;
	static int[][] map = new int[26][26];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);	
		int T_case = sc.nextInt();
		ad=new int[T_case+1][T_case+1];
		for(int i=1;i<=T_case;i++){
			String temp = sc.next();
			char[] c_arr = temp.toCharArray();
			for(int j=1;j<=T_case;j++){
				ad[i][j]=c_arr[j-1]-'0';
			}
 		}
		solution(T_case);
	}
	public static void solution(int T){
		//모든 것 다 훝어봐야 함
		for(int i=1;i<=T;i++){
			for(int j=1;j<=T;j++){
				if(ad[i][j]==1 && map[i][j]==0){
					count++;
					dfs(count,T,i,j);
				}
			}
		}
		int[] pr = new int[count];
		//단지별로 개수 체크 
		for(int i=1;i<=T;i++){
			for(int j=1;j<=T;j++){
				if(map[i][j]>=1){
					pr[map[i][j]-1]++;
				}
			}
		}
		System.out.println(count);
		Arrays.sort(pr);
		for(int i=0;i<count;i++){
			System.out.println(pr[i]);
		}
		
	}
	public static void dfs(int key,int T, int s_x,int s_y){
		map[s_x][s_y]=key;
		for(int i=0;i<4;i++){
			int visitX = s_x+dx[i];
			int visitY = s_y+dy[i];
			//함수로 만들어서 써도 가능하지만 시간이 늘어남
			if(visitX>=1 && visitX<=T && visitY>=1 && visitY<=T && ad[visitX][visitY]==1 && map[visitX][visitY]==0){
				dfs(key,T,visitX,visitY);
			}
		}
	}
}
