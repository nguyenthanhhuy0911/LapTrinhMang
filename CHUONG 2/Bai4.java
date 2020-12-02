import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

class Task implements Runnable {
    public int a, b;
    public ArrayList<Integer> arrayList = new ArrayList<>();

    public Task(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public void run() {
        for (int i = a; i < b; i++) {
            if (checkPrime(i)) {
                arrayList.add(i);
            }
        }
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

public class Bai4 {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        int a, b, c;
        System.out.print("Nhap vao so a: ");
        a = scan.nextInt();
        System.out.print("Nhap vao so b: ");
        b = scan.nextInt();

        System.out.print("Nhap vao so luong: ");
        c = scan.nextInt();

        if (c > (b - a)) {
            System.out.println("So luong khong hop le");
            return;
        }
        ArrayList<Integer> list = new ArrayList<>();
        final List<Task> tasks = new ArrayList<>();
        final List<Thread> threads = new ArrayList<>();

        int s = (b - a) / c; // các khoảng cho moi luồng
        int start = a;
        for (int i = 0; i < c; i++) {
            if (i == c - 1) {
                Task t = new Task(start, b + 1);
                tasks.add(t);
                Thread thread = new Thread(t);
                threads.add(thread);
            } else {
                Task t = new Task(start, start + s);
                tasks.add(t);
                Thread thread = new Thread(t);
                threads.add(thread);
            }
            start += s;
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

        for (final Task task : tasks) {
            list.addAll(task.arrayList);
        }
        Collections.sort(list);
        FileWriter fw = new FileWriter("out.txt");
        for (int i : list) {
            fw.write(String.valueOf(i) + "\n");
        }
        fw.close();
    }


}