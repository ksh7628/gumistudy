import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
한 칸에는 학생 한 명의 자리만 있을 수 있고, |r1 - r2| + |c1 - c2| = 1을 만족하는 두 칸이 (r1, c1)과 (r2, c2)를 인접하다고 한다.

비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다.
1을 만족하는 칸이 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
2를 만족하는 칸도 여러 개인 경우에는 행의 번호가 가장 작은 칸으로, 그러한 칸도 여러 개이면 열의 번호가 가장 작은 칸으로 자리를 정한다.
*/

public class Main {

	static ArrayList<Integer>[] like;
	static ArrayList<Integer> order;
	static int[][] map;
	static int[][] d = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
	static int N, priority, empty;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());

		// 초기화
		map = new int[N][N];
		int size = N * N;
		like = new ArrayList[size + 1];
		order = new ArrayList<>();
		for (int i = 0; i <= size; i++) {
			like[i] = new ArrayList<>();
		}

		// 좋아하는 학생
		for (int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			int student = Integer.parseInt(st.nextToken());
			order.add(student);
			for (int j = 0; j < 4; j++) {
				like[student].add(Integer.parseInt(st.nextToken()));
			}
		}

		for (int k = 0; k < size; k++) {
			int sNum = order.get(k);

			int sPriority = 0;
			int sEmpty = 0;
			int x = -1, y = -1;
			for (int i = N - 1; i >= 0; i--) {
				for (int j = N - 1; j >= 0; j--) {
					if (map[i][j] != 0)
						continue;
					priority = 0;
					empty = 0;
					checkAdj(sNum, i, j);
					if (sPriority < priority) {
						sPriority = priority;
						sEmpty = empty;
						x = i;
						y = j;
					} else if (sPriority == priority) {
						if (sEmpty < empty) {
							sEmpty = empty;
							x = i;
							y = j;
						} else if (sEmpty == empty) {
							x = i;
							y = j; // n-1부터 시작하므로 자연스럽게 3번조건
						}
					}
				}
			}
			map[x][y] = sNum;
		}

		int res = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				priority = 0;
				checkAdj(map[i][j], i, j);
				res += getSatisfied(priority);
			}
		}
		System.out.println(res);
		br.close();
	}

	static void checkAdj(int n, int i, int j) {
		for (int dir = 0; dir < 4; dir++) {
			int x = i + d[dir][0];
			int y = j + d[dir][1];
			if (x < 0 || y < 0 || x >= N || y >= N)
				continue;
			if (map[x][y] == 0) {
				empty++;
				continue;
			}
			for (int k = 0; k < 4; k++) {
				if (map[x][y] == like[n].get(k)) {
					priority++;
					break;
				}
			}
		}
	}

	static int getSatisfied(int p) {
		switch (p) {
			case 1:
				return 1;

			case 2:
				return 10;

			case 3:
				return 100;

			case 4:
				return 1000;

		}
		return 0;
	}

}