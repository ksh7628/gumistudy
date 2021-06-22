package bj.gold;

import java.util.*;
import java.io.*;

public class bj_21610_마법사상어와비바라기 {
	static int[] di = { 0, -1, -1, -1, 0, 1, 1, 1 }; // 좌 좌상 상 우상 우 우하 하 좌하
	static int[] dj = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[][] map;
	static int[] d, s;
	static int N, M;
	static boolean[][] cloud; // 비가 내리고, 구름이 사라진 위치 저장
	static Queue<int[]> clouds, movedPos;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N + 1][N + 1];
		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 1; j < N + 1; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		d = new int[M];
		s = new int[M];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			d[i] = Integer.parseInt(st.nextToken());
			s[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(solution());
		
		br.close();
	}

	private static int solution() {
		// 마법 시전, (N, 1), (N, 2), (N-1, 1), (N-1, 2) 구름 생성
		init();
		
		for (int i = 0; i < M; i++) {
			// 1.모든 구름이 d방향으로 s칸 이동 (범위 벗어나면 반대편 나오는거 주의)
			// 2.각 구름에서 비가 내려 구름이 있는 칸의 물 +1
			// 3.구름 모두 사라짐
			moveCloud(d[i], s[i]);
			
			// 4.2에서 물이 증가한 칸에 물복사 시전 -> 대각선으로 거리가 1인 칸에 물이 있는 "바구니의 수"만큼 현재위치 물 증가
			//  - 이때는 이동할 때랑 다르게 범위 벗어난 칸은 대각선으로 연결된 칸으로 간주 안함
			//  - 2에서 cloud[][]에 위치 저장해두기
			waterCopy();
			
			// 5.물 양이 2 이상인 모든 칸에 구름 생성, 물 양 2 감소.. 이때, 구름이 생기는 칸은 3에서 구름이 사라진 칸이 아님
			setCloud();
		}
		
		return getSum();
	}

	private static void init() {
		clouds = new LinkedList<>();
		movedPos = new LinkedList<>();
		
		clouds.offer(new int[] { N, 1 });
		clouds.offer(new int[] { N, 2 });
		clouds.offer(new int[] { N - 1, 1 });
		clouds.offer(new int[] { N - 1, 2 });
	}

	private static void moveCloud(int d, int s) {
		cloud = new boolean[N + 1][N + 1];
		
		while(!clouds.isEmpty()) {
			int[] cur = clouds.poll();
			
			int nx = (cur[0] + ((di[d - 1] + (N)) * s)) % (N) + 1;
			int ny = (cur[1] + ((dj[d - 1] + (N)) * s)) % (N) + 1;
			
//			int nx = cur[0];
//			int ny = cur[1];
//			for (int j = 0; j < s; j++) {
//				nx += di[d - 1];
//				ny += dj[d - 1];
//				
//				// 범위 벗어나면 반대편으로 등장.. 이거 쉽게하는거 있었던거같은데...
//				if (nx > N) nx = 1;
//				else if (nx < 1) nx = N;
//				
//				if (ny > N) ny = 1;
//				else if (ny < 1) ny = N;
//			}
			// 다 돌았으면 이동한 위치 물 +1, 이동한 구름 위치 표시 (4,5에서 사용)
			map[nx][ny]++;
			cloud[nx][ny] = true;
			movedPos.offer(new int[] { nx, ny });
		}
	}

	private static void waterCopy() {
		while(!movedPos.isEmpty()) {
			int[] cur = movedPos.poll();
			for (int i = 1; i < 8; i += 2) {
				int nx = cur[0] + di[i];
				int ny = cur[1] + dj[i];
				
				// 대각선 위치 바구니 범위 벗어나지 않고, 물이 들어있으면
				if (nx >= 1 && nx <= N && ny >= 1 && ny <= N && map[nx][ny] > 0)
					map[cur[0]][cur[1]]++;
			}
		}
	}

	private static void setCloud() {
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				// 물 양이 2 이상이고, 3에서 구름이 사라졌던 위치가 아니라면 구름 생성
				if (map[i][j] >= 2 && !cloud[i][j]) {
					clouds.offer(new int[] { i, j });
					map[i][j] -= 2;
				}
			}
		}
	}

	private static int getSum() {
		int sum = 0;
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				sum += map[i][j];
			}
		}
		return sum;
	}
}

// N*N
// 상하좌우 범위 벗어나면 반대편으로 나옴
// 시작 -> 구름 생성 (N, 1), (N, 2), (N-1, 1), (N-1, 2)
// 구름에 이동 명령.. 방향과 거리로 주어짐 (8방향)

// 1.모든 구름이 d방향으로 s칸 이동 (범위 벗어나면 반대편 나오는거 주의)
// 2.각 구름에서 비가 내려 구름이 있는 칸의 물 +1
// 3.구름 모두 사라짐
// 4.2에서 물이 증가한 칸에 물복사 시전 -> 대각선으로 거리가 1인 칸에 물이 있는 "바구니의 수"만큼 현재위치 물 증가
//  - 이때는 이동할 때랑 다르게 범위 벗어난 칸은 대각선으로 연결된 칸으로 간주 안함
// 5.물 양이 2 이상인 모든 칸에 구름 생성, 물 양 2 감소.. 이때, 구름이 생기는 칸은 3에서 구름이 사라진 칸이 아님

// M번 이동 후 바구니에 들어있는 물의 양 구하자