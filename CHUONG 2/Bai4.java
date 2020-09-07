import java.util.Scanner;

public class Bai4 implements Runnable {

    int a,b;

    public void nhap() {
        System.out.println("Nhap vao 2 so nguyen a, b");
        Scanner scanner = new Scanner(System.in);

        int a=scanner.nextInt();
        int b=scanner.nextInt();

    }

    private boolean  KTsoNgto(int a){
        if (a < 2) return false;
        int squareRoot = (int) Math.sqrt(a);
        for (int i = 2; i <= squareRoot; i++)
            if (a % i == 0) return false;
        return true;
    }

    private synchronized void HienthiSongto(int a,int b) throws InterruptedException  {
        System.out.println("Các số nguyen to tu a den b la: ");
        for (int i = a; i <= b; i++) {
            if (KTsoNgto(i)==true)
                System.out.println(i);
        }

    }
    @Override
    public void run() {
        try {
            this.nhap();
            this.HienthiSongto(a,b);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("ending in processing,......");

    }

    public static void main(String[] args) throws Exception {
        Bai4 main = new Bai4();
        Thread thread = new Thread(main);
        thread.start();

    }
}