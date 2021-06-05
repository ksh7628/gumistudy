package jun;

import java.io.*;
import java.util.*;

/**
 * <pre>
 * jun 
 * Main_s1_21608_상어초등학교_김선홍.java
 * </pre>
 *
 * @author	: KimSeonhong
 * @date	: 2021. 6. 5.
 * @version	: 0.2
 *
 * 분류: 구현
 * 난이도: 실버1
 * 혼자 품: O
 * 풀이: ArrayList 배열을 만들어서 현재 학생의 번호와 좋아하는 학생의 번호들을 차례대로 저장한 후 문제에서 주어진 조건의 우선순위에 따라 해당 좌표를 갱신시킨 후
 *      모든 학생들이 map 배열에 저장되었다면 사방탐색을 통해 만족도를 계산하여 풀이하였다.
 * 느낀 점: 처음 시간을 재고 풀었을때에는 자리배정을 어떻게 해야 되는지도 막막했고 시간내에 풀지 못했고, 두번째 풀이를 시도했을때도 조건을 모두 충족시키기가 쉽지 않았다.
 *        결국 시간을 많이 소모해서 풀긴 풀었으나 자료구조와 각 변수들을 효율적으로 사용하지 않은 것 같아서 다른 사람이 풀이한 더 좋은 코드를 참조해봐야겠다.
 *        오랜만에 시뮬레이션 문제를 풀어서 그런지 실버1인데도 굉장히 애를 먹은 문제여서 다음에 꼭 다시 풀어봐야겠다.
 */
public class Main_s1_21608_상어초등학교_김선홍 {
	static ArrayList<Integer>[] stuList;
	static int[][] map;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int[] satis = { 0, 1, 10, 100, 1000 };// 학생 수(인덱스)에 따른 만족도를 나타낸 배열
	static int N;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];// (1,1) ~ (N,N)
		stuList = new ArrayList[N * N + 1];
		for (int i = 1; i <= N * N; i++) {
			stuList[i] = new ArrayList<>();
		}

		for (int i = 1; i <= N * N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 5; j++) {
				stuList[i].add(Integer.parseInt(st.nextToken()));// stuList[i] = { 자기 자신, 선호학생1, 선호학생2, 선호학생3, 선호학생4 }
			}
		}

		process();
		System.out.println(getSatisfaction());
		br.close();
	}

	/* 
	 * Solution
	 * 1. 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다.
	 * 2. 1을 만족하는 칸이 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
	 * 3. 2를 만족하는 칸도 여러 개인 경우에는 행의 번호가 가장 작은 칸으로, 그러한 칸도 여러 개이면 열의 번호가 가장 작은 칸으로 자리를 정한다.
	 * 조건 3의 경우, 행과 열을 오름차순으로 탐색하기 때문에 조건의 우선순위에 따라 행과 열을 바로 갱신함(여러 개면 첫번째 칸에서 이미 갱신됨)
	 */
	private static void process() {
		map[2][2] = stuList[1].get(0);// 초기 자리 세팅: N >= 3이고 처음엔 빈공간 밖에 없으므로 최우선순위는 (2,2)가 된다.
		for (int k = 2; k <= N * N; k++) {
			int likeMax = 0, emptyMax = -1, row = 0, col = 0;
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (map[i][j] == 0) {
						int cnt = satisPosition(stuList[k], i, j);// 현재 위치에서 인접하는 좋아하는 학생의 수
						int ec = emptyPosition(stuList[k], i, j);// 현재 위치에서 인접하는 빈 자리의 수
						if (likeMax < cnt) {// 조건 1
							row = i;
							col = j;
							likeMax = cnt;
							emptyMax = ec;
						} else if (likeMax == cnt && emptyMax < ec) {// 조건 2
							row = i;
							col = j;
							emptyMax = ec;
						}
					}
				}
			}
			map[row][col] = stuList[k].get(0);// 갱신된 좌표에 해당하는 학생의 번호를 저장
		}
	}

	/* 현재 자리에 인접한 좋아하는 학생의 수를 구해주는 메서드 */
	private static int satisPosition(ArrayList<Integer> list, int x, int y) {
		int res = 0;
		for (int idx = 1; idx <= 4; idx++) {
			for (int dir = 0; dir < 4; dir++) {
				int nx = x + dx[dir];
				int ny = y + dy[dir];
				if (check(nx, ny)) {
					continue;
				}
				if (map[nx][ny] == list.get(idx)) {
					res++;
					break;
				}
			}
		}
		return res;
	}

	/* 현재 자리에 인접한 빈 자리의 수를 구해주는 메서드 */
	private static int emptyPosition(ArrayList<Integer> list, int x, int y) {
		int res = 0;
		for (int dir = 0; dir < 4; dir++) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			if (check(nx, ny)) {
				continue;
			}
			if (map[nx][ny] == 0) {
				res++;
			}
		}
		return res;
	}

	/* 배열 범위 체크 */
	private static boolean check(int x, int y) {
		return x < 1 || x > N || y < 1 || y > N;
	}

	/* 만족도 총합 계산 */
	private static int getSatisfaction() {
		int res = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				for (int k = 1; k <= N * N; k++) {
					if (map[i][j] == stuList[k].get(0)) {
						int cnt = 0;
						for (int dir = 0; dir < 4; dir++) {
							int nx = i + dx[dir];
							int ny = j + dy[dir];
							if (check(nx, ny)) {
								continue;
							}
							for (int idx = 1; idx <= 4; idx++) {
								if (map[nx][ny] == stuList[k].get(idx)) {// 현재 학생이 좋아하는 학생의 번호에 해당하는 학생이라면 학생 수 증가
									cnt++;
									break;
								}
							}
						}
						res += satis[cnt];// 해당하는 만족도 만큼 더해줌
						break;
					}
				}
			}
		}
		return res;
	}
}