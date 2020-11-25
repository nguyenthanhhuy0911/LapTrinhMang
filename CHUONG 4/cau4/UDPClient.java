import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClient {
    private String hostname;
    private int port;
    private DatagramSocket socket;
    private InetAddress address;

    public UDPClient(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;

    }

    private void menu() {
        System.out.println("Press 1 to login.");
        System.out.println("Press 2 to create new account.");
        Scanner scanner = new Scanner(System.in);
        String cmd = scanner.nextLine();
        String username, password;
        switch (cmd) {
            case "1":
                System.out.println("Please login");
                System.out.println("Enter username:");
                username = scanner.nextLine();
                System.out.println("Enter password:");
                password = scanner.nextLine();
                this.send("login\n" + username + "\n" + password);
                break;
            case "2":
                System.out.println("Enter username:");
                username = scanner.nextLine();
                System.out.println("Enter password:");
                password = scanner.nextLine();
                this.send("create\n" + username + "\n" + password);
                break;
            default:
                break;
        }

    }

    private void run() {
        System.out.println("Welcome to UED system: Enter command bellow!");
        try {
            address = InetAddress.getByName(hostname);
            socket = new DatagramSocket();
            while (true) {
                menu();
                byte[] incomingBuffer = new byte[255];
                DatagramPacket response = new DatagramPacket(incomingBuffer, incomingBuffer.length);
                socket.receive(response);
                String receiveMessage = new String(incomingBuffer, 0, response.getLength());
                System.out.println("Server response: " + receiveMessage);
            }
        } catch (IOException ex) {
            System.out.println("Client error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void send(String msg) {
        try {
            byte[] callData = msg.getBytes();
            DatagramPacket req = new DatagramPacket(callData, callData.length, address, port);
            socket.send(req);
        } catch (IOException e) {
            System.out.println("Error sending message: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        UDPClient client = new UDPClient("127.0.0.1", 8080);
        client.run();
    }
}
