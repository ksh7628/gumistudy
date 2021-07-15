import java.io.*;
import java.util.*;
public class Main {
	static int[][] arr;
	static boolean[][] visit;
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		int rain = 0;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				rain = Integer.max(rain, arr[i][j]);
			}
		}
		int max = Integer.MIN_VALUE;
		
    //모든 영역이 물에 잠기지 않을 수도 있으므로 0부터 최대 물높이까지로 연산
		for(int s = 0; s <= rain; s++) {
			visit = new boolean[n][n];
			//높이 이하 체크하기
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(arr[i][j] <= s) {
						visit[i][j] = true;
					}
				}
			}
			//안전영역에서 퍼져나가기
			int sum = 0;
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(!visit[i][j]) {
						bfs(i, j);
						sum++;
					}
				}
			}
			max = Integer.max(max, sum);
		}
		System.out.println(max);
	}
	private static void bfs(int x, int y) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {x, y});
		visit[x][y] = true;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			for(int d = 0; d < 4; d++) {
				int nx = cur[0]+dx[d];
				int ny = cur[1]+dy[d];
				if(0>nx||nx>=visit.length || 0>ny||ny>=visit.length) continue;
				if(!visit[nx][ny]) {
					q.offer(new int[] {nx, ny});
					visit[nx][ny] = true;
				}
			}
		}
	}
}
