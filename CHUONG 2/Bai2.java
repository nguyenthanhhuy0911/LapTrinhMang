import java.util.Scanner;

public class Bai2 {
    public static int dienTich = 0, chuVi = 0, dai = 0, rong = 0;

    public static void main(String argv[]) {
        try {
            Nhap2 nh = new Nhap2();
            TinhDT dt = new TinhDT();
            TinhCV cv = new TinhCV();
            int dem = 0;
            while (1 > 0) {
                if (Bai2.dienTich != 0) {
                    System.out.println("Diện tích bằng = " + Bai2.dienTich);
                    dem++;
                    Bai2.dienTich = 0;
                }
                if (Bai2.chuVi != 0) {
                    System.out.println("Chu vi bằng = " + Bai2.chuVi);
                    dem++;
                    Bai2.chuVi = 0;
                }
                if (dem == 2)
                    break;
                System.out.println();
                System.out.println("Luồng chính đang chờ kết quả");
                Thread.sleep(5000);
            }
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
    }
}

class Nhap2 extends Thread {
    Nhap2() {
        System.out.println("Tạo luồng nhập");
        start();
    }

    public void run() {
        Scanner nhap = new Scanner(System.in);
        try {
            System.out.print("Nhập chiều dài = ");
            Bai2.dai = nhap.nextInt();
            System.out.println();
            System.out.print("Nhập chiều rộng = ");
            Bai2.rong = nhap.nextInt();
            System.out.println();
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }
}

class TinhDT extends Thread {
    TinhDT() {
        System.out.println("Tạo luồng tính diện tích");
        start();
    }

    public void run() {
        while (1 > 0) {
            if ((Bai2.dai != 0) && (Bai2.rong != 0)) {
                Bai2.dienTich = Bai2.dai * Bai2.rong;
                break;
            } else {
                try {
                    System.out.println();
                    System.out.println("Luồng tính diện tích đang chờ nhập số liệu");
                    Thread.sleep(5000);
                } catch (Exception e) {
                    System.out.println("Error " + e);
                }
            }
        }
    }
}

class TinhCV extends Thread {
    TinhCV() {
        System.out.println("Tạo luồng tính chu vi");
        start();
    }

    public void run() {
        while (1 > 0) {
            if ((Bai2.dai != 0) && (Bai2.rong != 0)) {
                Bai2.chuVi = (Bai2.dai + Bai2.rong) * 2;
                break;
            } else {
                try {
                    System.out.println();
                    System.out.println("Luồng tính chu vi đang chờ nhập dữ liệu");
                    Thread.sleep(5000);
                } catch (Exception e) {
                    System.out.println("Error " + e);
                }
            }
        }
    }
}

