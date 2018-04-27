package tcp3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	String ip;
	int port;
	Socket socket;
	InputStream is;
	DataInputStream din;
	OutputStream out;
	DataOutputStream dos;
	
	public Client() {
		super();
	}

	public Client(String ip, int port) throws UnknownHostException, IOException {
		this.ip = ip;
		this.port = port;
		connectServer();
		startClient();
	}
	
	public void connectServer() {
		boolean flag = true;	// 루프 제어 플래그
		while(flag) {	// 서버와 통신 될 때 까지 접속 시도 루프
			try {
				socket = new Socket(ip, port);
				if(socket != null && socket.isConnected()) {
					flag = false ;
				}
			} catch (IOException e) {
				System.out.println("Re-Try Connection...");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	public void startClient() throws UnknownHostException, IOException {
		Scanner sc = new Scanner(System.in);
		is = socket.getInputStream();
		din = new DataInputStream(is);
		out = socket.getOutputStream();
		dos = new DataOutputStream(out);

		try {		
			System.out.println("Connected Server..");
			while(true) {
				System.out.println("보낼 메시지 입력하기");
				String msg = sc.nextLine();
				
				dos.writeUTF(msg);
				//dos.flush();				
				
				String str = din.readUTF();
				System.out.println("받은 메시지: "+str);
				if(str.equals("종료")) {
					break;
				}
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(socket != null) {
				socket.close();
			}
			if(dos!=null) {
				dos.close();
			}
			sc.close();
		}
	}
	
	public static void main(String[] args) {
		Client client = null;
		try {
			client = new Client("70.12.114.133", 7777);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
