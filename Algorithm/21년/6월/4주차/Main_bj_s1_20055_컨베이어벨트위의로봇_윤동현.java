import java.io.*;
import java.util.*;

/*
로봇을 올리는 위치에 올리거나 로봇이 어떤 칸으로 이동하면 그 칸의 내구도는 즉시 1만큼 감소한다.

1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다. 내리는 위치에 있는 로봇은 내린다.
2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다. 만약 이동할 수 없다면 가만히 있는다.
    2-1. 로봇이 이동하기 위해서는 로봇이 내리는 위치가 아니고, 이동하려는 칸에 로봇이 없으며, 그 칸의 내구도가 1 이상 남아 있어야 한다.
3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
4. 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다. 그렇지 않다면 1번으로 돌아간다.
*/

public class Main {

    static boolean[] robot;
    static int[] converyor;
    static int N, K; // 갯수, 내구도 k개 이상

    // shift right 1
    static void rotationBelt() {
        int tmp = converyor[2 * N - 1];
        for (int i = 2 * N - 1; i >= 1; i--) {
            converyor[i] = converyor[i - 1];
        }
        converyor[0] = tmp;

        for (int i = N - 2; i >= 1; i--) {
            robot[i] = robot[i - 1];
        }
        robot[N - 1] = false;
        robot[0] = false;
    }

    static void moveRobot() {
        for (int i = N - 2; i >= 0; i--) {
            if (robot[i] && !robot[i + 1] && converyor[i + 1] > 0) {
                robot[i + 1] = true;
                robot[i] = false;
                converyor[i + 1]--;
                if (converyor[i + 1] == 0)
                    K--;
            }
        }
    }

    static void putRobot() {
        if (converyor[0] > 0) {
            robot[0] = true;
            converyor[0]--;
            if (converyor[0] == 0)
                K--;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        converyor = new int[2 * N];
        robot = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0, size = 2 * N; i < size; i++) {
            converyor[i] = Integer.parseInt(st.nextToken());
        }
        int step = 0;
        while (K > 0) {
            step++;
            rotationBelt();
            moveRobot();
            putRobot();
            // System.out.println();
            // for (int v : converyor)
            // System.out.print(v + " ");
            // System.out.println();
            // for (boolean v : robot)
            // System.out.print(v + " ");
            // System.out.println();
            // System.out.println();
            // System.out.println("------------------");
        }

        System.out.println(step);
        br.close();
    }
}