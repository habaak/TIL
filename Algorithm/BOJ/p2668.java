import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p2668 {
	public static int N;
	public static int mat[];
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			mat[i]= Integer.parseInt(br.readLine());
		}
		
		dfs();
	}
	public static void dfs() {
		
	}
}
