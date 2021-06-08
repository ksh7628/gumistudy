package jun;

import java.io.*;
import java.util.*;

/**
 * @author	: KimSeonhong
 * @date	: 2021. 6. 8.
 *
 * 분류: 구현, 그래프 이론, 그래프 탐색, 너비 우선 탐색, 시뮬레이션
 * 난이도: 골드4
 * 혼자 품: O
 * 풀이: 
 * 1. 입력을 받으면서 값이 9이면 아기 상어의 좌표를 저장
 * 2-1. bfs를 통해 아기상어를 이동시키면서 먹을 수 있는 물고기가 있다면 그 좌표를 우선순위 큐에 저장 
 * 2-2. 이때 먹을 수 있는 물고기의 거리를 최소값으로 갱신하면서 최소 거리를 넘어간다면 탐색 중단
 * 3-1. 우선순위 큐의 크기가 0이 아니라면(아기 상어가 먹을 수 있는 물고기가 존재한다면) 물고기를 먹음
 * 3-2. 아기 상어가 먹은 물고기 수가 아기상어의 크기와 같아지면 먹은 물고기 수 초기화, 아기상어 크기+1, 시간 갱신
 * 3-3. 더 이상 먹을 수 있는 물고기가 존재하지 않는다면  그 동안 구한 시간이 정답이 됨
 * 느낀 점: 
 * 여러번 풀었던 문제이지만 이번에 처음 풀었을때에는 최소 거리 개념을 적용하기 전이라서 성능이 저번보다 많이 안좋아졌다.
 * 뒤늦게 떠올려서 리팩토링 하니 성능이 저번보다는 좋아졌다.
 */
public class Main_g4_16236_아기상어_김선홍 {
	static class Shark {
		int x, y;

		Shark(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	/*
	 * 먹을 수 있는 물고기가 두 마리 이상인 경우
	 * 1순위: 가장 가까운 거리
	 * 2순위: 가장 윗쪽
	 * 3순위: 가장 왼쪽
	 */
	static class Comp implements Comparator<Shark> {
		@Override
		public int compare(Shark s1, Shark s2) {
			if (s1.x == s2.x) {
				return s1.y - s2.y;
			}
			return s1.x - s2.x;
		}
	}

	static int[][] map, dist;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int N, sx, sy, fishCnt, size = 2, time;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {// 아기 상어 좌표 저장
					sx = i;
					sy = j;
				}
			}
		}

		map[sx][sy] = 0;// 아기상어가 이동할 것이므로 0으로 변환
		while (bfs());// 탐색 시작
		System.out.println(time);
		br.close();
	}

	/* bfs를 통해 물고기를 먹을 수 있다면 true, 더 이상 먹을 수 없다면 false를 반환  */
	private static boolean bfs() {
		int min = Integer.MAX_VALUE;// 먹을 수 있는 물고기와 아기 상어간 최단거리를 갱신하기 위한 변수
		dist = new int[N][N];
		Queue<Shark> q = new LinkedList<>();
		Queue<Shark> pq = new PriorityQueue<>(new Comp());// 먹을 수 있는 물고기들의 정보들을 Comp에 정의된 정렬 적용
		dist[sx][sy] = 1;// 1 base
		q.offer(new Shark(sx, sy));
		while (!q.isEmpty()) {
			Shark s = q.poll();
			if (dist[s.x][s.y] > min) {// 먹을 수 있는 물고기가 존재하는 거리보다 아기상어가 더 이동했다면 탐색 종료
				break;
			}
			
			for (int dir = 0; dir < 4; dir++) {
				int nx = s.x + dx[dir];
				int ny = s.y + dy[dir];
				// 배열 범위를 벗어났거나 이미 방문했거나 아기상어 크기보다 물고기 크기가 더 크다면 continue
				if (check(nx, ny) || dist[nx][ny] > 0 || map[nx][ny] > size) {
					continue;
				}

				dist[nx][ny] = dist[s.x][s.y] + 1;
				// 아기상어가 먹을 수 있는 물고기가 있고 그 물고기가 최단거리에 있다면 최단거리 갱신 후 좌표를 우선순위 큐에 담음
				if (map[nx][ny] < size && map[nx][ny] != 0 && dist[nx][ny] <= min) {
					min = dist[nx][ny];
					pq.offer(new Shark(nx, ny));
				}
				q.offer(new Shark(nx, ny));
			}
		}

		if (pq.isEmpty()) {// 먹을 수 있는 물고기가 하나도 없다면 false
			return false;
		}
		fistEat(pq.poll());
		return true;
	}

	/* 물고기를 먹은 후 현재 크기와 같은 수의 물고기를 먹었다면 크기를 증가 */
	private static void fistEat(Shark s) {
		time += dist[s.x][s.y] - 1;// 시간 갱신(1 base이므로 -1해줘야함)
		map[sx][sy] = 0;
		sx = s.x;
		sy = s.y;

		if (++fishCnt == size) {
			size++;
			fishCnt = 0;
		}
	}

	/* 배열 범위 체크 */
	private static boolean check(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= N;
	}
}