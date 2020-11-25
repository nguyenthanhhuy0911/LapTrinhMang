import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

public class UDPServer {
    private DatagramSocket socket;
    private int port;
    List<User> users = new ArrayList<>();

    public UDPServer(int port) throws SocketException {
        this.port = port;
        this.socket = new DatagramSocket(port);
    }

    public void run() {
        System.out.println("Server is running on port: " + port);
        while (true) {
            try {
                DatagramPacket request = new DatagramPacket(new byte[255], 255);
                socket.receive(request);
                String message = new String(request.getData(), 0, request.getLength());
                String[] arr = message.split("\n");
                if (arr.length < 3) {
                    this.send(request.getAddress(), request.getPort(), "invalid message data");
                    continue;
                }
                if (arr[0].equals("create")) {
                    // create new user
                    this.createUser(arr[1], arr[2]);
                    this.send(request.getAddress(), request.getPort(), "create user " + arr[1] + " successful!");
                } else if (arr[0].equals("login")) {
                    if (this.loginUser(arr[1], arr[2])) {
                        this.send(request.getAddress(), request.getPort(), "login success!");
                    } else {
                        this.send(request.getAddress(), request.getPort(), "login failed:wrong username or password");
                    }
                } else {
                    this.send(request.getAddress(), request.getPort(), "invalid message data");
                }

            } catch (IOException e) {
                System.out.println("IO error: " + e.getMessage());
            }
        }
    }

    private User createUser(String username, String password) {
        User user = new User(username, password);
        this.users.add(user);
        System.out.println("create new user:" + username + " password:" + password);
        return user;
    }

    private Boolean loginUser(String username, String password) {
        for (User user : this.users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    private void send(InetAddress address, int port, String msg) {
        byte[] data = msg.getBytes();
        DatagramPacket response = new DatagramPacket(data, data.length, address, port);
        try {
            socket.send(response);
        } catch (SocketTimeoutException ex) {
            System.out.println("Timeout error: " + ex.getMessage());
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Client error: " + ex.getMessage());
            ex.printStackTrace();
        }

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
