import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

class Client {
    InetAddress address;
    int port;

    public Client(InetAddress addr, int port) {
        this.address = addr;
        this.port = port;
    }
}

public class UDPServer {
    private DatagramSocket socket;
    private List<Client> clients = new ArrayList<>();

    public UDPServer(int port) throws SocketException {
        socket = new DatagramSocket(port);
    }

    private void run() {
        System.out.println("Your are server!");
        Thread receiveThread = new Thread(() -> {
            while (true) {
                try {
                    DatagramPacket request = new DatagramPacket(new byte[255], 255);
                    socket.receive(request);
                    String message = new String(request.getData(), 0, request.getLength());
                    if(!message.equals("__call__")){
                        System.out.println("Client says: " + message);
                    }else{
                        InetAddress clientAddress = request.getAddress();
                        int clientPort = request.getPort();
                        Client c = new Client(clientAddress, clientPort);
                        clients.add(c);
                    }

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
                    for (Client c : clients) {
                        DatagramPacket response = new DatagramPacket(data, data.length, c.address, c.port);
                        socket.send(response);
                    }

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


    }


    public static void main(String[] args) {
        try {
            UDPServer server = new UDPServer(8080);
            server.run();
        } catch (SocketException e) {
            System.out.println("Socket error: " + e.getMessage());
        }
    }
}
