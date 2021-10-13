package sep;

import java.io.*;
import java.util.*;

/**
 * @author	: KimSeonhong
 * @date	: 2021. 9. 14.
 *
 * 분류: 구현, 시뮬레이션
 * 난이도: 골드5
 * 소요 시간: 1h 07m
 * 혼자 품: O
 * 풀이: 모든 파이어볼이 동시에 이동한 후 하나로 합쳐지기 때문에 2개의 큐를 사용하여 구현했다.
 * 느낀 점: 
 * '질량이 0인 파이어볼은 소멸되어 없어진다' 이 한문장을 놓쳐서 글을 여러 번 읽은 끝에 해결했다.
 * 삼성 A형 문제 : 문제 잘 읽기>>설계>>>>>>효율성 인걸 잊지말자.
 */
public class Main_g5_20056_마법사상어와파이어볼_김선홍 {
	static class FB {
		int m, s, d;

		public FB(int m, int s, int d) {
			super();
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}

	static ArrayDeque<FB>[][] map;
	static ArrayDeque<FB>[][] nmap;
	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };// 12시부터 시계방향
	static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static int N, M, K;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new ArrayDeque[N][N];
		nmap = new ArrayDeque[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new ArrayDeque<>();
				nmap[i][j] = new ArrayDeque<>();
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			map[r][c].offer(new FB(m, s, d));
		}

		System.out.println(solution());
		br.close();
	}

	private static int solution() {
		while (--K >= 0) {
			// 1. 모든 파이어볼이 자신의 방향 di로 속력 si칸 만큼 이동한다.
			// 이동하는 중에는 같은 칸에 여러 개의 파이어볼이 있을 수도 있다.
			move();
			// 2. 파이어볼은 4개의 파이어볼로 나누어진다.
			divide();
		}
		return resultSum();
	}
	
	// 파이어볼 이동
	private static void move() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				while (!map[i][j].isEmpty()) {
					FB fb = map[i][j].poll();
					// ArrayIndexOutOfBounds를 방지하기 위해 방향값에 N을 더해줌
					int nx = (i + (dx[fb.d] + N) * fb.s) % N;
					int ny = (j + (dy[fb.d] + N) * fb.s) % N;
					nmap[nx][ny].offer(new FB(fb.m, fb.s, fb.d));
				}
			}
		}
	}

	// 2개 이상인 파이어볼을 모두 합친 후 4개로 나눔
	private static void divide() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int size = nmap[i][j].size();
				if (size > 1) {// 2개 이상인 경우 나눠줌
					int msum = 0;
					int ssum = 0;
					boolean isOdd = false;
					boolean isEven = false;

					while (!nmap[i][j].isEmpty()) {
						FB fb = nmap[i][j].poll();
						msum += fb.m;
						ssum += fb.s;

						if (fb.d % 2 == 1) {
							isOdd = true;
						} else {
							isEven = true;
						}
					}
					
					// 3. 나누어진 파이어볼의 질량, 속력, 방향은 다음과 같다.
					// 질량은 ⌊(합쳐진 파이어볼 질량의 합)/5⌋이다.
					// 속력은 ⌊(합쳐진 파이어볼 속력의 합)/(합쳐진 파이어볼의 개수)⌋이다.
					msum /= 5;
					ssum /= size;

					// 4. 질량이 0인 파이어볼은 소멸되어 없어진다.
					if (msum == 0) {
						continue;
					}

					// 합쳐지는 파이어볼의 방향이 모두 홀수이거나 모두 짝수이면, 방향은 0, 2, 4, 6이 되고, 그렇지 않으면 1, 3, 5, 7이 된다.
					if (!(isOdd && isEven)) {// 오직 짝수이거나 오직 음수
						for (int k = 0; k < 4; k++) {
							map[i][j].offer(new FB(msum, ssum, 2 * k));
						}
					} else {
						for (int k = 0; k < 4; k++) {
							map[i][j].offer(new FB(msum, ssum, 2 * k + 1));
						}
					}
					
				} else if (nmap[i][j].size() == 1) {// 한개는 합칠 게 없으므로 다시 map에 추가
					map[i][j].offer(nmap[i][j].poll());
				}
			}
		}
	}

	// 남아있는 파이어볼 질량의 합
	private static int resultSum() {
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				while (!map[i][j].isEmpty()) {
					FB fb = map[i][j].poll();
					sum += fb.m;
				}
			}
		}
		return sum;
	}
}