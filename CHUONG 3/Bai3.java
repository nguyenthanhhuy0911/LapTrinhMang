import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Bai3 {
    public static void main(String args[]) throws UnknownHostException {
        Scanner nhap = new Scanner(System.in);
        System.out.println("Nhap localhost: ");
        String name = nhap.nextLine();
        InetAddress host = InetAddress.getByName(name);
        InetAddress localHost= host.getLocalHost();
        System.out.println("localhost: "+localHost);
        System.out.println("Dia chi URL: "+ host.getHostAddress());
    }
}