import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

class TCPUser {
    private String username;
    private String password;

    public TCPUser(String username, String password) {
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

public class TCPServer {
    private ServerSocket socket;
    private int port;
    List<TCPUser> users = new ArrayList<>();

    public TCPServer(int port) throws IOException {
        this.port = port;
        this.socket = new ServerSocket(port);
    }

    public void run() {
        System.out.println("Server is running on port: " + port);
        while (true) {
            try {
                Socket socket = this.socket.accept();
                System.out.println("new client connected");
                // new thread
                Thread thread = new Thread(() -> {
                    try {
                        InputStream input = socket.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                        String message;
                        do {
                            message = reader.readLine();
                            String[] arr = message.split("__");
                            if (arr.length < 3) {
                                this.send(socket, "invalid message data");
                                continue;
                            }
                            if (arr[0].equals("create")) {
                                // create new user
                                this.createUser(arr[1], arr[2]);
                                this.send(socket, "create user " + arr[1] + " successful!");
                            } else if (arr[0].equals("login")) {
                                if (this.loginUser(arr[1], arr[2])) {
                                    this.send(socket, "login success!");
                                } else {
                                    this.send(socket, "login failed:wrong username or password");
                                }
                            } else {
                                this.send(socket, "invalid message data");
                            }
                        } while (!message.equals("bye"));
                        socket.close();
                    } catch (IOException e) {
                        System.out.println("IO error: " + e.getMessage());
                    }
                });
                thread.start(); // start thread

            } catch (IOException e) {
                System.out.println("IO error: " + e.getMessage());
            }
        }
    }

    private TCPUser createUser(String username, String password) {
        TCPUser user = new TCPUser(username, password);
        this.users.add(user);
        System.out.println("create new user:" + username + " password:" + password);
        return user;
    }

    private Boolean loginUser(String username, String password) {
        for (TCPUser user : this.users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
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

    public static void main(String[] args) {
        try {
            TCPServer server = new TCPServer(8080);
            server.run();
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }

    }
}
