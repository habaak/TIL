package tcp2;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	int port;
	ServerSocket serverSocket;
	boolean flag = true;
	Socket socket = null;
	
	public Server() throws IOException {
		port = 9999;
		serverSocket = new ServerSocket(port); // ��ǻ���� Ư�� ��Ʈ�� �����Ͽ� ���������� �� ���̴�. //�ٸ� ���α׷��� ����ϰ� ���� ���� ���� ó��
	}
	//Accept Client Socket
	//Sender Thread Create(Send Socket) and Start
	public void startServer() throws IOException {
		
		System.out.println("Start Server....");
		while(flag) { //�������� ������ ��ٸ�
			Socket socket = null;
			System.out.println("Ready Server....");
			socket = serverSocket.accept();
			
			Sender s = new Sender(socket);
			Thread t = new Thread(s);
			t.start();
			System.out.println("Accepted.... Client"+socket.getInetAddress());
			
		}
		System.out.println("End Server....");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Server server = null;
		try {
			server = new Server();
			server.startServer();
		} catch (IOException e) {
			
		}
	}

}
