import java.io.*;
import java.net.URL;
import java.util.Scanner;

public class Bai5 {

    public static void main(String arg[]) throws IOException {
        Scanner nhap = new Scanner(System.in);
        System.out.print("Nhap hostname: ");
        String ten = nhap.nextLine();
        boolean check = true;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("src/banned.txt"));

            String textInALine;

            while ((textInALine = br.readLine()) != null) {
                if (ten.equals(textInALine)){
                    System.out.println("Day la trang web trong danh sach cam");
                    check = false;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ten="https://" + ten;
        if (check){
            System.out.println("Noi dung: ");
            int i;
            InputStream a;
            try {
                URL u = new URL(ten);
                a = (InputStream)u.getContent();
                while((i=a.read())>0)
                    System.out.print((char)i);
                System.out.println();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

