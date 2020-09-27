public class StopMe extends Thread {
    public void run() {
        int count = 1;
        System.out.println("Tôi có thể đếm. Xem tôi đi");
        for (; ; ) {
            System.out.println(count++ + " ");
            try {
                Thread.sleep(500);
            } catch (InterruptedException ie) {
            }
        }
    }
    public static void main(String[] args) throws java.io.IOException {
        Thread counter = new StopMe();
        counter.start();
        System.out.println("Nhấn enter để dùng luồng đếm");
        System.in.read();
        counter.stop();
    }
}