import java.io.*;
import java.util.*;

public class Main {

    static int[][] map;
    static int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    static int N, sRow, sCol, sSize, eatRow, eatCol, eatCnt, time;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine()); // 공간의 크기

        map = new int[N][N]; // 실제 맵 초기화
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken()); // 물고기와 상어 입력
                if (map[i][j] == 9) { // 시작지점이기 때문에 위치값을 받고 0으로 초기화해도 상관이 없다.
                    sRow = i; // 상어의 row 좌표
                    sCol = j; // 상어의 col 좌표
                    map[i][j] = 0;
                }
            }
        }

        int preTime = time;
        sSize = 2;
        while (true) {
            bfs();
            if (preTime == time)
                break;
            preTime = time;
        }

        System.out.println(time);
        br.close();
    }

    static void bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];

        queue.offer(new int[] { sRow, sCol, 0 });
        visited[sRow][sCol] = true;

        int td = 0;
        eatRow = eatCol = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean isCheck = false; // 가까운 물고기를 찾았는지 확인하기 위한 변수

            for (int k = 0; k < size; k++) { // 현재 depth(거리)만큼만 돌린다.
                int[] cur = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int nr = cur[0] + dir[d][0];
                    int nc = cur[1] + dir[d][1];
                    int nd = cur[2] + 1; // 이동 시간

                    // 범위체크, 사이즈체크, 방문체크
                    if (rangeCheck(nr, nc) || map[nr][nc] > sSize || visited[nr][nc])
                        continue;

                    // 지나갈수있는 경우
                    if (map[nr][nc] == sSize || map[nr][nc] == 0) {
                        queue.offer(new int[] { nr, nc, nd });
                        visited[nr][nc] = true;
                        continue;
                    }

                    // 작은 녀석을 만났을때!!
                    isCheck = true;
                    td = nd;
                    if (eatRow > nr) { // 물고기가 위에있는 녀석을 선택
                        eatRow = nr;
                        eatCol = nc;
                    } else if (eatRow == nr && eatCol > nc) { // 같은 row라면 가장 왼쪽 물고기 선택
                        eatRow = nr;
                        eatCol = nc;
                    }
                }
            }

            // 해당 거리에 해당하는 큐를 모두 돌고 가장 가깝고 위에있으면서 왼쪽에 있는 물고기를 찾은 경우!
            if (isCheck)
                break;
        }

        if (eatRow != Integer.MAX_VALUE && eatCol != Integer.MAX_VALUE) {
            map[eatRow][eatCol] = 0; // 해당좌표의 물고기를 먹었다.
            // 먹었기 때문에 해당 좌표에 상어를 위치시킨다.
            sRow = eatRow;
            sCol = eatCol;

            time += td;
            eatCnt++;
            if (eatCnt > 0 && eatCnt % sSize == 0) {
                sSize++;
                eatCnt = 0;
            }
        }
    }

    static boolean rangeCheck(int r, int c) {
        return (r < 0 || r >= N || c < 0 || c >= N) ? true : false;
    }

}