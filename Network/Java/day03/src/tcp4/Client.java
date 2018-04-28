package tcp4;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	boolean flag = true;
	String address = "127.0.0.1";
	Socket socket;

	public Client() throws UnknownHostException, IOException {
		socket = new Socket(address, 7777);
		System.out.println("Connected Server ..");
	}

	// 소켓이 만들어지
	public void startClient() throws Exception {
		new Receiver(socket).start();
		Sender sender = new Sender(socket);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("input MESSAGE ...");

		while (flag) {
			System.out.println("input MESSAGE ...");
			String msg = br.readLine();

			if (msg.trim().equals("q")) {
				Thread t = new Thread(sender);
				sender.setSendMsg("q");
				t.start();
				flag = false;
				br.close();
				break;
			}
			Thread t = new Thread(sender);
			sender.setSendMsg(msg);
			t.start();
		}
		Thread.sleep(1000);
		socket.close();
		System.out.println("Server Client");
	}

	class Receiver extends Thread { // 들어올때까지 기다리고 읽는다.
		Socket socket;
		InputStream is;
		DataInputStream dis;

		public Receiver(Socket socket) throws IOException {
			this.socket = socket;
			is = socket.getInputStream();
			dis = new DataInputStream(is);
		}

		@Override
		public void run() {
			while (dis != null) {
				try {

					String str = dis.readUTF();
					System.out.println(str);
					if (str.trim().equals("q")) {
						dis.close();
						break;
					}
				} catch (Exception e) {
					System.out.println("Server Closed");
					break;
				}
			}

			try {
				Thread.sleep(1000);
				flag = false;
				socket.close();
				System.exit(0);
			} catch (Exception e) {
				e.printStackTrace();
			}

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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			new Client().startClient();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
