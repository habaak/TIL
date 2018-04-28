package tcp3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

//client에서 오는 message를 받고 어떤 client가 보낸 건지 파악하기(1:1)
//server는 reply하기 (multi-chating X)
public class Server {
	
	private int port;
	private boolean flag;
	private ServerSocket serverSocket;
	
	
	public Server() throws IOException {
		port = 7777;
		flag = true;
		serverSocket = new ServerSocket(port);
	}
	
	public void startServer() throws IOException {	
		System.out.println("Start Server...");
		System.out.println("Ready Server...");
		while(flag) {
			try {
				Thread clientThread = new Thread(new ReceiveAndSender(serverSocket.accept()));
				clientThread.start();
			} catch (IOException e) {
				System.out.println("통신 연결 중 오류 발생..");
			}
		}
		System.out.println("End Server...");
	}
	
	
	class ReceiveAndSender implements Runnable{

		private Socket socket;
		private InputStream in = null;
		private DataInputStream dis = null;
		private boolean flag_exit = true;
		
		private DataOutputStream dos = null;
		
		public ReceiveAndSender(Socket socket) {
			this.socket = socket;
			System.out.println("Connected Client... " + socket.getInetAddress());
		}
		
		
		@Override
		public void run() {
			try (OutputStream os = socket.getOutputStream();)
			{
				in = socket.getInputStream();
				dis = new DataInputStream(in);
				
				
				while(true) {
					String msg = dis.readUTF();
					dos = new DataOutputStream(os);
					Thread.sleep(1000);
					if(msg.equals("종료")) {
						dos.writeUTF("종료");
						dos.flush();
						break;
					}
					if(msg != null) {
						System.out.println(socket.getInetAddress()+": "+msg);
						dos.writeUTF("안녕 client야");
						dos.flush();
					}
					msg = null;
				}	
				
			} catch (IOException e) {
				System.out.println("통신 중 오류 발생..");
			} catch(InterruptedException e) {
				System.out.println("작업 중 오류 발생..");
			} finally {
				System.out.println("Disconnected Client... " + socket.getInetAddress());
					try {
						if(socket != null)
							socket.close();
						if(dis != null) {
							dis.close();
						}
						if(dos!=null) {
							dos.close();
						}
					} catch (IOException e) {
						System.out.println("통신 종료 중 오류 발생..");
					}
			}
		}
		
	}
	
	public static void main(String[] args) {
		try {
			Server server = new Server();
			server.startServer();
		} catch (IOException ie) {
			ie.printStackTrace();
		} finally {
			
		}
	}
}
