package jun;

import java.io.*;
import java.util.*;

/**
 * @author	: KimSeonhong
 * @date	: 2021. 6. 25.
 *
 * 분류: 구현, 시뮬레이션
 * 난이도: 실버1
 * 혼자 품: O
 * 풀이: 문제에서 요구하는 1~4의 과정을 진행하면서 로봇의 좌표를 저장하는 큐와 인덱스에 따라 로봇의 존재를 알려주는 boolean형 배열로 구현
 * 느낀 점: 
 * 처음에는 컨베이어 벨트와 로봇을 전부 2차원 배열로 구현하여 풀었는데 1차원 배열로도 충분히 풀리는 문제였다.
 * 분명 예전보단 어느 정도 최적화가 되었는데 그래도 다른 사람들에 비하면 성능이 좋은 편은 아니어서 나중에 다른 코드를 참조해서 다시 풀어봐야겠다.
 */
public class Main_s1_20055_컨베이어벨트위의로봇_김선홍 {
	static ArrayDeque<Integer> robot = new ArrayDeque<>();// 로봇의 좌표를 저장하는 큐
	static int[] belt;
	static boolean[] isRobot;// 해당 인덱스에 로봇 존재를 알려주는 배열
	static int N, K, cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		belt = new int[N * 2];
		isRobot = new boolean[N];

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N * 2; i++) {
			belt[i] = Integer.parseInt(st.nextToken());
		}

		System.out.println(process());
		br.close();
	}

	/* 1~4번 과정을 반복해서 단계 수 반환 */
	private static int process() {
		int res = 0;
		while (true) {
			res++;
			rotate();// 1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다. 내리는 위치에 있는 로봇은 내린다.
			moveCheck();// 2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다. 만약 이동할 수 없다면 가만히 있는다.
						//    로봇이 이동하기 위해서는 로봇이 내리는 위치가 아니고, 이동하려는 칸에 로봇이 없으며, 그 칸의 내구도가 1 이상 남아 있어야 한다.
			setRobot();// 3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
			if (check()) {// 4. 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다. 그렇지 않다면 1번으로 돌아간다.
				break;
			}
		}
		return res;
	}

	/* 컨베이어 벨트와 로봇을 한 칸씩 밀어줌 */
	private static void rotate() {
		int tmp = belt[2 * N - 1];
		for (int i = 2 * N - 1; i > 0; i--) {
			belt[i] = belt[i - 1];
		}
		belt[0] = tmp;

		for (int i = N - 1; i > 0; i--) {
			isRobot[i] = isRobot[i - 1];
		}
		isRobot[0] = false;// 이동이 완료되었으므로 올리는 위치에는 로봇이 존재하지 않음
		isRobot[N - 1] = false;// 내리는 위치에 있는 로봇을 내려줌

		int size = robot.size();
		for (int i = 0; i < size; i++) {// 내리는 위치를 제외한 로봇을 한 칸씩 이동시킨 인덱스를 큐에 넣어줌
			int idx = robot.poll();
			if (idx == N - 1) {
				continue;
			}

			robot.offer(idx + 1);
		}
	}

	/* 이동할 수 있는 로봇은 이동 */
	private static void moveCheck() {
		int size = robot.size();
		for (int i = 0; i < size; i++) {
			int idx = robot.poll();
			// 내리는 위치에 있거나 이동할려는 위치에 로봇이 있거나 그 위치의 벨트 내구도가 1미만이면 현재 로봇 위치만 큐에 넣고 continue
			if ((idx == N - 1) || isRobot[idx + 1] || belt[idx + 1] < 1) {
				robot.offer(idx);
				continue;
			}
			belt[idx + 1]--;
			isRobot[idx + 1] = true;
			isRobot[idx] = false;
			robot.offer(idx + 1);
		}
	}

	/* 로봇을 올리는 위치에 올림 */
	private static void setRobot() {
		if (belt[0] != 0) {// 올리는 위치의 내구도가 0이 아니면 로봇을 올림
			belt[0]--;
			isRobot[0] = true;
			robot.offer(0);
		}
	}

	/* 내구도가 0인 칸의 개수가 K개 이상인지 검사 */
	private static boolean check() {
		int cnt = 0;
		for (int i = 0; i < 2 * N; i++) {
			if (belt[i] == 0) {
				if (++cnt >= K) {// K개 이상이면 true
					return true;
				}
			}
		}
		return false;// 아니라면 false
	}
}
