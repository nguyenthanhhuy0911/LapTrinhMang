class Callme2 {
    void call2(String msg) {
        System.out.println("["+msg);
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
            return;
        }
        System.out.println("]");
    }
}
class Caller2 implements Runnable {
    String msg;
    Callme2 target;
    Thread t;

    public Caller2(Callme2 tar, String s) {
        target = tar;
        msg = s;
        t = new Thread(this);
        t.start();
    }

    public void run() {
        synchronized (target) {
            target.call2(msg);
        }
    }
}
class DongBo2{
    public static void main(String[] args) {
        Callme2 target = new Callme2();
        Caller2 ob1 = new Caller2(target,"Hello");
        Caller2 ob2 = new Caller2(target, "Synchronized");
        Caller2 ob3 = new Caller2(target,"World");
    }
}