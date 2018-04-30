package tcp5;

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

	boolean flag = true; //client���� ��� key in�� �ϱ� ���ؼ�
	boolean cflag = true;
	/*String address = "192.168.0.39";*/
	//String address = "70.12.114.132";
	String address = "203.246.196.46";
	Socket socket;

	public Client() throws UnknownHostException, IOException {
		while(cflag) {	// ������ ��� �� �� ���� ���� �õ� ����
			try {
				socket = new Socket(address, 8888);
				System.out.println("Connected Server...");
				if(socket != null && socket.isConnected()) {
					cflag = false ;
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

	// ������ �������
	public void startClient() throws Exception {
		new Receiver(socket).start();
		Sender sender = new Sender(socket);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("input MESSAGE ...");

		//client���� �Է� ���� ���� ����ؼ� ������ �����ϱ� ����
		while (flag) {
			System.out.println("input MESSAGE ...");
			String msg = br.readLine();
			
			if (msg.trim().equals("q")) {
				Thread t = new Thread(sender);
				sender.setSendMsg("q");
				t.start();
				flag = false;
				br.close();
				break; //whileloop��
			}
			//�������� �����ϰ��� �ϴ� ���� �Է¹ް� �����带 ���ؼ� ����
			Thread t = new Thread(sender);
			sender.setSendMsg(msg);
			t.start();
		}
		Thread.sleep(1000);
		socket.close(); //���α׷� ����
		System.out.println("Server Client");
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

	class Receiver extends Thread { // ���ö����� ��ٸ��� �д´�.
		Socket socket;
		InputStream is;
		DataInputStream dis;
		
		public Receiver () {}

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
						//dis.close();
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
