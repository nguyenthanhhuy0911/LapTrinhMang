public class Bai5 implements Runnable {

    int n;

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
        for (int i = 2; i < n; i++)
            if (checkPrime(i) == true)
                System.out.println(i);
    }

    private synchronized int sum(int n) throws InterruptedException  {
        int S=0;
        for (int i = 2; i < n; i++) {
            if (checkPrime(i) == true)
                S=S+i;
        }
        return S;
    }
    @Override
    public void run() {
        try {
            this.display(n);
            System.out.print("Tong cac so nguyen to tu 1 den 1000 la: ");
            System.out.println(this.sum(n));

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