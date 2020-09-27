class A {
    synchronized void phuongthuc1(B b) {
        String tenTD = Thread.currentThread().getName();
        System.out.println(tenTD + " dang goi phuong thuc A.phuongthuc1()");
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("A bi ngat");
        }
        System.out.println(tenTD + " Dang goi thu B.phuongthuc4()");
        b.phuongthuc4();
    }

    synchronized void phuongthuc2() {
        System.out.println("Ben tron phuong thuc A.phuongthuc2()");
    }

    static class B {
        synchronized void phuongthuc3(A a) {
            String tenTD = Thread.currentThread().getName();
            System.out.println(tenTD + " dang goi phuong thuc B.phuongthuc3");
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("B bi ngat");
            }
            System.out.println(tenTD + " Dang goi phuong thuc A.phuongthuc2()");
            a.phuongthuc2();
        }

        synchronized void phuongthuc4() {
            System.out.println("Ben trong phuong thuc B.phuongthuc4()");
        }
    }

    static class Deadlock implements Runnable {
        A a = new A();
        B b = new B();

        Deadlock() {
            Thread.currentThread().setName("MainThread");
            Thread t = new Thread(this, "RacingThread");
            t.start();
            a.phuongthuc1(b);
            System.out.println("Tro lai tuyen doan main");
        }

        public void run() {
            b.phuongthuc3(a);
            System.out.println("Tro lai tuyen doan khac");
        }

        public static void main(String[] args) {
            new Deadlock();
        }
    }
}