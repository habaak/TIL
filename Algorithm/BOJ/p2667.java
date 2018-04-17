import java.io.*;
import java.util.ArrayList;
import java.util.Collections;


public class p2667 {

    static final int[] dx = {0, -1, 0, 1};
    static final int[] dy = {-1, 0, 1, 0};

    static int N;
    static int[][] mat = new int[25][25];
    static boolean[][] visited = new boolean[25][25];
    static ArrayList<Integer> list = new ArrayList<Integer>();

    public static void main(String[] args) throws IOException {

        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                mat[i][j] = s.charAt(j) - '0';
            }
        }

        int sector = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && mat[i][j] != 0) {
                	sector++;
                    int cnt = dfs(i, j, sector, 0);
                    list.add(cnt);
                }
            }
        }

        Collections.sort(list);
        System.out.println(sector);
        for (int x : list) {
            System.out.println(x);
        }
    }

    static int dfs(int row, int col, int sector, int cnt) {
        cnt++;
        visited[row][col] = true;

        for (int i = 0; i < 4; i++) {
            int nx = row + dx[i];
            int ny = col + dy[i];

            if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
            if (mat[nx][ny] == 0) continue;
            if (visited[nx][ny]) continue;

            cnt = dfs(nx, ny, sector, cnt);
        }

        return cnt;
    }
}