import java.io.*;
import java.util.*;

public class Main {

    static int[][] A, cloud; // 물의 양
    // ←, ↖, ↑, ↗, →, ↘, ↓, ↙
    static int[][] dir = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 } };
    static int N, M, nr, nc;

    static int[][] moveCloud(int d, int s) {
        int[][] newCloud = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                nr = (i + (dir[d][0] + N) * s) % N;
                nc = (j + (dir[d][1] + N) * s) % N;
                newCloud[nr][nc] = cloud[i][j];
                if (newCloud[nr][nc] == 1) {
                    A[nr][nc]++;
                    newCloud[nr][nc] = 2;
                }
            }
        }
        return newCloud;
    }

    // 물 복사시에는 연결되어있지 않으므로 범위체크
    static boolean rangeCheck(int r, int c) {
        return (r < 0 || r >= N || c < 0 || c >= N) ? true : false;
    }

    static int cntWater(int r, int c) {
        int cnt = 0;
        for (int d = 1; d < 8; d += 2) {
            nr = r + dir[d][0];
            nc = c + dir[d][1];

            // 범위에 벗어나거나 물이 없으면 물을 복사할수없다.
            if (rangeCheck(nr, nc) || A[nr][nc] == 0)
                continue;

            cnt++;
        }
        return cnt;
    }

    static void copyWater() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // cloud값이 2는 비가내리고 사라진 구름이므로
                if (cloud[i][j] == 2) {
                    A[i][j] += cntWater(i, j);
                }
            }
        }
    }

    static void createCloud() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (A[i][j] >= 2 && cloud[i][j] == 0) {
                    A[i][j] -= 2;
                    cloud[i][j] = 1;
                }
                if (cloud[i][j] == 2) {
                    cloud[i][j] = 0;
                }
            }
        }
    }

    static int sumWater() {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum += A[i][j];
            }
        }
        return sum;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        cloud = new int[N][N];
        cloud[N - 1][0] = cloud[N - 1][1] = cloud[N - 2][0] = cloud[N - 2][1] = 1;

        int d = 0, s = 0;
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());

            d = Integer.parseInt(st.nextToken()) - 1;
            s = Integer.parseInt(st.nextToken());

            cloud = moveCloud(d, s);
            copyWater();
            createCloud();
        }

        System.out.println(sumWater());
        br.close();
    }

}