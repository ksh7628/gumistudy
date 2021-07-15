import java.io.*;
import java.util.*;
public class Main {
	static int n;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); //n개의 섬
		int m = Integer.parseInt(st.nextToken()); //다리의 수
		List<int[]>li[] = new ArrayList[n];
		for(int i = 0; i < n; i++) li[i] = new ArrayList<>();
		
		int right = 0, left = 0;
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken());
			li[a].add(new int[] {b, c});
			li[b].add(new int[] {a, c});
			left = Integer.max(left, c);
		}
		
		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken())-1;
		int y = Integer.parseInt(st.nextToken())-1;
		
		while(right <= left) {
			int mid = (right+left)/2;
			if(bfs(li, x, y, mid)) right = mid+1;
			else left = mid-1;
		}
		
		System.out.println(left);
	}

	private static boolean bfs(List<int[]>[] li, int x, int y, int mid) {
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visit = new boolean[n];
		q.offer(x);
		visit[x] = true;
		while(!q.isEmpty()) {
			int cur = q.poll();
			if(cur == y) return true;
			for(int[] i: li[cur]) {
				//i[1]은 다리중량, mid는 내가 지고 있는 짐...
				if(visit[i[0]] || i[1] < mid) continue;
				q.offer(i[0]);
				visit[i[0]] = true;
			}
		}
		return false;
	}
}