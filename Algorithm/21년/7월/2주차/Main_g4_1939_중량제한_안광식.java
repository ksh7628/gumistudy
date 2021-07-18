package bj.gold;

import java.io.*;
import java.util.*;

public class bj_1939_중량제한 {
	static int N, M, start, end, left, right;
	static ArrayList<int[]> matrix[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		left = right = 0;
		
		matrix = new ArrayList[N];
		for (int i = 0; i < N; i++)
			matrix[i] = new ArrayList<int[]>();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int A = Integer.parseInt(st.nextToken()) - 1;
			int B = Integer.parseInt(st.nextToken()) - 1;
			int C = Integer.parseInt(st.nextToken());
			matrix[A].add(new int[] { B, C });
			matrix[B].add(new int[] { A, C }); // 양방향
			right = Math.max(right, C);
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		start = Integer.parseInt(st.nextToken()) - 1;
		end = Integer.parseInt(st.nextToken()) - 1;
		
		System.out.println(Solution());
		
		br.close();
	}
	
	private static int Solution() {
		int mid = (left + right) / 2;
		while (left <= right) {
			if (bfs(mid)) 
				left = mid + 1;
			else 
				right = mid - 1;
			mid = (left + right) / 2;
		}
		
		return mid;
	}

	private static boolean bfs(int weight) {
		Queue<Integer> q = new LinkedList<Integer>();
		boolean visited[] = new boolean[N];
		
		visited[start] = true;
		q.offer(start);
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			if (cur == end)
				return true;
			
			for (int[] target : matrix[cur]) {
				if (visited[target[0]])
					continue;
				if (weight > target[1]) // 내가 가진 짐이 다리의 중량 제한보다 크다면 못감
					continue;
				
				visited[target[0]] = true;
				q.offer(target[0]);
			}
		}
		
		return false;
	}
}

// 섬 수 N (2 <= N <= 10,000)
// 다리 수 (1 <= M <= 100,000)
// (1 <= A), (B <= N), (1 <= C <= 1,000,000,000)
// A, B 섬 사이에 중량제한 C인 다리가 존재한다는 뜻
