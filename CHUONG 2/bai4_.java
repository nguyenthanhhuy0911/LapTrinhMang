package com.company;
import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Random;


import static java.lang.Math.sqrt;

public class C22 {
    public static int n = 0;

    public static void main(String argv[]) {

        Scanner nhap = new Scanner(System.in);
        int n, a, b;
        System.out.println("nhập vào số luồng: ");
        n = nhap.nextInt();
        System.out.println("nhập vào a: ");
        a = nhap.nextInt();
        System.out.println("nhập vào b: ");
        b = nhap.nextInt();
        luon1 l1= new luon1();
        luon2 l2= new luon2();
        luon3 l3= new luon3();
        luon4 l4 = new luon4();
        if (n == 1) {
            l1.run(a,b);
        }

        if (n==2)
        {
            l1.run(a,(int )b/n);
            l2.run((int)b/n+1,b);
        }
        if (n==3)
        {
            l1.run(a,(int )b/n);
            l2.run( (int )(b/n)+1,2*((int )(b/n)) );
            l3.run(2*((int )(b/n))+1 ,b);
        }

        if (n==4)
        {
            l1.run(a,(int )b/n);
            l2.run( (int )(b/n)+1,2*((int )(b/n)) );
            l3.run(2*((int )(b/n))+1 ,3*((int)(b/n)) );
            l4.run(3*((int)(b/n)+1 ),b );
        }
    }


    public static boolean check(int n) {
        if (n < 2) return false;
        if (n == 2) return true;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

}
   class luon1 extends Thread {
        luon1() {

            start();
        }
        public  void run(int y, int x) {
            System.out.println("ket qua luon 1 trong khoảng: "+y+" và"+x);
            for (int i = y; i < x; i++) {
                if (C22.check(i) == true) System.out.println(i);
            }
        }
    }

    class luon2 extends Thread {
        luon2() {

            start();
        }

        public void run(int y, int x) {
            System.out.println("ket qua luon 2 trong khoảng: "+y+" và"+x);
            for (int i = y; i < x; i++) {
                if (C22.check(i) == true) System.out.println(i);
            }
        }
    }

    class luon3 extends Thread {
        luon3() {

            start();
        }

        public void run(int y, int x) {
            System.out.println("ket qua luon 3  trong khoảng:" +y+" và"+x);
            for (int i = y; i < x; i++) {
                if (C22.check(i) == true) System.out.println(i);
            }
        }
    }

class luon4 extends Thread {
    luon4() {

        start();
    }

    public void run(int y, int x) {
        System.out.println("ket qua luon 4  trong khoảng:" +y+" và"+x);
        for (int i = y; i < x; i++) {
            if (C22.check(i) == true) System.out.println(i);
        }
    }
}