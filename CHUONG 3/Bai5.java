import java.io.*;
import java.util.Scanner;

public class Bai5 {

    public static void main(String arg[]) throws IOException {
        Scanner nhap = new Scanner(System.in);
        System.out.print("Nhap hostname: ");
        String ten = nhap.nextLine();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("src/banned.txt"));

            String textInALine;

            while ((textInALine = br.readLine()) != null) {
                if (ten.equals(textInALine)){
                    System.out.println("Day la trang web trong danh sach cam");
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
    }
}

