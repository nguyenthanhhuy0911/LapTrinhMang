/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BAI5;

/**
 *
 * @author KA
 */
public class Main implements Runnable {

    int n;
    public Main(int n) {
        this.n = n;
    }

    private boolean  checkPrime(int n){
        if (n < 2) {
            return false;
        }
        // check so nguyen to khi n >= 2
        for (int i = 2; i <= (int) Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    private  synchronized void display(int n) throws InterruptedException  {
        System.out.println("Cac so nguyen to nho hon 1000: ");
        for (int i = 2; i < n; i++) {
            if (checkPrime(i)==true)

                System.out.println(i);
        }

    }

    private  synchronized int sum(int n) throws InterruptedException  {

        int S=0;
        for (int i = 2; i < n; i++) {
            if (checkPrime(i)==true)
                S=S+i;
        }
        return S;
    }
    @Override
    public void run() {
        try {

            this.display(n);
            System.out.println("Tong cac so nguyen to tu 1 den 1000 la: ");
            System.out.println(this.sum(n));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("ending in processing,......");

    }

    public static void main(String[] args) throws Exception {
        int n = 1000;
        Main main = new Main(n);
        Thread thread = new Thread(main);
        thread.start();


    }
}