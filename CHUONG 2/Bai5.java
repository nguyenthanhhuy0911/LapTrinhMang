package com.company;

import java.lang.reflect.Array;

public class Bai5   implements Runnable{

    int n;
    int sum=0;
    public Bai5(int n) {
        this.n = n;
    }

    private boolean checkPrime(int n){
        if (n < 2) return false;
        for (int i = 2; i <= (int) Math.sqrt(n); i++)
            if (n % i == 0) return false;
        return true;
    }

    private synchronized void display(int n) throws InterruptedException  {
        System.out.println("Cac so nguyen to nho hon 1000: ");
       luon1 l1= new luon1();
        for (int i = 2; i < n; i++)
            if (checkPrime(i) == true)
            {
                System.out.println(i);
                l1.run(i);

            }


    }



    class luon1 extends Thread {
        luon1() {

            start();
        }
        public  void run( int x) {
            sum+=x;

        }
    }
    @Override
    public void run() {
        try {
            this.display(n);
            System.out.print("Tong cac so nguyen to tu 1 den 1000 la: ");
            System.out.println(sum);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        int n = 1000;
        Bai5 main = new Bai5(n);
        Thread thread = new Thread(main);
        thread.start();
    }
}