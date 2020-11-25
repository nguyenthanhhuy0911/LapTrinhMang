import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {
    private String hostname;
    private int port;
    private Socket socket;

    public TCPClient(String hostname, int port) {
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
                this.send("login__" + username + "__" + password);
                break;
            case "2":
                System.out.println("Enter username:");
                username = scanner.nextLine();
                System.out.println("Enter password:");
                password = scanner.nextLine();
                this.send("create__" + username + "__" + password);
                break;
            default:
                break;
        }

    }

    private void run() {
        System.out.println("Welcome to UED system: Enter command bellow!");
        try {
            socket = new Socket(hostname, port);
        } catch (IOException ex) {
            System.out.println("Client error: " + ex.getMessage());
        }
        Thread t1 = new Thread(() -> {
            while (true) {
                menu();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
               while (true){
                   InputStream input = socket.getInputStream();
                   BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                   String msg = reader.readLine();
                   System.out.println("Server replies: " + msg);
               }
            } catch (IOException e) {
                System.out.println("IO Error: " + e.getMessage());
            }
        });
        t1.start();
        t2.start();
    }

    private void send(String msg) {
        try {
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            writer.println(msg);
        } catch (IOException e) {
            System.out.println("Send error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        TCPClient client = new TCPClient("127.0.0.1", 8080);
        client.run();
    }
}
