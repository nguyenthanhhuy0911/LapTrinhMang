package com.company;

public class Account1 {
    private double balance;
    void Account1() {

        balance = 0.0;
    }

    public synchronized double getBalance() {
        return balance;
    }

    public synchronized void setBalance(double amount) {
        balance = amount;
    }
}
class XuLy1 implements Runnable{
    Account1 tk;
    double st;
    String mag;
    int l;
    Thread t;
    public XuLy1 (Account1 x, double y,String str){
        tk = x;
        st = y;
        mag = str;
        t = new Thread(this);
        t.start();
}
     public void run(){
        tk.setBalance(st);
         System.out.println(" So tien cua tai khoan "+mag+" la: "+tk.getBalance());
         tk.setBalance(0.0);
         try {
             Thread.sleep(0);
         }
         catch (InterruptedException e){
             return;
         }
     }
    public static void main(String[] args) {
	     Account1 tkh = new Account1();
	     XuLy1 t1 = new XuLy1(tkh,15,"Hoa");
	     XuLy1 t2 = new XuLy1(tkh,30,"Hong");
	     XuLy1 t3 = new XuLy1(tkh,45,"Hue");
    }
}
