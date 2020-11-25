package cau2;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SongSongTCP_Sever {

    public final static int defaultPort = 7;
    public static int clientOnline = 0;
    static int clientNo = 0;

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(defaultPort);
            while (true) {
                try {
                    System.out.println("Server dang hoat dong!");
                    System.out.println("----------------------------------");
                    Socket s = ss.accept();
                    clientNo++;
                    System.out.println("----------------------------------");
                    System.out.println("Khoi dong luong cho Client [" + clientNo + "]");
                    RequestProcessing rp = new RequestProcessing(s);
                    rp.start();
                    rp.clientNo = clientNo;
                    SongSongTCP_Sever.clientOnline++;
                    System.out.println("So client online hien tai la: " + SongSongTCP_Sever.clientOnline);
                    System.out.println("----------------------------------");
                } catch (IOException e) {
                    System.out.println("Connection Error" + e);
                }
            }
        } catch (IOException e) {
            System.out.println("Creation Socket Error:" + e);
        }
    }

    static class RequestProcessing extends Thread {
        Socket skc;
        public int messageNo = 1;
        public int clientNo = 0;

        public RequestProcessing(Socket s) {
            skc = s;
        }

        public void run() {
            try {
                DataOutputStream opstr = new DataOutputStream(skc.getOutputStream());
                BufferedReader ipstr = new BufferedReader(new InputStreamReader(skc.getInputStream()));
                while (true) {
                    System.out.println();
                    String str = ipstr.readLine();
                    System.out.println("Client[" + clientNo + "]" + " doi Message thu " + messageNo);
                    messageNo++;
                    if (str.equals("quit")) {
                        opstr.writeBytes(str);
                        System.out.println("Client[" + clientNo + "]" + " is Disconnected");
                        SongSongTCP_Sever.clientOnline--;
                        System.out.println("So client online hien tai la: " + SongSongTCP_Sever.clientOnline);
                        System.out.println();
                        break;
                    }
                    ScriptEngineManager mgr = new ScriptEngineManager();
                    ScriptEngine engine = mgr.getEngineByName("JavaScript");



                    int strHoa = (int) engine.eval(str);
                    opstr.writeBytes(String.valueOf(strHoa));
                    opstr.write(13);
                    opstr.write(10);
                    opstr.flush();
                    System.out.println("Server da goi tra loi " + strHoa + " cho Client[" + clientNo + "]");
                }
                skc.close();
            } catch (IOException | ScriptException e) {
                System.out.println("Connection Error: " + e);
            }
        }
    }
}
