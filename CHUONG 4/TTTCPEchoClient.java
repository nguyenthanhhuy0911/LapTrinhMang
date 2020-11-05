import java.io.*;
import java.net.Socket;

public class TTTCPEchoClient{
    public static void main(String args[]){
        System.out.println("Client dang chay\n");
        try {
            Socket s = new Socket("localhost",7);
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            for (int i='0'; i<='9';i++){
                os.write(i);
                int ch = is.read();
                System.out.print((char)ch);
            }
            System.out.println("\n");
            System.out.println("Client dong ket noi");
            s.close();
        } catch(IOException ie){
            System.out.println("Loi: khong tao duoc Socket");
        }
    }
}