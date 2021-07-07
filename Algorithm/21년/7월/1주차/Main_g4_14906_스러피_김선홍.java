package jul;

import java.io.*;
import java.util.regex.*;

/**
 * @author	: KimSeonhong
 * @date	: 2021. 7. 5.
 *
 * 분류: 문자열, 정규 표현식, 재귀
 * 난이도: 골드4
 * 소요 시간: 1h 19m
 * 혼자 품: O
 * 풀이: 
 * 문제에서 요구하는 스럼프와 스림프를 정규 표현식으로 정의한다. 이 때, 스림프는 A+B+스림프+C 또는 A+스럼프+C 2가지 경우가
 * 있으므로 2개의 정규 표현식으로 정의한 후 구하고자 하는 스러피는 스림프1+스럼프 또는 스림프2+스럼프 2가지 경우이므로
 * 입력받은 문자열이 둘 중 하나를 만족한다면 스러피라 할 수 있다.
 * 느낀 점: 
 * 일주일 전 정규 표현식 기본 문제인 Contact(https://www.acmicpc.net/problem/1013)를 풀어서 쉽게 풀 수 있을 줄 알았는데
 * 아직 덜 익숙해서 생각보다 시간이 오래 걸렸다. 재귀로도 잘 짜면 풀 수 있을 것 같다.
 */
public class Main_g4_14906_스러피_김선홍 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		String slump = "(D|E)F+((D|E)F+)*G";
		
		String slimp1 = "(AH|AB((AH|(ABAHC)+)|(A(D|E)F+(((D|E)F+)*)GC))+C)";// A+B+스림프+C
		String slimp2 = "(AH|A(D|E)F+((D|E)F+)*GC)";// A+스럼프+C
		
		String slurpy1 = slimp1 + slump;// 1번 조건을 만족하는 스림프 사용
		String slurpy2 = slimp2 + slump;// 2번 조건을 만족하는 스림프 사용
		
		Pattern p1 = Pattern.compile(slurpy1);
		Pattern p2 = Pattern.compile(slurpy2);
		
		sb.append("SLURPYS OUTPUT\n");
		for (int tc = 1; tc <= T; tc++) {
			String str = br.readLine();
			
			Matcher m1 = p1.matcher(str);
			Matcher m2 = p2.matcher(str);

			if (m1.matches() || m2.matches()) {
				sb.append("YES\n");
			} else {
				sb.append("NO\n");
			}
		}

		sb.append("END OF OUTPUT");
		System.out.println(sb);
		br.close();
	}
}