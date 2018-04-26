package t1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Server {

	boolean flag = true;
	
	public void startServer() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		while(flag) {
			System.out.println("Server Ready");
			st = new StringTokenizer(br.readLine());  //waiting
			
			String msg = st.nextToken();
			//Receiver Thread
			Receiver receiver = new Receiver(msg);
			receiver.start();
		}
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println("Server Start");
		new Server().startServer();
		System.out.println("Server Stop");
	}

	//GET REQUEST
	//AND RESPONE
	class Receiver extends Thread{
		
		String msg;
		public Receiver() {}
		public Receiver(String msg) {
			super();
			this.msg = msg;
		}
		@Override
		public void run() {
			for (int i = 0; i < 10; i++) {
				try {
					Thread.sleep(500);
					System.out.println(i);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println(msg+"Completed");
			//Sender Thread를 통해
			//Client에게 전송
			Sender sender = new Sender(msg);
			sender.start();
			
		}
		
	}//end Receiver
	
	class Sender extends Thread{
		String msg;

		public Sender() {}
		public Sender(String msg) {
			super();
			this.msg = msg;
		}

		@Override
		public void run() {
			for (int i = 0; i < 10; i++) {
				try {
					Thread.sleep(500);
					System.out.println(i);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println(msg + ": Send Completed");
		}
		
	}
}
