import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.util.Scanner;

public class Bai2 {

    public static void main(String arg[ ]) throws IOException {
        int i;
        InputStream a;
        Scanner nhap = new Scanner(System.in);
        System.out.print("Nhap dia chi URL: ");
        String url = nhap.nextLine();
        try {
            URL u = new URL(url);
            a = (InputStream)u.getContent();
            while((i=a.read())>0)
                System.out.print((char)i);
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

