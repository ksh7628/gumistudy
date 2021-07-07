package jul;

import java.io.*;
import java.util.*;

/**
 * @author	: KimSeonhong
 * @date	: 2021. 7. 6.
 *
 * 분류: 문자열, 슬라이딩 윈도우
 * 난이도: 골드5
 * 소요 시간: 0h 38m
 * 혼자 품: O
 * 풀이: 
 * 1. 각 알파벳의 위치를 저장하는 큐와 개수를 저장하는 배열을 선언해준다.
 * 2. 반복문을 수행하면서 각 알파벳의 위치를 큐에 넣고 개수를 갱신해준다.
 * 3. 현재 알파벳의 개수가 K랑 같다면 해당 알파벳을 인덱스로 가지는 큐 배열의 첫번째 위치에서 현재 위치까지의 길이를 최솟값과 비교하여 갱신해준다.
 * 4. 또한 현재 알파벳과 해당 알파벳을 인덱스로 가지는 큐 배열의 첫번째 위치에 있는 알파벳이 같다면 그 길이를 최댓값과 비교하여 갱신해준다.
 * 5. 다음 탐색을 위해 해당 문자를 인덱스로 가지는 큐 배열에서 값을 빼고 개수 또한 하나 뺀다.
 * 느낀 점: 
 * O(n)으로 해결하긴 했지만 이 방식이 슬라이딩 윈도우 방식인지는 잘 모르겠다.
 * 그리고 어떤 Collection을 배열로 선언했을 때 어떤 인덱스의 size() 메서드를 비교하는 방식이 생각보다 느려서 카운트용 배열을 따로 만들어줬는데
 * 이유를 잘 몰라서 이유를 한번 찾아보고 슬라이딩 윈도우 알고리즘도 한 번 찾아봐야겠다.
 */
public class Main_g5_20437_문자열게임2_김선홍 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		@SuppressWarnings("unchecked")
		ArrayDeque<Integer>[] q = new ArrayDeque[26];// a~z 까지 각 알파벳이 등장할 때 인덱스를 저장하는 큐 배열
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			String W = br.readLine();
			int K = Integer.parseInt(br.readLine());
			int max = 0, min = 10001;

			int[] cnt = new int[26];// a~z 까지 문자 개수를 저장하는 배열
			for (int i = 0; i < 26; i++) {
				q[i] = new ArrayDeque<>();
			}

			for (int i = 0; i < W.length(); i++) {
				char c = W.charAt(i);
				int idx = c - 97;// 해당 문자의 인덱스
				
				q[idx].offer(i);// 문자열에서 해당 문자의 위치를 큐에 넣어줌
				if (++cnt[idx] == K) {// 문자 개수를 증가시켰을 떄 K개가 된다면 최솟값 갱신
					min = Math.min(min, i - q[idx].peek() + 1);
					if (c == W.charAt(q[idx].peek())) {// 현재 문자가 처음으로 등장한 위치의 문자와 같다면 최댓값 갱신
						max = Math.max(max, i - q[idx].peek() + 1);
					}
					
					// 다음 탐색을 위해 해당 문자를 인덱스로 가지는 큐에서 인덱스를 빼고 개수 또한 하나 뺀다
					q[idx].poll();
					cnt[idx]--;
				}
			}

			if (max == 0 || min == 10001) {// 둘중 하나라도 못 찾았다면 -1
				sb.append(-1).append("\n");
			} else {
				sb.append(min).append(" ").append(max).append("\n");
			}
		}

		System.out.print(sb);
		br.close();
	}
}
