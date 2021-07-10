import java.io.*;

public class Main_bj_20437_문자열게임2 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			char[] w = br.readLine().toCharArray();
			int k = Integer.parseInt(br.readLine());
			
			int[] cnt = new int[26];
			for(int i = 0; i < w.length; i++) {
				cnt[w[i]-'a']++;
			}
			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;
			for(int i = 0; i < w.length; i++) {
				if(cnt[w[i]-'a'] < k) continue;
				int cur = 0;
				for(int j = i; j < w.length; j++) {
					if(w[j]==w[i]) cur++;
					if(cur == k) {
						min = Integer.min(min, j-i+1);
						max = Integer.max(max, j-i+1);
						break;
					}
				}
			}
			System.out.println(min!=Integer.MAX_VALUE?min+" "+max:-1);
		}
			
	}
}
