import java.io.*;
import java.util.*;

public class Main {

  static class Cal implements Comparable<Cal> {
    int n, cnt;

    Cal(int n, int cnt) {
      this.n = n;
      this.cnt = cnt;
    }

    public void setCnt() {
      this.cnt++;
    }

    @Override
    public int compareTo(Cal o) {
      if (this.cnt == o.cnt) {
        return Integer.compare(this.n, o.n);
      }
      return Integer.compare(this.cnt, o.cnt);
    }
  }

  static ArrayList<Cal>[] cal;
  static int[][] map;
  static int r, c, k, R, C, size;

  static boolean isExist(ArrayList<Cal> al, int n) {
    for (Cal cal : al) {
      if (cal.n == n) {
        cal.setCnt();
        return true;
      }
    }
    return false;
  }

  static void calRC() {
    if (R >= C) {
      cal = new ArrayList[R];
      for (int i = 0; i < R; i++)
        cal[i] = new ArrayList<>();

      size = 0;
      for (int i = 0; i < R; i++) {
        for (int j = 0; j < C; j++) {
          if (map[i][j] != 0 && !isExist(cal[i], map[i][j])) {
            cal[i].add(new Cal(map[i][j], 1));
          }
        }
        Collections.sort(cal[i]);
        size = Math.max(size, cal[i].size());
      }

      C = (size * 2 > 100) ? 100 : size * 2;
      map = new int[R][C];
      for (int i = 0; i < R; i++) {
        int tc = 0;
        for (int j = 0; j < cal[i].size(); j++) {
          map[i][tc++] = cal[i].get(j).n;
          if (tc >= 100)
            continue;
          map[i][tc++] = cal[i].get(j).cnt;
          if (tc >= 100)
            continue;
        }
      }
      return;
    }

    cal = new ArrayList[C];
    for (int i = 0; i < C; i++)
      cal[i] = new ArrayList<>();

    size = 0;
    for (int j = 0; j < C; j++) {
      for (int i = 0; i < R; i++) {
        if (map[i][j] != 0 && !isExist(cal[j], map[i][j])) {
          cal[j].add(new Cal(map[i][j], 1));
        }
      }
      Collections.sort(cal[j]);
      size = Math.max(size, cal[j].size());
    }

    R = (size * 2 > 100) ? 100 : size * 2;
    map = new int[R][C];
    for (int j = 0; j < C; j++) {
      int tr = 0;
      for (int i = 0; i < cal[j].size(); i++) {
        map[tr++][j] = cal[j].get(i).n;
        if (tr >= 100)
          continue;
        map[tr++][j] = cal[j].get(i).cnt;
        if (tr >= 100)
          continue;
      }
    }
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    r = Integer.parseInt(st.nextToken()) - 1;
    c = Integer.parseInt(st.nextToken()) - 1;
    k = Integer.parseInt(st.nextToken());

    R = C = 3;
    map = new int[R][C];
    for (int i = 0; i < R; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < C; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int res;
    for (res = 0; res <= 100; res++) {
      if (R <= r || C <= c || map[r][c] != k) {
        calRC();
        continue;
      }
      break;
    }
    System.out.println((res > 100) ? -1 : res);

    br.close();
  }
}