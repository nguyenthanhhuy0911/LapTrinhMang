package cau1;

import java.io.*;
import java.net.Socket;

public class Bai1SSTCPchoClient {
    public static int messageNo = 1; // Cổng mặc định

    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 7);
            System.out.println("Client da ket noi den Server");
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader ipstr = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream opstr = new DataOutputStream(s.getOutputStream());
            BufferedReader br = new BufferedReader(isr);
            while (true) {
                System.out.println();
                System.out.print("Nhap vao so can chuyen doi - nhap vao quit de thoat: ");
                String str = br.readLine();
                opstr.writeBytes(str);
                opstr.write(13);
                opstr.write(10);
                opstr.flush();
                System.out.println("Client dang goi message thu " + messageNo + " cho Server!");
                messageNo++;
                str = ipstr.readLine();
                System.out.println("Client da nhan: " + str + " tu Server!");
                if (str.equals("quit")) break;
            }
            System.out.println();
            System.out.println("Client ngat ket noi!");
            s.close();
        } catch (IOException ie) {
            System.out.println("Lỗi: Không tạo được socket");
        }
    }
}
