package http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class http2 {

	public static void main(String[] args) throws Exception {
		String name = "���Ϲ�";
		
		name = URLEncoder.encode(name,"UTF-8");
		String surl = "http://70.12.114.132:8070/webserver/login?id=qq&pwd=11&name="+name;
		
		URL url = new URL(surl);
		
		URLConnection conn = url.openConnection();
		conn.setConnectTimeout(5000); //5�� ���� ������ ������ ���� ó�� ����
		
		InputStream is = conn.getInputStream();
		InputStreamReader isr = new InputStreamReader(is,"UTF-8");
		BufferedReader br = new BufferedReader(isr);
		
		String str = br.readLine();
		System.out.println(str);
		br.close();
	}

}

