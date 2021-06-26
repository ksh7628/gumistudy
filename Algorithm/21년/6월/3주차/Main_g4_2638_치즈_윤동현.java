import java.io.*;
import java.util.*;

public class Main {

    static boolean[][] visited;
    static int[][] map;
    static int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    static int N, M, nr, nc, cheese;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1)
                    cheese++;
            }
        }

        int res = 0;
        while (cheese > 0) {
            visited = new boolean[N][M];
            dfs(0, 0);
            cheeseBack();
            res++;
        }

        System.out.println(res);
        br.close();
    }

    static boolean rangeCheck(int r, int c) {
        return (r < 0 || c < 0 || r >= N || c >= M) ? true : false;
    }

    static void dfs(int r, int c) {
        visited[r][c] = true;

        for (int d = 0; d < 4; d++) {
            nr = r + dir[d][0];
            nc = c + dir[d][1];
            if (rangeCheck(nr, nc) || visited[nr][nc])
                continue;

            if (map[nr][nc] > 0) {
                map[nr][nc]++;
                if (map[nr][nc] == 3) {
                    map[nr][nc] = 0;
                    cheese--;
                    visited[nr][nc] = true;
                }
                continue;
            }
            dfs(nr, nc);
        }
    }

    static void cheeseBack() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 2)
                    map[i][j] = 1;
            }
        }
    }

}