package tcp7;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends Thread {

	boolean flag = true;
	boolean cflag = true;
	/* String address = "192.168.0.39"; */
	// String address = "203.246.196.46";
	String address = "70.12.114.132";
	Socket socket;

	public Client() throws UnknownHostException, IOException {

	}

	@Override
	public void run() {
		while (cflag) { // 서버와 통신 될 때 까지 접속 시도 루프
			try {
				socket = new Socket(address, 8888);
				System.out.println("Connected Server...");
				if (socket != null && socket.isConnected()) {
					cflag = false;
				}
				break;
			} catch (IOException e) {
				System.out.println("Re-Try Connection...");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
		// After Connected ...
		try {
			new Receiver(socket).start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendMsg(String msg) {
		try {
			Sender sender = new Sender(socket);
			sender.setSendMsg(msg);
			new Thread(sender).start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 소켓이 만들어지
	/*
	 * public void startClient() throws Exception { new Receiver(socket).start();
	 * Sender sender = new Sender(socket); //BufferedReader br = new
	 * BufferedReader(new InputStreamReader(System.in));
	 * System.out.println("input MESSAGE ...");
	 * 
	 * //client에서 입력 받은 값을 계속해서 서버로 전송하기 위해 while (flag) {
	 * System.out.println("input MESSAGE ..."); String msg = br.readLine();
	 * 
	 * if (msg.trim().equals("q")) { Thread t = new Thread(sender);
	 * sender.setSendMsg("q"); t.start(); flag = false; br.close(); break;
	 * //whileloop끝 } //서버에게 전송하고자 하는 값을 입력받고 스레드를 통해서 전송 Thread t = new
	 * Thread(sender); sender.setSendMsg(msg); t.start(); } Thread.sleep(1000);
	 * socket.close(); //프로그램 종료 System.out.println("Server Client"); }
	 */

	/*
	 * public static void main(String[] args) { // TODO Auto-generated method stub
	 * try { new Client().startClient(); //객체 생성 및 프로그램 시작 } catch (Exception e) {
	 * // TODO Auto-generated catch block e.printStackTrace(); } }
	 */

	class Receiver extends Thread { // 들어올때까지 기다리고 읽는다.
		Socket socket;
		InputStream is;
		DataInputStream dis;

		public Receiver() {
		}

		public Receiver(Socket socket) throws IOException {
			this.socket = socket;
			is = socket.getInputStream();
			dis = new DataInputStream(is);
		}

		@Override
		public void run() {
			try {
				while (flag && dis != null) {

					String str = dis.readUTF();
					System.out.println(str);
					if (str.trim().equals("q")) {
						dis.close();
						break;
					}
				}
			} catch (Exception e) {
				System.out.println("Server Closed");
			} finally { //while이 끝나는 시점
				try {
					dis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			stopClient();
		}
	}

	public void stopClient() {
		try {
			Thread.sleep(1000);
			flag = false;
			if (socket != null) {
				socket.close();
			}
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