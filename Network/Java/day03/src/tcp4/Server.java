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

	private int port;
	private boolean flag;
	private ServerSocket serverSocket;

	public Server() throws IOException {
		port = 7777;
		flag = true;
		serverSocket = new ServerSocket(port);
	}

	// 소켓이 만들어지
	public void startServer() throws Exception {
		System.out.println("Server Start ...");

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("input MESSAGE ...");
		String msg = br.readLine();

		if (msg.equals("q")) {
			br.close();
		}

		System.out.println("Server Stop");
	}

	class Receiver extends Thread { // 들어올때까지 기다리고 읽는다.
		private Socket socket;
		private InputStream is = null;
		private DataInputStream dis = null;

		public Receiver() {
		}

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
				Thread.sleep(1000);
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
