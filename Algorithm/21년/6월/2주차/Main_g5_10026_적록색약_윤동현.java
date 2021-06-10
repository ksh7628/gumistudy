import java.io.*;

/* 
(색상의 차이를 거의 느끼지 못하는 경우도 같은 색상이라 한다) -> 적록색약인 사람은 빨간색과 초록색을 같은 구역이라고 생각한다는 것!!
*/

public class Main {

    static char map[][];
    static boolean[][][] visited;
    static int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine()); // 크기

        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            char[] temp = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                map[i][j] = temp[j];
            }
        }

        visited = new boolean[2][N][N];

        int no = 0, yes = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[0][i][j]) {
                    dfs(i, j, map[i][j], 0);
                    no++;
                }
                if (!visited[1][i][j]) {
                    dfs(i, j, map[i][j], 1);
                    yes++;
                }
            }
        }

        bw.write(String.valueOf(no));
        bw.write(" ");
        bw.write(String.valueOf(yes));
        bw.flush();
        bw.close();
        br.close();
    }

    static boolean rangeCheck(int y, int x) {
        return (y < 0 || x < 0 || y >= N || x >= N) ? true : false;
    }

    static void dfs(int y, int x, char val, int isCheck) {

        visited[isCheck][y][x] = true;

        for (int d = 0; d < 4; d++) {
            int ny = y + dir[d][0];
            int nx = x + dir[d][1];

            if (rangeCheck(ny, nx) || visited[isCheck][ny][nx])
                continue;

            if (isCheck == 1) { // 적록색약인 경우
                if (val == 'B' && map[ny][nx] != val)
                    continue;
                else if (val != 'B' && map[ny][nx] == 'B')
                    continue;
            } else {
                if (map[ny][nx] != val)
                    continue;
            }

            dfs(ny, nx, val, isCheck);
        }
    }

}