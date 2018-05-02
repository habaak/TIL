package multiChat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;


public class Server extends Thread{

	ServerSocket serverSocket;
	boolean flag = true;
	boolean rflag = true;
	//ArrayList<DataOutputStream> list = new ArrayList<>();
	HashMap<String, DataOutputStream> hm;
	
	
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
		Socket socket;// Thread�� ������ socket�� ���̱� ����
		String ip;
		
		public Receiver (Socket socket) {
			try {
				this.socket = socket;
				
				is = socket.getInputStream();
				dis = new DataInputStream(is);
				os = socket.getOutputStream();
				dos = new DataOutputStream(os);
				ip = socket.getInetAddress().toString();
				
				broadcast(ip+"���� �����߽��ϴ�.");
				synchronized (hm) {
					hm.put(ip, dos);
				}
				System.out.println("Connected Count:"+hm.size());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		

		@Override
		public void run() {
			try {
				//client�� ������ �޽����� �޴´�.
				while(rflag) {
					if(socket.isConnected() && dis != null & dis.available() > 0) {
						String str = dis.readUTF();
						if(str != null && str.equals("q")) {
							Set<String> keys = hm.keySet();
							Iterator<String> it = keys.iterator();
							
							while(it.hasNext()) {
								String key = it.next();
								if(hm.get(key).equals(dos)) {
									synchronized (hm) {
										hm.remove(ip);
									}
								}
							}
							System.out.println("Connected Count:"+hm.size());
							break;
						}
						sendAll(str);
					}
					
				}
				
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				synchronized (hm) {
					hm.remove(ip);
					System.out.println(hm.size());
				}try {
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
	public void broadcast(String msg) {
		synchronized (hm) {
			try {
				for(DataOutputStream dos : hm.values()) {
					dos.writeUTF(msg);
					dos.flush();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
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
				if(!hm.isEmpty() && hm.size()>=0) {
					for(DataOutputStream dos : hm.values()) {
						dos.writeUTF(msg);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Server server =null;
		server = new Server();
		server.start();
	}

}
	
