package baekjoon;

import java.io.*;
import java.util.*;

public class Main_bj_16236_아기상어 {
	static class Point implements Comparable<Point>{
		int x;
		int y;
		int dist;
		
		public Point(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}

		@Override
		public int compareTo(Point o) {
			// 거리가 가까운 물고기가 많다면, 가장 위에 있는 물고기, 그러한 물고기가 여러마리라면, 가장 왼쪽에 있는 물고기
			if(this.dist == o.dist) {
				if(this.x == o.x) 
					return Integer.compare(this.y, o.y);
				return Integer.compare(this.x, o.x);
			}
			return Integer.compare(this.dist, o.dist);
		}
	}
	static int N;
	static int sharkSize = 2;
	static int answer;
	static int eatCnt;
	static int[] di = {-1, 0, 1, 0};
	static int[] dj = {0, 1, 0, -1};
	static int sharkX;
	static int sharkY;
	static boolean[][] visit;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		int[][] area = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				area[i][j] = Integer.parseInt(st.nextToken());
				if(area[i][j] == 9) {
					sharkX = i;
					sharkY = j;
					area[i][j] = 0;
				}
			}
		}
		
		PriorityQueue<Point> queue = new PriorityQueue<>();
		queue.add(new Point(sharkX, sharkY, 0));
		visit = new boolean[N][N];
		visit[sharkX][sharkY] = true;
		
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			for(int d = 0; d < 4; d++) {
				int nx = cur.x + di[d];
				int ny = cur.y + dj[d];
				// 범위 안, 방문 X, 내 크기보다 작거나 같다면 지나갈 수 있음(물고기가 없어도 지나갈 수 있음)
				if(nx < 0 || nx >= N || ny < 0 || ny >= N)
					continue;
				if(visit[nx][ny])
					continue;
				if(area[nx][ny] > sharkSize)
					continue;
				visit[nx][ny] = true;
				if(area[nx][ny] <= sharkSize)
					queue.add(new Point(nx, ny, cur.dist + 1));
			}
			
			if(queue.peek() != null) {
				Point peek = queue.peek();
				if(area[peek.x][peek.y] <sharkSize && area[peek.x][peek.y] > 0) {
					eatCnt++;
					// 자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1 증가한다!!!
					if(eatCnt == sharkSize) {
						sharkSize++;
						eatCnt = 0;
					}
					area[peek.x][peek.y] = 0;		// 먹으면 그 칸은 빈 칸
					
					queue.clear();
					queue.add(new Point(peek.x, peek.y, 0));
					answer += peek.dist;
					visit = new boolean[N][N];
					visit[peek.x][peek.y] = true;
				}
			}
		}
		
		System.out.println(answer);
	}
}

