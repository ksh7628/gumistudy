package bj.silver;

import java.io.*;
import java.util.*;

public class bj_2468_안전영역 {
	static int N, max, highest;
	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };
	static int[][] map;
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		highest = 0;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				highest = Math.max(map[i][j], highest);
			}
		}
		
		max = 0;
		solution();
		
		System.out.println(max);
		
		br.close();
	}
	private static void solution() {
		for (int i = 0; i < highest; i++) {
			visited = new boolean[N][N];
			rain(i);
			safeArea();
		}
	}
	
	private static void safeArea() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (bfs(i, j)) 
					cnt++;
			}
		}
		max = Math.max(max, cnt);
	}
	
	private static boolean bfs(int i, int j) {
		if (visited[i][j])
			return false;
		
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] { i, j });
		visited[i][j] = true;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			
			for (int d = 0; d < 4; d++) {
				int nx = cur[0] + di[d];
				int ny = cur[1] + dj[d];
				
				if (nx >= 0 && nx < N && ny >= 0 && ny < N && map[nx][ny] != 0 && !visited[nx][ny]) {
					q.offer(new int[] { nx, ny });
					visited[nx][ny] = true;
				}
			}
		}
		
		return true;
	}
	
	private static void rain(int height) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] <= height)
					visited[i][j] = true;
			}
		}
	}
}

// N*N map 각 격자에 높이 주어짐
// 비가 0 ~ 100까지 오는 경우들에 대해서 안전영역(섬) 수 구하기
// 물에 잠기는 영역 모두 0 주고 !visited && != 0 인 경우에 대해 bfs 돌아서 섬 수 구하기

// dfs 사용하기 !!!!!!!!!!!!!!!!