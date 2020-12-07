package chuong_4_cau_3;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;


public class TCPServer {
    private ServerSocket socket;
    private List<Socket> clients = new ArrayList<>();

    public TCPServer(int port) throws IOException {
        this.socket = new ServerSocket(port);
    }

    private void send(Socket s, String msg) {
        try {
            OutputStream output = s.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            writer.println(msg);
        } catch (IOException e) {
            System.out.println("Send message to client error: " + e.getMessage());
        }
    }


    private void run() {
        System.out.println("Your are server!");
        Thread receiveThread = new Thread(() -> {
            while (true) {
                try {
                    Socket socket = this.socket.accept();
                    InputStream input = socket.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    String message;
                    do {
                        message = reader.readLine();
                        if(!message.equals("__call__")){
                            System.out.println("Client says: " + message);
                        }else{
                            this.clients.add(socket);
                        }
                    } while (!message.equals("bye"));


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
                    for (Socket c : clients) {
                        this.send(c,theString );
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


    public static void main(String[] args) throws IOException {
        try {
            TCPServer server = new TCPServer(8080);
            server.run();
        } catch (SocketException e) {
            System.out.println("Socket error: " + e.getMessage());
        }
    }
}
