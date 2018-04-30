package tcp5;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Server {
	private int port =8888;
	ServerSocket serverSocket;
	Socket socket = null;
	boolean flag = true;
	
	ArrayList<DataOutputStream> list = new ArrayList<>();
	//HashMap<String, DataOutputStream> hm;

	//server ���� ����
	public Server() throws IOException {
		serverSocket = new ServerSocket(port);
		System.out.println("Server Ready...");
	}
	
	//client acceptó�� - while loop
	public void start() throws IOException {
		while(flag) {
			System.out.println("Waiting client");
			socket= serverSocket.accept();
			System.out.println("Connect : "+serverSocket.getInetAddress());
			//socket ���� Receiver �ʿ�
			new Receiver(socket).start();
		}
	}
	
	public static void main(String[] args) {
		Server server = null;
		try {
			server = new Server();
			server.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//
	class Receiver extends Thread{
		InputStream is;
		DataInputStream dis;
		OutputStream os;
		DataOutputStream dos;
		
		public Receiver(Socket socket) throws IOException {
			// socket ���� ���ο� input/output stream�� ��������� ������ ������ ��ġ�� �ʴ´�.
			is = socket.getInputStream();
			dis = new DataInputStream(is);
			os = socket.getOutputStream();
			dos = new DataOutputStream(os);
			//����� Arraylist�� �߰�
			list.add(dos);
			//����� ��
			System.out.println("Client count : "+list.size());
			
			
		}
		@Override
		public void run() {
			while(dis != null) {
				String msg=null;
				try {
					msg = dis.readUTF();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(msg.equals("q")) {
					break;
				}
				System.out.println(msg);
				//send Spring server
				SendHttp http;
				try {
					http = new SendHttp(msg);
					http.start();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			list.remove(dos);
			if(dos != null) {
				try {
					dos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(dis != null) {
				try {
					dis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	
	class SendHttp extends Thread {
		String msg;
		String speed;
		String temp;
		String stringurl= "http://127.0.0.1:8070/webserver/main.do";
		public SendHttp(String msg) throws IOException {
			StringTokenizer st = new StringTokenizer(msg," ");
			speed = st.nextToken();
			temp = st.nextToken();
			this.speed = speed;
			this.temp = temp;
			System.out.println("speed : "+speed+"temp"+temp);
		}
		@Override
		public void run() {
			stringurl+= "?speed="+speed+"&temp="+temp;
			System.out.println(stringurl);
			try {
				URL url = new URL(stringurl);
				HttpURLConnection conn = (HttpURLConnection)url.openConnection();
				conn.setRequestMethod("GET");
				conn.setConnectTimeout(5000);
				conn.getInputStream();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Http Error");
			}
		}
	}
}