import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = "";
        int tempHH = 0, tempMM = 0, t = 0, res = 0;

        String[] s = br.readLine().split(" ");

        String[] temp = s[0].split(":");
        tempHH = Integer.parseInt(temp[0]);
        tempMM = Integer.parseInt(temp[1]);
        int start = tempHH * 100 + tempMM;

        temp = s[1].split(":");
        tempHH = Integer.parseInt(temp[0]);
        tempMM = Integer.parseInt(temp[1]);
        int end = tempHH * 100 + tempMM;

        temp = s[2].split(":");
        tempHH = Integer.parseInt(temp[0]);
        tempMM = Integer.parseInt(temp[1]);
        int terminate = tempHH * 100 + tempMM;

        HashMap<String, Boolean> map = new HashMap<>();

        while ((input = br.readLine()) != null) { //
            s = input.split(" ");
            temp = s[0].split(":");
            tempHH = Integer.parseInt(temp[0]);
            tempMM = Integer.parseInt(temp[1]);
            t = tempHH * 100 + tempMM;

            if (t <= start) {
                map.put(s[1], false);
            }

            if (t >= end && t <= terminate) {
                if (map.containsKey(s[1]) && !map.get(s[1])) {
                    map.put(s[1], true);
                    res++;
                }
            }
        }

        System.out.println(res);

        br.close();
    }
}