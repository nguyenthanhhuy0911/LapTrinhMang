/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BAI4;

import java.util.Scanner;

/**
 *
 * @author KA
 */

public class Main implements Runnable {

    int a,b;


    public void nhap() {
        System.out.println("nhap vao 2 so nguyen a b");
        Scanner scanner = new Scanner(System.in);

        int a=scanner.nextInt();
        int b=scanner.nextInt();

    }
    private boolean  KTsoNgto(int a){
        if (a < 2) {
            return false;
        }
        // check so nguyen to khi n >= 2
        int squareRoot = (int) Math.sqrt(a);
        for (int i = 2; i <= squareRoot; i++) {
            if (a % i == 0) {
                return false;
            }
        }
        return true;
    }
    private  synchronized void HienthiSongto(int a,int b) throws InterruptedException  {
        System.out.println("--Các số nguyen to tu a den b la: ");
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
        Main main = new Main();
        Thread thread = new Thread(main);
        thread.start();

    }
}