package multiChat.client;

//import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClient {

	String ipAddress;
	int port = 7777;

	Socket clientSocket;
	// BufferedReader br;
	boolean flag = true;

	InputStream is;
	OutputStream os;
	ObjectInputStream ois;
	ObjectOutputStream oos;

	String user_id;
	String sendMsg;
	String receiveMsg;

	Receiver receiver;

	public ChatClient(String id, String ip) throws UnknownHostException, IOException {
		user_id = id;
		ipAddress=ip;
		clientSocket = new Socket(ipAddress, port);
		System.out.println("Server conn...");

		os = clientSocket.getOutputStream();
		oos = new ObjectOutputStream(os);
		is = clientSocket.getInputStream();
		ois = new ObjectInputStream(is);
	}

	public void startClient() throws IOException {
		try {
			oos.writeUTF(user_id);
			oos.flush();
			
			receiver = new Receiver(clientSocket, ois);
			
			Thread t = new Thread(receiver);
			t.start();
			
			//Message SEND
			while(flag) {
				sendMsg = ois.readUTF();
				oos.writeUTF(sendMsg);
				oos.flush();
				System.out.println(sendMsg);
				
				if (sendMsg.equals("quit")) {
					flag = false;
					break;
				}
			}
			System.out.println("Server close");
			System.exit(0);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			is.close();
			os.close();
			ois.close();
			oos.close();
			System.exit(0);
		}
		
	}

	public static void main(String[] args) {
		if(args.length <2 ) {
			System.out.print("USAGE : java  ChatClient  사용자_id  서버_ip");
            System.exit(0);
		}

		try {
			new ChatClient(args[0],args[1]).startClient();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
