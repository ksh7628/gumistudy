import java.io.*;
import java.util.*;

public class Main_s2_19583_싸이버개강총회_김수빈 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] times = br.readLine().split(" ");		// 개강총회 시작 시간, 개강총회 끝난 시간, 개강총회 "스트리밍" 끝난 시간
		//String[] S = times[0].split(":");
		//String[] E = times[1].split(":");
		//String[] Q = times[2].split(":");
		
		Set<String> before = new HashSet<>();
		Set<String> after = new HashSet<>();
		
		String str = "";
		// (str = br.readLine()) != null
		// !(str = br.readLine()).equals("")
		while((str = br.readLine()) != null) {		// 채팅기록 입력받음
			String[] chat = str.split(" ");
			//String[] chatTime = chat[0].split(":");
			
			if(chat[0].compareTo(times[0]) <= 0)
				before.add(chat[1]);
			else if(chat[0].compareTo(times[1]) >= 0 && chat[0].compareTo(times[2]) <= 0)
				after.add(chat[1]);
		}
		
		int answer = 0;
		for(String name : before) {
			if(after.contains(name))
				answer++;
		}
		System.out.println(answer);
	}
}