import java.util.Scanner;

public class Bai1 {
    public static int n=0;

    public static void main(String argv[]) {
        try {
            Nhap1 nh = new Nhap1();
            soLe sl = new soLe();
            xuat_All xuat = new xuat_All();
            soChan sc = new soChan();
            ASCII a =new ASCII();

            while (true) {
                if(Bai1.n!=0)
                {
                    nh.run();
                    sl.run();
                    sc.run();
                    xuat.run();
                    a.run();

                }
                System.out.println("Luồng chính đang chờ kết quả");
                Thread.sleep(5000);
                if(Bai1.n!=0) break;
            }
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
    }
}

class Nhap1 extends Thread {
    Nhap1() {
        System.out.println("Tạo luồng nhập");
        start();
    }

    public void run() {

        Scanner nhap = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Nhập vào giá trị của N: ");
                Bai1.n = nhap.nextInt();

                System.out.println();

            } catch (Exception e) {

                System.out.println("Error" + e);
            }

            if(Bai1.n!=0) break;
        }
    }
}

class soLe extends Thread {
    soLe() {
        System.out.println("Luồng in các số lẻ nhỏ hơn N:");
        start();
    }

    public void run() {
        while (true) {
            if (Bai1.n != 0) {
                for (int i = 1; i < Bai1.n; i = i + 2) {
                    System.out.println("Số lẻ: " + i + " ");
                }
                System.out.println();
                break;
            } else {
                try {
                    System.out.println("Luồng in số lẻ đang chờ dữ liệu");
                    Thread.sleep(5000);

                } catch (Exception e) {
                    System.out.println("Error " + e);
                }
            }
        }
    }
}

class soChan extends Thread {
    soChan() {
        System.out.println("Luồng in ra các số chẵn nhỏ hơn N");
        start();
    }

    public void run() {
        while (1 > 0) {
            if (Bai1.n != 0) {
                for (int i=0;i<Bai1.n;i=i+2)
                {
                    System.out.println("Số chẵn: " + i + " ");

                }
                System.out.println();
                break;
            } else {
                try {
                    System.out.println();
                    System.out.println("Luồng in ra các số chẵn nhỏ hơn N đang chờ số liệu");
                    Thread.sleep(5000);
                } catch (Exception e) {
                    System.out.println("Error " + e);
                }
            }
        }
    }
}

class xuat_All extends Thread {
    xuat_All() {
        System.out.println("Luồng in ra các số nhỏ hơn N");
        start();
    }

    public void run() {
        while (1 > 0) {
            if (Bai1.n != 0) {
                for (int i=1;i<=Bai1.n;i++)
                {
                    System.out.println("Số từ 1 tới n: " + i + " ");

                }
                System.out.println();
                break;
            } else {
                try {
                    System.out.println();
                    System.out.println("Luồng in ra các số từ 1 tới N đang chờ số liệu");
                    Thread.sleep(5000);
                } catch (Exception e) {
                    System.out.println("Error " + e);
                }
            }
        }
    }
}
class ASCII extends Thread {
    ASCII() {
        System.out.println("Luồng in các ký tự hoa trong bảng mã ASCII");
        start();
    }

    public void run() {
        for (char i='A';i<='Z';i++)
        {
            System.out.println("Ký tự: " + i + " ");
        }
        System.out.println();

    }
}