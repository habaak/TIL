package t2;

class T2 extends Thread {
	int res = 1;
	boolean flag = true;
	
	public int getResult() {
		return this.res;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(flag) {
			res++;
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

public class Main {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		T2 thread = new T2();
		thread.start();
		int result = 0;
		while(result <= 20) {
			result = thread.getResult();
			System.out.println(result);
			if(result == 20) {
				thread.setFlag(false);	
				break;
			}
		}
	}

}
