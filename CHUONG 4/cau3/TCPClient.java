package chuong_4_cau_3;

import java.io.*;
import java.net.*;

public class TCPClient {
    static final String hostname = "127.0.0.1";
    static final int port = 8080;

    public static void send(Socket socket, String msg) {
        try {
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            writer.println(msg);
        } catch (IOException e) {
            System.out.println("Send error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println("Your are client");
        try {
            Socket socket = new Socket(hostname, port);
            // init call
            send(socket,"__call__");
            Thread receiveThread = new Thread(() -> {
                while (true) {
                    try {
                        InputStream input = socket.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                        String msg = reader.readLine();
                        System.out.println("Server says: " + msg);
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
                        send(socket, theString);
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
