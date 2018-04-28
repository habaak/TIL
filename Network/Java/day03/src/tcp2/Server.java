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
		port = 7777;
		serverSocket = new ServerSocket(port); // 컴퓨터의 특정 포트에 접속하여 서버역할을 할 것이다. //다른 프로그램이 사용하고 있을 떄는 예외 처리
	}
	//Accept Client Socket
	//Sender Thread Create(Send Socket) and Start
	public void startServer() throws IOException {
		
		System.out.println("Start Server....");
		while(flag) { //누군가가 들어오길 기다림
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
