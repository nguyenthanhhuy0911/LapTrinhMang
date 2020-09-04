package com.company;

class ThuaKeThread extends Thread {
    String word;
    int delay;
    ThuaKeThread(String s, int d){
        word =s;
        delay=d;
    }
    public void run(){
        try{
            for (int i=0; i<30; i++){
                System.out.println(word+" ");
                sleep(delay);
            }
        }
        catch (InterruptedException e) {
            return;
        }
    }
    public static void main(String[] args) {
        ThuaKeThread t1 = new ThuaKeThread("ping", 15);
        t1.start();
        ThuaKeThread t2 = new ThuaKeThread("PONG", 30);
        t2.start();
    }
}
