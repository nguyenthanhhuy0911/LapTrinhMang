class Callme {
    synchronized void call(String msg) {
        //void call(String msg){
        System.out.println("["+msg);
        try {
            Thread.sleep(3000);
        }
        catch (InterruptedException e) {
            return;
        }
        System.out.println("]");
    }
}
class Caller implements Runnable{
    String msg;
    Callme target;
    Thread t;
    public Caller(Callme tar, String s){
        target = tar;
        msg = s;
        t = new Thread(this);
        t.start();
    }
    public void run(){

        target.call(msg);
    }
}
class DongBo1{
    public static void main(String[] args) {
        Callme target = new Callme();
        Caller ob1 = new Caller(target,"Hello");
        Caller ob2 = new Caller(target, "Synchronized");
        Caller ob3 = new Caller(target,"World");
    }
}