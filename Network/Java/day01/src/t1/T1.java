package t1;

class Thread1 extends Thread{
	String msg;
	boolean flag  = true;
	
	
	public Thread1 (String msg) {
		this.msg = msg;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	@Override
	public void run() {
		// thread acting area
		int cnt=0;
		while(flag) {
			System.out.println(msg+ " : "+ cnt++);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

class Thread2 implements Runnable{
	String msg;
	boolean flag=true;
	int cnt = 0;
	public Thread2 (String msg) {
		this.msg = msg;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	@Override
	public void run() {
		// thread acting area
		while(flag) {
			System.out.println(msg+" : "+cnt++);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}	
}

public class T1 {

	public static void main(String[] args) throws InterruptedException {
		// Thread 1 / Thread2
		Thread1 t1 = new Thread1("T1");//다형성 이런거 없음
		
		Thread2 r = new Thread2("t2"); // Runnable 객체
		Thread t2 = new Thread(r);
		
		
		t1.start();
		t2.start();
		Thread.sleep(5000);
		t1.setFlag(false);
		r.setFlag(false);
	}

	
}



