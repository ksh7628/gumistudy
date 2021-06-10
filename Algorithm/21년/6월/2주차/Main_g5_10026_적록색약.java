package baekjoon;

import java.io.*;
import java.util.Arrays;

public class Main_bj_10026_적록색약 {
	static int N;
	static char[][] picture;
	static boolean[][] no_visit;
	static boolean[][] yes_visit;
	static int[] di = {-1, 0, 1, 0};
	static int[] dj = {0, 1, 0, -1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		picture = new char[N][N];
		no_visit = new boolean[N][N];
		yes_visit = new boolean[N][N];
		
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int j = 0; j < N; j++) {
				picture[i][j] = str.charAt(j);
			}
		}
		
		// 적록색약이 아닌 사람과 적록색약인 사람의 구역의 수
		int no_RG = 0;
		int yes_RG = 0;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!no_visit[i][j]) {
					no_RG++;
					no_RG_search(i, j);
				}
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(picture[i][j] == 'G')
					picture[i][j] = 'R';
			}
		}
		
		for(int i = 0; i < N; i++)
			Arrays.fill(no_visit[i], false);
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!no_visit[i][j]) {
					yes_RG++;
					no_RG_search(i, j);
				}
			}
		}
		
		System.out.println(no_RG + " " + yes_RG);
	}
	
	private static void no_RG_search(int i, int j) {
		no_visit[i][j] = true;
		for(int d = 0; d < 4; d++) {
			int nx = i + di[d];
			int ny = j + dj[d];
			if(0 <= nx && nx < N && 0 <= ny && ny < N) {
				if(!no_visit[nx][ny] && picture[nx][ny] == picture[i][j]) {
					no_RG_search(nx, ny);
				}
			}
		}
	}
	
//	private static void yes_RG_search(int i, int j) {
//		yes_visit[i][j] = true;
//		
//		for(int d = 0; d < 4; d++) {
//			int nx = i + di[d];
//			int ny = j + dj[d];
//			if(0 <= nx && nx < N && 0 <= ny && ny < N) {
//				if(!yes_visit[nx][ny]) {
//					if(picture[nx][ny] == picture[i][j])
//						yes_RG_search(nx, ny);
//					else {
//						if((picture[nx][ny] == 'R' && picture[i][j] == 'G') || (picture[nx][ny] == 'G' && picture[i][j] == 'R'))
//							yes_RG_search(nx, ny);
//					}
//				}
//			}
//		}
//	}
}
