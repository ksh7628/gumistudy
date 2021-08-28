package jul;

import java.io.*;
import java.util.*;

/**
 * @author	: KimSeonhong
 * @date	: 2021. 7. 22.
 *
 * 분류: 구현, 정렬, 시뮬레이션
 * 난이도: 골드4
 * 소요 시간: 1h 1m
 * 혼자 품: O
 * 풀이: 
 * 1. 초기 배열 크기를 3x3으로 고정하여 입력값들을 각 변수와 배열에 저장한 후 100초가 지나기 전까지 R 또는 C 연산을 수행한다.
 * 2. R 연산 기준으로 Number 객체를 저장하는 ArrayList 배열을 행 크기만큼 선언해준다.
 * 3. 배열의 모든 인덱스를 순회하면서 행마다 수를 키로, 해당 수의 등장 횟수를 값으로 가지는 HashMap을 선언해준다.
 * 4. arr[i][j]가 0이라면 무시하고 다음 배열 값을 순회하면서 hm에 아직 등록되지 않은 수 라면 (arr[i][j], 1)로 저장해주고 등록된 수라면 해당 수의 값을 1 증가시켜 저장한다.
 * 5. 한 행의 순회가 끝났다면 hm의 모든 키를 탐색하면서 Number 객체를 al[i]에 저장한다.
 * 6. 또한 열의 최댓값을 구하기 위해 al[i] 크기의 두 배와 비교하여 최댓값을 갱신해준 후 al[i]를 정의한 정렬 조건에 따라 정렬해준다.
 * 7. 모든 배열 값 탐색이 끝나면 max값이 100을 넘어간다면 열 크기를 100으로, 그렇지 않다면 max로 저장한 후 arr의 크기를 R, C로 초기화 시켜준다.
 * 8. 2중 for문을 통해 행마다 해당 행을 인덱스로 가지는 al배열에서 수, 등장 횟수 순으로 arr에 저장한다.
 * 9. C 연산 또한 위 R 연산과 알고리즘은 같으나 탐색 순서를 열, 행 순으로 바꿔서 적용한다.
 * 느낀 점: 
 * 한 달 전에 머리를 싸매고 푼 문제라서 어렴풋이 기억이 난 덕에 저번보다 빨리 풀었는데, 성능이 조금 뒤떨어지게 되었다. 저번 같은 경우, HashMap을 사용하지 않고 ArrayList로만
 * 풀어서 차이가 조금 난 것 같다. 굳이 HashMap을 사용하지 않아도 되는 문제였기에 저번에 푼 알고리즘을 다시 보는 것이 좋을 것 같다.
 */
public class Main_g4_17140_이차원배열과연산_김선홍 {
	static class Number implements Comparable<Number> {
		int num, cnt;// 수, 등장 횟수

		public Number(int num, int cnt) {
			super();
			this.num = num;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Number o) {
			if (this.cnt == o.cnt) {// 수의 등장 횟수가 같다면 수의 오름차순으로
				return this.num - o.num;
			}
			return this.cnt - o.cnt;// 수의 등장 횟수의 오름차순으로
		}
	}

	static int[][] arr = new int[3][3];// 처음에는 무조건 3x3 배열
	static int r, c, k, R = 3, C = 3;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		r = Integer.parseInt(st.nextToken()) - 1;// 0 base
		c = Integer.parseInt(st.nextToken()) - 1;// 0 base
		k = Integer.parseInt(st.nextToken());

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < C; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(solution());
		br.close();
	}

	private static int solution() {
		int time = 0;
		while (time <= 100) {// 100초까지 실행
			if (r < R && c < C && arr[r][c] == k) {// 배열 범위를 벗어나지 않으면서 해당 인덱스에 k값이 있다면 걸린 시간 반환
				return time;
			}

			if (R >= C) {// 행 크기가 열 크기보다 크거나 같으면 R 연산 수행
				rowSort();
			} else {// 행 크기가 열 크기보다 작다면 C 연산 수행 
				colSort();
			}

			time++;
		}

		return -1;// 100초가 지나도 A[r][c] = k가 안된다면 -1 반환
	}

	@SuppressWarnings("unchecked")
	/* R 연산 수행 */
	private static void rowSort() {
		ArrayList<Number>[] al = new ArrayList[R];// 행 개수 만큼 ArrayList 배열 크기 지정
		int max = 0;// 연산 수행 후 늘어난 열 길이의 최댓값

		for (int i = 0; i < R; i++) {
			al[i] = new ArrayList<>();
			HashMap<Integer, Integer> hm = new HashMap<>();// 수와 수의 빈도를 저장하는 HashMap

			for (int j = 0; j < C; j++) {
				if (arr[i][j] == 0) {// 배열 값이 0이라면 continue -> 수를 정렬할 때 0은 무시
					continue;
				}

				if (!hm.containsKey(arr[i][j])) {// 아직 등록되지 않은 수 라면 (숫자, 1)로 저장
					hm.put(arr[i][j], 1);
				} else {// 이미 등록된 수 라면 (숫자, value + 1)로 저장
					hm.put(arr[i][j], hm.get(arr[i][j]) + 1);
				}
			}

			for (int key : hm.keySet()) {// HashMap을 순회하여 연산을 적용하기 위해 리스트에 넣어줌
				al[i].add(new Number(key, hm.get(key)));
			}

			max = Math.max(max, al[i].size() * 2);// Number 객체가 수와 등장 횟수로 이루어지기 때문에 리스트 길이의 두 배와 최댓값을 비교하여 갱신 
			Collections.sort(al[i]);// 정렬 수행
		}

		if (max > 100) {
			C = 100;
		} else {
			C = max;
		}

		arr = new int[R][C];// 바뀐 값 적용
		for (int i = 0; i < R; i++) {
			int j = 0;
			for (Number number : al[i]) {// 수, 등장 횟수 순으로 배열에 저장
				arr[i][j] = number.num;
				arr[i][j + 1] = number.cnt;
				j += 2;
			}
		}
	}

	@SuppressWarnings("unchecked")
	/* C 연산 수행 */
	private static void colSort() {
		ArrayList<Number>[] al = new ArrayList[C];
		int max = 0;

		for (int j = 0; j < C; j++) {
			al[j] = new ArrayList<>();
			HashMap<Integer, Integer> hm = new HashMap<>();

			for (int i = 0; i < R; i++) {
				if (arr[i][j] == 0) {
					continue;
				}

				if (!hm.containsKey(arr[i][j])) {
					hm.put(arr[i][j], 1);
				} else {
					hm.put(arr[i][j], hm.get(arr[i][j]) + 1);
				}
			}

			for (int key : hm.keySet()) {
				al[j].add(new Number(key, hm.get(key)));
			}

			max = Math.max(max, al[j].size() * 2);
			Collections.sort(al[j]);
		}

		if (max > 100) {
			R = 100;
		} else {
			R = max;
		}

		arr = new int[R][C];
		for (int j = 0; j < C; j++) {
			int i = 0;
			for (Number number : al[j]) {
				arr[i][j] = number.num;
				arr[i + 1][j] = number.cnt;
				i += 2;
			}
		}
	}
}