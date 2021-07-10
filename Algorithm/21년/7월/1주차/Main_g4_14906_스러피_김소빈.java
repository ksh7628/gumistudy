package a0701;

import java.io.*;

public class Main_g4_14906_스러피_김소빈 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		sb.append("SLURPYS OUTPUT\n");
		
		int n = Integer.parseInt(br.readLine());
		for(int i = 0; i < n; i++) {
			String str = br.readLine();
			int mid = str.length()-1;
			while(mid >= 0) {
				if(str.charAt(mid)!='C' && str.charAt(mid)!='H') mid--;
				else break;
			}
			
			if(mid >= 0 && isSlimp(str.substring(0, mid+1)) && isSlump(str.substring(mid+1)) ) {
				sb.append("YES\n");
			}
			else {
				sb.append("NO\n");
			}
		}
		
		sb.append("END OF OUTPUT\n");
		System.out.println(sb);
	}

	private static boolean isSlimp(String str) {
		int len = str.length();
		
		if(len < 2) return false;
		else if(len == 2) {
			if(str.equals("AH")) return true;
			else return false;
		}
		else {
			if(str.charAt(0)!='A' || str.charAt(len-1)!='C') return false;
			
			if(str.charAt(1) == 'B') return isSlimp(str.substring(2, len-1));
			else return isSlump(str.substring(1, len-1));
		}
	}

	private static boolean isSlump(String str) {
		int len = str.length();
		
		if(len < 3) return false;
		else if(str.charAt(0) != 'D' && str.charAt(0) != 'E') return false;
		else if(str.charAt(1) != 'F') return false;
		else if(str.charAt(len-1) != 'G') return false;
		
		int idx = 2;
		while(idx < len && str.charAt(idx) == 'F') idx++;
		
		if(idx == len) return false;
		else if(idx == len-1) return true;
		
		return isSlump(str.substring(idx));
	}

	
}
