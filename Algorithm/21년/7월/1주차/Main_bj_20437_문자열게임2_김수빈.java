package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main_bj_20437_문자열게임2_김수빈 {
	public static List<Integer>[] list;
	static int answer_3, answer_4, k;
	public static String str;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();;

		long beforeTime = System.currentTimeMillis();
		for (int tc = 0; tc < T; tc++) {
			list = new ArrayList[26]; // 각 알파벳마다 위치하는 인덱스 저장
			str = br.readLine();
			k = Integer.parseInt(br.readLine());

			for (int i = 0; i < 26; i++) {
				list[i] = new ArrayList<>();
			}

			for (int i = 0; i < str.length(); i++) {
				int idx = str.charAt(i) - 'a';
				list[idx].add(i);
			}

			boolean flag = false;
			answer_3 = Integer.MAX_VALUE;
			answer_4 = Integer.MIN_VALUE;

			for (int i = 0; i < 26; i++) {
				if (list[i].size() >= k) { // 그 알파벳 개수가 k개 이상일때만 확인하자
					flag = true;
					findLength(i);
				}
			}

			if (!flag) {
				sb.append("-1").append("\n");
				continue;
			}
			else {
				sb.append(answer_3).append(" ").append(answer_4).append("\n");
			}
		}
		System.out.println(sb.toString());
	}

	private static void findLength(int i) {
		List<Integer> alpha = list[i];
		int s = 0;
		int end = k - 1;
		
		while(end < alpha.size()) {
			answer_3 = Math.min(answer_3, list[i].get(end) - list[i].get(s) + 1);
			answer_4 = Math.max(answer_4, list[i].get(end) - list[i].get(s) + 1);
			s++;
			end++;
		}
	}
}
