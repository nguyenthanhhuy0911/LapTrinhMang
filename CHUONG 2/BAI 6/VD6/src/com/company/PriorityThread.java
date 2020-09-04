package com.company;

class MyThread extends Thread {
    String name;
    int n;

    MyThread(String name, int n) {
        this.name = name;
        this.n = n;
        System.out.println("Thread " + name + " has been created ....!");
        start();
    }

    public void run() {
        for (int i = 0; i < n; i++) {
            System.out.println("Hello, I'm " + name);
            System.out.println(" I go to bed now, bye bye ... wow ... ");
        }
    }

}
public class PriorityThread{
    public static void main(String[] args) {
	   int n = 100;
	   int nt = 12;
	   int prio[]= {Thread.MAX_PRIORITY,
                       Thread.NORM_PRIORITY,
                        Thread.MIN_PRIORITY };
	   for (int i=0; i< nt; i++){
	       MyThread t = new MyThread("Thread"+i,n);
	       t.setPriority(prio[i%3]);
       }
    }
}
