import java.io.*;
import java.util.*;
public class Main_g4_17140_이차원배열과연산_김수빈 {
	static class Number implements Comparable<Number>{
		int num;
		int cnt;
		public Number(int num) {
			this.num = num;
		}
		public Number(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}
		@Override
		public int compareTo(Number o) {
			if(this.cnt == o.cnt)
				return Integer.compare(this.num, o.num);
			else
				return Integer.compare(this.cnt, o.cnt);
		}
	}
	static int[][] arr;
	static List<Number> list;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		arr = new int[3][3];	// 초기 3x3 배열
		for(int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int time = -1;
		while(true) {
			time++;
			if(time > 100) {		// 100초를 넘어가면 -1 출력
				time = -1;
				break;
			}
			if(r - 1 < arr.length && c - 1 < arr[0].length) {
				if(arr[r - 1][c - 1] == k) {	// 반복문 탈출!
					break;
				}
			}
			
			int rowLength = arr.length;
			int colLength = arr[0].length;
			if(rowLength >= colLength)
				calR();
			else
				calC();
		}
		System.out.println(time);
	}
	private static void calR() {	// 모든 행에 대해서 정렬
		int[][] tmp = new int[100][100];
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < arr.length; i++) {
			list = new ArrayList<>();
			int[] count = new int[101];
			for(int j = 0; j < arr[0].length; j++) {
//				if(arr[i][j] != 0 && list.contains(new Number(arr[i][j]))) {
//					int idx = list.indexOf(new Number(arr[i][j]));
//					list.set(idx, new Number(arr[i][j], list.get(idx).cnt + 1));
//				}
//				else if(arr[i][j] != 0)
//					list.add(new Number(arr[i][j], 1));
				if(arr[i][j] != 0)
					count[arr[i][j]]++;
			}
			
			for(int k = 1; k < count.length; k++) {
				if(count[k] != 0)
					list.add(new Number(k, count[k]));
			}
			Collections.sort(list);
			int idx = 0;
			for(int l = 0; l < list.size(); l++) {
				tmp[i][idx] = list.get(l).num;
				tmp[i][idx + 1] = list.get(l).cnt;
				idx += 2;
				max = Math.max(max, idx);
			}
		}
		
		// tmp의 값을 다시 arr에 넣어야함 -> arr의 사이즈 조절 필요
		if(max > 100)
			max = 100;
		
		arr = new int[arr.length][max];
		for(int i = 0; i < arr.length; i++) {
			for(int k = 0; k < arr[0].length; k++) {
				arr[i][k] = tmp[i][k];
			}
		}
	}
	
	private static void calC() {	// 모든 열에 대해서 정렬
		int[][] tmp = new int[100][100];
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < arr[0].length; i++) {
			list = new ArrayList<>();
			int[] count = new int[101];
			for(int j = 0; j < arr.length; j++) {
//				if(arr[i][j] != 0 && list.contains(new Number(arr[i][j]))) {
//					int idx = list.indexOf(new Number(arr[i][j]));
//					list.set(idx, new Number(arr[i][j], list.get(idx).cnt + 1));
//				}
//				else if(arr[i][j] != 0)
//					list.add(new Number(arr[i][j], 1));
				if(arr[j][i] != 0)
					count[arr[j][i]]++;
			}
			
			for(int k = 1; k < count.length; k++) {
				if(count[k] != 0)
					list.add(new Number(k, count[k]));
			}
			Collections.sort(list);
			int j = 0;
			for(int l = 0; l < list.size(); l++) {
				tmp[j][i] = list.get(l).num;
				tmp[j + 1][i] = list.get(l).cnt;
				j += 2;
				max = Math.max(max, j);
			}
		}
		
		// tmp의 값을 다시 arr에 넣어야함 -> arr의 사이즈 조절 필요
		if(max > 100)
			max = 100;
		
		arr = new int[max][arr[0].length];
		for(int i = 0; i < arr.length; i++) {
			for(int k = 0; k < arr[0].length; k++) {
				arr[i][k] = tmp[i][k];
			}
		}
	}

}
