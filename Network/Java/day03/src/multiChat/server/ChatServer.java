package multiChat.server;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class ChatServer {

	int port = 7777;
	ServerSocket serverSocket;
	Socket child = null;

	HashMap<String, ObjectOutputStream> hm;

	public ChatServer() {
		ChatServerThread serverThread;
		Thread t;

		try {
			serverSocket = new ServerSocket(port);
			System.out.println("====채팅 서버====");
			System.out.println("Waiting Client");

			hm = new HashMap<String, ObjectOutputStream>();

			while (true) {
				child = serverSocket.accept();
				if (child != null) {
					serverThread = new ChatServerThread(child, hm);
					t = new Thread(serverThread);
					t.start();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ChatServer();
	}

}

class ChatServerThread implements Runnable {
	Socket child;
	OutputStream os;
	ObjectOutputStream oos;
	InputStream is;
	ObjectInputStream ois;

	String user_id;
	HashMap<String, ObjectOutputStream> hm;
	InetAddress ip;
	String msg;

	public ChatServerThread(Socket s, HashMap<String, ObjectOutputStream> h) {
		// TODO Auto-generated constructor stub
		child = s;
		hm = h;

		try {
			is = child.getInputStream();
			ois = new ObjectInputStream(is);
			os = child.getOutputStream();
			oos = new ObjectOutputStream(os);
			user_id = ois.readUTF();
			ip = child.getInetAddress();
			System.out.println(ip + "로부터 " + user_id + "님이 접속하였습니다.");
			broadcast(user_id + "님이 접속하셨습니다.");

			synchronized (hm) {
				hm.put(user_id, oos);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String receiveData;

		try {
			while ((receiveData = ois.readUTF()) != null) {
				if (receiveData.equals("quit")) {
					synchronized (hm) {
						hm.remove(user_id);
					}
					break;
				} else if (receiveData.indexOf("\n") >= 0) {
					sendMsg(receiveData);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			synchronized (hm) {
				hm.remove(user_id);
			}

			broadcast(user_id + "님이 퇴장했습니다.");
			System.out.println(user_id + "님이 퇴장했습니다.");

			try {
				if (child != null) {
					ois.close();
					oos.close();
					child.close();
				}
			}

			catch (Exception e) {
			}
		}

	}

	public void broadcast(String msg) {
		synchronized (hm) {
			try {
				for (ObjectOutputStream oos : hm.values()) {
					oos.writeUTF(msg);
					oos.flush();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

	public void sendMsg(String msg) {
		int begin = msg.indexOf(" ") + 1;
		int end = msg.indexOf(" ", begin);

		if (end != -1) {
			String id = msg.substring(begin, end);
			String message = msg.substring(end + 1);
			ObjectOutputStream oos = hm.get(id);
		}
	}
}