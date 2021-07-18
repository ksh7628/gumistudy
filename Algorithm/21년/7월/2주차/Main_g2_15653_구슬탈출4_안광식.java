package bj.gold;

import java.io.*;
import java.util.*;

public class bj_15653_구슬탈출4 {
	static int N, M;
	static int rx1, ry1, bx1, by1;
	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };
	static char[][] map;
	static boolean[][][][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		visited = new boolean[N][M][N][M];
		
		for (int i = 0; i < N; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < M; j++) {
				if (tmp.charAt(j) == '#') 
					map[i][j] = '#';
				else if (tmp.charAt(j) == 'O')
					map[i][j] = 'O';
				else {
					if(tmp.charAt(j) == 'R') {
						rx1 = i;
						ry1 = j;
					}
					else if (tmp.charAt(j) == 'B') {
						bx1 = i;
						by1 = j;
					}
					map[i][j] = '.';
				}
			}
		}
		
		System.out.println(Solution());
		
		br.close();
	}
	private static int Solution() {
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] { rx1, ry1, bx1, by1, 0 });
		visited[rx1][ry1][bx1][by1] = true;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int rx = cur[0];
			int ry = cur[1];
			int bx = cur[2];
			int by = cur[3];
			int cnt = cur[4];

			if (map[rx][ry] == 'O') {
				return cnt;
			}
			
			for (int d = 0; d < 4; d++) {
				int rnx = rx;
				int rny = ry;
				int bnx = bx;
				int bny = by;
				
				// 일단 빨간공, 파란공 둘 다 쭉 밀어주기
				while (map[rnx + di[d]][rny + dj[d]] != '#' && map[rnx][rny] != 'O') {
					rnx += di[d];
					rny += dj[d];
				}
				
				while (map[bnx + di[d]][bny + dj[d]] != '#' && map[bnx][bny] != 'O') {
					bnx += di[d];
					bny += dj[d];
				}
				
				// 빨, 파 좌표가 겹치면 기존 위치 기준으로 좌표 다시 잡아주기
				if (rnx == bnx && rny == bny) {
					if (map[rnx][rny] == 'O') continue;
					
					// 상
					if (d == 0) {
						if (rx > bx) {
							rnx += 1;
						}
						else {
							bnx += 1;
						}
					}
					// 우
					else if (d == 1) {
						if (ry < by) {
							rny -= 1;
						}
						else {
							bny -= 1;
						}
					}
					// 하
					else if (d == 2) {
						if (rx < bx) {
							rnx -= 1;
						}
						else {
							bnx -= 1;
						}
					}
					// 좌
					else if (d == 3) {
						if (ry > by) {
							rny += 1;
						}
						else {
							bny += 1;
						}
					}
				}
				
				// 파란공이 들어가는 경우 컷
				if (map[bnx][bny] == 'O') continue;
				
				// 최종 좌표 방문 여부 검사
				if (!visited[rnx][rny][bnx][bny]) {
					visited[rnx][rny][bnx][bny] = true;
					q.offer(new int[] { rnx, rny, bnx, bny, cnt + 1 });
				}
			}
		}
		
		return -1;
	}
}

// N*M
// 빨, 파 구슬 하나씩, 빨강을 구멍에 넣으면 승리
// 상하좌우 기울이기 (빨 ,파 동시에 움직이고 가장 끝 칸까지 이동)
// 파란 구슬은 구멍에 들어가면 안됨
// 빨 파 겹칠 수 없음 (이동 조건은 == '.' 으로)
// 더 이상 구슬이 움직이지 않을 때 까지 수행.. (움직일 구슬이 파랑밖에 남지 않을때 까지?)
// R,B 동시에 체크하는 visited ..........

// . # O R B








