package http;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Http3 {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		URL url = new URL("http://70.12.114.132:8070/webserver/doitR.zip");
		
		InputStream is = url.openStream();
		FileOutputStream out = new FileOutputStream("down.zip");
		
		
		
		int i = 0;
		while(true) {
			i = is.read();
			if(i == -1) {
				break;
			}
			out.write(i);
		}
		is.close();
		out.close();
	}

}
