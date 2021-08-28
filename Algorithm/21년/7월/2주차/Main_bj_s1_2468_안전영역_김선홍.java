package jul;

import java.io.*;
import java.util.StringTokenizer;

/**
 * @author	: KimSeonhong
 * @date	: 2021. 7. 12.
 *
 * 분류: 그래프 이론, 그래프 탐색, 브루트포스 알고리즘, 너비 우선 탐색, 깊이 우선 탐색
 * 난이도: 실버1
 * 소요 시간: 0h 18m
 * 혼자 품: O
 * 풀이: 
 * 1. 입력값들을 map에 저장하면서 최댓값을 maxVal에 따로 저장한다.
 * 2. 1 ~ maxVal 까지 브루트포스 + dfs를 통해 안전한 영역의 최댓값을 갱신한다.
 * 느낀 점: 
 * 처음에는 res값을 0으로 주고 시작해서 틀렸었는데 아무곳도 잠기지 않는 경우를 고려하여 값을 1로 수정하니 맞추게 되었다.
 * 쉬운 문제이지만 이러한 점을 놓치지 않도록 문제를 꼼꼼하게 읽어보는 습관을 계속 가져야 겠다고 느꼈다.
 */
public class Main_bj_s1_2468_안전영역_김선홍 {
	static int[][] map;
	static boolean[][] visit;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int N, maxVal;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				maxVal = Math.max(maxVal, map[i][j]);// 최댓값 갱신
			}
		}

		System.out.println(process());
		br.close();
	}

	/* 1 ~ maxVal까지 브루트포스 + dfs를 통해 안전한 영역의 최댓값을 반환 */
	private static int process() {
		int res = 1;// 아무곳도 잠기지 않는 경우가 있을 수 있으므로 안전한 영역의 최소 개수는 1 이상
		
		for (int k = 1; k < maxVal; k++) {
			visit = new boolean[N][N];
			int area = 0;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visit[i][j] && map[i][j] > k) {
						dfs(i, j, k);
						area++;
					}
				}
			}
			
			res = Math.max(res, area);
		}
		
		return res;
	}

	/* dfs를 통해 현재 depth보다 큰 지점을 영역으로 표기 */
	private static void dfs(int x, int y, int depth) {
		visit[x][y] = true;
		
		for (int dir = 0; dir < 4; dir++) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			
			if (check(nx, ny) && !visit[nx][ny] && map[nx][ny] > depth) {
				dfs(nx, ny, depth);
			}
		}
	}

	/* 배열 범위 체크 */
	private static boolean check(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < N;
	}
}