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

		port = 8888;
		flag = true;
		serverSocket = new ServerSocket(port);
	}

	// ������ �������
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

			Thread t = new Thread(sender);//sender�� ������ �Է� ���� stirng�� �����ϴ� ������ �Ѵ�
			sender.setSendMsg(msg);
			t.start();
			if (msg.equals("q")) {
				br.close();
			}
		}
		System.out.println("Server Stop");
	}


	class Receiver extends Thread { // ���ö����� ��ٸ��� �д´�.

	//Receiver�� ��� ����ְ� sender�� �����͸� ���������� ���� �����ȴ�. ��, sender�� ��� �װ� ��� ������ 
	
	//socket�� ������ inputstream�� ���� ��ٷ��� -> �ݺ��ؼ� �д´�
	class Receiver extends Thread { // ���ö����� ��ٸ��� �д´�.

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
				Thread.sleep(1000); //��� ��ٷȴٰ� ������ ���´�
				socket.close();
				System.exit(0);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	class Sender implements Runnable { // ���ڸ� �Է��ϸ� Sender�� ��������� ����
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
