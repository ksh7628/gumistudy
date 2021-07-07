import java.io.*;
import java.util.*;

public class Main {

    static char[] ch;
    static int idx;

    static boolean slumpCheck() {
        if (idx >= ch.length)
            return false;

        if (ch[idx] == 'D' || ch[idx] == 'E') {

            boolean isCheck = false;
            while (ch[++idx] == 'F') {
                isCheck = true;
                if (idx == ch.length - 1)
                    break;
            }

            if (ch[idx] == 'G' && isCheck) {
                idx++;
                return true;
            } else if (slumpCheck() && isCheck) {
                idx++;
                return true;
            }
        }
        return false;
    }

    static boolean slimpCheck() {
        if (ch[idx] == 'A') {
            idx++;
            if (idx >= ch.length)
                return false;

            if (ch[idx] == 'H') {
                idx++;
                return true;
            } else if (ch[idx] == 'B') {
                idx++;
                if (idx >= ch.length)
                    return false;
                if (slimpCheck()) {
                    if (idx >= ch.length)
                        return false;
                    if (ch[idx] == 'C') {
                        idx++;
                        return true;
                    }
                }
            } else if (ch[idx] == 'D' || ch[idx] == 'E') {
                if (slumpCheck()) {
                    if (ch[idx] == 'C') {
                        idx++;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        sb.append("SLURPYS OUTPUT").append("\n");

        int N = Integer.parseInt(br.readLine());
        while (N-- > 0) {
            ch = br.readLine().toCharArray();
            idx = 0;
            boolean slimp = slimpCheck();
            boolean slump = slumpCheck();

            if (ch[0] != 'A' || ch[ch.length - 1] != 'G' || ch.length < 4) {
                sb.append("NO").append("\n");
                continue;
            }

            if (slimp && slump && idx >= ch.length) {
                sb.append("YES").append("\n");
            } else {
                sb.append("NO").append("\n");
            }
        }

        sb.append("END OF OUTPUT").append("\n");
        System.out.println(sb.toString());
        br.close();
    }

}