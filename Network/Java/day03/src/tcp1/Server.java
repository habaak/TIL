package tcp1;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	int port;
	ServerSocket serverSocket;
	Socket socket;
	OutputStream out;
	OutputStreamWriter outw;
	boolean flag = true;
	public Server() throws IOException {
		port = 7777;
		serverSocket = new ServerSocket(port); // ��ǻ���� Ư�� ��Ʈ�� �����Ͽ� ���������� �� ���̴�. //�ٸ� ���α׷��� ����ϰ� ���� ���� ���� ó��
	}

	public void startServer() throws IOException {
		System.out.println("Start Server....");
		while(flag) {
		try {
			System.out.println("Ready Server....");
			socket = serverSocket.accept(); // �������� �����⸦ ��ٸ� 
			System.out.println("Accepted.... Client"+socket.getInetAddress());
			out = socket.getOutputStream();
			outw = new OutputStreamWriter(out);
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			outw.write("�ȳ�");
			
		} catch (IOException e) {
			throw e;
		} finally {
			if(outw != null) {
				outw.close();
			}
			if(socket != null) {
				socket.close();
			}
		}
		System.out.println("End Server....");
		}
	}

	public static void main(String[] args) {
		Server server = null;
		try {
			server = new Server();

			server.startServer();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
