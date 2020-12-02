import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Scanner;

public class Bai4 {
    public static void main(String args[])throws IOException {
        int i;
        Scanner input = new Scanner(System.in);
        System.out.print("Nhap URL: ");
        String  URL = input.nextLine();
        try {
            java.net.URL ul=new URL(URL);
            URLConnection u=ul.openConnection();
            System.out.println("Ngay tao: "+ new Date(u.getDate()));
            System.out.println("Ngay chinh sua sau cung: "+ new Date(u.getLastModified()));
            System.out.println("Ngay het han: "+ new Date(u.getExpiration()));
        } catch(MalformedURLException e){
            System.out.println(e);
        }
    }
}