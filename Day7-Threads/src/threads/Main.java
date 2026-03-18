package threads;

public class Main {
	public static void main(String[] args) throws Exception {
		//1. using thread class
		Thread t1 = new MyThread();
		t1.start();
		t1.join();
		System.out.println("Main");
	//	thread.sleep(2000);
	//	using runnable
//		Thread task = new Thread (new MyTask());
//		task.start();
	}

}
