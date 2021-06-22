package jun;

import java.io.*;
import java.util.*;

/**
 * @author	: KimSeonhong
 * @date	: 2021. 6. 22.
 *
 * 분류: 구현, 시뮬레이션
 * 난이도: 골드5
 * 혼자 품: O
 * 풀이: 
 * 1. 입력받은 정보들을 통해 처음에는 구름 좌표가 지정되어 있으므로 초기에 cloud에 넣고 시작한다.
 * 2-1. 모든 구름들을 이동시킨 후 이동된 좌표의 물의 양을 1씩 증가시킨다. 이 때, 배열 범위를 벗어나면 다시 반대편에서 시작되기 때문에 모듈러 연산을 해준다.
 * 2-2. 구름이 모두 사라진다고 했지만 나중에 조건이 따로 있으므로 이동된 구름들의 좌표를 isCloud에서 true로 설정한다.
 * 3. 물복사버그가 시전되면 2-2에서 체크한 isCloud의 값이 true일 때에만 대각선 사방을 체크해서 물이 존재하는 좌표의 수만큼 현재 좌표에 물의 양을 더해준다.
 * 4. 물의 양이 2 이상이고 isCloud의 값이 false일 때에만 초기화된 cloud에 넣어주고 물의 양을 2 감소시킨다.
 * 5. 2 ~ 5를 M번 수행한 후 물 양의 총합을 구한다.
 * 느낀 점: 
 * 오랜만에 푸는 시뮬레이션 문제였는데 이전에 문제를 잘 안읽어서 문제를 못 푼 경험이 꽤 있었기에 꼼꼼하게 읽은 후 하나씩 구현을 해 나가니 풀만했던 것 같다.
 */
public class Main_g5_21610_마법사상어와비바라기_김선홍 {
	static class Cloud {
		int x, y;

		public Cloud(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	static ArrayList<Cloud> cloud = new ArrayList<>();
	static int[][] water;
	static boolean[][] isCloud;// 구름이 있는 좌표를 나타냄
	static int[] d, s;// 이동 방향, 이동 거리
	static int[] dx = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };
	static int[] dy = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };
	static int N, M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		water = new int[N][N];
		d = new int[M];
		s = new int[M];
		
		// 1. 입력받은 정보들을 통해 처음에는 구름 좌표가 지정되어 있으므로 초기에 cloud에 넣고 시작한다.
		// 문제에서 처음 주어진 구름들의 좌표 생성
		cloud.add(new Cloud(N - 1, 0));
		cloud.add(new Cloud(N - 1, 1));
		cloud.add(new Cloud(N - 2, 0));
		cloud.add(new Cloud(N - 2, 1));

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				water[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			d[i] = Integer.parseInt(st.nextToken());
			s[i] = Integer.parseInt(st.nextToken());
		}

		System.out.println(process());
		br.close();
	}

	/* M번의 비바라기 시전 후 물의 총합 반환 */
	private static int process() {
		for (int i = 0; i < M; i++) {
			// 2-1. 모든 구름들을 이동시킨 후 이동된 좌표의 물의 양을 1씩 증가시킨다. 이 때, 배열 범위를 벗어나면 다시 반대편에서 시작되기 때문에 모듈러 연산을 해준다.
			// 2-2. 구름이 모두 사라진다고 했지만 나중에 조건이 따로 있으므로 이동된 구름들의 좌표를 isCloud에서 true로 설정한다.
			Move(d[i], s[i]);
			// 3. 물복사버그가 시전되면 2-2에서 체크한 isCloud의 값이 true일 때에만 대각선 사방을 체크해서 물이 존재하는 좌표의 수만큼 현재 좌표에 물의 양을 더해준다.
			// 4. 물의 양이 2 이상이고 isCloud의 값이 false일 때에만 초기화된 cloud에 넣어주고 물의 양을 2 감소시킨다.
			waterCopyBug();
			// 5. 2 ~ 5를 M번 수행한 후 물 양의 총합을 구한다.
		}

		return waterSum();
	}

	/* 모든 구름을 dir방향으로 dist만큼 이동 */
	private static void Move(int dir, int dist) {
		isCloud = new boolean[N][N];
		for (Cloud c : cloud) {
			int cx = (c.x + (dx[dir] + N) * dist) % N;
			int cy = (c.y + (dy[dir] + N) * dist) % N;
			isCloud[cx][cy] = true;
			water[cx][cy]++;
		}
	}

	/* 이동한 위치에 있는 구름에 물복사버그 마법을 시전 */
	private static void waterCopyBug() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (isCloud[i][j]) {// 해당 좌표에 구름이 존재할때 물복사버그 마법 적용 
					int cnt = 0;
					for (int dir = 2; dir <= 8; dir += 2) {// 대각선 방향만 체크
						int nx = i + dx[dir];
						int ny = j + dy[dir];
						if (check(nx, ny) && water[nx][ny] > 0) {
							cnt++;
						}
					}
					water[i][j] += cnt;// 대각선 사방에 있는 물이 있는 개수만큼 물의 양 증가
				}
			}
		}

		cloud = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (water[i][j] >= 2 && !isCloud[i][j]) {// 물의 양이 2이상이고 이동된 구름의 좌표가 아닌 경우에만 새로운 구름을 만듬
					cloud.add(new Cloud(i, j));
					water[i][j] -= 2;
				}
			}
		}
	}

	/* 배열 범위 체크 */
	private static boolean check(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < N;
	}

	/* 물 양의 총합을 구함 */
	private static int waterSum() {
		int res = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				res += water[i][j];
			}
		}
		return res;
	}
}
