import java.io.*;
import java.util.*;

public class Main {
    // i번 파이어볼의 위치는 (ri, ci), 질량은 mi이고, 방향은 di, 속력은 si이다. 위치 (r, c)는 r행 c열을 의미한다.
    // 격자의 행과 열은 1번부터 N번까지 번호가 매겨져 있고, 1번 행은 N번과 연결되어 있고, 1번 열은 N번 열과 연결되어 있다.

    static Queue<int[]>[] fireball, temp;
    static int[][] dir = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 } };
    static int N, M, K, r, c, nr, nc, m, s, d, odd, even, sum, value[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        fireball = new Queue[N * N];
        temp = new Queue[N * N];
        for (int i = 0; i < N * N; i++) {
            fireball[i] = new ArrayDeque<>();
            temp[i] = new ArrayDeque<>();
        }

        int r, c, m, s, d, idx;
        r = c = m = s = d = idx = 0;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken()) - 1;
            c = Integer.parseInt(st.nextToken()) - 1;
            m = Integer.parseInt(st.nextToken()); // 질량
            s = Integer.parseInt(st.nextToken()); // 속력
            d = Integer.parseInt(st.nextToken()); // 방향
            idx = (r * N) + c;
            fireball[idx].add(new int[] { m, s, d });
        }

        while (K-- > 0) {
            move();
        }

        for (Queue<int[]> queue : fireball) {
            queue.forEach((value) -> {
                sum += value[0];
            });
        }

        System.out.println(sum);
        br.close();
    }

    static void move() {

        for (int i = 0; i < N * N; i++) {
            if (fireball[i].isEmpty())
                continue;

            r = i / N;
            c = i % N;
            while (!fireball[i].isEmpty()) {
                value = fireball[i].poll();
                nr = (r + (dir[value[2]][0] + N) * value[1]) % N;
                nc = (c + (dir[value[2]][1] + N) * value[1]) % N;
                temp[nr * N + nc].add(new int[] { value[0], value[1], value[2] });
            }
        }

        for (int i = 0; i < N * N; i++) {
            if (temp[i].size() < 2) {
                if (!temp[i].isEmpty())
                    fireball[i].add(temp[i].poll());
                continue;
            }

            odd = 0; // 홀수
            even = 0; // 짝수
            m = 0;
            s = 0;

            while (!temp[i].isEmpty()) {
                value = temp[i].poll();
                m += value[0];
                s += value[1];
                if (value[2] % 2 == 0)
                    even++;
                else
                    odd++;
            }

            m = m / 5;
            s = s / (odd + even);

            if (m > 0) {
                int j = (odd == 0 || even == 0) ? 0 : 1;

                for (; j < 8; j += 2) {
                    fireball[i].add(new int[] { m, s, j });
                }
            }

        }

    }
}