import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main_g5_20437_문자열게임2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder res = new StringBuilder();
        ArrayDeque<Integer>[] alphaIdx = new ArrayDeque[26];
        char[] W;
        int alpha[], K, min, max;

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            W = br.readLine().toCharArray(); // 문자열 입력
            K = Integer.parseInt(br.readLine()); // 양의 정수 K

            alpha = new int[26];
            for (int i = 0; i < 26; i++)
                alphaIdx[i] = new ArrayDeque<>();

            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;
            int tmp = 0;
            int val = 0;
            int first = 0;
            for (int i = 0; i < W.length; i++) {
                tmp = W[i] - 'a';

                alphaIdx[tmp].offer(i);
                alpha[tmp]++;
                while (alpha[tmp] == K) {
                    first = alphaIdx[tmp].poll();

                    val = i;
                    val -= first;
                    val += 1;
                    if (val < min)
                        min = val;
                    if (val > max)
                        max = val;

                    alpha[tmp]--;
                }
            }

            if (min == Integer.MAX_VALUE || max == Integer.MIN_VALUE) {
                res.append(-1).append("\n");
                continue;
            }
            res.append(min).append(" ").append(max).append("\n");
        }

        System.out.println(res.toString());
        br.close();
    }
}