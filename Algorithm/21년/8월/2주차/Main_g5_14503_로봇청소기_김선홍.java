package aug;

import java.io.*;
import java.util.*;

/**
 * @author	: KimSeonhong
 * @date	: 2021. 8. 10.
 *
 * 분류: 구현, 시뮬레이션
 * 난이도: 골드5
 * 소요 시간: 0h 55m
 * 혼자 품: O
 * 풀이: 방향은 시계방향으로 주어지지만 탐색을 할 때에는 반시계방향으로 탐색을 하는 것에 주의하고 현재 위치가 0일때만 청소 횟수를 카운트 해주면 된다.
 * 느낀 점: 
 * 처음에 (방향+3+i)%4로 줘서 반시계 방향으로 가지 못하여 (방향+7-i)%4로 주게 되었고 방향 전환 처리는 잘 해준것 같은데 청소가 안된 방까지 카운트를 해주는 부분을 수정하니 무난하게 풀렸다.
 * 오랜만에 푼 시뮬레이션 문제였고 예전에 고생을 꽤나 한 문제였는데도 항상 조그마한 실수때매 생각보다 시간이 더 걸리게 된 것 같다. 문제를 꼼꼼하게 읽는 습관이 중요하다.
 */
public class Main_g5_14503_로봇청소기_김선홍 {
	static int[][] map;
	static int[] dx = { -1, 0, 1, 0 };// 북 동 남 서(시계방향)
	static int[] dy = { 0, 1, 0, -1 };
	static int N, M, x, y, d;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		st = new StringTokenizer(br.readLine(), " ");
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(cleaner());
		br.close();
	}

	// map[x][y] = 0: 아직 청소하지 않은 구역, 1: 벽, 2: 청소한 구역 으로 정의
	private static int cleaner() {
		int res = 0;
		loop: while (true) {
			if (map[x][y] == 0) {// 1. 현재 위치를 청소한다, 이미 청소한 곳이면 청소하지 않는다.
				map[x][y] = 2;
				res++;
			}

			for (int i = 0; i < 4; i++) {// 2. 현재 위치에서 현재 방향을 기준으로 왼쪽 방향부터 차례대로 인접한 칸을 탐색한다.
				int nd = (d + 7 - i) % 4;// 현재 방향의 반시계 방향
				int nx = x + dx[nd];
				int ny = y + dy[nd];

				if (map[nx][ny] == 0) {// a. 왼쪽 방향에 아직 청소하지 않은 공간이 존재한다면, 그 방향으로 회전한 다음 한 칸을 전진하고 1번부터 진행한다.
					d = nd;
					x = nx;
					y = ny;
					continue loop;
				}
				
				// b. 왼쪽 방향에 청소할 공간이 없다면, 그 방향으로 회전하고 2번으로 돌아간다.
			}

			// d. 네 방향 모두 청소가 이미 되어있거나 벽이면서, 뒤쪽 방향이 벽이라 후진도 할 수 없는 경우에는 작동을 멈춘다.
			if (map[x + dx[(d + 2) % 4]][y + dy[(d + 2) % 4]] == 1) {
				break;
			}

			// c. 네 방향 모두 청소가 이미 되어있거나 벽인 경우에는, 바라보는 방향을 유지한 채로 한 칸 후진을 하고 2번으로 돌아간다.
			x += dx[(d + 2) % 4];
			y += dy[(d + 2) % 4];
		}

		return res;
	}
}
