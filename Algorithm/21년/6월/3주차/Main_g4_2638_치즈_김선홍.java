package jun;

import java.io.*;
import java.util.*;

/**
 * @author	: KimSeonhong
 * @date	: 2021. 6. 17.
 *
 * 분류: 구현, 그래프 이론, 그래프 탐색, 너비 우선 탐색, 시뮬레이션, 깊이 우선 탐색
 * 난이도: 골드4
 * 혼자 품: O
 * 풀이: 
 * 1. 입력값들을 map에 저장하면서 치즈 개수를 센다.
 * 2-1. 치즈 개수가 0이라면 녹일 치즈가 없으므로 반복문을 바로 종료한다.
 * 2-2. 치즈 개수가 0이 아니라면 dfs를 통해 녹일 수 있는 치즈를 체크한다.
 * 3. 녹일 수 있는 치즈의 값은 녹여서 0으로 저장하고 녹일 수 없는 치즈들은 1로 되돌린다.
 * 4. 치즈가 모두 녹을 때까지 반복한다. 
 * 느낀 점: 
 * dfs, bfs 둘다 쓸 수 있는 문제라면 dfs로 접근하는 것이 빠른 방법이라는 것을 다시 알게 된 문제였다.
 */
public class Main_g4_2638_치즈_김선홍 {
	static int[][] map;
	static boolean[][] visit;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int N, M, cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {// 치즈 개수 카운팅
					cnt++;
				}
			}
		}

		System.out.println(process());
		br.close();
	}

	/* 풀이 과정 */
	private static int process() {
		int res = 0;
		while (cnt != 0) {// 치즈가 다 녹을 떄까지 반복
			visit = new boolean[N][M];
			dfs(0, 0);
			meltCheck();
			res++;
		}
		return res;
	}

	/* 녹일 수 있는 치즈를 녹임 */
	private static void meltCheck() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] >= 3) {// 2번 이상 접촉했다면 녹임
					map[i][j] = 0;
					cnt--;
				} else if (map[i][j] != 0) {// 접촉횟수가 남아있으므로 1로 되돌림
					map[i][j] = 1;
				}
			}
		}
	}

	/* dfs를 통해 공기와 접촉한 치즈를 체크 */
	private static void dfs(int x, int y) {
		visit[x][y] = true;

		for (int dir = 0; dir < 4; dir++) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];

			if (check(nx, ny) || visit[nx][ny]) {
				continue;
			}

			if (map[nx][ny] != 0) {
				map[nx][ny]++;// 몇번 접촉 했는지 체크
				continue;
			}

			visit[nx][ny] = true;
			dfs(nx, ny);
		}
	}

	/* 배열 범위 체크 */
	private static boolean check(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= M;
	}
}