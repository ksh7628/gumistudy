package sep;

import java.io.*;
import java.util.*;

/**
 * @author	: KimSeonhong
 * @date	: 2021. 9. 27.
 *
 * 분류: 구현, 자료 구조, 시뮬레이션, 덱, 큐
 * 난이도: 골드5
 * 소요 시간: 1h 01m
 * 혼자 품: O
 * 풀이: 
 * map 배열에 빈칸(0), 뱀(1), 사과(2)로 나타낸 후 큐를 사용하여 꼬리부터 머리 순으로 저장되도록 하여 풀이했다.
 * 명령어 배열의 마지막 명령이 끝난 후에도 벽이나 몸에 부딪힐 때까지 while문을 반복했다.
 * 느낀 점: '게임 시작 시간으로부터 X초가 끝난 뒤' 이 부분을 뒤늦게 이해하였고 명령어 조건문을 잘못 써서 생각보다 오래 걸리게 된 문제였다.
 */
public class Main_g5_3190_뱀_김선홍 {
	static int[][] map;
	static int[] time;
	static char[] dir;
	static int[] dx = { 0, 1, 0, -1 };// 우하좌상
	static int[] dy = { 1, 0, -1, 0 };
	static int N, L, x, y, d;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		int K = Integer.parseInt(br.readLine());

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			map[r][c] = 2;
		}

		L = Integer.parseInt(br.readLine());
		time = new int[L];
		dir = new char[L];

		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			time[i] = Integer.parseInt(st.nextToken());
			dir[i] = st.nextToken().charAt(0);
		}

		System.out.println(solution());
		br.close();
	}

	// map[i][j] = 0(빈 칸), 1(뱀), 2(사과)
	// 꼬리~머리 순으로 큐에 좌표가 저장
	private static int solution() {
		int res = 0, idx = 0;
		ArrayDeque<int[]> q = new ArrayDeque<>();
		map[x][y] = 1;
		q.offer(new int[] { x, y });

		while (true) {
			// 시작과 동시에 1초가 증가하고 뱀이 머리를 내미는 좌표 갱신
			res++;
			x += dx[d];
			y += dy[d];
			if (!check()) {
				break;
			}

			if (map[x][y] == 2) {// 사과를 먹으면 몸길이 늘어남 = 꼬리 제거 안함
				q.offer(new int[] { x, y });
				map[x][y] = 1;
			} else if (map[x][y] == 1) {// 자신 몸에 부딪히면 종료
				break;
			} else {// 빈 칸이면 꼬리 제거
				q.offer(new int[] { x, y });
				map[x][y] = 1;
				int[] p = q.poll();
				map[p[0]][p[1]] = 0;
			}

			// 뱀의 방향을 바꿔야 하는 시간이 되면 문자에 따라 방향 수정
			if (idx < L && res == time[idx]) {
				switch (dir[idx]) {
				case 'L':
					d = (d + 3) % 4;
					break;
				case 'D':
					d = (d + 1) % 4;
				}
				idx++;
			}
		}

		return res;
	}

	// 배열 범위 체크
	private static boolean check() {
		return 0 <= x && x < N && 0 <= y && y < N;
	}
}
