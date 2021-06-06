package bj.gold;

import java.util.*;
import java.io.*;

public class bj_1584_게임 {
	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };
	static int SIZE = 501;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		map = new int[SIZE][SIZE];

		// 위험 구역
		int N = Integer.parseInt(br.readLine());
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			setMap(Math.min(x1, x2), Math.min(y1, y2), Math.max(x1, x2), Math.max(y1, y2), 1);
		}

		// 죽음 구역
		int M = Integer.parseInt(br.readLine());
		for (int n = 0; n < M; n++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			setMap(Math.min(x1, x2), Math.min(y1, y2), Math.max(x1, x2), Math.max(y1, y2), -1);
		}
		
		System.out.println(bfs());
		
		/*
		int[][] visited = new int[SIZE][SIZE];

		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] { 0, 0, 0 });
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			
			for (int d = 0; d < 4; d++) {
				int nx = cur[0] + di[d];
				int ny = cur[1] + dj[d];
				
				// 지도 밖으로 안나가고 죽음 구역 아닐 때
				if (nx >= 0 && nx <= 500 && ny >= 0 && ny <= 500 && map[nx][ny] != -1) {
					// 안전 구역 or 위험 구역일 때 hp 조정해준 값이 기존 방문 이력보다 더 적은 hp로 이동이 가능하면 이동 (또는 아직 가본적 없는 곳일 때)
					if (cur[2] + map[nx][ny] < visited[nx][ny] || visited[nx][ny] == 0) {
						visited[nx][ny] = cur[2] + map[nx][ny];
						q.offer(new int[] { nx, ny, visited[nx][ny] });
					}
				}
			}
		}
		
		if (visited[SIZE - 1][SIZE - 1] == 0) System.out.println(-1);
		else System.out.println(visited[SIZE - 1][SIZE]);
		*/

		br.close();
	}

	// x1 < x2 && y1 < y2
	static void setMap(int x1, int y1, int x2, int y2, int val) {
		for (int i = x1; i <= x2; i++) {
			for (int j = y1; j <= y2; j++) {
				map[i][j] = val;
			}
		}
	}
	
	// *우선순위 큐* 이용..!
	static int bfs() {
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// pq에 들어갈 객체는 int배열.. x, y, hp 순으로 들어갈거라 hp 오름차순으로 정렬
				return Integer.compare(o1[2], o2[2]);
			}
		});
		
		boolean[][] visited = new boolean[SIZE][SIZE];
		
		pq.offer(new int[] { 0, 0, 0 });
		visited[0][0] = true;
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			
			// 끝에 도달했으면 바로 종료 (pq이기 때문에 끝에 도달한 순간 최소 비용으로 도착한다는 것을 보장)
			if (cur[0] == SIZE - 1 && cur[1] == SIZE - 1) {
				return cur[2];
			}
			
			for (int d = 0; d < 4; d++) {
				int nx = cur[0] + di[d];
				int ny = cur[1] + dj[d];
				
				// 밖으로 안나가고 죽음 구역 아니면서 방문한 적 없는 곳일 떄
				if (nx >= 0 && nx < SIZE && ny >= 0 && ny < SIZE && map[nx][ny] != -1 && !visited[nx][ny]) {
					// 안전 구역 0, 위험 구역 1 이기 때문에 map 좌표 값 더해주면 됨
					pq.offer(new int[] { nx, ny, cur[2] + map[nx][ny] });
					visited[nx][ny] = true;
				}
			}
		}
		
		return -1;
	}
}

// 탈출 게임
// 죽음의 구역 : 접근 불가
// 위험한 구역 : 움직일 때 마다 hp 1씩 감소 (들어갈 때 피 감소)
// (0,0) 출발, (500,500) 도착
// 4방향 이동 가능