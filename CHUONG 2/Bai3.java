import java.util.Random;

public class Bai3 {
    public static int n;

    public static void main(String argv[]) {


        Nhap3 nh = new Nhap3();
        TinhBP  dt = new TinhBP();
        int dem = 0;
        while (1 > 0) {
            if (Bai3.n != 0) {
                nh.run();
                dt.run();


            }


        }
    }

}

class Nhap3 extends Thread {
    Nhap3() {
        System.out.println("Tạo luồng random");
        start();
    }

    public void run() {

        Random rand = new Random();
        while (true) {

            try {
                Thread.sleep(2000);

                Bai3.n = rand.nextInt(20);

                System.out.println("Số ngẫu nhiên: " + Bai3.n);

            } catch (Exception e) {
                System.out.println("Error" + e);
            }
        }
    }
}
class TinhBP extends Thread {
    TinhBP() {
        System.out.println("Luồng tính bình phương");
        start();
    }

    public void run() {

        while (true) {

            try {
                Thread.sleep(5000);
                System.out.println("Binh phương của " + Bai3.n + " là:  " + Bai3.n * Bai3.n);

            } catch (Exception e) {
                System.out.println("Error " + e);
            }
        }
    }
}

