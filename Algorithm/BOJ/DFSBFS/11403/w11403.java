package baekjoon;

import java.util.Scanner;

/*14368 KB	368 MS*/
public class w11403 {

	static int size=0;
	static int ad[][];
	static int map[];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		size = sc.nextInt();
		ad= new int[size][size];
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				ad[i][j] = sc.nextInt();
			}	
		}
		for(int i=0;i<size;i++){
			map = new int[size];
			dfs(i,false);
			for(int j=0;j<size;j++){
				
				System.out.print(map[j]+" ");
			}System.out.println();
		}
	}
	public static void dfs(int start,boolean isFirst){
		if(isFirst){
			map[start]=1;
		}
		for(int k=0;k<size;k++){
			if(ad[start][k]==1 && map[k]==0){
				dfs(k,true);
			}
		}
	}
}
