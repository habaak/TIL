package multiChat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Server2 extends Thread {

	ServerSocket serverSocket;
	boolean flag = true;
	boolean rflag = true;
	ArrayList<DataOutputStream> list = new ArrayList<>();
	HashMap<String, DataOutputStream> map = new HashMap<>();

	public Server2() throws IOException {
		// Create ServerSocket ...
		serverSocket = new ServerSocket(8888);
		System.out.println("Ready Server...");
	}

	@Override
	public void run() {
		// Accept Client Connection ...
		try {
			while (flag) {
				System.out.println("Ready Accept");
				Socket socket = serverSocket.accept();
				new Receiver(socket).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	class Receiver extends Thread {

		InputStream in;
		DataInputStream din;
		OutputStream out;
		DataOutputStream dout;
		Socket socket;
		String ip;

		public Receiver(Socket socket) {
			try {
				this.socket = socket;
				in = socket.getInputStream();
				din = new DataInputStream(in);
				out = socket.getOutputStream();
				dout = new DataOutputStream(out);
				ip = socket.getInetAddress().getHostAddress();
				map.put(ip, dout);
				System.out.println("Connected Count:" + map.size());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} // end Recevier

		@Override
		public void run() {
			try {
				while (rflag) {

					if (socket.isConnected() && din != null && din.available() > 0) {
						String str = din.readUTF();
						if (str != null && str.equals("q")) {
							map.remove(ip);
							System.out.println("Connected Count:" + map.size());
							break;
						}
						sendAll("[" + ip + "]" + str);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {

				try {
					Thread.sleep(200);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				if (dout != null) {
					try {
						dout.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (din != null) {
					try {
						din.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (socket != null) {
					try {
						socket.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

	}

	public void sendAll(String msg) {
		System.out.println(msg);
		Sender sender = new Sender();
		sender.setMeg(msg);
		sender.start();
	}

	// Send Message All Clients
	class Sender extends Thread {

		String msg;

		public void setMeg(String msg) {
			this.msg = msg;
		}

		@Override
		public void run() {
			try {
				Collection<DataOutputStream> col = map.values();
				Iterator<DataOutputStream> it = col.iterator();
				while (it.hasNext()) {
					it.next().writeUTF(msg);
				}

			} catch (Exception e) {
				// e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Server server = null;
		try {
			server = new Server();
			server.start();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}