import java.io.*;
import java.util.*;

public class Main {

	static final int MAX = 501; // (0,0) -> (500,500) 이므로 501이 필요
	static int[][] map = new int[MAX][MAX];
	static int[][] d = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
	static int N, M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = stoi(br.readLine()); // 위험한 구역의 수
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = stoi(st.nextToken());
			int y1 = stoi(st.nextToken());
			int x2 = stoi(st.nextToken());
			int y2 = stoi(st.nextToken());
			pushInfo(y1, x1, y2, x2, 1);
		}
		// x,y이므로 배열을 반대로 생각해야한다!!!
		M = stoi(br.readLine()); // 죽음의 구역의 수
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = stoi(st.nextToken());
			int y1 = stoi(st.nextToken());
			int x2 = stoi(st.nextToken());
			int y2 = stoi(st.nextToken());
			pushInfo(y1, x1, y2, x2, -1);
		}

		System.out.println(bfs(0, 0));

		br.close();
	}

	static int stoi(String s) {
		return Integer.parseInt(s);
	}

	static void pushInfo(int x1, int y1, int x2, int y2, int v) {
		for (int i = Math.min(x1, x2); i <= Math.max(x1, x2); i++) {
			for (int j = Math.min(y1, y2); j <= Math.max(y1, y2); j++) {
				if (map[i][j] == v)
					continue;
				map[i][j] = v;
			}
		}
	}

	// 범위체크
	static boolean rangeCheck(int x, int y) {
		return (x < 0 || x >= MAX || y < 0 || y >= MAX) ? true : false;
	}

	static int bfs(int x, int y) {
		// pq를 통해 항상 라이프를 최소로 잃은 node가 실행되므로 해당노드가 500,500에 도착한다면
		// 항상 최소의 라이프를 희생해서 온다는것을 보장한다.
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[2], o2[2]);
			}
		});
		boolean[][] visited = new boolean[MAX][MAX];

		pq.offer(new int[] { x, y, 0 });
		visited[x][y] = true;

		while (!pq.isEmpty()) {
			int[] cur = pq.poll();

			if (cur[0] == MAX - 1 && cur[1] == MAX - 1) {
				return cur[2];
			}

			for (int dir = 0; dir < 4; dir++) {
				int nx = cur[0] + d[dir][0];
				int ny = cur[1] + d[dir][1];
				int nl = cur[2];

				// 범위를 벗어나거나, 죽은구역이거나 방문했다면 방문하지 않아도 된다.
				if (rangeCheck(nx, ny) || map[nx][ny] == -1 || visited[nx][ny])
					continue;

				// 만약 위험한 구역이라면 목숨 하나를 잃고 갈수 있으므로, 아닌경우는 안전한구역이기에 nl값에 변화가 없다.
				if (map[nx][ny] == 1)
					nl++;
				visited[nx][ny] = true;
				pq.offer(new int[] { nx, ny, nl });
			}
		}

		return -1;
	}
}