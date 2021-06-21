package bj.gold;

import java.util.*;
import java.io.*;

public class bj_2638_치즈 {
	static class Pos {
		int x, y;
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };
	static int[][] map;
	static boolean[][] air;
	static int N, M;
	static ArrayList<Pos> cheese;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		cheese = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1)
					cheese.add(new Pos(i, j));
			}
		}
		
		System.out.println(answer());
		
		br.close();
	}

	private static int answer() {
		int time = 0;
		
		while(cheese.size() > 0) {
			airBfs();
			
			melt(getRemoveCheese());
			
			time++;
		}
		
		return time;
	}

	private static void airBfs() {
		Queue<Pos> q = new LinkedList<>();
		air = new boolean[N][M];
		
		q.offer(new Pos(0, 0));
		air[0][0] = true;
		
		while (!q.isEmpty()) {
			Pos cur = q.poll();
			
			for (int d = 0; d < 4; d++) {
				int nx = cur.x + di[d];
				int ny = cur.y + dj[d];
				
				if (nx >= 0 && nx < N && ny >= 0 && ny < M && !air[nx][ny]) {
					if (map[nx][ny] == 0) {
						air[nx][ny] = true;
						q.offer(new Pos(nx, ny));
					}
				}
			}
		}
	}

	private static ArrayList<Pos> getRemoveCheese() {
		ArrayList<Pos> res = new ArrayList<>();
		for (int i = 0; i < cheese.size(); i++) {
			Pos cur = cheese.get(i);
			
			int cnt = 0;
			for (int d = 0; d < 4; d++) {
				int nx = cur.x + di[d];
				int ny = cur.y + dj[d];
				if (nx >= 0 && nx < N && ny >= 0 && ny < M && map[nx][ny] == 0 && air[nx][ny]) {
					cnt++;
				}
			}
			
			if (cnt >= 2) {
				cheese.remove(i--);
				res.add(cur);
			}
		}
		
		return res;
	}

	private static void melt(ArrayList<Pos> toRemove) {
		for (int i = 0; i < toRemove.size(); i++) {
			Pos cur = toRemove.get(i);
			map[cur.x][cur.y] = 0; 
		}
	}
}

// N*M (N >= 5, M <= 100)
// 두 변 이상이 실내온도이상이면 한 시간만에 녹음
// 회색이 치즈!! 아닌건 실온
// 치즈 내부 공간은 실온이 아님!!!!
