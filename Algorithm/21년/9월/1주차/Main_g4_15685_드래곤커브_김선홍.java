package sep;

import java.io.*;
import java.util.*;

/**
 * @author	: KimSeonhong
 * @date	: 2021. 9. 7.
 *
 * 분류: 구현, 시뮬레이션
 * 난이도: 골드4
 * 소요 시간: 0h 54m
 * 혼자 품: O
 * 풀이: 0세대를 미리 그린 후 g세대까지의 드래곤 커브를 재귀로 그려나가면서 배열에 저장하는 방법으로 풀었다.
 * 느낀 점: 예전에 한번 힘들게 푼 문제여서 그런지 이번에는 빠르게 풀게 되어 실력이 향상됨을 느꼈다.
 */
public class Main_g4_15685_드래곤커브_김선홍 {
	static class Curve {
		int x, y, d;

		public Curve(int x, int y, int d) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}

	static ArrayList<Integer> dir;
	static boolean[][] map = new boolean[101][101];
	static int[] dx = { 0, -1, 0, 1 };// 우상좌하
	static int[] dy = { 1, 0, -1, 0 };
	static int x, y, g;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			y = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			g = Integer.parseInt(st.nextToken());
			
			process(d);
		}

		System.out.println(squareCount());
		br.close();
	}

	private static void process(int d) {
		map[x][y] = true;
		dir = new ArrayList<>();
		dir.add(d);
		zeroCurve();
		drawCurve(0);
	}

	// 0세대 드래곤 커브 생성
	private static void zeroCurve() {
		int d = dir.get(0);
		x += dx[d];
		y += dy[d];
		int nd = (d + 1) % 4;
		map[x][y] = true;
		dir.add(nd);
	}

	// g세대가 될 때까지 드래곤 커브 확장
	private static void drawCurve(int lv) {
		if (lv == g) {
			return;
		}

		int li = dir.size() - 1;
		for (int i = li; i > 0; i--) {
			int d = dir.get(i);
			x += dx[d];
			y += dy[d];
			int nd = (d + 1) % 4;

			map[x][y] = true;
			dir.add(nd);
		}

		drawCurve(lv + 1);
	}

	// 정사각형의 네 꼭짓점이 모두 드래곤 커브의 일부인 것의 개수를 셈
	private static int squareCount() {
		int res = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (map[i][j] && map[i][j + 1] && map[i + 1][j] && map[i + 1][j + 1]) {
					res++;
				}
			}
		}
		return res;
	}
}
