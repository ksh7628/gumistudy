package a0826;

import java.io.*;
import java.util.*;

public interface Main_bj_8972_미친아두이노 {
	static int[] dx = {0, 1, 1, 1, 0, 0, 0,-1,-1,-1};
	static int[] dy = {0,-1, 0, 1,-1, 0, 1,-1, 0, 1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		char[][] arr = new char[r][c];
		
		int x = 0, y = 0;
		List<int[]> li = new ArrayList<>();
		for(int i = 0; i < r; i++) {
			arr[i] = br.readLine().toCharArray();
			for(int j = 0; j < c; j++) {
				if(arr[i][j] == 'I') {
					arr[i][j] = '.';
					x = i; y = j;
				}
				else if(arr[i][j] == 'R') {
					li.add(new int[] {i, j});
				}
			}
		}
		String moving = br.readLine();
		for(int i = 1; i <= moving.length(); i++) {
			int dir = moving.charAt(i-1)-'0';
			//1. 종수 이동
			x += dx[dir]; y += dy[dir];
			//2. 종수 위치에 미친 아두이노가 있으면 게임 종료
			if(arr[x][y] == 'R') {
				System.out.println("kraj "+i);
				return;
			}
			//3. 미친 아두이노들 이동
			int[][] visit = new int[r][c];
			for(int j = 0; j < li.size(); j++) {
				int far = Integer.MAX_VALUE;
				int[] loc = li.get(j);
				arr[loc[0]][loc[1]] = '.';
				int ni = 0, nj = 0;
				for(int k = 1; k < 10; k++) {
					if(k == 5) continue;
					int nx = loc[0]+dx[k];
					int ny = loc[1]+dy[k];
					if(far > Math.abs(nx-x)+Math.abs(ny-y)) {
						far = Math.abs(nx-x)+Math.abs(ny-y);
						//4. 미친 아두이노가 종수에게 온 경우
						if(far == 0) {
							System.out.println("kraj "+i);
							return;
						}
						ni = nx; nj = ny;
					}
				}
				visit[ni][nj]++;
			}
			li = new ArrayList<>();
			//5. 이동한 미친 아두이노끼리 겹치는 경우
			for(int m = 0; m < r; m++) {
				for(int n = 0; n < c; n++) {
					if(visit[m][n] == 1) {
						li.add(new int[] {m, n});
						arr[m][n] = 'R';
					}
				}
			}
		}
		arr[x][y] = 'I';
		for(int m = 0; m < r; m++) {
			for(int n = 0; n < c; n++) {
				System.out.print(arr[m][n]);
			}
			System.out.println();
		}
	}
}
