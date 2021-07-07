package Algorithm.BOJ.다시풀어보기;

import java.io.*;
import java.util.*;

public class Main_bj_g2_12100_2048 {

    static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    static int N, nr, nc, res;

    static void upMerge(int[][] map) {
        for (int j = 0; j < N; j++) {
            for (int i = 0; i < N; i++) {
                if (map[i][j] == 0)
                    continue;

                for (int k = i + 1; k < N; k++) {
                    if (map[k][j] == 0)
                        continue;
                    if (map[k][j] > map[i][j] || map[k][j] < map[i][j])
                        break;

                    map[i][j] *= 2;
                    map[k][j] = 0;
                    break;
                }
            }
        }
    }

    static void upMove(int d, int[][] map) {
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0)
                    continue;
                nr = i + dir[d][0];
                nc = j + dir[d][1];
                while (true) {
                    if (nr <= 0 || map[nr][nc] > 0)
                        break;
                    nr += dir[d][0];
                    nc += dir[d][1];
                }

                if (map[nr][nc] == 0) {
                    map[nr][nc] = map[i][j];
                    map[i][j] = 0;
                } else {
                    nr -= dir[d][0];
                    nc -= dir[d][1];
                    if (map[nr][nc] != 0)
                        continue;
                    map[nr][nc] = map[i][j];
                    map[i][j] = 0;
                }
            }
        }
    }

    static void downMerge(int[][] map) {
        for (int j = 0; j < N; j++) {
            for (int i = N - 1; i >= 0; i--) {
                if (map[i][j] == 0)
                    continue;

                for (int k = i - 1; k >= 0; k--) {
                    if (map[k][j] == 0)
                        continue;
                    if (map[k][j] > map[i][j] || map[k][j] < map[i][j])
                        break;

                    map[i][j] *= 2;
                    map[k][j] = 0;
                    break;
                }
            }
        }
    }

    static void downMove(int d, int[][] map) {
        for (int i = N - 2; i >= 0; i--) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0)
                    continue;
                nr = i + dir[d][0];
                nc = j + dir[d][1];
                while (true) {
                    if (nr >= N - 1 || map[nr][nc] > 0)
                        break;
                    nr += dir[d][0];
                    nc += dir[d][1];
                }

                if (map[nr][nc] == 0) {
                    map[nr][nc] = map[i][j];
                    map[i][j] = 0;
                } else {
                    nr -= dir[d][0];
                    nc -= dir[d][1];
                    if (map[nr][nc] != 0)
                        continue;
                    map[nr][nc] = map[i][j];
                    map[i][j] = 0;
                }
            }
        }
    }

    static void leftMerge(int[][] map) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0)
                    continue;

                for (int k = j + 1; k < N; k++) {
                    if (map[i][k] == 0)
                        continue;
                    if (map[i][k] > map[i][j] || map[i][k] < map[i][j])
                        break;

                    map[i][j] *= 2;
                    map[i][k] = 0;
                    break;
                }
            }
        }
    }

    static void leftMove(int d, int[][] map) {
        for (int i = 0; i < N; i++) {
            for (int j = 1; j < N; j++) {
                if (map[i][j] == 0)
                    continue;
                nr = i + dir[d][0];
                nc = j + dir[d][1];
                while (true) {
                    if (nc <= 0 || map[nr][nc] > 0)
                        break;
                    nr += dir[d][0];
                    nc += dir[d][1];
                }

                if (map[nr][nc] == 0) {
                    map[nr][nc] = map[i][j];
                    map[i][j] = 0;
                } else {
                    nr -= dir[d][0];
                    nc -= dir[d][1];
                    if (map[nr][nc] != 0)
                        continue;
                    map[nr][nc] = map[i][j];
                    map[i][j] = 0;
                }
            }
        }
    }

    static void rightMerge(int[][] map) {
        for (int i = 0; i < N; i++) {
            for (int j = N - 1; j >= 0; j--) {
                if (map[i][j] == 0)
                    continue;

                for (int k = j - 1; k >= 0; k--) {
                    if (map[i][k] == 0)
                        continue;
                    if (map[i][k] > map[i][j] || map[i][k] < map[i][j])
                        break;

                    map[i][j] *= 2;
                    map[i][k] = 0;
                    break;
                }
            }
        }
    }

    static void rightMove(int d, int[][] map) {
        for (int i = 0; i < N; i++) {
            for (int j = N - 2; j >= 0; j--) {
                if (map[i][j] == 0)
                    continue;
                nr = i + dir[d][0];
                nc = j + dir[d][1];
                while (true) {
                    if (nc >= N - 1 || map[nr][nc] > 0)
                        break;
                    nr += dir[d][0];
                    nc += dir[d][1];
                }

                if (map[nr][nc] == 0) {
                    map[nr][nc] = map[i][j];
                    map[i][j] = 0;
                } else {
                    nr -= dir[d][0];
                    nc -= dir[d][1];
                    if (map[nr][nc] != 0)
                        continue;
                    map[nr][nc] = map[i][j];
                    map[i][j] = 0;
                }
            }
        }
    }

    static void selectMove(int d, int[][] map) {
        switch (d) {
            case 0:
                upMerge(map);
                upMove(d, map);
                break;
            case 1:
                downMerge(map);
                downMove(d, map);
                break;
            case 2:
                leftMerge(map);
                leftMove(d, map);
                break;
            case 3:
                rightMerge(map);
                rightMove(d, map);
                break;
        }
    }

    static void move(int cnt, int[][] map) {
        if (cnt == 5) {
            for (int[] m : map) {
                for (int v : m) {
                    res = Math.max(res, v);
                }
            }
            return;
        }
        for (int d = 0; d < 4; d++) {
            int[][] temp = new int[N][N];
            for (int i = 0; i < N; i++) {
                System.arraycopy(map[i], 0, temp[i], 0, N);
            }
            selectMove(d, temp);
            move(cnt + 1, temp);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        move(0, map);

        System.out.println(res);

        br.close();
    }
}