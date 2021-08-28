package aug;

import java.io.*;
import java.util.*;

/**
 * @author	: KimSeonhong
 * @date	: 2021. 8. 26.
 *
 * 분류: 구현, 시뮬레이션
 * 난이도: 골드3
 * 소요 시간: 1h 6m
 * 혼자 품: O
 * 풀이: 
 * 1. 종수의 좌표를 jsx, jsy에 저장하고 미친 아두이노의 좌표를 큐에 넣음
 * 2. 입력받은 방향 문자열의 길이만큼 반복문을 수행하면서 종수의 아두이노가 미친 아두이노가 있는 칸으로 이동할 경우 게임에서 지므로 "kraj" + 현재까지의 이동 횟수 출력
 * 3. 종수의 아두이노를 해당 방향으로 이동시킴
 * 4. 미친 아두이노를 8방향 중 종수의 아두이노와 가장 가까운 거리를 가지는 방향으로 이동
 * 5. 미친 아두이노가 종수의 아두이노가 있는 칸으로 이동할 경우 게임에서 지므로 "kraj" + 현재까지의 이동 횟수 출력
 * 6. cnt 값이 2이상인 좌표는 미친 아두이노가 2개 이상 존재한다는 말이므로 큐에 넣지않고 cnt값을 0으로 초기화시킴
 * 7. 방향 문자열 길이 만큼 이동했다면 map을 출력
 * 느낀 점: 
 * 미친 아두이노들을 동시에 이동시킨 후 2개 이상일 경우 해당 칸의 아두이노를 모두 없애기 위해 큐를 사용하여 풀었다.
 * 처음부터 이동하고 난 후 원래 좌표를 빈칸으로 하면 연쇄적으로 아두이노가 잘못 이동할 수 있으므로 모두 이동한 후에 빈칸 처리를 해주었다.
 */
public class Main_g3_8972_미친아두이노_김선홍 {
	static ArrayDeque<int[]> q = new ArrayDeque<>();// 미친 아두이노의 좌표를 관리
	static int[][] cnt;// 미친 아두이노의 개수를 저장
	static char[][] map;
	static int[] dx = { 1, 1, 1, 0, 0, 0, -1, -1, -1 }; // 0 ~ 8, 인덱스 4: 제자리
	static int[] dy = { -1, 0, 1, -1, 0, 1, -1, 0, 1 };
	static String directions;
	static int R, C, jsx, jsy, X;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		cnt = new int[R][C];

		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == 'I') {// 종수 시작 좌표 저장
					jsx = i;
					jsy = j;
				} else if (map[i][j] == 'R') {// 미친 아두이노
					q.offer(new int[] { i, j });
					cnt[i][j]++;// 해당 좌표에 있는 미친 아두이노 개수 증가
				}
			}
		}

		directions = br.readLine();
		solution();
		br.close();
	}

	private static void solution() {
		boolean isLive = true;

		for (int i = 1; i <= directions.length(); i++) {
			int dir = directions.charAt(i) - '1';
			int nx = jsx + dx[dir];
			int ny = jsy + dy[dir];

			// 2. 종수의 아두이노가 미친 아두이노가 있는 칸으로 이동한 경우에는 게임이 끝나게 되며, 종수는 게임을 지게 된다.
			if (map[nx][ny] == 'R') {
				isLive = false;
				X = i;
				break;
			}

			// 1. 먼저, 종수가 아두이노를 8가지 방향(수직,수평,대각선)으로 이동시키거나, 그 위치에 그대로 놔둔다.
			map[jsx][jsy] = '.';
			jsx = nx;
			jsy = ny;
			map[jsx][jsy] = 'I';

			// 3, 4 수행
			isLive = crazyMove(i);
			if (!isLive) {
				break;
			}

			// 5 수행
			checkArduino();
		}

		StringBuilder sb = new StringBuilder();
		if (isLive) {
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
		} else {
			sb.append("kraj ").append(X);
		}

		System.out.print(sb);
	}

	private static boolean crazyMove(int res) {
		// 3. 미친 아두이노는 8가지 방향 중에서 종수의 아두이노와 가장 가까워 지는 방향으로 한 칸 이동한다.
		// 즉, 종수의 위치를 (r1,s1), 미친 아두이노의 위치를 (r2, s2)라고 했을 때, |r1-r2| + |s1-s2|가 가장 작아지는
		// 방향으로 이동한다.
		int size = q.size();
		for (int j = 0; j < size; j++) {
			int[] cur = q.poll();
			int min = 20005, rx = 0, ry = 0;

			for (int d = 0; d < 9; d++) {
				int nx = cur[0] + dx[d];
				int ny = cur[1] + dy[d];

				if (distance(jsx, jsy, nx, ny) < min) {
					min = distance(jsx, jsy, nx, ny);
					rx = nx;
					ry = ny;
				}
			}

			// 4. 미친 아두이노가 종수의 아두이노가 있는 칸으로 이동한 경우에는 게임이 끝나게 되고, 종수는 게임을 지게 된다.
			if (map[rx][ry] == 'I') {
				X = res;
				return false;
			}

			cnt[cur[0]][cur[1]]--;
			cnt[rx][ry]++;
			map[cur[0]][cur[1]] = '.';
			q.offer(new int[] { rx, ry });
		}

		return true;
	}

	private static void checkArduino() {
		// 5. 2개 또는 그 이상의 미친 아두이노가 같은 칸에 있는 경우에는 큰 폭발이 일어나고, 그 칸에 있는 아두이노는 모두 파괴된다.
		int size = q.size();
		ArrayList<int[]> al = new ArrayList<>();
		for (int j = 0; j < size; j++) {
			int[] cur = q.poll();
			if (cnt[cur[0]][cur[1]] >= 2) {
				al.add(cur);
				map[cur[0]][cur[1]] = '.';
			} else {
				map[cur[0]][cur[1]] = 'R';
				q.offer(cur);
			}
		}

		for (int[] cur : al) {
			cnt[cur[0]][cur[1]] = 0;
		}
	}

	// 거리 계산
	private static int distance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}
}