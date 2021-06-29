package bj.gold;

import java.util.*;
import java.io.*;

public class bj_12100_2048Easy {
	static int N, max;
	static int[][] original;
	static int[][][] copyMap;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		original = new int[N][N];
		max = 0;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				original[i][j] = Integer.parseInt(st.nextToken());
				max = Integer.max(max, original[i][j]);
			}
		}
		
		copyMap = new int[5][N][N];
		
		solution(0);
		System.out.println(max);
		
		br.close();
	}
	
	private static void solution(int idx) {
		// 종료
		if (idx == 5) {
			return;
		}
		
		// 모든 방향 해보기
		copyMap(idx);
		up(copyMap[idx]);
		solution(idx + 1);
		
		copyMap(idx);
		down(copyMap[idx]);
		solution(idx + 1);

		copyMap(idx);
		left(copyMap[idx]);
		solution(idx + 1);

		copyMap(idx);
		right(copyMap[idx]);
		solution(idx + 1);
	}
	
	private static void copyMap(int idx) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (idx > 0) copyMap[idx][i][j] = copyMap[idx - 1][i][j];
				else copyMap[idx][i][j] = original[i][j];
			}
		}
	}
	
	private static void up(int[][] map) {
		for (int i = 0; i < N; i++) {
			int next = 1;
			int target = 0;
			while(next < N) {
				
				// 비교 대상이 0이면 다음으로 넘어감
				if (map[next][i] == 0) {
					next++;
				}
				
				// 비교 대상이 target과 같을 때  (합쳐짐)
				else if (map[next][i] == map[target][i]) {
					map[target][i] = map[next][i] * 2;
					map[next][i] = 0;
					max = Integer.max(max, map[target][i]);
					target++;
					next++;
				}
				
				// 현재 위치 빈 칸인 경우 위로 올리기
				else if (map[target][i] == 0) {
					map[target][i] = map[next][i];
					map[next][i] = 0;
					next++;
				}
				
				// 비교 대상이 target과 다를 때 (아예 빈 칸도 없을 때)
				else {
					target++;
				}
				
				if (target == next) {
					next++;
				}
			}
		}
	}
	
	private static void down(int[][] map) {
		for (int i = 0; i < N; i++) {
			int next = N - 2;
			int target = N - 1;
			while(next >= 0) {
				
				// 비교 대상이 0이면 다음으로 넘어감
				if (map[next][i] == 0) {
					next--;
				}
				
				// 비교 대상이 cur과 같을 때  (합쳐짐)
				else if (map[next][i] == map[target][i]) {
					map[target][i] = map[next][i] * 2;
					map[next][i] = 0;
					max = Integer.max(max, map[target][i]);
					target--;
					next--;
				}
				
				// 현재 위치 빈 칸인 경우 위로 올리기
				else if (map[target][i] == 0) {
					map[target][i] = map[next][i];
					map[next][i] = 0;
					next--;
				}
				
				// 비교 대상이 cur과 다를 때 (아예 빈 칸도 없을 때)
				else {
					target--;
				}
				
				if (target == next) {
					next--;
				}
			}
		}
	}
	
	private static void right(int[][] map) {
		for (int i = 0; i < N; i++) {
			int next = N - 2;
			int target = N - 1;
			while(next >= 0) {
				
				// 비교 대상이 0이면 다음으로 넘어감
				if (map[i][next] == 0) {
					next--;
				}
				
				// 비교 대상이 cur과 같을 때  (합쳐짐)
				else if (map[i][next] == map[i][target]) {
					map[i][target] = map[i][next] * 2;
					map[i][next] = 0;
					max = Integer.max(max, map[i][target]);
					target--;
					next--;
				}
				
				// 현재 위치 빈 칸인 경우 위로 올리기
				else if (map[i][target] == 0) {
					map[i][target] = map[i][next];
					map[i][next] = 0;
					next--;
				}
				
				// 비교 대상이 cur과 다를 때 (아예 빈 칸도 없을 때)
				else {
					target--;
				}
				
				if (target == next) {
					next--;
				}
			}
		}
	}
	
	private static void left(int[][] map) {
		for (int i = 0; i < N; i++) {
			int next = 1;
			int target = 0;
			while(next < N) {
				
				// 비교 대상이 0이면 다음으로 넘어감
				if (map[i][next] == 0) {
					next++;
				}
				
				// 비교 대상이 cur과 같을 때  (합쳐짐)
				else if (map[i][next] == map[i][target]) {
					map[i][target] = map[i][next] * 2;
					map[i][next] = 0;
					max = Integer.max(max, map[i][target]);
					target++;
					next++;
				}
				
				// 현재 위치 빈 칸인 경우 위로 올리기
				else if (map[i][target] == 0) {
					map[i][target] = map[i][next];
					map[i][next] = 0;
					next++;
				}
				
				// 비교 대상이 cur과 다를 때 (아예 빈 칸도 없을 때)
				else {
					target++;
				}
				
				if (target == next) {
					next++;
				}
			}
		}
	}
}

// 2048 게임.. 5번 이동해서 만들 수 있는 가장 큰 블록의 값

// 상하좌우로 미는 메소드 먼저 작성

// 

// 모든 경우의 수?
// 백트래킹은 어떻게 할지..