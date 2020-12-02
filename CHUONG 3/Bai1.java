import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Bai1 {
    public static void main(String[] args) throws MalformedURLException {
        Scanner nhap = new Scanner(System.in);
        System.out.println("Nhap dia chi URL: ");
        String name = nhap.nextLine();
        URL url = new URL(name);
        System.out.println("URL : " + url.toString());
        System.out.println("file name : " + url.getFile());
        System.out.println("host : " + url.getHost());
        System.out.println("port : " + url.getPort());
        System.out.println("protocol : " + url.getProtocol());
    }
}
