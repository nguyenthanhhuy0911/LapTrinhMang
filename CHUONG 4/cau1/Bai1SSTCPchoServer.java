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
        int n = 0;
        static String st = "";
        static int[] c = new int[10];
        static String[] mau = new String[]{"khong ", "mot ", "hai ", "ba ", "bon ",
                "nam ", "sau ", "bay ", "tam ", "chin "};
        static void get(int vt){
            if (vt == 7) return;
            switch (vt)
            {
                case 1:
                    st += "trieu ";
                    break;
                case 4:
                    if (!((c[vt] == 0) && (c[vt - 1] == 0) && (c[vt - 2] == 0)))
                        st += "nghin ";
                    break;
                case 2: case 5:
                if (!(((c[vt + 2] == 0) && (c[vt + 1] == 0)) && (c[vt] == 0)))
                    st += "tram ";
                break;

                case 3: case 6:
                if (c[vt] != 0)
                    st += "muoi ";
                else
                {
                    if (c[vt + 1] != 0)
                        st += "le ";
                }
                break;
            }
        }

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
                    st = "";
                    // Kiem tra phai so hay khong
                    n = 0;
                    boolean check = true;
                    for (int i = 0; i < str.length(); i++)
                        if (!(str.charAt(i) >= '0' && str.charAt(i) <= '9')) {
                            st = "Khong phai so nguyen";
                            check = false;
                        }
                        else n = n * 10 + (str.charAt(i) - '0');
                    // Chuyen so qua chu
                    if (check) {
                        int i;
                        for (i = 1; i <= 7; i++)
                            c[i] = 0;
                        i = 7;
                        while (n != 0)
                        {
                            c[i] = n % 10;
                            n = n / 10;
                            i--;
                        }
                        int vtd = 8;
                        for (i = 1; i <= 7; i++)
                            if (c[i] != 0)
                            {
                                vtd = i;
                                break;
                            }
                        if (vtd == 8)
                        {
                            st += "khong ";
                            break;
                        }
                        for (i = vtd; i <= 7; i++)
                        {
                            switch (c[i])
                            {
                                case 1: case 2: case 3: case  4: case  6: case 7: case 8: case 9:
                                if (!((c[i] == 1) && ((i == 6) || (i == 3))))
                                    st += mau[c[i]];
                                get(i);
                                break;
                                case 5:
                                    if (i == 7)
                                    {
                                        if (c[i - 1] == 0)
                                            st += "nam ";
                                        else
                                            st += "lam ";
                                    }
                                    else
                                    {
                                        if (i == 4)
                                        {
                                            if (c[i - 1] == 0)
                                                st += "nam ";
                                            else
                                                st += "lam ";
                                        }
                                        else
                                            st += mau[c[i]];
                                        get(i);
                                    }
                                    break;
                                case 0:
                                    if (((i == 5) || (i == 2)) && ((c[i + 2] != 0) || ((c[i + 1] != 0))))
                                        st += mau[c[i]];
                                    get(i);
                                    break;
                            }
                        }
                    }
                    st = "'" + st + "'";
                    opstr.writeBytes(st);
                    opstr.write(13);
                    opstr.write(10);
                    opstr.flush();
                    System.out.println("Server da goi tra loi " + st + " cho Client[" + clientNo + "]");
                }
                skc.close();
            } catch (IOException e) {
                System.out.println("Connection Error: " + e);
            }
        }
    }
}
