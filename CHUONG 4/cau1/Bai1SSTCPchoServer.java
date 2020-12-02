package cau1;

import java.io.*;
import java.net.*;

public class Bai1SSTCPchoServer {
    public final static int defaultPort = 7;
    public static int clientOnline = 0;
    static int clientNo = 0;

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(defaultPort);
            while (true) {
                try {
                    System.out.println("Server dang hoat dong!");
                    System.out.println("----------------------------------");
                    Socket s = ss.accept();
                    clientNo++;
                    System.out.println("----------------------------------");
                    System.out.println("Khoi dong luong cho Client [" + clientNo + "]");
                    RequestProcessing rp = new RequestProcessing(s);
                    rp.start();
                    rp.clientNo = clientNo;
                    Bai1SSTCPchoServer.clientOnline++;
                    System.out.println("So client online hien tai la: " + Bai1SSTCPchoServer.clientOnline);
                    System.out.println("----------------------------------");
                } catch (IOException e) {
                    System.out.println("Connection Error" + e);
                }
            }
        } catch (IOException e) {
            System.out.println("Creation Socket Error:" + e);
        }
    }

    static class RequestProcessing extends Thread {
        Socket skc;
        public int messageNo = 1;
        public int clientNo = 0;

        public RequestProcessing(Socket s) {
            skc = s;
        }

        public void run() {
            try {
                DataOutputStream opstr = new DataOutputStream(skc.getOutputStream());
                BufferedReader ipstr = new BufferedReader(new InputStreamReader(skc.getInputStream()));
                while (true) {
                    System.out.println();
                    String str = ipstr.readLine();
                    System.out.println("Client[" + clientNo + "]" + " doi Message thu " + messageNo);
                    messageNo++;
                    if (str.equals("quit")) {
                        opstr.writeBytes(str);
                        System.out.println("Client[" + clientNo + "]" + " is Disconnected");
                        Bai1SSTCPchoServer.clientOnline--;
                        System.out.println("So client online hien tai la: " + Bai1SSTCPchoServer.clientOnline);
                        System.out.println();
                        break;
                    }
                    String res = "";
                    // Kiem tra phai so hay khong
                    int x = 0;
                    for (int i = 0; i < str.length(); i++)
                        if (!(str.charAt(i) >= '0' && str.charAt(i) <= '9'))
                            res = "Khong phai so nguyen";
                        else x = x * 10 + (str.charAt(i) - '0');
                    // Chuyen so qua chu
                    int digit = (int) (Math.log10((float)x) + 1);
                    if (digit > 9)
                        res = "Dai qua chuyen khong noi!!!";
                    else {
                        // Xet 9 chu so
                        if (digit == 9) {
                            int temp = x / 100000000;
                            if (temp == 1) res += "Mot tram ";
                            else if (temp == 2) res += "Hai tram ";
                            else if (temp == 3) res += "Ba tram ";
                            else if (temp == 4) res += "Bon tram ";
                            else if (temp == 5) res += "Nam tram ";
                            else if (temp == 6) res += "Sau tram ";
                            else if (temp == 7) res += "Bay tram ";
                            else if (temp == 8) res += "Tam tram ";
                            else if (temp == 9) res += "Chin tram ";
                            digit--;
                            x %= 100000000;
                        }
                        // Xet 8 chu so
                        if (digit == 8) {
                            int temp = x / 10000000;
                            if (temp == 1) res += "muoi ";
                            else if (temp == 2) res += "hai muoi ";
                            else if (temp == 3) res += "ba muoi ";
                            else if (temp == 4) res += "bon muoi ";
                            else if (temp == 5) res += "nam muoi ";
                            else if (temp == 6) res += "sau muoi ";
                            else if (temp == 7) res += "bay muoi ";
                            else if (temp == 8) res += "tam muoi ";
                            else if (temp == 9) res += "chin muoi ";
                            digit--;
                            x %= 10000000;
                        }
                        // Xet 7 chu so
                        if (digit == 7) {
                            int temp = x / 1000000;
                            if (temp == 1) res += "mot trieu ";
                            else if (temp == 2) res += "hai trieu ";
                            else if (temp == 3) res += "ba trieu ";
                            else if (temp == 4) res += "bon trieu ";
                            else if (temp == 5) res += "nam trieu ";
                            else if (temp == 6) res += "sau trieu ";
                            else if (temp == 7) res += "bay trieu ";
                            else if (temp == 8) res += "tam trieu ";
                            else if (temp == 9) res += "chin trieu ";
                            digit--;
                            x %= 1000000;
                        }
                        // Xet 6 chu so
                        if (digit == 6) {
                            int temp = x / 100000;
                            if (temp == 1) res += "mot tram ";
                            else if (temp == 2) res += "hai tram ";
                            else if (temp == 3) res += "ba tram ";
                            else if (temp == 4) res += "bon tram ";
                            else if (temp == 5) res += "nam tram ";
                            else if (temp == 6) res += "sau tram ";
                            else if (temp == 7) res += "bay tram ";
                            else if (temp == 8) res += "tam tram ";
                            else if (temp == 9) res += "chin tram ";
                            digit--;
                            x %= 100000;
                        }
                        // Xet 5 chu so
                        if (digit == 5) {
                            int temp = x / 10000;
                            if (temp == 1) res += "muoi ";
                            else if (temp == 2) res += "hai muoi ";
                            else if (temp == 3) res += "ba muoi ";
                            else if (temp == 4) res += "bon muoi ";
                            else if (temp == 5) res += "nam muoi ";
                            else if (temp == 6) res += "sau muoi ";
                            else if (temp == 7) res += "bay muoi ";
                            else if (temp == 8) res += "tam muoi ";
                            else if (temp == 9) res += "chin muoi ";
                            digit--;
                            x %= 10000;
                        }
                        // Xet 4 chu so
                        if (digit == 4) {
                            int temp = x / 1000;
                            if (temp == 0) res += "nghin ";
                            else if (temp == 1) res += "mot nghin ";
                            else if (temp == 2) res += "hai nghin ";
                            else if (temp == 3) res += "ba nghin ";
                            else if (temp == 4) res += "bon nghin ";
                            else if (temp == 5) res += "nam nghin ";
                            else if (temp == 6) res += "sau nghin ";
                            else if (temp == 7) res += "bay nghin ";
                            else if (temp == 8) res += "tam nghin ";
                            else if (temp == 9) res += "chin nghin ";
                            digit--;
                            x %= 1000;
                        }
                        //Xet 3 chu so
                        if (digit == 3) {
                            int temp = x / 100;
//                            if (temp == 0) res += "khong tram ";
//                            else
                            if (temp == 1) res += "mot tram ";
                            else if (temp == 2) res += "hai tram ";
                            else if (temp == 3) res += "ba tram ";
                            else if (temp == 4) res += "bon tram ";
                            else if (temp == 5) res += "nam tram ";
                            else if (temp == 6) res += "sau tram ";
                            else if (temp == 7) res += "bay tram ";
                            else if (temp == 8) res += "tam tram ";
                            else if (temp == 9) res += "chin tram ";
                            digit--;
                            x %= 100;
                        }
                        // Xet 2 chu so
                        if (digit == 2) {
                            int temp = x / 10;
//                            if (temp == 0) res += "linh ";
//                            else
                            if (temp == 1) res += "muoi ";
                            else if (temp == 2) res += "hai muoi ";
                            else if (temp == 3) res += "ba muoi ";
                            else if (temp == 4) res += "bon muoi ";
                            else if (temp == 5) res += "nam muoi ";
                            else if (temp == 6) res += "sau muoi ";
                            else if (temp == 7) res += "bay muoi ";
                            else if (temp == 8) res += "tam muoi ";
                            else if (temp == 9) res += "chin muoi ";
                            digit--;
                        }
                        // Xet 1 chu so
                        if (digit == 1) {
                            int temp = x % 10;
                            if (temp == 1) res += "mot";
                            else if (temp == 2) res += "hai";
                            else if (temp == 3) res += "ba";
                            else if (temp == 4) res += "bon";
                            else if (temp == 5){
                                if (str.length() == 1) res += "nam";
                                else res += "lam";
                            }
                            else if (temp == 6) res += "sau";
                            else if (temp == 7) res += "bay";
                            else if (temp == 8) res += "tam";
                            else if (temp == 9) res += "chin";
                        }
                    }
                    res = "'" + res + "'";
                    opstr.writeBytes(res);
                    opstr.write(13);
                    opstr.write(10);
                    opstr.flush();
                    System.out.println("Server da goi tra loi " + res + " cho Client[" + clientNo + "]");
                }
                skc.close();
            } catch (IOException e) {
                System.out.println("Connection Error: " + e);
            }
        }
    }
}
