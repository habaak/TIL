import java.util.Scanner;
 
public class Main {
 
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
 
        int N=input.nextInt();
 
        int P[]=new int[N+1];
 
        for(int i=1; i<=N; i++)
        {
            P[i]=input.nextInt();
        }
 
        int dp[]=new int[N+1];        // n개 팔았을때 최대수익
        dp[0]=0;                    
        dp[1]=P[1];        
 
        for(int i=2; i<=N; i++)
        {
            dp[i]=P[i];
            for(int j=0; j<=i; j++)
            {
                dp[i]=Math.max(dp[i], dp[i-j]+dp[j]);
            }
        }
 
        System.out.println(dp[N]);
    }
}
