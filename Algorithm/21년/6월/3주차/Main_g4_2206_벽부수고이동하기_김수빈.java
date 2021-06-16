package baekjoon;

import java.io.*;
import java.util.*;

public class Main_g4_2206_벽부수고이동하기 {
	static class Point{
		int x, y;
		int dist;
		int crash;
		
		public Point(int x, int y, int dist, int crash) {
			this.x = x;
			this.y = y;
			this.dist = dist;
			this.crash = crash;		// 0 또는 1(1이 부신 것)
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		boolean[][][] visit = new boolean[2][N][M];
		
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) -'0';
			}
		}
		
		int answer = -1;
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(0, 0, 1, 0));		// 시작하는 칸도 개수로 포함
		visit[0][0][0] = true;
		
		int[] di = {-1, 0, 1, 0};
		int[] dj = {0, 1, 0, -1};
		
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			if(cur.x == N - 1 && cur.y == M - 1) {
				answer = cur.dist;
				break;
			}
			for(int d = 0; d < 4; d++) {
				int nx = cur.x + di[d];
				int ny = cur.y + dj[d];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= M)
					continue;
				// 지나갈 수 있는 경우
				if(map[nx][ny] == 0) {
					if(visit[cur.crash][nx][ny])
						continue;
					queue.add(new Point(nx, ny, cur.dist + 1, cur.crash));
					visit[cur.crash][nx][ny] = true;
				}
				
				// 벽이지만 부술 수 있다!
				else {
					// 부술 수 없는 경우
					if(cur.crash != 0 || visit[cur.crash + 1][nx][ny])
						continue;
					
					// 부술 수 있는 경우
					queue.add(new Point(nx, ny, cur.dist + 1, cur.crash + 1));
					visit[cur.crash + 1][nx][ny] = true;
				}
			}
		}
		System.out.println(answer);
	}
}
