package bj.gold;

import java.util.*;
import java.io.*;

public class bj_16236_아기상어 {
	static class Shark {
		int x, y, size, state;
		
		public Shark(int x, int y) {
			this.x = x;
			this.y = y;
			this.size = 2;
			this.state = 0;
		}
	}
	
	static int N, time;
	static int[] di = { -1, 0, 0, 1 };
	static int[] dj = { 0, -1, 1, 0 };
	static int[][] map;
	static Shark shark;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9)
					shark = new Shark(i, j);
			}
		}
		
		time = 0;
		babyShark();
		
		System.out.println(time);
	}

	private static void babyShark() {
		// 아기상어 현재 위치에서 가장 가까운 물고기 좌표 찾기
		while (true) {
			int[] res = bfs();
			if (res[0] == -1) {
				return;
			}
			// 해당 좌표로 이동 (시간++, 상어포만감++, 상어사이즈++)
			move(res[0], res[1], res[2]);
			
		}
	}

	private static void move(int x, int y, int len) {
		time += len;
		
		shark.state++;
		if (shark.state == shark.size) {
			shark.state = 0;
			shark.size++;
		}
		
		map[shark.x][shark.y] = 0;
		map[x][y] = 9;
		
		shark.x = x;
		shark.y = y;
	}

	private static int[] bfs() {
		Queue<int[]> q = new LinkedList<int[]>();
		boolean[][] visited = new boolean[N][N];
		
		q.offer(new int[] { shark.x, shark.y, 0 });
		visited[shark.x][shark.y] = true;
		
		int minDist = Integer.MAX_VALUE;
		int[] result = { -1, -1, -1 };
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			if (cur[2] > minDist) {
				break;
			}
			
			for (int d = 0; d < 4; d++) {
				int nx = cur[0] + di[d];
				int ny = cur[1] + dj[d];
				// 맵 밖으로 안나가고, 방문 안했고, 상어보다 작거나 같은 사이즈 물고기
				if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny] && map[nx][ny] <= shark.size) {
					q.offer(new int[] { nx, ny, cur[2] + 1 });
					visited[nx][ny] = true;
					
					if (map[nx][ny] < shark.size && map[nx][ny] > 0) {
						if (minDist == Integer.MAX_VALUE) {
							result[0] = nx;
							result[1] = ny;
							result[2] = cur[2] + 1;
							minDist = cur[2] + 1;
						}
						else if (minDist == cur[2] + 1) {
							if (result[0] > nx) {
								result[0] = nx;
								result[1] = ny;
							}
							else if (result[0] == nx) {
								if (result[1] > ny) {
									result[0] = nx;
									result[1] = ny;
								}
							}
						}
					}
				}
			}
		}
		
		return result;
	}
}

// N*N 맵에 물고기 M마리, 상어 1마리 (한 칸에 물고기 최대 1마리)

// 처음 상어 크기 2, 상하좌우 이동 가능
// 자신보다 큰 물고기 칸 못감
// 자신보다 작은 물고기만 먹을 수 있음
// 자신과 크기가 같은 물고기는 지나갈 수만 있음

// 아기상어 이동 규칙
// 1.먹을 수 있는 물고기 없으면 엄마상어 부릅
// 2.먹을 수 있는 물고기 1마리라면 그 물고기 먹으러 감
// 3.먹을 수 있는 물고기 1마리보다 많으면 거리가 가장 가까운 물고기
//  3-1)거리는 아기상어가 있는 칸에서 물고기 있는 칸으로 이동할 때 지나야하는 칸 개수 최소값
//  3-2)거리가 가까운 물고기 많으면 가장 위, 그것도 여러개면 가장 왼쪽 물고기 먹음

// 이동에는 1초 걸리고 먹는데는 시간 안걸림 (이동과 동시에 먹는다)
// 자신의 크기만큼 물고기를 먹으면 1 커짐 (크기 2이면 2마리 먹으면 성장, 3되면 3마리 더먹어야 4로 성장)
// 물고기 먹방 몇초동안 하나?

// 0: 빈 칸
// 1, 2, 3, 4, 5, 6: 칸에 있는 물고기의 크기
// 9: 아기 상어의 위치







