package t3;

import java.util.Scanner;

class Receiver implements Runnable{

	String cmd;
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//
			
			if(cmd!=null && cmd.equals("s")) {
				//Send Message
				for(int i =0; i<=50;i++) {
					
					System.out.println(i);
					try {
						Thread.sleep(300);			
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(cmd.equals("e")) {
						break;
					}
				}
				//Send Message End...
			}
		}
	}
}

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Receiver r = new Receiver();
		Thread t = new Thread(r);
		t.start();
		Scanner sc = new Scanner(System.in);
		System.out.println("Strart Input CMD - Press S");
		String cmd = sc.nextLine();
		r.setCmd(cmd);
		System.out.println("END Input CMD - Press E");
		String cmd2 = sc.nextLine();
		r.setCmd(cmd2);
		
		sc.close();
		
	}

}
