package cau1;

import java.io.*;
import java.net.*;

public class Bai1SSTCPchoServer {
    public final static int defaultPort = 7;
    public static int clientOnline = 0;
    static int clientNo = 0;

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(defaultPort); // khởi tạo socket server để đón nhận client
            while (true) {
                try {
                    System.out.println("Server dang hoat dong!");
                    System.out.println("----------------------------------");
                    Socket s = ss.accept(); //khi có client thì nhận client 
                    clientNo++; // tăng client number lên 1 đơn vị 
                    System.out.println("----------------------------------");
                    System.out.println("Khoi dong luong cho Client [" + clientNo + "]");
                    RequestProcessing rp = new RequestProcessing(s); // khởi tạo 1 luồng xử riêng đối với mỗi client
                    rp.start(); // chạy luồng vừa được khởi tạo ở trên
                    rp.clientNo = clientNo; // set client number vao luòng 
                    Bai1SSTCPchoServer.clientOnline++; // tăng số lượng client đang online lên 1 đơn vị 
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

    // class này là luồng xử lý cho mỗi client 
    static class RequestProcessing extends Thread {
        Socket skc;
        public int messageNo = 1; // khởi tạo biến đếm số thứ tự của message client gửi tới
        public int clientNo = 0;  // số thứ tự của client đang online 
        int n = 0; // biến lưu số mà sau khi đã convert từ string str của client gửi qua
        static String st = ""; //biến gán vào kết quả 
        static int[] c = new int[10]; // khởi tạo mảng để lưu các kí tự số sau khi tách từ n eg: 100 - > 1,0,0 
        static String[] mau = new String[]{"khong ", "mot ", "hai ", "ba ", "bon ",
                "nam ", "sau ", "bay ", "tam ", "chin "}; 
        static void get(int vt){ // thuật toán chuyển vị trí qua chữ 1 234 567 
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
         // khởi tạo class với socket client là tham số 
        public RequestProcessing(Socket s) {
            skc = s;
        }

        // chạy luồng của class này 
        public void run() {
            try {
                DataOutputStream opstr = new DataOutputStream(skc.getOutputStream());
                BufferedReader ipstr = new BufferedReader(new InputStreamReader(skc.getInputStream()));
                while (true) { // lặp lại 
                    System.out.println();
                    String str = ipstr.readLine(); // đọc message từ client gửi về server 
                    System.out.println("Client[" + clientNo + "]" + " doi Message thu " + messageNo); // in ra thông tin 
                    messageNo++; // tăng số lượng message client lên 1 
                    if (str.equals("quit")) { // nếu message nhận được là "quit" thì sẽ thông báo ra là disconnected và thoát vòng lặp
                        opstr.writeBytes(str);
                        System.out.println("Client[" + clientNo + "]" + " is Disconnected");
                        Bai1SSTCPchoServer.clientOnline--;
                        System.out.println("So client online hien tai la: " + Bai1SSTCPchoServer.clientOnline);
                        System.out.println();
                        break;
                    }
                    st = ""; // gán kết quả trả về là string rỗng 
                    // Kiem tra phai so hay khong
                    n = 0; // là số cần chuyển từ string sang number  có thể dùng gọn hơn   int i=Integer.parseInt("200");  
                    boolean check = true;
                    // hàm kiểm tra chuổi str có phải số từ 0-9 hay khong 
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
                        // thuât toán tách các số từ 1 chuổi , và gán ngược lại mảng theo thứ tự từ trái sang , 
                        // chú ý tách là tách từ phải qua trái (tức là hàng đơn vị quả hàng chục ..
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
