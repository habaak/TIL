package tcp4;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.plaf.synth.SynthSpinnerUI;

public class Server {
	private int port=7788;
	private boolean flag = true;
	private ServerSocket serverSocket;

	public Server() throws IOException {
		
		serverSocket = new ServerSocket(port);
	}

	// 소켓이 만들어지
	public void startServer() throws Exception {
		System.out.println("Server Start ...");
		Socket socket = serverSocket.accept();
		System.out.println("Server Connected...");
		System.out.println(serverSocket.getInetAddress());
		
		Receiver receiver = new Receiver(socket);
		receiver.start();
		Sender sender = new Sender(socket);
		

		while(flag) {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("input MESSAGE ...");
			String msg = br.readLine();

			Thread t = new Thread(sender);//sender는 서버가 입력 받은 stirng을 전송하는 역할을 한다
			sender.setSendMsg(msg);
			t.start();
			if (msg.equals("q")) {
				br.close();
			}
		}
		System.out.println("Server Stop");
	}
	//Receiver는 계속 살아있고 sender는 데이터를 보내고자할 때만 생성된다. 즉, sender는 계속 죽고 계속 생성됨 
	
	//socket을 줬으니 inputstream을 만들어서 기다려라 -> 반복해서 읽는다
	class Receiver extends Thread { // 들어올때까지 기다리고 읽는다.
		private Socket socket;
		private InputStream is = null;
		private DataInputStream dis = null;

		public Receiver() {}

		public Receiver(Socket socket) throws IOException {
			this.socket = socket;
			is = socket.getInputStream();
			dis = new DataInputStream(is);
		}

		public void stopReciver() {
			try {
				dis.close();
				dis = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			while (dis != null) {
				try {
					String msg = dis.readUTF();
					System.out.println(msg);
					if (msg.equals("q")) {
						break;
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Server Closed");
					break;
				}
			}
			try {
				Thread.sleep(1000); //잠깐 기다렸다가 소켓을 끊는다
				socket.close();
				System.exit(0);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	class Sender implements Runnable { // 문자를 입력하면 Sender가 만들어지고 전송
		Socket socket;
		OutputStream os;
		DataOutputStream dos;
		String sendMsg;

		public Sender(Socket socket) throws IOException {
			this.socket = socket;
			os = socket.getOutputStream();
			dos = new DataOutputStream(os);
		}

		public void setSendMsg(String sendMsg) {
			this.sendMsg = sendMsg;
		}

		@Override
		public void run() {
			try {
				if (dos != null) {
					dos.writeUTF(sendMsg);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		try {
			Server server = new Server();
			server.startServer();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}
	}

}
