import java.net.*;
import java.io.*;

public class UDPEchoClient extends Object{
    public final static int serverPort = 7;
    public static void main(String[] args) {
        try {
            //  if (args.length ==0) {
            //     System.out.print("Syntax: java UDPClient HostName");
            //     return;
            //  }
            DatagramSocket ds = new DatagramSocket();
            InetAddress server = InetAddress.getByName("localhost");
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            while(true) {
                System.out.print("Nhap du lieu can goi: ");
                String theString = br.readLine();
                byte[] data = theString.getBytes();
                DatagramPacket dp = new DatagramPacket(data,data.length,server, serverPort);
                ds.send(dp); // Send gói tin sang Echo Server
                if(theString.equalsIgnoreCase("quit")){
                    System.out.println("Client ngat ket noi");
                    break;
                }
                byte[] buffer = new byte[6000];
                // Gói tin nhận
                DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
                ds.receive(incoming);
                System.out.println("Server tra loi: ");
                // Đổi dữ liệu nhận được dạng mảng bytes ra chuỗi và in ra màn hình
                System.out.println(new String(incoming.getData(), 0, incoming.getLength()));
            }
            ds.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}