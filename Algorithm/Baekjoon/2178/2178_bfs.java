/*

 * 백준 2178번, 미로 탐색 문제.

 * 

 * 1. N*M크기의 배열로 표현되는 미로가 있다.

 * 2. 미로에서 1은 이동 가능, 0은 이동 불가능을 나타낸다.

 * 3. 이 때, (1,1)에서 출발하여 (N,M)으로 이동할 때, 지나야 하는 최소 칸의 수를 구하여라.

 * 

 * 

 */

import java.util.Scanner;

import java.util.Queue;

import java.util.LinkedList;

import java.awt.Point;

 

public class boj_2178_miro {

 

    private static int Map[][];

    private static boolean visited[][];

    private static int N, M;

    private static int total;

    private static int xx[] = { -1, 1, 0, 0 };

    private static int yy[] = { 0, 0, -1, 1 };

 

    public static void bfs(int x, int y,int time) {

        visited[x][y] = true;

        Queue<Point> Q = new LinkedList<Point>();

        Queue<Integer> level = new LinkedList<>();

        Q.add(new Point(x, y));

        time++;

        level.add(time);

 

            while (!Q.isEmpty()) {

                int cx = Q.peek().x; // current x

                int cy = Q.peek().y; // current y

                int Time = (int)level.poll();

                Q.poll();

    

                if (cx == N - 1 && cy == M - 1) { 

                    total = Time++; 

                    return; 

                }

                

                for (int i = 0; i < 4; i++) {

                    int nx = cx + xx[i]; // next x

                    int ny = cy + yy[i]; // next y

                    if (nx < 0 || ny < 0 || nx > N - 1 || ny > M - 1)

                        continue;

                    if (visited[nx][ny])

                        continue;

                    if (Map[nx][ny] == 1) {

                        visited[nx][ny] = true;

                        Q.add(new Point(nx, ny));

                        level.add(Time+1);

                    }

                }

            }

 

    }

 

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);

 

        N = sc.nextInt();

        M = sc.nextInt();

        Map = new int[N][M];

        visited = new boolean[N][M];

 

        for (int i = 0; i < N; i++) {

            String temp = sc.next();

            for (int j = 0; j < M; j++) {

                Map[i][j] = (int) temp.charAt(j) - 48;

            }

        }

        bfs(0, 0, 0); // 0,0에서 bfs 시작.

        System.out.println(total);

    }

}