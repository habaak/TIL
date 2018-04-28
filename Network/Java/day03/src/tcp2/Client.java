package tcp2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	String ip;
	int port;

	Socket socket;
	InputStream is;
	InputStreamReader isr;
	BufferedReader br;

	public Client() {
	}

	public Client(String ip, int port) throws IOException {
		this.ip = ip;
		this.port = port;
		connect();
		startClient();
	}

	public void connect() {
		boolean flag2 = true;
		while(flag2) {
			try {
				socket = new Socket(ip,port);
				if(socket != null && socket.isConnected()) {
					break;
				}
			} catch (IOException e) {
				System.out.println("Retry");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {}
			}
		}
	}

	public void startClient() throws UnknownHostException, IOException {
		try {
			System.out.println("Connected");

			is = socket.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);

		} catch (UnknownHostException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} finally {
			br.close();
			socket.close();
		}
	}

	public static void main(String[] args) {
		Client client = null;

		try {
			// client = new Client("192.168.0.39",7777);
			client = new Client("70.12.114.130", 7777);

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// client.startClient();

	}

}
