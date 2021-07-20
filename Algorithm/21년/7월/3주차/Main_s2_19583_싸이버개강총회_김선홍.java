package jul;

import java.io.*;
import java.util.*;

/**
 * @author	: KimSeonhong
 * @date	: 2021. 7. 20.
 *
 * 분류: 구현, 자료 구조, 문자열, 해시를 사용한 집합과 맵
 * 난이도: 실버2
 * 소요 시간: 0h 49m
 * 혼자 품: O
 * 풀이: 
 * 입력 종료 조건을 따로 명시하지 않았으므로 EOF까지 다 검사하면서 이름을 nameSet에 저장하고 입장이 확인된 이름이면 btSet에 넣어주고 퇴장이 확인된 이름이면
 * resultSet에 이름을 저장한 후 while문이 종료되면 resultSet.size()를 통해 정답을 구하는 방식으로 풀었다.
 * 느낀 점: 
 * 구현 자체는 어렵지 않으나 EOF에 주의해야 되는 문제이다. 처음에는 BufferedReader 클래스의 ready() 메서드를 사용했는데 NullPointerException이 발생하여
 * while 조건문에 br.readLine()를 input에 저장하고 null이 아닐 때 까지 반복하는 방식을 택했는데 이 경우 백준 사이트에서는 프로그램이 문제 없이 수행되지만, 이클립스
 * 에서는 입력을 한 후 Ctrl + z를 통해 while문을 종료할 수 있다. 자바에서의 EOF방식도 잘 알고 있어야겠다.
 */
public class Main_s2_19583_싸이버개강총회_김선홍 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		String[] s = new String[3];
		for (int i = 0; i < 3; i++) {
			s[i] = st.nextToken();
		}

		HashSet<String> btSet = new HashSet<>();// 시작 시간 이전에 보이는 이름을 저장
		HashSet<String> nameSet = new HashSet<>();// 이름을 저장
		HashSet<String> resultSet = new HashSet<>();// 끝낸 시간과 스트리밍을 끝낸 시간 사이에 보이는 이름을 저장
		String input = "";

		while ((input = br.readLine()) != null) {// EOF까지 검사
			st = new StringTokenizer(input, " ");
			String time = st.nextToken();
			String name = st.nextToken();
			nameSet.add(name);

			if (s[0].compareTo(time) >= 0) {
				btSet.add(name);
			}
			if (time.compareTo(s[1]) >= 0 && s[2].compareTo(time) >= 0 && btSet.contains(name)) {
				resultSet.add(name);
			}
		}

		System.out.println(resultSet.size());
		br.close();
	}
}
