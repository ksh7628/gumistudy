import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int[][] due = new int[3][2];
		for(int i = 0; i < 3; i++) {
			String[] tmp = st.nextToken().split(":");
			due[i][0] = Integer.parseInt(tmp[0]);
			due[i][1] = Integer.parseInt(tmp[1]);
		}
		Set<String> ppl = new HashSet<>();
		int cnt = 0; String str;
		while((str=br.readLine()) != null) {
			st = new StringTokenizer(str, " ");
			
			String[] time = st.nextToken().split(":");
			String name = st.nextToken();

			int curh = Integer.parseInt(time[0]);
			int curm = Integer.parseInt(time[1]);
			
			if(curh<due[0][0] || (curh==due[0][0]&&curm<=due[0][1])) {
				ppl.add(name);
			}
			else if(curh>due[1][0] || (curh==due[1][0]&&curm>=due[1][1])) {
				if(curh<due[2][0] || (curh==due[2][0]&&curm<=due[2][1])) {
					if(ppl.contains(name)) {
						cnt++;
						ppl.remove(name);
					}
				}
				else break;
			}
		}
		System.out.println(cnt);
	}
}
