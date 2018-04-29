package multiChat.client;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Receiver implements Runnable{

	Socket clientSocket;
	InputStream is;
	ObjectInputStream ois;
	String receiveMsg;
	
	public Receiver(Socket s, ObjectInputStream ois) {
		// TODO Auto-generated constructor stub
		clientSocket =s;
		this.ois =ois;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while((receiveMsg = ois.readUTF()) != null) {
				System.out.println(receiveMsg);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				ois.close();
				is.close();
				clientSocket.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

}
