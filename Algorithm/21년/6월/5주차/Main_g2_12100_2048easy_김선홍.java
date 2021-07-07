package jun;

import java.io.*;
import java.util.*;

public class Main_g2_12100_2048easy_김선홍 {
	static int[][] map;
	static int[] dir = new int[5];
	static int N, max;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		brute(0);
		System.out.println(max);
		br.close();
	}

	/* 중복순열을 통해 가장 큰 수를 찾음 */
	private static void brute(int cnt) {
		if (cnt == 5) {
			int[][] tmap = new int[N][N];
			for (int i = 0; i < N; i++) {
				System.arraycopy(map[i], 0, tmap[i], 0, N);
			}
			for (int i = 0; i < 5; i++) {
				move(dir[i], tmap);
			}
			max = Math.max(max, maxNum(tmap));
			return;
		}

		for (int i = 0; i < 4; i++) {
			dir[cnt] = i;
			brute(cnt + 1);
		}
	}

	private static void move(int d, int[][] tmap) {
		switch (d) {
		case 0:
			for (int j = 0; j < N; j++) {
				int pi = -1;// 몇 번째 행이 합쳐졌는지 확인하는 변수
				for (int i = 1; i < N; i++) {// 옮기는 방향의 두번째 블록부터 검사
					if (tmap[i][j] == 0) {// 0이면 블록이 아니므로 continue
						continue;
					}

					int ni = i;
					while (ni-- > 0) {
						if (tmap[ni][j] == 0) {// 옮기는 방향의 앞 블록이 0이면 그 방향으로 이동 가능
							continue;
						}

						if (tmap[ni][j] == tmap[i][j]) {// 같은 값을 같는 블록을 찾았다면
							if (pi != ni) {// 아직 합치지 않은 행이라면
								tmap[ni][j] *= 2;
								tmap[i][j] = 0;
								pi = ni;// 합쳐준 행을 저장
							}
							break;
						} else {
							break;
						}
					}

					// 이전에 합쳐진 위치랑 탐색한 위치가 같거나 서로 다른 칸이라면 그 전칸 까지만 이동
					if (pi == ni || tmap[ni][j] != tmap[i][j]) {
						int tmp = tmap[i][j];
						tmap[i][j] = 0;
						tmap[ni + 1][j] = tmp;
					}
				}
			}
			break;
		case 1:
			for (int j = 0; j < N; j++) {
				int pi = N;
				for (int i = N - 2; i >= 0; i--) {
					if (tmap[i][j] == 0) {
						continue;
					}

					int ni = i;
					while (ni++ < N - 1) {
						if (tmap[ni][j] == 0) {
							continue;
						}

						if (tmap[ni][j] == tmap[i][j]) {
							if (pi != ni) {
								tmap[ni][j] *= 2;
								tmap[i][j] = 0;
								pi = ni;
							}
							break;
						} else {
							break;
						}
					}

					if (pi == ni || tmap[ni][j] != tmap[i][j]) {
						int tmp = tmap[i][j];
						tmap[i][j] = 0;
						tmap[ni - 1][j] = tmp;
					}
				}
			}
			break;
		case 2:
			for (int i = 0; i < N; i++) {
				int pj = -1;
				for (int j = 1; j < N; j++) {
					if (tmap[i][j] == 0) {
						continue;
					}

					int nj = j;
					while (nj-- > 0) {
						if (tmap[i][nj] == 0) {
							continue;
						}

						if (tmap[i][nj] == tmap[i][j]) {
							if (pj != nj) {
								tmap[i][nj] *= 2;
								tmap[i][j] = 0;
								pj = nj;
							}
							break;
						} else {
							break;
						}
					}

					if (pj == nj || tmap[i][nj] != tmap[i][j]) {
						int tmp = tmap[i][j];
						tmap[i][j] = 0;
						tmap[i][nj + 1] = tmp;
					}
				}
			}
			break;
		case 3:
			for (int i = 0; i < N; i++) {
				int pj = N;
				for (int j = N - 2; j >= 0; j--) {
					if (tmap[i][j] == 0) {
						continue;
					}

					int nj = j;
					while (nj++ < N - 1) {
						if (tmap[i][nj] == 0) {
							continue;
						}

						if (tmap[i][nj] == tmap[i][j]) {
							if (pj != nj) {
								tmap[i][nj] *= 2;
								tmap[i][j] = 0;
								pj = nj;
							}
							break;
						} else {
							break;
						}
					}

					if (pj == nj || tmap[i][nj] != tmap[i][j]) {
						int tmp = tmap[i][j];
						tmap[i][j] = 0;
						tmap[i][nj - 1] = tmp;
					}
				}
			}
			break;
		}
	}

	/* 이동을 5번 했으면 가장 큰 수를 반환 */
	private static int maxNum(int[][] tmap) {
		int res = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				res = Math.max(res, tmap[i][j]);
			}
		}
		return res;
	}
}