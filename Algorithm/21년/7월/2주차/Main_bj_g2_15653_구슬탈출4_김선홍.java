package jul;

import java.io.*;
import java.util.*;

/**
 * @author	: KimSeonhong
 * @date	: 2021. 7. 13.
 *
 * 분류: 구현, 그래프 이론, 그래프 탐색, 너비 우선 탐색, 시뮬레이션
 * 난이도: 골드2
 * 소요 시간: 0h 37m
 * 혼자 품: O
 * 풀이: 
 * 1. 입력값들을 map에 저장하면서 빨간 구슬의 좌표와 파란 구슬의 좌표를 따로 저장한다.
 * 2-1. bfs를 통해 큐에 저장된 좌표를 꺼내서 사방 탐색을 수행한다.
 * 2-2. 먼저 파란 구슬을 벽에 부딪힐 때까지 이동시킨다. 이 때, 파란 구슬이 구멍에 빠진다면 현재 방향 탐색을 할 필요가 없으므로 다음 탐색으로 넘어간다.
 * 2-3. 다음으로 빨간 구슬을 이동시킨다. 빨간 구슬이 구멍에 빠진다면 문제의 조건에 맞는 최소 횟수를 찾게 되므로 리턴한다.
 * 2-4. 위의 두 경우에 해당되지 않는 경우 두 구슬의 좌표가 같다면 이동 거리를 비교하여 더 이동한 구슬의 좌표를 한칸 뒤로 되돌린다.
 * 3. 모든 탐색을 했는데도 빨간 구슬만 구멍에 빠트릴 수 없다면 -1를 리턴한다.
 * 느낀 점: 
 * 많은 예외처리를 신경 써야 하는 문제였는데 몇주 전에 나마 구슬탈출 시리즈 몇개를 풀어서 그런지 처음 풀었을 때보다는 확실히 나아진 것 같다.
 * 특히 맵의 크기가 작기 때문에 4차원으로 배열을 선언해도 충분히 풀 수 있게 되었다.
 */
public class Main_bj_g2_15653_구슬탈출4_김선홍 {
	static char[][] map;
	static boolean[][][][] visit;// 빨간 구슬 좌표와 파란 구슬 좌표를 인덱스로 가지는 4차원 boolean형 배열
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int N, M, rx, ry, bx, by;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		visit = new boolean[N][M][N][M];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == 'R') {
					rx = i;
					ry = j;
				} else if (map[i][j] == 'B') {
					bx = i;
					by = j;
				}
			}
		}

		System.out.println(bfs());
		br.close();
	}

	/* bfs를 통해 구슬을 이동시키면서 빨간 구슬만 구멍에 들어갈 때의 최소 횟수 반환 */
	private static int bfs() {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		visit[rx][ry][bx][by] = true;
		q.offer(new int[] { rx, ry, bx, by });
		int cnt = 0;

		while (!q.isEmpty()) {
			cnt++;
			int size = q.size();
			
			for (int i = 0; i < size; i++) {
				int[] cur = q.poll();
				dirloop: for (int dir = 0; dir < 4; dir++) {
					int nrx = cur[0] + dx[dir];
					int nry = cur[1] + dy[dir];
					int nbx = cur[2] + dx[dir];
					int nby = cur[3] + dy[dir];

					// 파란 구슬 이동
					while (true) {
						if (map[nbx][nby] == '#') {// 벽을 만났다면 좌표를 한칸 뒤로 이동시킨후 break
							nbx -= dx[dir];
							nby -= dy[dir];
							break;
						} else if (map[nbx][nby] == 'O') {// 파란 구슬이 구멍에 도착했다면 현재 방향 탐색을 중단
							continue dirloop;
						}

						nbx += dx[dir];
						nby += dy[dir];
					}

					// 빨간 구슬 이동
					while (true) {
						if (map[nrx][nry] == '#') {
							nrx -= dx[dir];
							nry -= dy[dir];
							break;
						} else if (map[nrx][nry] == 'O') {// 빨간 구슬이 구멍에 도착했다면 최소 횟수 반환
							return cnt;
						}

						nrx += dx[dir];
						nry += dy[dir];
					}

					if (nrx == nbx && nry == nby) {// 위치가 같다면 거리를 비교하여 더 많이 이동한 구슬을 한칸 뒤로 이동
						int rdist = Math.abs(cur[0] - nrx) + Math.abs(cur[1] - nry);
						int bdist = Math.abs(cur[2] - nbx) + Math.abs(cur[3] - nby);

						if (rdist > bdist) {
							nrx -= dx[dir];
							nry -= dy[dir];
						} else {
							nbx -= dx[dir];
							nby -= dy[dir];
						}
					}

					if (!visit[nrx][nry][nbx][nby]) {// 아직 방문 안했다면 방문 처리 후 큐에 넣음
						visit[nrx][nry][nbx][nby] = true;
						q.offer(new int[] { nrx, nry, nbx, nby });
					}
				}
			}
		}

		return -1;// 탐색이 완료되어도 빨간 구슬만 구멍에 넣을 수 없다면 -1
	}
}