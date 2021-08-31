package aug;

import java.io.*;
import java.util.*;

/**
 * @author : KimSeonhong
 * @date : 2021. 8. 31.
 *
 * 분류: 구현, 그래프 이론, 그래프 탐색, 너비 우선 탐색, 시뮬레이션
 * 난이도: 골드5
 * 소요 시간: 0h 21m
 * 혼자 품: O
 * 풀이: 더 이상 인구 이동이 일어나지 않을 때까지 반복문을 돌려서 dfs를 통해 국경선이 열리는 나라들에 대해 인구 평균 값을 갱신해나가며 풀이하였다.
 * 느낀 점: 
 * 처음에는 bfs로 풀었으나 최단거리를 구할 필요가 없는 문제라서 dfs로 다시 풀게 되었다.
 * 또한 ArrayList 뿐만 아니라 Queue나 Stack도 foreach문이 사용된다는 것을 알게 되었다.
 * 2차 리팩토링을 통해 시작 좌표는 pos에 넣지 않고 populationMove()에 인자를 추가하여 풀었더니 메모리를 많이 절약하게 되었다.
 * 첫 방식은 인구 이동이 불가능해도 dfs에서 시작좌표를 pos에 넣어줬었는데 큐에 많은 데이터를 넣고 빼다보니 메모리가 많이 소모되었다.
 */

public class Main_g5_16234_인구이동_김선홍 {
	static ArrayDeque<int[]> pos = new ArrayDeque<>();
	static int[][] map;
	static boolean[][] visit;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int N, L, R, sum;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(solution());
		br.close();
	}

	private static int solution() {
		int res = 0;
		while (true) {
			boolean isMove = false;// 인구 이동 여부를 판단
			visit = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visit[i][j]) {
						sum = 0;
						dfs(i, j);
						
						if (!pos.isEmpty()) {// 좌표가 1개보다 많다 -> 인구 이동 가능
							isMove = true;
							populationMove(i, j);
						}
					}
				}
			}

			if (!isMove) {// 인구 이동이 불가능하면 반복문 종료
				break;
			}
			res++;
		}

		return res;
	}

	// dfs로 인구 이동이 가능한 좌표를 큐에 넣음
	private static void dfs(int x, int y) {
		sum += map[x][y];
		visit[x][y] = true;

		for (int dir = 0; dir < 4; dir++) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];

			if (check(nx, ny) && !visit[nx][ny]) {
				int dis = Math.abs(map[x][y] - map[nx][ny]);
				if (L <= dis && dis <= R) {// L이상 R이하 일때에만 dfs 재귀 수행
					pos.offer(new int[] { nx, ny });
					dfs(nx, ny);
				}
			}
		}
	}

	// 인구 이동 후 인구수를 갱신
	private static void populationMove(int x, int y) {
		int mean = sum / (pos.size() + 1);
//		for (int[] p : pos) {
//			map[p[0]][p[1]] = mean;
//		}
		map[x][y] = mean;
		while (!pos.isEmpty()) {
			int[] p = pos.poll();
			map[p[0]][p[1]] = mean;
		}
	}

	// 배열 경계 체크
	private static boolean check(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < N;
	}
}
