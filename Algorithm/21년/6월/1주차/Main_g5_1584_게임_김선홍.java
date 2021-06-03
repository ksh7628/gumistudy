package jun;

import java.io.*;
import java.util.*;

/**
 * <pre>
 * jun 
 * Main_g5_1584_게임_김선홍.java
 * </pre>
 *
 * @author	: KimSeonhong
 * @date	: 2021. 6. 3.
 * @version	: 0.3
 *
 * 분류: 그래프 이론, 그래프 탐색, 너비 우선 탐색, 누적 합, 다익스트라, 0-1 너비 우선 탐색
 * 난이도: 골드5
 * 혼자 품: X
 * 풀이: 매번 이동할때마다 잃은 생명의 최솟값을 갱신해나가야 되므로 우선순위 큐를 사용하여 풀었다.
 *      일반 큐를 사용할 경우 이동하는 과정에서 잃은 생명이 적은데 이미 방문한 곳이 잃은 생명이 더 많은 경우를 처리하기가 쉽지 않을 것이다.
 * 느낀 점: 처음 풀었을 때에는 일반 큐를 사용하여 풀이를 시도했는데 계속 예외적인 케이스가 발생하는걸 보면서 설계를 잘못했다는 생각이 들었고
 *        같은 스터디원의 풀이를 보니 우선순위 큐를 사용하면 간단하게 풀리는 문제여서 자료구조 선택의 중요성을 느끼게 되었다.
 */
public class Main_g5_1584_게임_김선홍 {
	static class Move implements Comparable<Move> {
		int x, y, life;

		Move(int x, int y, int life) {
			this.x = x;
			this.y = y;
			this.life = life;
		}

		@Override
		public int compareTo(Move m) {// 생명의 최솟값을 구하기 위해 생명의 오름차순으로 정렬하게끔 정의
			return Integer.compare(this.life, m.life);
		}
	}

	static int[][] map = new int[501][501];// 0~500
	static boolean[][] visit = new boolean[501][501];
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			setArea(x1, y1, x2, y2, 1);// 위험 영역을 1로 채움
		}
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			setArea(x1, y1, x2, y2, 2);// 죽음 영역을 2로 채움
		}

		System.out.println(bfs());
		br.close();
	}

	/* 사각형 영역을 채움 */
	private static void setArea(int x1, int y1, int x2, int y2, int fill) {
		int minX = Math.min(x1, x2);// x좌표 시작점
		int maxX = Math.max(x1, x2);// x좌표 끝점
		int minY = Math.min(y1, y2);// y좌표 시작점
		int maxY = Math.max(y1, y2);// y좌표 끝점
		for (int j = minX; j <= maxX; j++) {// 시작점 ~ 끝점 영역 채움
			for (int i = minY; i <= maxY; i++) {
				map[i][j] = fill;
			}
		}
	}

	/* 우선순위 큐를 사용하여 bfs 탐색 */
	private static int bfs() {
		PriorityQueue<Move> pq = new PriorityQueue<>();
		visit[0][0] = true;
		pq.offer(new Move(0, 0, 0));
		while (!pq.isEmpty()) {
			Move m = pq.poll();
			if (m.x == 500 && m.y == 500) {// 도착하면 현재까지 소모된 생명 return
				return m.life;
			}
			for (int dir = 0; dir < 4; dir++) {
				int nx = m.x + dx[dir];
				int ny = m.y + dy[dir];
				// 배열 범위를 벗어나거나, 이미 방문했거나, 죽음 영역이면 continue
				if (check(nx, ny) || visit[nx][ny] || map[nx][ny] == 2) {
					continue;
				}
				visit[nx][ny] = true;
				if (map[nx][ny] == 1) {// 위험 구역이면 잃는 생명을 증가
					pq.offer(new Move(nx, ny, m.life + 1));
				} else {// 안전 구역이면 생명을 잃지않음
					pq.offer(new Move(nx, ny, m.life));
				}
			}
		}
		return -1;// 도착할수 없으면 -1 return
	}

	/* 배열 범위 체크 */
	private static boolean check(int x, int y) {
		return x < 0 || x > 500 || y < 0 || y > 500;
	}
}