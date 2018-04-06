import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static int [] map = new int [200001];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int start = sc.nextInt();
		int dest = sc.nextInt();
		Arrays.fill(map, -1);
		map[start]=0;
		map[dest]=Math.abs(dest-start);
		func(start,dest,0);
		System.out.println(map[dest]);
//		for(int i=0;i<dest*2;i++) {
//			if(map[i]!=-1)System.out.print(i+":"+map[i]+" ");
//		}
	}

	public static void func(int start, int dest, int cnt) {
		if(map[dest]<cnt&&map[dest]!=-1)return;
		if(map[start]<cnt&&map[start]!=-1)return;
		if(start>=dest) {
			cnt += start-dest;
			if(map[dest]>cnt)map[dest]=cnt;
		}else {
			map[start]=cnt;
			func(start*2,dest,cnt+1);
			if(start!=0)func(start-1,dest,cnt+1);
			func(start+1,dest,cnt+1);
		}
	}
}
