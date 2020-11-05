import java.net.*;
import java.io.*;
import java.lang.String.*;
public class UDPEchoServer {
    public final static int port = 7;
    public static void main(String[] args) {
        System.out.println("Server dang hoat dong");
        try {
            DatagramSocket ds = new DatagramSocket(port);
            byte[] buffer = new byte[6000];
            while(true) {
                DatagramPacket incoming = new DatagramPacket(buffer,buffer.length);
                ds.receive(incoming);
                String theString = new String(incoming.getData(),0,incoming.getLength());
                System.out.println("Server nhan duoc: " + theString + " tu Client");
                if (theString.equalsIgnoreCase("quit")){
                    System.out.println("Client ngat ket noi");
                    break;
                }
                theString = theString.toUpperCase();
                System.out.println("Server gởi: " + theString + " cho Client");
                DatagramPacket outsending = new DatagramPacket(theString.getBytes(),
                        incoming.getLength(),incoming.getAddress(), incoming.getPort());
                ds.send(outsending);
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}