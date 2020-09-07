package com.company;
import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Random;
public class C22 {
    public static int n=0;

    public static void main(String argv[]) {
        try {
            Nhap nh = new Nhap();
            soLe sl = new soLe();
            xuat_All xuat = new xuat_All();
            soChang sc = new soChang();
            ASCII a =new ASCII();
            a.run();
            int dem = 0;
            while (true) {
                if(C22.n!=0)
                {
                    nh.run();
                    sl.run();
                    sc.run();
                    xuat.run();


                }
                System.out.println("Luồng chính đang chờ kết quả");
                Thread.sleep(5000);





                if(C22.n!=0) break;


            }
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
    }
}

class Nhap extends Thread {
    Nhap() {
        System.out.println("Tạo luồng nhập");
        start();
    }

    public void run() {

        Scanner nhap = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("nhập vào giá trị của N: ");
                C22.n = nhap.nextInt();

                System.out.println();

            } catch (Exception e) {

                System.out.println("Error" + e);
            }
            if(C22.n!=0) break;
        }
    }
}

class soLe extends Thread {
    soLe() {
        System.out.println("Luồn in  Các sô lẻ Nhỏ hơn N:");
        start();
    }

    public void run() {
        while (true) {
            if (C22.n != 0) {
                System.out.println("cac số lẽ nhỏ hơn N là: ");
                for (int i = 1; i < C22.n; i = i + 2) {
                    System.out.println(i);
                }
                break;
            } else {
                try {
                    System.out.println("Luồng in số lẽ đang chờ dữ liệu");
                    Thread.sleep(5000);

                } catch (Exception e) {
                    System.out.println("Error " + e);
                }
            }
        }
    }
}

class soChang extends Thread {
    soChang() {
        System.out.println("Luồn in ra các sổ chẳng nhỏ hơn N");
        start();
    }

    public void run() {
        while (1 > 0) {
            if (C22.n != 0) {
                System.out.println("các sô chẳng nhỏ hơn N là: ");
                for (int i=0;i<C22.n;i=i+2)
                {
                    System.out.println(i);

                }
                break;
            } else {
                try {
                    System.out.println();
                    System.out.println("Luồng in  ra các số Chẩng  nhỏ hơn N đang chờ số liệu");
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
        System.out.println("Luồn in ra  các số  nhỏ hơn N");
        start();
    }

    public void run() {
        while (1 > 0) {
            if (C22.n != 0) {
                System.out.println("các sô từ 1 đến N là: ");
                for (int i=0;i<C22.n;i++)
                {
                    System.out.println(i);

                }
                break;
            } else {
                try {
                    System.out.println();
                    System.out.println("Luồng in  ra các số   nhỏ hơn N đang chờ số liệu");
                    Thread.sleep(5000);
                } catch (Exception e) {
                    System.out.println("Error " + e);
                }
            }
        }
    }
}
class ASCII extends Thread {


    public void run() {
        for (int i=65;i<91;i++)
        {
            System.out.println((char)i);
        }

    }
}