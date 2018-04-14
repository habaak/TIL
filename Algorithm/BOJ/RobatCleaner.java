import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ#14503 �κ�û�ұ�
 * https://www.acmicpc.net/problem/14503
 */

public class RobatCleaner {

    //static final int NORTH = 0;
    //static final int EAST = 1;
    //static final int SOUTH = 2;
    //static final int WEST = 3;

    static final int BLANK = 0;
    static final int WALL = 1;
    static final int CLEANED = 2;

    static int[] dRow = {-1, 0, 1, 0};
    static int[] dCol = {0, 1, 0, -1};

    static int N, M;
    static int[][] map = new int[51][51];

    public static void main(String[] args) throws IOException {

        int cRow, cCol;
        int cDir;
        int cnt = 0;

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        cRow = Integer.parseInt(st.nextToken());
        cCol = Integer.parseInt(st.nextToken());
        cDir = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {

                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // solve
        while (true) {
        	boolean ret = false;
        	
            // 1. ���� ��ġ�� û���Ѵ�
            if (map[cRow][cCol] == BLANK) {

                cnt++;
                map[cRow][cCol] = CLEANED;
            }

            
            // 2. ���� ���⿡�� ���� �������� Ž���� �����Ѵ�
            for (int i = 0; i < 4; i++) {

                int nextDir = (cDir + 3) % 4;
                int nextRow = cRow + dRow[nextDir];
                int nextCol = cCol + dCol[nextDir];

                // 2-1.���ʹ��⿡ ���� û������ ���� ������ �����ϸ�, �� �������� ȸ���Ѵ��� �� ĭ�� �����ϰ� û���Ѵ�
                if (map[nextRow][nextCol] == BLANK) {

                    cDir = nextDir;
                    cRow = nextRow;
                    cCol = nextCol;

                    ret = true;
                    break;
                }
                // 2-2. �� ���⿡ û���� ������ ���ٸ�, �� �������� �׳� ȸ���Ѵ�
                else {

                    cDir = nextDir;
                }
            }

            if (!ret) {

                // 2-4. 4���� û���� ���� ����, ������ �Ұ����� ��� ����
                if (map[cRow - dRow[cDir]][cCol - dCol[cDir]] == WALL) {

                    System.out.println(cnt);
                    break;
                }
                // 2-3. 4���� û���� ���� ���� ������ ������ ��쿡 �׳� ����
                else {

                    cRow = cRow - dRow[cDir];
                    cCol = cCol - dCol[cDir];
                }
            }

        } // ~ while loop
    }
}
