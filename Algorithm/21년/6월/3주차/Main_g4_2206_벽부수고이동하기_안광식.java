package bj.gold;

import java.io.*;
import java.util.*;

public class bj_2206_벽부수고이동하기 {
	static class Pos { 
		int x, y, dist, destroy;
		public Pos(int x, int y, int dist, int destroy) {
			this.x = x;
			this.y = y;
			this.dist = dist;
			this.destroy = destroy;
		}
	}
	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };
	static int[][] map;
	static boolean[][][] visited;
	static int N, M;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		
		System.out.println(bfs());
		
		br.close();
	}
	
	private static int bfs() {
		Queue<Pos> q = new LinkedList<>();
		q.offer(new Pos(0, 0, 1, 0));
		visited = new boolean[2][N][M];
		visited[0][0][0] = true;
		visited[1][0][0] = true;
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			if (cur.x == N - 1 && cur.y == M - 1) {
				return cur.dist;
			}
			
			for (int d = 0; d < 4; d++) {
				int nx = cur.x + di[d];
				int ny = cur.y + dj[d];
				
				if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
					// 빈 칸일 때 (그냥 진행)
					if (map[nx][ny] == 0 && !visited[cur.destroy][nx][ny]) {
						visited[cur.destroy][nx][ny] = true;
						q.offer(new Pos(nx, ny, cur.dist + 1, cur.destroy));
					}
					
					// 벽일 때
					else {
						// 벽 부술 기회 남아있으면
						if (cur.destroy == 0 && !visited[1][nx][ny]) {
							q.offer(new Pos(nx, ny, cur.dist + 1, 1));
							visited[1][nx][ny] = true;
						}
					}
				}
			}
		}
		
		return -1;
	}
}

// N*M
// 0:빈 칸, 1:벽
// (0,0) -> (N-1,M-1) 최단경로 이동 (시작 칸, 도착 칸도 포함해서 카운트)
// 벽은 한 개 까지 부술 수 있음
