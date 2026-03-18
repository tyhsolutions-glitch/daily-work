package threads;

class MyTask implements Runnable {
    @Override
    public void run() {
        System.out.println("Task Running");
    }
}

public class Main {   
    public static void main(String[] args) {
        Thread t = new Thread(new MyTask());
        t.start();
    }
}
