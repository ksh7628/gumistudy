package bj.gold;

import java.util.*;
import java.io.*;

public class bj_10026_적록색약 {
	static int di[] = { -1, 0, 1, 0 };
	static int dj[] = { 0, 1, 0, -1 };
	static int N;
	static char[][] map;
	static boolean[][] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		int a = getAreaCnt();
		setRG();
		int b = getAreaCnt();
		
		System.out.println(a + " " + b);
		
		br.close();
	}
	
	private static void setRG() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 'R')
					map[i][j] = 'G';
			}
		}
	}

	private static int getAreaCnt() {
		int areaCnt = 0;
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					bfs(i, j);
					areaCnt++;
				}
			}
		}
		
		return areaCnt;
	}
	
	private static void bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<int[]>();
		
		q.offer(new int[] { x, y });
		visited[x][y] = true;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int curX = cur[0];
			int curY = cur[1];
			char curVal = map[curX][curY];
			
			for (int d = 0; d < 4; d++) {
				int nx = curX + di[d];
				int ny = curY + dj[d];
				
				if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny] && map[nx][ny] == curVal) {
					q.offer(new int[] { nx, ny } );
					visited[nx][ny] = true;
				}
			}
		}
	}
}

// 구역은 같은 색으로 이루어져 있고, 같은 색이 상하좌우로 인접해 있는 경우 같은 구역
// 적록색약은 R,G 구분 못함 (같은걸로 취급)
// 적록색약이 봤을 때, 아닌 사람이 봤을 때 구역의 수 구하라

// 적록 아닌사람 먼저 구하고
// R,G 같은걸로 다 바꿔서 다시?