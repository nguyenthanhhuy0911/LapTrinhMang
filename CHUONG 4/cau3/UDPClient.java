import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class UDPClient {
    static final String hostname = "127.0.0.1";
    static final int port = 8080;

    public static void main(String[] args) {
        System.out.println("Your are client");
        try {
            InetAddress address = InetAddress.getByName(hostname);
            DatagramSocket socket = new DatagramSocket();

            // init call
            byte[] callData = "call".getBytes();
            DatagramPacket initCallRequest = new DatagramPacket(callData, callData.length, address, port);
            socket.send(initCallRequest);

            Thread receiveThread = new Thread(() -> {
                while (true) {
                    try {
                        byte[] incomingBuffer = new byte[255];
                        DatagramPacket response = new DatagramPacket(incomingBuffer, incomingBuffer.length);
                        socket.receive(response);
                        String receiveMessage = new String(incomingBuffer, 0, response.getLength());
                        System.out.println("Server says: " + receiveMessage);
                    } catch (IOException e) {
                        System.out.println("IO error: " + e.getMessage());
                    }
                }
            });

            Thread sendThread = new Thread(() -> {
                while (true) {
                    try {
                        InputStreamReader isr = new InputStreamReader(System.in); // Nhập
                        BufferedReader br = new BufferedReader(isr); // một chuỗi
                        String theString = br.readLine(); // từ bàn phím
                        byte[] data = theString.getBytes();
                        DatagramPacket request = new DatagramPacket(data, data.length, address, port);
                        socket.send(request);
                    } catch (SocketTimeoutException ex) {
                        System.out.println("Timeout error: " + ex.getMessage());
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        System.out.println("Client error: " + ex.getMessage());
                        ex.printStackTrace();
                    }
                }
            });
            receiveThread.start();
            sendThread.start();
        } catch (IOException ex) {
            System.out.println("Client error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
