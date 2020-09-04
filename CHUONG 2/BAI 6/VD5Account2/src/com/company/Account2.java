package com.company;

public class Account2 {
    private double balance;
    boolean ban = false;

    void Account2() {
        balance = 0.0;
    }

    public synchronized void setBalance(double amount) {
        if (ban)
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println();
            }
        balance = amount;
        ban = true; //khoa khong cho ghi de
        notify();
    }

    public synchronized double getBalance() {
        if (!ban)
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println();
            }
        ban = false;//mo khoa de cap nhat
        notify();
        return balance;
    }

}
class XuLy2 implements Runnable{
    Account2 tk;
    double st;
    String mag;
    int l;
    Thread t;
    public XuLy2 (Account2 x, double y,String str){
        tk = x;
        st = y;
        mag = str;
        t = new Thread(this);
        t.start();
    }
    public void run(){
        tk.setBalance(st);
        System.out.println(" So tien cua tai khoan "+mag+" la: "+tk.getBalance());
        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException e){
            return;
        }
    }
    public static void main(String[] args) {
        Account2 tkh = new Account2();
        XuLy2 t1 = new XuLy2(tkh,15,"Hoa");
        XuLy2 t2 = new XuLy2(tkh,30,"Hong");
        XuLy2 t3 = new XuLy2(tkh,45,"Hue");
    }
}

