package com.company;

import java.lang.reflect.Array;

public class Bai5 {


    public static int sum = 0;

    public static void main(String[] args) throws Exception {

    nguyento nt =new nguyento();
    nt.display(100);
        System.out.println("tong cac so nguyen to la: "+sum);

    }

    public static  boolean check(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= (int) Math.sqrt(n); i++)
            if (n % i == 0) return false;
        return true;
    }
}
class nguyento {
    synchronized void display(int n) throws InterruptedException {
        System.out.println("Cac so nguyen to nho hon 1000: ");
        tong l1 = new tong();
        for (int i = 2; i < n; i++)
            if (Bai5.check(i) == true) {

                System.out.print(i+" ");
                Thread.sleep(500);
                l1.run(i);

            }


    }

}

class tong extends Thread {
        tong() {

            start();
        }
        public  void run( int x) {
            Bai5.sum+=x;
            System.out.println("tong:  "+Bai5.sum);
        }
    }


