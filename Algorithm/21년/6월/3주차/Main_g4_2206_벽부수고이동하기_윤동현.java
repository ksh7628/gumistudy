import java.io.*;
import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static char[][] map;
    static int N, M;

    public static void main(String[] args) throws Exception {
        input();
        bfs(0, 0);
        System.out.println(sb);
    }

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        br.close();
    }

    static void bfs(int r, int c) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][][] visited = new boolean[2][N][M]; // 벽을 부순적있는지 없는지를 위한 3차원 배열
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } }; // 사방 탐색을 위한 방향

        queue.offer(new int[] { r, c, 1, 0 }); // 행, 열, 거리, 벽 부순적 있는지 없는지
        visited[0][r][c] = true;

        int[] cur = null;
        int nr = 0, nc = 0, nd = 0, nb = 0;
        while (!queue.isEmpty()) {
            cur = queue.poll();

            if (cur[0] == N - 1 && cur[1] == M - 1) {
                sb.append(cur[2]);
                return;
            }

            for (int d = 0; d < 4; d++) {
                nr = cur[0] + dir[d][0];
                nc = cur[1] + dir[d][1];
                nd = cur[2] + 1;
                nb = cur[3];

                if (rangeCheck(nr, nc) || visited[nb][nr][nc])
                    continue;

                if (map[nr][nc] == '1') { // 벽을 만났지만
                    // 벽을 부순적이 있으면 넘어간다.
                    if (nb == 1)
                        continue;
                    ++nb; // 아니라면 벽을 부술꺼기 때문에 증가
                }
                visited[nb][nr][nc] = true;
                queue.offer(new int[] { nr, nc, nd, nb });
            }
        }

        sb.append(-1);
    }

    static boolean rangeCheck(int r, int c) {
        return (r < 0 || c < 0 || r >= N || c >= M) ? true : false;
    }
}