package http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class http1 {

	public static void main(String[] args) {
/*		
		InetAddress ia = null;
		ia = InetAddress.getByName("localhost");
		System.out.println(ia.getHostAddress());
		System.out.println(ia.getHostName());
*/
		URL url = null;
		String address = "http://70.12.114.132:8070/webserver/NewHtml.html";
		try {
			url = new URL(address); //����
		}catch(MalformedURLException e) {
			e.printStackTrace();
		}
		InputStreamReader isr = null; //�ܾ����
		BufferedReader br =null; //���δ���
		
		String str = null;
		StringBuffer sb = new StringBuffer(); //String�� ������ �Ұ����ϱ� ������ StringBuffer(line ����)�� ����Ѵ�.
		
				
		try {
			isr = new InputStreamReader(url.openStream());
			br = new BufferedReader(isr);
			while(true) {
				str = br.readLine();
				if(str==null) {
					break;
				}
				sb.append(str);
			}
		} catch (IOException e) { //server down , network disconn
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println(sb);
	}

}
