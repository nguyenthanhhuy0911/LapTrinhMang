package com.company;
import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class C22 {
    public static int dienTich = 0, chuVi = 0, dai = 0, rong = 0;

    public static void main(String argv[]) {
        try {
            Nhap nh = new Nhap();
            TinhDT dt = new TinhDT();
            TinhCV cv = new TinhCV();
            int dem = 0;
            while (1 > 0) {
                if (C22.dienTich != 0) {
                    System.out.println("Diện tích bằng = " + C22.dienTich);
                    dem++;
                    C22.dienTich = 0;
                }
                if (C22.chuVi != 0) {
                    System.out.println("Chu vi bằng = " + C22.chuVi);
                    dem++;
                    C22.chuVi = 0;
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

     class Nhap extends Thread {
        Nhap() {
            System.out.println("Tạo luồng nhập");
            start();
        }

        public void run() {
            Scanner nhap = new Scanner(System.in);
            try {
                System.out.println("Nhập chiều dài = ");
                C22.dai = nhap.nextInt();
                System.out.println();
                System.out.println("Nhập chiều rộng = ");
                C22.rong = nhap.nextInt();
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
                if ((C22.dai != 0) && (C22.rong != 0)) {
                    C22.dienTich = C22.dai * C22.rong;
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
                if ((C22.dai != 0) && (C22.rong != 0)) {
                    C22.chuVi = (C22.dai + C22.rong) * 2;
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

