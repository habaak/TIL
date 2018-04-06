import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
 
public class Al1697 {
    public static void main(String[] args) {        
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        Queue<Integer> q = new LinkedList<>();
        int[] dist = new int[100001];
        boolean isEnd = false;
        
        for(int i=0; i<dist.length; i++) {
            dist[i] = -1;
        }
        
        dist[N] = 0;
        q.offer(N);
        while(!q.isEmpty()) {
            int nowPos = q.poll();
            int[] nextPos = new int[]{nowPos-1, nowPos+1, nowPos*2};
            for(int i=0; i<nextPos.length; i++) {
                if(0<=nextPos[i] && nextPos[i]<=100000 && dist[nextPos[i]] == -1) {
                    dist[nextPos[i]] = dist[nowPos] + 1;
                    q.offer(nextPos[i]);
                    if(nextPos[i] == K) {
                        isEnd = true;
                        break;
                    }
                }
            }
            if(isEnd) break;
        }
        System.out.print(dist[K]);
    }
}
 