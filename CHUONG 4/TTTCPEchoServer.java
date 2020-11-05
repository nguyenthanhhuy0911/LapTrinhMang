import java.net.*;
import java.io.*;

public class TTTCPEchoServer {
    public final static int defaultPort = 7;
    static int tt=0;
    public static void main(String[] args) {
        System.err.println("Server dang chay");
        try {
            ServerSocket ss = new ServerSocket(defaultPort);
            while (true) {
                try {
                    tt++;
                    System.err.println("Server đang chờ kết nối từ Client" + tt);
                    Socket s = ss.accept();
                    System.err.println("Server đang phục vụ Client thứ " + tt);
                    OutputStream os = s.getOutputStream();
                    InputStream is = s.getInputStream();
                    int ch=0;
                    while(true) {
                        ch = is.read();
                        if(ch == -1) break;
                        os.write(ch);
                    }
                    s.close();
                } catch (IOException e) {
                    System.err.println(" Connection Error: "+e);
                }
            }
        } catch (IOException e) {
            System.err.println(" Server Creation Error:"+e);
        }
    }
}