package jun;

import java.io.*;
import java.util.*;

/**
 * @author	: KimSeonhong
 * @date	: 2021. 6. 15.
 *
 * 분류: 그래프 이론, 그래프 탐색, 너비 우선 탐색
 * 난이도: 골드4
 * 혼자 품: O
 * 풀이: 
 * 1. 문자형 2차원 배열에 입력받은 맵의 정보를 저장 및 벽을 부순 횟수마다 방문을 따로 체크하기 위해 3차원 boolean형 배열을 정의한다.
 * 2. bfs를 통해 벽을 부술 수 있을 때와 부술 수 없을 때를 crashCnt 멤버 변수를 이용하여 처리한다.
 * 느낀 점: 
 * 이 문제를 세번째로 풀면서 점점 최적화가 잘 되가는데 제일 의문인 점이 3차원 배열의 인덱스 정의 순서에 따라 시간이 꽤 차이가 난다는 점이다.
 * [벽을 부순 횟수][행][열]로 정의한 배열이 [행][열][벽을 부순 횟수] 보다 약 100ms 더 빨라서 이 부분이 궁금해졌기 떄문에 검색을 해봤는데
 * 이 부분은 따로 찾을 수 없어서 백준 질문 게시판에 글을 작성했다.(해결: 캐시의 지역성과 관련된 문제였고 다차원 배열의 크기는 작은 차수부터 설정해주면 좋다.)
 */
public class Main_g4_2206_벽부수고이동하기_김선홍 {
	static class Wall {
		int x, y, dist, crashCnt;// 행, 열, 이동거리, 벽 부순 횟수

		Wall(int x, int y, int dist, int crashCnt) {
			this.x = x;
			this.y = y;
			this.dist = dist;
			this.crashCnt = crashCnt;
		}
	}

	static char[][] map;
	static boolean[][][] visit;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int N, M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		visit = new boolean[2][N][M];

		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}

		System.out.println(bfs());
		br.close();
	}

	/* bfs를 통해 최소거리를 반환 */
	private static int bfs() {
		ArrayDeque<Wall> q = new ArrayDeque<>();
		visit[0][0][0] = true;
		q.offer(new Wall(0, 0, 1, 0));// 시작하는 칸을 포함해서 거리를 셈

		while (!q.isEmpty()) {
			Wall w = q.poll();
			if (w.x == N - 1 && w.y == M - 1) {// 도착했다면 거리 반환
				return w.dist;
			}

			for (int dir = 0; dir < 4; dir++) {
				int nx = w.x + dx[dir];
				int ny = w.y + dy[dir];
				if (check(nx, ny)) {// 배열 범위를 벗어난다면 다음 탐색으로
					continue;
				}

				if (w.crashCnt == 1) {// 벽을 이미 한번 부쉈다면
					if (visit[1][nx][ny] || map[nx][ny] == '1') {
						continue;
					}
					
					visit[1][nx][ny] = true;
					q.offer(new Wall(nx, ny, w.dist + 1, 1));
				} else {// 벽을 아직 안부쉈다면
					if (visit[0][nx][ny]) {
						continue;
					}
					
					visit[map[nx][ny] - '0'][nx][ny] = true;
					q.offer(new Wall(nx, ny, w.dist + 1, map[nx][ny] - '0'));
				}
			}
		}

		return -1;// 도착 못했다면 -1 반환
	}

	/* 배열 범위 체크 */
	private static boolean check(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= M;
	}
}
