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
<<<<<<< HEAD
		port = 8888;
		flag = true;
=======
		
>>>>>>> f33626d251efb1611463ab0dbd54729d6effda11
		serverSocket = new ServerSocket(port);
	}

	// ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
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

			Thread t = new Thread(sender);//sender´Â ¼­¹ö°¡ ÀÔ·Â ¹ÞÀº stirngÀ» Àü¼ÛÇÏ´Â ¿ªÇÒÀ» ÇÑ´Ù
			sender.setSendMsg(msg);
			t.start();
			if (msg.equals("q")) {
				br.close();
			}
		}
		System.out.println("Server Stop");
	}
<<<<<<< HEAD

	class Receiver extends Thread { // ï¿½ï¿½ï¿½Ã¶ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½Ù¸ï¿½ï¿½ï¿½ ï¿½Ð´Â´ï¿½.
=======
	//Receiver´Â °è¼Ó »ì¾ÆÀÖ°í sender´Â µ¥ÀÌÅÍ¸¦ º¸³»°íÀÚÇÒ ¶§¸¸ »ý¼ºµÈ´Ù. Áï, sender´Â °è¼Ó Á×°í °è¼Ó »ý¼ºµÊ 
	
	//socketÀ» ÁáÀ¸´Ï inputstreamÀ» ¸¸µé¾î¼­ ±â´Ù·Á¶ó -> ¹Ýº¹ÇØ¼­ ÀÐ´Â´Ù
	class Receiver extends Thread { // µé¾î¿Ã¶§±îÁö ±â´Ù¸®°í ÀÐ´Â´Ù.
>>>>>>> f33626d251efb1611463ab0dbd54729d6effda11
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
				Thread.sleep(1000); //Àá±ñ ±â´Ù·È´Ù°¡ ¼ÒÄÏÀ» ²÷´Â´Ù
				socket.close();
				System.exit(0);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	class Sender implements Runnable { // ï¿½ï¿½ï¿½Ú¸ï¿½ ï¿½Ô·ï¿½ï¿½Ï¸ï¿½ Senderï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½
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
