public class Bai5 {
    
    public static int sum = 0;

    public static void main(String[] args) throws Exception {

        nguyento nt =new nguyento();
        nt.display(100);
        System.out.println("Tong cac so nguyen to la: "+sum);

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
        tong l1 = new tong();
        for (int i = 2; i < n; i++)
            if (Bai5.check(i) == true) {
                System.out.println("So nguyen to: " + i);
                l1.run(i);

            }


    }

}

class tong extends Thread {
    tong() {

        start();
    }
    public void run( int x) {
        Bai5.sum+=x;
        System.out.println("Tong: " + Bai5.sum);
    }
}