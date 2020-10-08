import java.io.*;
import java.net.InetAddress;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class History {
    private String address;
    private String ip;
    private String time;

    public History(String address, String ip, String time) {
        this.address = address;
        this.ip = ip;
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public String getIp() {
        return ip;
    }

    public String getTime() {
        return time;
    }

    public String print() {
        return this.getAddress() + "  " + this.getIp() + "  " + this.getTime();
    }
}

class Browser {
    private final String historyFileName = "history.txt";
    private ArrayList<History> histories = new ArrayList<>();

    public Browser() {
        this.readHistoryFromFile();
    }

    private void readHistoryFromFile() {
        try {
            Scanner scanner = new Scanner(new File(historyFileName));
            while (scanner.hasNextLine()) {
                String l = scanner.nextLine();
                if (!l.isEmpty()) {
                    String[] s = l.split("  ");
                    History h = new History(s[0], s[1], s[2]);
                    this.histories.add(h);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Khong doc duoc file history");
        }
    }

    private String formatUrl(String address) {
        if (!address.startsWith("http://") && !address.startsWith("https://")) {
            return "http://" + address;
        }
        return address;
    }

    private void saveHistory(History history) {
        HashMap<String, Boolean> addressMap = new HashMap<>();
        ArrayList<History> newHistories = new ArrayList<>();
        newHistories.add(history);
        addressMap.put(history.getAddress(), true);
        for (History h : this.histories) {
            if (!addressMap.containsKey(h.getAddress())) {
                addressMap.put(h.getAddress(), true);
                newHistories.add(h);
            }
        }
        this.histories = newHistories;
        this.writeToFile();
    }

    private void writeToFile() {
        try {
            File output = new File(historyFileName);
            FileWriter writer = new FileWriter(output);
            if (this.histories != null) {
                for (History h : this.histories) {
                    writer.append(h.print());
                    writer.append("\n");
                }
            }
            writer.flush();
            writer.close();
        } catch (Exception e) {
            System.out.println("Khong the write history vao file");
        }

    }

    private String getCurrentTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public History go(String address) {
        try {
            URL url = new URL(this.formatUrl(address));
            String hostname = url.getHost();
            // lấy dia chi ip
            InetAddress add = InetAddress.getByName(hostname);
            String ip = add.getHostAddress();
            History history = new History(hostname, ip, this.getCurrentTime());
            saveHistory(history);
            return history;
        } catch (Exception e) {
            System.out.println("Loi xay ra: Page not found");
        }
        return null;
    }

    public void printHistories() {
        for (History h : this.histories) {
            System.out.println(h.print());
        }
    }
}

class Menu {
    public void render() {
        System.out.println("-------------- Menu ----------------------");
        System.out.println("H: Xem lich su truy cap");
        System.out.println("N: Truy cap trang web moi");
        System.out.println("W: Thoat");
        System.out.println("------------------------------------------------");
        System.out.print("Nhap vao command: ");
    }
}

public class Bai6 {
    public static void main(String[] args) {
        Browser browser = new Browser();
        Menu menu = new Menu();
        menu.render();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String cmd = scanner.nextLine().toLowerCase();
            switch (cmd) {
                case "h":
                    System.out.println("Lich su truy cap: ");
                    browser.printHistories();
                    menu.render();
                    break;

                case "w":
                    System.out.println("Good bye!");
                    System.exit(1);
                    break;

                case "n":
                    System.out.print("Nhap vao dia chi website: ");
                    String address = scanner.nextLine();
                    browser.go(address);
                    menu.render();
                    break;
                default:
                    System.out.println("Yeu cau cua ban khong co trong danh sach");
                    break;
            }
        }
    }
}
