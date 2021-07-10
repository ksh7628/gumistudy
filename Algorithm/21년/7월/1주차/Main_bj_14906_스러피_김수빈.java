package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_bj_14906_스러피_김수빈 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 정규식으로 풀 수도 있겠다 -> 문자열이 짧을 때!
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		sb.append("SLURPYS OUTPUT").append("\n");
		for(int i = 0; i < num; i++) {
			String str = br.readLine();		// 스러피인지 확인할 문자열
			str = str.toUpperCase();
			if(isSlurpy(str))
				sb.append("YES").append("\n");
			else
				sb.append("NO").append("\n");
		}
		// sb.setLength(sb.length() - 1);
		sb.append("END OF OUTPUT").append("\n");
		System.out.println(sb.toString());
	}

	private static boolean isSlurpy(String str) {
		for(int i = 2; i < str.length() - 2; i++) {
			if(isSlimp(str, i) && isSlump(str.substring(i), str.length() - i)) {
				return true;
			}
		}
		return false;
	}
	
	private static boolean isSlimp(String str, int len) {
		if(str.charAt(0) == 'A') {	// 첫 번째 문자는 'A'
			if(len == 2 && str.charAt(1) == 'H')	// 두개의 문자로만 된 스림프라면 두번째 문자는 'H'
				return true;
			
			else if(len >= 3) {
				// 1. A + B + 스림프 + C
				if(str.charAt(1) == 'B') {
					if(isSlimp(str.substring(2), len - 3) && str.charAt(len - 1) == 'C')
						return true;
				}
				// 2. A + 스럼프 + C
				if(isSlump(str.substring(1), len - 2) && str.charAt(len - 1) == 'C')
					return true;
			}
		}
		return false;
	}

	private static boolean isSlump(String str, int len) {
		if((str.charAt(0) == 'D' || str.charAt(0) == 'E') && str.charAt(1) == 'F' && str.charAt(len - 1) == 'G') {
			// 하나 이상의 F가 반복되어 연달아 + 또 다른 스럼프나 G가 온다
			for(int i = 1; i < len - 1; i++) {
				if(str.charAt(i) != 'F') {
					if(!isSlump(str.substring(i), len - i))
						return false;
				}
			}
			return true;
		}
		return false;
	}
}
