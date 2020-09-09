import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

class Task implements Runnable {
    public int n;
    public boolean isPrimeNumber;

    public Task(Integer n) {
        this.n = n;
    }

    public void run() {
        this.isPrimeNumber = this.checkPrime(this.n);
    }

    boolean checkPrime(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}

public class B4 {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.print("Nhap vao so a: ");
        int a = scan.nextInt();
        System.out.print("Nhap vao so b: ");
        int b = scan.nextInt();
        ArrayList<Integer> list = new ArrayList<>();
        final List<Task> tasks = new ArrayList<>();
        final List<Thread> threads = new ArrayList<>();

        for (int i = a; i <= b; i++) {
            Task t = new Task(i);
            tasks.add(t);

            Thread thread = new Thread(t);
            threads.add(thread);
        }
        for (final Thread thread : threads) {
            thread.start();
        }
        for (final Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for(final Task task: tasks){
            if (task.isPrimeNumber){
                list.add(task.n);
            }
        }
        Collections.sort(list);

        FileWriter fw = new FileWriter("out.txt");
        for(int i : list){
            fw.write(String.valueOf(i)+ "\n");
        }
        fw.close();
    }


}
