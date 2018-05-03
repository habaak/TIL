package tcp7;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread{

	ServerSocket serverSocket;
	boolean flag = true;
	boolean rflag = true;
	ArrayList<DataOutputStream> list = new ArrayList<>();
	
	public Server() throws IOException {
		serverSocket = new ServerSocket(8888);
		System.out.println("Server Ready...");
	}
	
	//start server 
	@Override
	public void run() {
		//Accept Client Connection...
		try {
			while(flag) {
				System.out.println("Ready Accept");
				Socket socket = serverSocket.accept();
				new Receiver(socket).start();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	class Receiver extends Thread{
		InputStream is;
		DataInputStream dis;
		OutputStream os;
		DataOutputStream dos;
		Socket socket;// Thread가 끝날때 socket을 죽이기 위해
		
		public Receiver (Socket socket) {
			try {
				this.socket = socket;
				is = socket.getInputStream();
				dis = new DataInputStream(is);
				os = socket.getOutputStream();
				dos = new DataOutputStream(os);
				list.add(dos);
				System.out.println("Connected Count : "+list.size());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		

		@Override
		public void run() {
			try {
				//client가 보내는 메시지를 받는다.
				while(rflag) {
					if(socket.isConnected() && dis != null & dis.available() > 0) {
						String str = dis.readUTF();
						if(str != null && str.equals("q")) {
							break;
						}
						System.out.println(str);
						//sendAll(str);
					}
				}
				
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				list.remove(dos);
				System.out.println(list.size());
				try {
					Thread.sleep(200);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(dis != null) {
					try {
						dis.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(dos != null) {
					try {
						dos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(socket != null) {
					try {
						socket.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		
	}
	public void sendAll(String msg) {
		Sender sender = new Sender();
		sender.setMsg(msg);
		sender.start();
	}

	//Send Message All Clients
	class Sender extends Thread{
		String msg;
		public void setMsg(String msg) {
			this.msg = msg;
		}
		
		@Override
		public void run() {
			try {
				if(!list.isEmpty() && list.size()>=0) {
					for(DataOutputStream dos : list) {
						dos.writeUTF(msg);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void stopServer() {
		rflag = false;
		
	}

}