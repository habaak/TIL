package implement.p9498;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	public static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int score = Integer.parseInt(st.nextToken());
		
		if (score >= 90) {
            sb.append('A');
        } else if (score >= 80) {
        	sb.append('B');
        } else if (score >= 70) {
        	sb.append('C');
        } else if (score >= 60) {
        	sb.append('D');
        } else {
        	sb.append('F');
        }
		System.out.println(sb);
	}

}
