package a0722;

import java.util.*;

public class Main_bj_17140_이차원배열과연산 {
	static class pair implements Comparable<pair>{
		int key, time;
		public pair(int key, int time) {
			this.key = key;
			this.time = time;
		}
		@Override
		public int compareTo(pair o) {
			int cmp = this.time-o.time;
			return cmp==0?this.key-o.key:cmp;
		}
	}
	static int row, col;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int r = sc.nextInt()-1;
		int c = sc.nextInt()-1;
		int k = sc.nextInt();
		int[][] arr = new int[100][100];
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		sc.close();
		row = 3; col = 3;
		int cnt = 0;
		while(true) {
			if(arr[r][c] == k || cnt > 100) break;
			if(row >= col) { //r연산
				for(int i = 0; i < row; i++) {
					int[] tmp = sorting(arr[i]);
					Arrays.fill(arr[i], 0);
					for(int j = 0; j < 100; j++) {
						if(tmp[j] == 0) {
							col = Math.max(col, j);
							break;
						}
						arr[i][j] = tmp[j]; 
					}
				}
			}else { //c연산
				for(int i = 0; i < col; i++) {
					int[] tmp = new int[101];
					int idx = 0;
					for(int j = 0; j < row; j++) {
						tmp[idx++] = arr[j][i]; 
						arr[j][i] = 0;
					}
					tmp = sorting(tmp);
					for(int j = 0; j < 100; j++) {
						if(tmp[j] == 0) {
							row = Math.max(row, j);
							break;
						}
						arr[j][i] = tmp[j];
					}
				}
			}
//			System.out.println(row+" "+col);
//			for(int i = 0; i < 6; i++) {
//				for(int j = 0; j < 6; j++) System.out.print(arr[i][j]+" ");
//				System.out.println();
//			}
			cnt++;
		}
		System.out.println(cnt>100?-1:cnt);
	}
	private static int[] sorting(int[] arr) {
		int[] tmp = new int[101];
		//1. 맵에다가 넣어서 카운팅해야지
		Map<Integer, Integer> mapper = new HashMap<>();
		for(int i = 0; i < col; i++) {
			int cur = arr[i];
			if(cur == 0) continue;
			arr[i] = 0;
			if(mapper.containsKey(cur)) mapper.put(cur, mapper.get(cur)+1);
			else mapper.put(cur, 1);
		}
		//2. 정렬해야하니까 리스트에 넣어야지
		List<pair> li = new ArrayList<>();
		for(int key: mapper.keySet()) {
			int time = mapper.get(key);
			li.add(new pair(key, time));
		}
		//3. 정렬
		Collections.sort(li);
		int idx = 0;
		for(pair cur: li) {
			tmp[idx++] = cur.key;
			tmp[idx++] = cur.time;
		}
		return tmp;
	}
	static void sorting(int[][]arr, boolean type) {
		int size = 0;
		for(int i = 0; i < row; i++) {
			Map<Integer, Integer> mapper = new HashMap<>();
			for(int j = 0; j < col; j++) {
				int cur = arr[i][j];
				if(cur == 0) continue;
				arr[i][j] = 0;
				if(mapper.containsKey(cur)) mapper.put(cur, mapper.get(cur)+1);
				else mapper.put(cur, 1);
			}
			size = Integer.max(size, mapper.size()*2);
			List<pair> li = new ArrayList<>();
			for(int key: mapper.keySet()) {
				int time = mapper.get(key);
				li.add(new pair(key, time));
			}
			Collections.sort(li);
			int idx = 0;
			for(pair cur: li) {
				arr[i][idx++] = cur.key;
				arr[i][idx++] = cur.time;
			}
		}
		if(type) col = size;
		else row = size;
	}
}
